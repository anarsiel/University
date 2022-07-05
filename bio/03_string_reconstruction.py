import networkx


class Task:
    @staticmethod
    def make_graph(k, lines):
        graph = networkx.DiGraph()
        for i in range(len(lines)):
            graph.add_node(i)
        for i in range(len(lines)):
            for j in range(len(lines)):
                if i == j:
                    continue

                if lines[i][1:k] == lines[j][:k - 1] and lines[j][-k:-1] == lines[i][-k + 1:]:
                    print("blagoi")
                    graph.add_edge(i, j)
        # print(graph)
        return graph

    @staticmethod
    def solve():
        def get_filenames():
            import sys
            task_number = sys.argv[0].split('/')[-1].split('_')[0]
            input = f"input_datasets/{task_number}.txt"
            output = f"output_datasets/{task_number}.txt"
            return input, output

        input_file, output_file = get_filenames()

        with open(input_file) as f:
            k, d = [int(x) for x in f.readline().strip().split(" ")]
            lines = [x.strip() for x in f]

        graph = Task.make_graph(k, lines)

        path = networkx.dag_longest_path(graph)

        a, b = [], []
        for vertex in path:
            a.append(lines[vertex][0])

        for vertex in path:
            b.append(lines[vertex][-1])
        b = b[-(2 * k + d - 1):]

        with open(output_file, 'w') as f:
            f.write("".join(a + b))


if __name__ == "__main__":
    Task.solve()
