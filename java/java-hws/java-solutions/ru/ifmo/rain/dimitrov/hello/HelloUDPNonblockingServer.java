package ru.ifmo.rain.dimitrov.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloUDPNonblockingServer extends CommonServer implements HelloServer {
    private Selector selector;
    private DatagramChannel channel;
    private ExecutorService executor;

    @Override
    public void start(int port, int threads) {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            throw new InternalException("Error happened, while creating selector", e);
        }

        executor = Executors.newSingleThreadExecutor();
        final SocketAddress socketAddress = new InetSocketAddress(port);
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(socketAddress);

            channel.register(selector, SelectionKey.OP_READ, new ChannelProviderServer());
        } catch (ClosedChannelException e) {
            throw new InternalException("Cannot register channel to selector", e);
        } catch (IOException e) {
            throw new InternalException("Error happened, while binding", e);
        }

        executor.submit(this::handleRequest);

        System.out.println("Server started");
    }

    @Override
    public void close() {
        executor.shutdown();
        try {
            channel.close();
        } catch (IOException e) {
            throw new InternalException("Error while closing channel", e);
        }

        try {
            selector.close();
        } catch (IOException e) {
            throw new InternalException("Error while closing selector", e);
        }
        System.out.println("Server closed");
    }

    private void handleRequest() {
        while (!Thread.interrupted()) {
            try {
                selector.select();
            } catch (IOException e) {
                throw new InternalException("Error happened, while selecting", e);
            }

            Iterator<SelectionKey> selectedKeysIterator = selector.selectedKeys().iterator();
            SelectionKey key;
            while (selectedKeysIterator.hasNext()) {
                key = selectedKeysIterator.next();

                handleKey(key);

                selectedKeysIterator.remove();
            }
        }
    }

    private void handleKey(SelectionKey key) {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ChannelProviderServer info = (ChannelProviderServer) key.attachment();
        if (key.isWritable()) {
            sendRequest(channel, info);
            key.interestOps(SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            receiveRequest(channel, info);
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    private void sendRequest(DatagramChannel channel, ChannelProviderServer info) {
        try {
            channel.send(ByteBuffer.wrap(info.getMessage().getBytes(StandardCharsets.UTF_8)), info.getSocketAddress());
        } catch (IOException e) {
            System.err.println("Error happened, while sending request." + e.getMessage());
        }
    }

    private void receiveRequest(DatagramChannel channel, ChannelProviderServer info) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(channel.getOption(StandardSocketOptions.SO_RCVBUF));
            info.setSocketAddress(channel.receive(buffer));

            buffer.flip();

            info.setMessage("Hello, " + StandardCharsets.UTF_8.decode(buffer).toString());
        } catch (IOException e) {
            System.err.println("Error happened, while receiving request." + e.getMessage());
        }
    }
}
