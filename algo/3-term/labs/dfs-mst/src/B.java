import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class B {
    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader(System.in);
        BufferedOutputStream writer = new BufferedOutputStream(System.out);

        int n = reader.nextInt();
        int m = reader.nextInt();

        Tree tree = new Tree(n, m);

        for (int i = 0; i < m; ++i) {
            int v = reader.nextInt();
            int u = reader.nextInt();
            --v;
            --u;

            tree.edges.get(v).add(new OneEdge(u, i + 1));
            tree.edges.get(u).add(new OneEdge(v, i + 1));
        }

        Dfs dfs = new Dfs(tree);

        for (int i = 0; i < n; ++i) {
            if (!dfs.used[i]) {
                dfs.findBridges(i, -1);
            }
        }

        Collections.sort(dfs.bridges);

        writer.write((Integer.toString(dfs.bridges.size()) + "\n").getBytes());
        for (int x : dfs.bridges) {
            writer.write((Integer.toString(x) + " ").getBytes());
        }
        writer.close();
    }

    static class Dfs {
        Tree tree;
        Boolean[] used;
        int[] tin, tout, fup;
        int timer = 0;

        ArrayList<Integer> bridges;

        Dfs(Tree tree) {
            this.tree = tree;
            tin = new int[tree.n];
            tout = new int[tree.n];
            fup = new int[tree.n];

            used = new Boolean[tree.n];
            for (int i = 0; i < tree.n; ++i) {
                used[i] = false;
                tin[i] = tout[i] = fup[i] = 0;
            }

            bridges = new ArrayList<>();
        }

        public void findBridges(int vertex, int p) {
            used[vertex] = true;
            tin[vertex] = fup[vertex] = timer++;

            for (OneEdge to : tree.edges.get(vertex)) {
                if (to.to == p) continue;

                if (used[to.to]) {
                    fup[vertex] = Math.min(fup[vertex], tin[to.to]);
                } else {
                    findBridges(to.to, vertex);

                    fup[vertex] = Math.min(fup[vertex], fup[to.to]);
                    if (fup[to.to] > tin[vertex]) {
                        bridges.add(to.edgeIndex);
                    }
                }
            }
        }
    }

    static class Tree {
        int root = 0;
        int n, m;
        ArrayList<ArrayList<OneEdge>> edges;

        Tree(int n, int m) {
            this.n = n;
            this.m = m;

            edges = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                edges.add(new ArrayList<>());
        }
    }

    static class OneEdge {
        int to;
        int edgeIndex;

        OneEdge(int to, int edgeIndex) {
            this.to = to;
            this.edgeIndex = edgeIndex;
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
