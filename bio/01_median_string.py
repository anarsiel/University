from itertools import product


class Task:
    @staticmethod
    def hamming_distance(s1, s2):
        res = 0
        for ab in zip(s1, s2):
            a, b = ab[0], ab[1]
            res += a != b
        return res

    @staticmethod
    def pattern_text(s1, s2):
        res = None
        for i in range(len(s2) - len(s1) + 1):
            hd = Task.hamming_distance(s1, s2[i:])

            if res is None or res > hd:
                res = hd
        return res

    @staticmethod
    def pattern_dna(kmer, texts):
        res = 0
        for text in texts:
            res += Task.pattern_text(kmer, text)

        return res

    @staticmethod
    def solve():
        def get_filenames():
            import sys
            task_number = sys.argv[0].split('/')[-1].split('_')[0]
            input  = f"input_datasets/{task_number}.txt"
            output = f"output_datasets/{task_number}.txt"
            return input, output

        input_file, output_file = get_filenames()

        with open(input_file) as f:
            k = int(f.readline())
            texts = [x.strip() for x in f.readlines()]

        kmer_min, dist_min = None, None
        for kmer in [''.join(x) for x in product('ATGC', repeat=k)]:
            dist = Task.pattern_dna(kmer, texts)
            if kmer_min is None or dist < dist_min:
                kmer_min = kmer
                dist_min = dist

        with open(output_file, 'w') as f:
            f.write(kmer_min)


if __name__ == "__main__":
    Task.solve()
