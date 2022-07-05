import collections

class Task:
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
            text = f.readline().strip()
            len_t = len(text)

        all = []
        for part in range(len_t):
            for cnt in range(part + 1, len_t + 1):
                all.append(text[part:cnt])

        counter = collections.Counter(all)
        repeated_parts = [part for part, cnt in counter.items() if cnt > 1]

        mx = None
        for repeat in repeated_parts:
            if mx is None or len(mx) < len(repeat):
                mx = repeat

        with open(output_file, 'w') as f:
            f.write(mx)


if __name__ == "__main__":
    Task.solve()
