package ru.ifmo.rain.dimitrov.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class HelloUDPClient extends CommonClient implements HelloClient {
    private static final int SOCKET_SO_TIMEOUT = 100;

    @Override
    public void run(String host, int port, String prefix, int threads, int requests) {
        try {
            final SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(host), port);

            try {
                ExecutorService workers = Executors.newFixedThreadPool(threads);

                IntStream.range(0, threads)
                        .forEach(thread -> workers.submit(
                                () -> {
                                    try {
                                        runParallelTask(socketAddress, prefix, thread, requests);
                                    } catch (InternalException e) {
                                        throw new InternalException("Error happened, while running a task.", e);
                                    }
                                })
                        );

                workers.shutdown();
                workers.awaitTermination(requests * threads, TimeUnit.SECONDS);
            } catch (IllegalArgumentException e) {
                throw new InternalException(String.format("Wrong threads count: %d", threads), e);
            } catch (InterruptedException e) {
                throw new InternalException("Interruption happened.", e);
            }
        } catch (UnknownHostException e) {
            throw new InternalException(String.format("Wrong host: %s", host), e);
        } catch (IllegalArgumentException e) {
            throw new InternalException(String.format("Wrong port: %d", port), e);
        }
    }

    private void runParallelTask(final SocketAddress address, final String prefix, final int thread, final int requests) throws InternalException {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(SOCKET_SO_TIMEOUT);

            int receiveBufferSize = socket.getReceiveBufferSize();
            final DatagramPacket request = new DatagramPacket(
                    new byte[receiveBufferSize], receiveBufferSize, address
            );

            for (int requestIdx = 0; requestIdx < requests; requestIdx++) {
                String requestMessage = MessageProvider.createMessage(prefix, thread, requestIdx);

                while (!socket.isClosed() && !Thread.interrupted()) {
                    try {
                        MessageProvider.setMessage(request, requestMessage);
                        socket.send(request);
                        request.setData(new byte[receiveBufferSize]);
                        socket.receive(request);
                        String responseMessage = MessageProvider.getMessage(request);

                        if (responseMessage.contains(requestMessage)) {
                            System.out.println(String.format("Response received: %s", responseMessage));
                            break;
                        }
                    } catch (IOException ignored) {/* ignored */ }
                }
            }
        } catch (SocketException e) {
            throw new InternalException("Socket connection error.", e);
        }
    }

}
