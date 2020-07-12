package ru.ifmo.rain.dimitrov.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.util.Arrays;
import java.util.Objects;

public class CommonServer {
    private static void validateArgs(String[] args) throws InternalException {
        if (args == null || args.length != 2) {
            throw new InternalException("2 arguments must be passed");
        }

        if (Arrays.stream(args).anyMatch(Objects::isNull)) {
            throw new InternalException("Both arguments must not be null");
        }

        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new InternalException("Both arguments must be int");
        }
    }

    public static void main(String[] args) {
        try {
            validateArgs(args);
        } catch (InternalException e) {
            System.err.println(String.format("Args validation error: %s", e.getMessage()));
            return;
        }

        int port = Integer.parseInt(args[0]);
        int threads = Integer.parseInt(args[1]);

        try {
            HelloServer server = new HelloUDPServer();
            server.start(port, threads);
        } catch (InternalException e) {
            System.err.println(e.getMessage());
        }
    }
}
