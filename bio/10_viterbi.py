import numpy


class Task:
    @staticmethod
    def launch_viterby(letters, states, trans_matrix, emi_matrix, text):
        len_s, len_t, len_a = len(states), len(text), len(letters)

        X = []
        for i in range(len_s):
            tmp = []
            for j in range(len_s):
                tmp.append(trans_matrix[states[i]][states[j]])
            X += [tmp]

        Y = []
        for i in range(len_s):
            tmp = []
            for j in range(len_a):
                tmp.append(emi_matrix[states[i]][letters[j]])
            Y += [tmp]

        state2idx = {}
        for idx in range(len_s):
            state2idx[states[idx]] = idx

        letter2idx = {}
        for idx in range(len_a):
            letter2idx[letters[idx]] = idx

        tmp = []
        for i in text:
            tmp.append(letter2idx[i])
        text = (*tmp,)

        X, Y = numpy.array(X), numpy.array(Y)
        t1, t2 = numpy.full((len_t, len_s), 0.0), numpy.full((len_t, len_s), 0)
        t1[0, :] = Y[:, text[0]] * (numpy.full(len_s, 1.0 / len_s))  # Pi
        t2[0, :] = range(len_s)
        for i, j in enumerate(text[1:]):
            for k in range(len_s):
                trellis = X[:, k] * t1[i, :]

                mx, mx_idx = None, None
                for h in range(len_s):
                    if mx is None or trellis[h] > mx:
                        mx, mx_idx = trellis[h], h

                t2[i + 1, k], t1[i + 1, k] = mx_idx, trellis[mx_idx]

            t1[i + 1, :] *= Y[:, j]

        mx, mx_idx = None, None
        for i in range(len_s):
            if mx is None or t1[len_t - 1, i] > mx:
                mx, mx_idx = t1[len_t - 1, i], i

        tmp = [0] * len_t
        tmp[-1] = mx_idx
        for idx in reversed(range(len_t - 1)):
            tmp[idx] = t2[idx + 1, tmp[idx + 1]]

        path = ""
        for idx in tmp:
            path += str(states[idx])
        return path

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
            text = f.readline().strip(); f.readline()

            letters = f.readline().strip().split(); f.readline()
            states = f.readline().strip().split(); f.readline()

            first_row = f.readline().strip().split()
            trans_matrix = {}
            for i in range(len(states)):
                line = f.readline().strip().split()[1:]

                idx = 0
                trans_matrix[states[i]] = {}
                for key in first_row:
                    value = line[idx]
                    idx += 1
                    trans_matrix[states[i]][key] = float(value)
            f.readline()

            first_row = f.readline().strip().split()
            emi_matrix = {}
            for i in range(len(states)):
                line = f.readline().strip().split()[1:]

                idx = 0
                emi_matrix[states[i]] = {}
                for key in first_row:
                    value = line[idx]
                    idx += 1
                    emi_matrix[states[i]][key] = float(value)

        with open(output_file, 'w') as f:
            f.write(Task.launch_viterby(letters, states, trans_matrix, emi_matrix, text))


if __name__ == "__main__":
    Task.solve()
