package ru.ifmo.rain.dimitrov.hello;

import java.util.Arrays;
import java.util.Objects;

public class CommonClient {
    private static void validateArgs(String[] args) throws InternalException {
        if (args == null || args.length != 5) {
            throw new InternalException("5 arguments must be passed");
        }

        if (Arrays.stream(args).anyMatch(Objects::isNull)) {
            throw new InternalException("Arguments must not be null");
        }

        try {
            Integer.parseInt(args[1]);
            Integer.parseInt(args[3]);
            Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            throw new InternalException("Arguments #2, #4 and #5 must be int");
        }
    }

    public static void main(String[] args) {
        try {
            validateArgs(args);
        } catch (InternalException e) {
            System.err.println(String.format("Args validation error: %s", e.getMessage()));
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String prefix = args[2];
        int threads = Integer.parseInt(args[3]);
        int request = Integer.parseInt(args[4]);

        try {
            new HelloUDPClient().run(host, port, prefix, threads, request);
        } catch (InternalException e) {
            System.out.println(e.getMessage());
        }
    }
}
