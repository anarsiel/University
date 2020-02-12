import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Walk {

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) return;

        try {
            new File(args[1]).getParentFile().mkdirs();
        } catch (NullPointerException ignored) {}

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Path path = Paths.get(line);
                    if (Files.isDirectory(path)) {
                        recursiveTraversal(path, writer);
                    } else {
                        writer.write(String.format("%08x %s\n", calculateFileHash(path), path.toString()));
                    }
                } catch (InvalidPathException e) {
                    writer.write(String.format("%08x %s\n", 0, line));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recursiveTraversal(Path directoryPath, BufferedWriter writer) throws IOException {
        File[] children = new File(directoryPath.toString()).listFiles();

        for (File child : children) {
            if (child.isFile()) {
                writer.write(String.format("%08x %s\n", calculateFileHash(child.toPath()), child.toString()));
            } else {
                recursiveTraversal(child.toPath(), writer);
            }
        }
    }

    private static Integer calculateFileHash(Path filePath) {
        try (InputStream reader = new FileInputStream(filePath.toString())) {

            int input;
            int rv = 0x811c9dc5;
            while ((input = reader.read()) >= 0) {
                rv = (rv * 0x01000193) ^ ((byte) input & 0xff);
            }
            return rv;
        } catch (IOException e) {
            return 0;
        }
    }
}
