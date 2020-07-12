import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class F {
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

            tree.edges.get(v).add(u);
        }

        Dfs dfs = new Dfs(tree);

        for (int i = 0; i < n; ++i) {
            if (dfs.used.get(i) == 0) {
                dfs.run(i);
            }
        }

        int[] answ = new int[n];

        for (int i = 0; i < n; ++i) {
            ArrayList<Integer> childrens = new ArrayList<>();
            for (int to : tree.edges.get(dfs.vertexesInTopSort.get(i))) {
                childrens.add(answ[to]);
            }

            Collections.sort(childrens);

            for (int j = 0; j < childrens.size(); ++j) {
                if (answ[dfs.vertexesInTopSort.get(i)] != childrens.get(j))
                    break;
                answ[dfs.vertexesInTopSort.get(i)]++;
            }
        }

        for (int x : answ) {
            writer.write(Integer.toString(x).getBytes());
            writer.write(" ".getBytes());
        }

        writer.close();
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

    static class Dfs {
        Tree tree;
        ArrayList<Integer> used;
        ArrayList<Integer> vertexesInTopSort;
        boolean treeHasCycle = false;

        Dfs(Tree tree) {
            this.tree = tree;
            vertexesInTopSort = new ArrayList<>();
            used = new ArrayList<>();
            for (int i = 0; i < tree.n; ++i) {
                used.add(i, 0);
            }
        }

        public void run(int vertex) {
            used.set(vertex, 1);

            for (int to : tree.edges.get(vertex)) {
                if (used.get(to) == 0) {
                    run(to);
                } else if (used.get(to) == 1){
                    treeHasCycle = true;
                }
            }

            vertexesInTopSort.add(vertex);
            used.set(vertex, 2);
        }
    }

    static class Tree {
        int root;
        int n, m;
        ArrayList<ArrayList<Integer>> edges;

        Tree(int n, int m) {
            this.n = n;
            this.m = m;

            edges = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                edges.add(new ArrayList<>());
        }
    }
}