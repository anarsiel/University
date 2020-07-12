package ru.ifmo.rain.dimitrov.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HelloUDPServer implements HelloServer {
    private static final int AWAIT_TERMINATION = 100;

    private DatagramSocket socket;
    private ExecutorService executor;
    private ExecutorService workers;

    private int receiveBufferSize = 0;

    @Override
    public void start(int port, int threads) {
        try {
            socket = new DatagramSocket(port);
            receiveBufferSize = socket.getReceiveBufferSize();

            workers = Executors.newFixedThreadPool(threads);
            executor = Executors.newSingleThreadExecutor();

            executor.submit(this::handleRequest);
        } catch (SocketException e) {
            throw new InternalException(String.format("Wrong port: %d.", port), e);
        }
    }

    @Override
    public void close() {
        socket.close();
        executor.shutdown();
        workers.shutdown();

        try {
            executor.awaitTermination(AWAIT_TERMINATION, TimeUnit.SECONDS);
            workers.awaitTermination(AWAIT_TERMINATION, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println(
                    String.format("Awaiting of termination was interrupted. %s", e.getMessage())
            );
        }
    }

    private void handleRequest() throws InternalException {
        try {
            while (!socket.isClosed() && !Thread.interrupted()) {
                final DatagramPacket request = new DatagramPacket(
                        new byte[receiveBufferSize], receiveBufferSize
                );

                socket.receive(request);

                workers.submit(() -> {
                    String helloMessage = "Hello, " + MessageProvider.getMessage(request);

                    MessageProvider.setMessage(request, helloMessage);
                    try {
                        socket.send(request);
                    } catch (IOException ignored) { /* ignored */ }
                });
            }
        } catch (IOException e) {
            if (!socket.isClosed()) {
                throw new InternalException("Error happened while receiving data.", e);
            }
        }
    }
}
