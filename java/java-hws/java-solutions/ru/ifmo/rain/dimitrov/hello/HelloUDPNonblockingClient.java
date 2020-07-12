package ru.ifmo.rain.dimitrov.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static ru.ifmo.rain.dimitrov.hello.MessageProvider.createMessage;

public class HelloUDPNonblockingClient extends CommonClient implements HelloClient {
    private static final int SOCKET_SO_TIMEOUT = 100;

    @Override
    public void run(String host, int port, String prefix, int threads, int requests) {
        Selector selector;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            throw new InternalException("Error happened, while creating selector", e);
        }

        SocketAddress socketAddress;
        Map<Channel, ChannelProviderClient> createdChannels = new HashMap<>();
        try {
            socketAddress = new InetSocketAddress(InetAddress.getByName(host), port);

            for (int i = 0; i < threads; i++) {
                DatagramChannel channel = DatagramChannel.open();
                channel.connect(socketAddress);
                channel.configureBlocking(false);

                ChannelProviderClient channelProvider = new ChannelProviderClient(i);
                channel.register(selector, SelectionKey.OP_WRITE, channelProvider);

                createdChannels.put(channel, channelProvider);
            }
        } catch (UnknownHostException e) {
            throw new InternalException(String.format("Wrong host: %s", host), e);
        } catch (IllegalArgumentException e) {
            throw new InternalException(String.format("Wrong port: %d", port), e);
        } catch (SocketException e) {
            throw new InternalException("Error while binding socket", e);
        } catch (ClosedChannelException e) {
            throw new InternalException("Channel is fully closed or closed exactly to that operation", e);
        } catch (IOException e) {
            throw new InternalException("Error while creating new DatagramChannel", e);
        }

        runParallelTask(selector, prefix, threads * requests, requests);

        try {
            selector.close();
        } catch (IOException e) {
            throw new InternalException("Error happened, while closing selector", e);
        }

        createdChannels.forEach((key, value) -> {
            try {
                key.close();
            } catch (IOException e) {
                throw new InternalException(String.format("Error happened, while closing channel #%s", value.getThreadIdx()), e);
            }
        });
    }

    private void runParallelTask(final Selector selector, final String prefix, final int maxRequests, final int requests) throws InternalException {

        int handledRequestsCount = 0;
        while (!Thread.interrupted() && handledRequestsCount < maxRequests) {
            try {
                selector.select(SOCKET_SO_TIMEOUT);
            } catch (IOException e) {
                throw new InternalException("Error occurred while trying to select.", e);
            }

            Iterator<SelectionKey> selectedKeysIterator = selector.selectedKeys().iterator();
            if (!selectedKeysIterator.hasNext()) {
                for (final SelectionKey key : selector.keys()) {
                    if (handleKey(key, prefix, requests, true)) {
                        handledRequestsCount++;
                    }
                }
            }

            SelectionKey key;
            while (selectedKeysIterator.hasNext()) {
                key = selectedKeysIterator.next();

                if (handleKey(key, prefix, requests, false)) {
                    handledRequestsCount++;
                }

                selectedKeysIterator.remove();
            }
        }
    }

    private boolean handleKey(SelectionKey key, String prefix, int requests, boolean serverCheck) {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ChannelProviderClient channelProvider = (ChannelProviderClient) key.attachment();
        String message = createMessage(prefix, channelProvider.getThreadIdx(), channelProvider.getRequests());

        boolean oneMoreRequest = false;

        if (key.isWritable()) {
            if (sendRequest(channel, message)) {
                key.interestOps(SelectionKey.OP_READ);
            }
        } else if (key.isReadable() && !serverCheck) {
            if (channelProvider.getRequests() == requests) {
                return false;
            }

            ByteBuffer receivedByteBuffer = null;
            try {
                receivedByteBuffer = ByteBuffer.allocate(channel.getOption(StandardSocketOptions.SO_RCVBUF));
            } catch (IOException e) {
                System.err.println("Error happened, while allocation new ByteBuffer" + e.getMessage());
            }

            String responseMessage = receiveRequest(channel, receivedByteBuffer);
            System.out.println("Message received.");

            if (responseMessage != null && responseMessage.contains(message)) {
                channelProvider.incRequests();
                oneMoreRequest = true;
            }

            if (channelProvider.getRequests() < requests) {
                key.interestOps(SelectionKey.OP_WRITE);
            }
        }
        return oneMoreRequest;
    }

    private boolean sendRequest(final DatagramChannel channel, final String message) {
        try {
            channel.send(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)), channel.getRemoteAddress());
        } catch (IOException e) {
            System.err.println("Error happened, while sending request." + e.getMessage());
            return false;
        }
        return true;
    }

    private String receiveRequest(final DatagramChannel channel, ByteBuffer buffer) {
        try {
            channel.receive(buffer);
            buffer.rewind();
            return StandardCharsets.UTF_8.decode(buffer).toString();
        } catch (IOException e) {
            System.err.println("Error happened, while receiving request." + e.getMessage());
        }
        return null;
    }

}
