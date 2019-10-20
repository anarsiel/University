import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class I {
    static Pair[] point;

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader(System.in);
        BufferedOutputStream writer = new BufferedOutputStream(System.out);

        ArrayList<Double> distance = new ArrayList<>();
        int n = reader.nextInt();
        for (int i = 0; i < n; ++i)
            distance.add(1e9 + 239);

        point = new Pair[n];
        for (int i = 0; i < n; ++i) {
            int x = reader.nextInt();
            int y = reader.nextInt();
            point[i] = new Pair(x, y);
        }

        ArrayList<Boolean> used = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            used.add(false);

        distance.set(0, 0.0);
        while (true) {
            int v = -1;
            for (int i = 0; i < n; ++i)
                if (!used.get(i) && distance.get(i) < 1e9 + 239 && (v == -1 || distance.get(v) > distance.get(i)))
                    v = i;

            if (v == -1) {
                double answ = 0;
                for (int i = 0; i < n; ++i) {
                    answ += distance.get(i);
                }

                System.out.println(answ);
                return;
            };

            used.set(v, true);
            for (int i = 0; i < n; ++i)
                if (!used.get(i))
                    distance.set(i, Math.min(distance.get(i), sqrD(v, i)));
        }
    }

    static double sqrD(int i1, int i2) {
        Pair p1 = point[i1];
        Pair p2 = point[i2];
        return Math.sqrt((p1.first - p2.first) * (p1.first - p2.first) +
                         (p1.second - p2.second) * (p1.second - p2.second));
    }

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
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
