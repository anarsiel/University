import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class D {
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

        Bridges bridges = new Bridges();
        HashSet<Integer> bridgesInTree = bridges.findBrindges(tree);

        Dfs dfs = new Dfs(tree, bridgesInTree);

        for (int i = 0; i < tree.n; ++i) {
            if (!dfs.used[i])
                dfs.findComponents(i, dfs.nextFreeColor++);
        }

        writer.write((Integer.toString(dfs.nextFreeColor) + "\n").getBytes());
        for (int x : dfs.vertexesColors) {
            writer.write((Integer.toString(x + 1) + " ").getBytes());
        }
        writer.close();
    }

    static class Dfs {
        Tree tree;
        Boolean[] used;
        int[] vertexesColors;
        HashSet<Integer> bridges;
        int nextFreeColor = 0;

        Dfs(Tree tree, HashSet<Integer> bridges) {
            this.tree = tree;
            vertexesColors = new int[tree.n];

            used = new Boolean[tree.n];
            for (int i = 0; i < tree.n; ++i) {
                used[i] = false;
            }

            this.bridges = bridges;
        }

        void findComponents(int vertex, int currentColor) {
            used[vertex] = true;
            vertexesColors[vertex] = currentColor;

            for (OneEdge to : tree.edges.get(vertex)) {
                if (!used[to.to]) {
                    if (bridges.contains(to.edgeIndex)) {
                        findComponents(to.to, nextFreeColor++);
                    } else {
                        findComponents(to.to, currentColor);
                    }
                }
            }
        }
    }

    public static class Bridges {
        public HashSet<Integer> findBrindges(Tree tree) throws IOException {
            Dfs dfs = new Dfs(tree);

            for (int i = 0; i < tree.n; ++i) {
                if (!dfs.used[i]) {
                    dfs.run(i, -1);
                }
            }

            return dfs.bridges;
        }

        class Dfs {
            Tree tree;
            Boolean[] used;
            int[] tin, tout, fup;
            int timer = 0;

            HashSet<Integer> bridges;

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

                bridges = new HashSet<>();
            }

            public void run(int vertex, int pEdge) {
                used[vertex] = true;
                tin[vertex] = fup[vertex] = timer++;

                for (OneEdge to : tree.edges.get(vertex)) {
                    if (to.edgeIndex == pEdge) continue;

                    if (used[to.to]) {
                        fup[vertex] = Math.min(fup[vertex], tin[to.to]);
                    } else {
                        run(to.to, to.edgeIndex);

                        fup[vertex] = Math.min(fup[vertex], fup[to.to]);
                        if (fup[to.to] > tin[vertex]) {
                            bridges.add(to.edgeIndex);
                        }
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
