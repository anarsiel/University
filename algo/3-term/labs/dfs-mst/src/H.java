import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class H {
    public static void main(String[] args) throws IOException {
//        InputReader reader = new InputReader(System.in);
//        BufferedOutputStream writer = new BufferedOutputStream(System.out);
        InputReader reader = new InputReader(new FileInputStream("avia.in"));
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("avia.out"));

        int n = reader.nextInt();

        Graph graph = new Graph(n, n*n);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int w = reader.nextInt();

                graph.edges.get(i).add(new Edge(j, w));
                graph.reversedEdges.get(j).add(new Edge(i, w));
            }
        }

        int l = -1;
        int r = 1_000_000_239;
        //int r = 1_00;

        while (l != r - 1) {
            int middle = (l + r) / 2;

            if (graph.isStrongConnected(middle)) {
                r = middle;
            } else {
                l = middle;
            }
        }

        writer.write(Integer.toString(r).getBytes());
        writer.close();
    }

    static class Dfs {
        Graph graph;
        Boolean[] used;
        ArrayList<Integer> vertexesToutOrder;
        int[] vertexesColors;

        Dfs(Graph graph) {
            this.graph = graph;

            vertexesColors = new int[graph.n];
            used = new Boolean[graph.n];
            for (int i = 0; i < graph.n; ++i) {
                used[i] = false;
            }
            vertexesToutOrder = new ArrayList<>();
        }

        public void run(int vertex, int maxWeight) {
            used[vertex] = true;

            for (Edge to : graph.reversedEdges.get(vertex)) {
                if (!used[to.to] && maxWeight >= to.weight) {
                    run(to.to, maxWeight);
                }
            }

            vertexesToutOrder.add(vertex);
        }

        public void run2(int vertex, int color, int maxWeight) {
            used[vertex] = true;
            vertexesColors[vertex] = color;

            for (Edge to : graph.edges.get(vertex)) {
                if (!used[to.to] && maxWeight >= to.weight) {
                    run2(to.to, color, maxWeight);
                }
            }
        }

        void clearUsed() {
            for (int i = 0; i < graph.n; ++i)
                used[i] = false;
        }
    }

    static class Graph {
        int n, m;
        ArrayList<ArrayList<Edge>> edges;
        ArrayList<ArrayList<Edge>> reversedEdges;

        Graph(int n, int m) {
            this.n = n;
            this.m = m;

            edges = new ArrayList<>();
            reversedEdges = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                edges.add(new ArrayList<>());
                reversedEdges.add(new ArrayList<>());
            }
        }

        boolean isStrongConnected(int weight) {
            Dfs dfs = new Dfs(this);

            for (int i = 0; i < n; ++i) {
                if (!dfs.used[i]) {
                    dfs.run(i, weight);
                }
            }

            Collections.reverse(dfs.vertexesToutOrder);
            dfs.clearUsed();

            int curColor = 0;
            for (int vertex : dfs.vertexesToutOrder) {
                if (!dfs.used[vertex]) {
                    dfs.run2(vertex, curColor++, weight);
                }
            }

            return curColor <= 1;
        }
    }

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
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
