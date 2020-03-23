package ru.ifmo.rain.dimitrov.walk;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RecursiveWalk extends Exception{

    public static void main(String[] args) {
        try {
            if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
                throw new InternalException("Incorrect amount of input");
            }

            File outputFile = new File(args[1]);
            if (outputFile.getParentFile() != null) {
                try {
                    Files.createDirectories(outputFile.getParentFile().toPath().getFileName());
                } catch (IOException e) {
                    throw new InternalException("Incorrect output file: " + args[1], e);
                }
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        try {
                            Path path = Paths.get(line);
                            if (Files.isDirectory(path)) {
                                traverseRecursively(path, writer);
                            } else {
                                printFileHash(writer, calculateFileHash(path), path.toString());
                            }
                        } catch (InvalidPathException e) {
                            printFileHash(writer, 0, line);
                        }
                    }
                } catch (IOException e) {
                    throw new InternalException("Incorrect output file: " + args[1], e);
                }
            } catch (FileNotFoundException e) {
                throw new InternalException("Input file not found: " + args[0], e);
            } catch (IOException e) {
                throw new InternalException("Incorrect input file: " + args[0], e);
            }
        } catch (InternalException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void traverseRecursively(Path directoryPath, BufferedWriter writer) throws InternalException {
        try {
            Files.walk(directoryPath).map(Path::toString).
                    filter(s -> new File(s).isFile()).forEach(f -> {
                try {
                    printFileHash(writer, calculateFileHash(Paths.get(f)), f);
                } catch (InternalException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
        } catch (IOException e) {
            throw new InternalException("Incorrect filename", e);
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

    private static void printFileHash(Writer writer, int fileHash, String path) throws InternalException {
        try {
            writer.write(String.format("%08x %s%n", fileHash, path));
        } catch (IOException e) {
            throw new InternalException("Incorrect output file", e);
        }
    }

    private static class InternalException extends Exception {

        public InternalException(String message) {
            super(message);
        }

        public InternalException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}

