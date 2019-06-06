import java.io.*;
import java.util.*;

public class Task {
    public static void main(String[] args) throws IOException {
       InputReader reader = new InputReader(System.in);
       BufferedOutputStream out = new BufferedOutputStream(System.out);
        // InputReader reader = new InputReader(new FileInputStream("minonpath.in"));
        // BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("minonpath.out"));
        int n = reader.nextInt();
        Tree tree = new Tree(n, reader);
        int m = reader.nextInt();

        for (int i = 0; i < m; i++) {
            int v = reader.nextInt();
            int u = reader.nextInt();

            out.write(Integer.toString(tree.minOnPath(v, u)).getBytes());
            out.write("\n".getBytes());
        }
        out.close();
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

    static class Tree {
        int size;
        int[] p;
        int[] depths;
        int[][] dp;
        int[][] min;
        int[] w;
        int root = 1;
        int log;
        ArrayList<ArrayList<Integer>> v;

        Tree(int size, InputReader source) {
            this.size = size;
            this.w = new int[size + 10];
            this.p = new int[size + 10];
            this.depths = new int[size + 10];
            this.dp = new int[size + 10][log2(size) + 1];
            this.min = new int[size + 10][log2(size) + 1];
            this.v = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                v.add(new ArrayList<>());
            }
            log = log2(size);

            getParents(source);
            setDp();
            dfs(root, 0);
        }

        public int minOnPath(int v, int u) {
            int ans = Integer.MAX_VALUE;
            int lca = lca(v, u);
            int v1 = v;
            int u1 = u;
            if (lca == v) {
                int h = depths[u1] - depths[v1];
                for (int i = log; i >= 0; i--) {
                    if ((1 << i) <= h) {
                        h -= (1 << i);
                        ans = Math.min(ans, min[u1][i]);
                        u1 = dp[u1][i];
                    }
                }
            } else if (lca == u) {
                int h = depths[v1] - depths[u1];
                for (int i = log; i >= 0; i--) {
                    if ((1 << i) <= h) {
                        h -= (1 << i);
                        ans = Math.min(ans, min[v1][i]);
                        v1 = dp[v1][i];
                    }
                }
            } else {
                ans = Math.min(minOnPath(v, lca), minOnPath(u, lca));
            }

            return ans;
        }

        public int lca(int v, int u) {
            if (depths[v] > depths[u]) {
                int tmp = v;
                v = u;
                u = tmp;
            }

            int h = depths[u] - depths[v];
            for (int i = log; i >= 0; i--) {
                if ((1 << i) <= h) {
                    h -= (1 << i);
                    u = dp[u][i];
                }
            }

            if (u == v) {
                return v;
            }

            for (int i = log; i >= 0; i--) {
                if (dp[v][i] != dp[u][i]) {
                    v = dp[v][i];
                    u = dp[u][i];
                }
            }

            return p[v];
        }

        private void dfs(int v, int d) {
            depths[v] = d;
            for (int i = 0; i < v.get(v).size(); i++) {
                int to = v.get(v).get(i);
                dfs(to, d + 1);
            }
        }

        private void getParents(InputReader source) {

            for (int i = 2; i <= size; i++) {
                int x = source.nextInt();
                int y = source.nextInt();

                p[i] = x;
                v.get(x).add(i);
                w[i] = y;
            }
        }

        private void setDp() {
            for (int i = 1; i <= size; i++) {
                dp[i][0] = p[i];
                min[i][0] = w[i];
            }

            for (int j = 1; j <= log; j++) {
                for (int i = 1; i <= size; i++) {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                    min[i][j] = Math.min(min[i][j - 1], min[dp[i][j - 1]][j - 1]);
                }
            }
        }

        public void print() {
            for (int i = 1; i <= size; i++) {
                System.out.print(i + ": ");
                for (int j = 0; j < v.get(i).size(); j++) {
                    System.out.print(v.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }
    }

    public static int log2(int n) {
        if (n == 0) {
            return 0;
        }

        return log2(n / 2) + 1;
    }
}