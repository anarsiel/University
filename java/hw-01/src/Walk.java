import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Walk {

    public static void main(String[] args) {
        new File(args[1]).getParentFile().mkdirs();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {

            String line;
            ArrayList<Path> filePaths = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                filePaths.add(Paths.get(line));
            }

            for (Path filePath : filePaths) {
                writer.write(String.format("%08x %s\n", calculateFileHash(filePath), filePath.toString()));
            }

        } catch (IOException e) {
            e.printStackTrace();
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
