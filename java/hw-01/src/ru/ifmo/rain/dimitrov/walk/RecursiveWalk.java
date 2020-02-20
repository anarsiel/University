package ru.ifmo.rain.dimitrov.walk;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecursiveWalk {

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) return;

        try {
            new File(args[1]).getParentFile().mkdirs();
        } catch (NullPointerException ignored) {}

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        Path path = Paths.get(line);
                        if (Files.isDirectory(path)) {
                            recursiveTraversal(path, writer);
                        } else {
                            printFileHash(writer, path);
                        }
                    } catch (InvalidPathException e) {
                        writer.write(String.format("%08x %s%n", 0, line));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException");
                // e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("IOException");
            // e.printStackTrace();
        }
    }

    private static void recursiveTraversal(Path directoryPath, BufferedWriter writer) {
        try {
            Files.walk(directoryPath).map(Path::toString).
                    filter(s -> new File(s).isFile()).forEach(f -> printFileHash(writer, Paths.get(f)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateFileHash(Path filePath) {
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(filePath.toString()))) {
            int rv = 0x811c9dc5, result;
            byte[] b = new byte[1024];

            while ((result = reader.read(b)) >= 0) {
                for (int i = 0; i < result; ++i) {
                    rv = (rv * 0x01000193) ^ (b[i] & 0xff);
                }
            }
            return rv;
        } catch (IOException e) {
            return 0;
        }
    }

    private static void printFileHash(Writer writer, Path path) {
        try {
            writer.write(String.format("%08x %s%n", calculateFileHash(path), path.toString()));
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}

