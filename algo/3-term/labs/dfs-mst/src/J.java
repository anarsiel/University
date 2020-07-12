import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class J {
    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader(System.in);
        BufferedOutputStream writer = new BufferedOutputStream(System.out);

        int n = reader.nextInt();
        int m = reader.nextInt();

        Tree tree = new Tree(n, m);

        for (int i = 0; i < m; ++i) {
            int v = reader.nextInt();
            int u = reader.nextInt();
            int w = reader.nextInt();
            --v;
            --u;

            tree.edges.add(new Edge(v, u, w));
        }

        Collections.sort(tree.edges, new EdgeComparator());
        DSU dsu = new DSU(tree);

        long stWeight = 0;
        for (Edge edge : tree.edges) {
            if (dsu.get(edge.a) != dsu.get(edge.b)) {
                stWeight += edge.weight;
                dsu.unite(edge.a, edge.b);
            }
        }

        writer.write((Long.toString(stWeight) + "\n").getBytes());
        writer.close();
    }

    public static class DSU {
        int[] vertexColor;
        int[] prev;

        DSU(Tree tree) {
            vertexColor = new int[tree.n];
            prev = new int[tree.n];

            for (int i = 0; i < tree.n; ++i) {
                vertexColor[i] = prev[i] = i;
            }
        }

        int get(int vertex) {
            if (prev[vertex] == vertex)
                return vertex;
            return prev[vertex] = get(prev[vertex]);
        }

        void unite(int vertex, int urtex) {
            vertex = get(vertex);
            urtex = get(urtex);

            if (vertex == urtex) return;

            prev[vertex] = urtex;
        }
    }

    static class Tree {
        int root = 0;
        int n, m;
        ArrayList<Edge> edges;

        Tree(int n, int m) {
            this.n = n;
            this.m = m;

            edges = new ArrayList<>();
        }
    }

    static class Edge {
        int a, b, weight;

        Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge e1, Edge e2) {
            return (e1.weight < e2.weight) ? -1 : ((e1.weight == e2.weight) ? 0 : 1);
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
