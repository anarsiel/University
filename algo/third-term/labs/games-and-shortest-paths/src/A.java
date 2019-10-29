import java.io.*;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader(System.in);
        BufferedOutputStream writer = new BufferedOutputStream(System.out);

        Floyd floyd = new Floyd(reader);

        floyd.run();

        for (int i = 0; i < floyd.n; ++i) {
            for (int j = 0; j < floyd.n; ++j)
                writer.write((Integer.toString(floyd.shortestPaths[i][j]) + " ").getBytes());
            writer.write("\n".getBytes());
        }

        writer.close();
    }

    static class Floyd {
        int n;
        int[] []shortestPaths;

        Floyd(InputReader reader) {
            this.n = reader.nextInt();
            shortestPaths = new int[n][n];

            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    shortestPaths[i][j] = reader.nextInt();
        }

        void run() {
            for (int k = 0; k < n; ++k)
                for (int i = 0; i < n; ++i)
                    for (int j = 0; j < n; ++j) {
                        shortestPaths[i][j] = Math.min(shortestPaths[i][j],
                                                       shortestPaths[i][k] + shortestPaths[k][j]);
                    }
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
