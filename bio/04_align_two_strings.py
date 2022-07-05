class Task:
    opening_p = 11
    extension_p = 1

    @staticmethod
    def get_blossum():
        g = open('tmp.txt', 'r')
        scoring_matrix = [line[1:].strip().split() for line in g.readlines()]
        letters = scoring_matrix[0]
        scoring_matrix = scoring_matrix[1:]

        blossum_matrix = dict()
        for i in range(len(scoring_matrix)):
            for j in range(len(scoring_matrix)):
                blossum_matrix[letters[i] + letters[j]] = int(scoring_matrix[i][j])
        return blossum_matrix

    @staticmethod
    def fill_dp(v, w):
        scoring_matrix = Task.get_blossum()

        lv, lw = len(v), len(w)
        dp = [dict(), dict(), dict()]
        for i in range(3):
            dp[i][0] = 0

        for k in range(1, (lv + 1) * (lw + 1)):
            if k <= lv:
                dp[0][k] = -Task.opening_p - Task.extension_p * (k - 1)
                dp[1][k] = Task.opening_p * (Task.extension_p - Task.opening_p)
                dp[2][k] = dp[0][k]
            else:
                dp[0][k] = -(Task.opening_p - Task.extension_p) * Task.opening_p
                dp[1][k] = -Task.opening_p - (int(k / lv) - 1) * Task.extension_p
                dp[2][k] = dp[1][k]
                k += lv

        for i in range(lv + 1):
            for j in range(lw + 1):
                idx = j * (lv + 1) + i

                if i != 0 and j != 0:
                    dp[0][idx] = max(
                        dp[0][idx - 1] - Task.extension_p,
                        dp[2][idx - 1] - Task.opening_p
                    )

                if j > 0:
                    dp[1][idx] = max(
                        dp[1][(j - 1) * (lv + 1) + i] - Task.extension_p,
                        dp[2][(j - 1) * (lv + 1) + i] - Task.opening_p
                    )

                dp[2][idx] = max(
                    dp[0][idx],
                    dp[1][idx]
                )

                if j >= 1 and i >= 1:
                    dp[2][idx] = max(
                        dp[2][idx],
                        dp[2][(j - 1) * (lv + 1) + i - 1] + scoring_matrix[v[i - 1] + w[j - 1]]
                    )

        max_score = max(
            dp[0][lw * (lv + 1) + lv],
            dp[1][lw * (lv + 1) + lv],
            dp[2][lw * (lv + 1) + lv]
        )
        return dp, max_score

    @staticmethod
    def reverse_dp(v, w, dp, max_alignment_score):
        lv = len(v)
        lw = len(w)

        letter_for = 'vw'
        v_align, w_align = '', ''

        if max_alignment_score == dp[0][lw * (lv + 1) + lv]:
            letter_for = 'v'
        elif max_alignment_score == dp[1][lw * (lv + 1) + lv]:
            letter_for = 'w'

        idx_v, idx_w = lv, lw
        while idx_w != 0 and idx_w != 0:
            print(idx_w, idx_v)

            idx = idx_w * (lv + 1) + idx_v
            print(dp[0][idx], dp[1][idx], dp[2][idx], end='\n')
            if letter_for == 'v':
                idx_v -= 1

                w_align += '-'
                v_align += v[idx_v]

                if dp[0][idx] == dp[2][idx - 1] - Task.opening_p:
                    letter_for = 'vw'
                    # letter_for = 'v' ??? проверить
                continue

            if letter_for == 'w':
                idx_w -= 1

                v_align += '-'
                w_align += w[idx_w]

                if dp[1][idx] == dp[2][idx_w * (lv + 1) + idx_v] - Task.opening_p:
                    letter_for = 'vw'
                continue

            if dp[2][idx] == dp[0][idx]:
                letter_for = 'v'
            elif dp[2][idx] == dp[1][idx]:
                letter_for = 'w'
            else:
                idx_v -= 1
                idx_w -= 1
                v_align += v[idx_v]
                w_align += w[idx_w]

        print(v_align, w_align, sep='\n')
        return [v_align[::-1], w_align[::-1]]

    @staticmethod
    def find_best(v, w):
        dp, max_alignment_score = Task.fill_dp(v, w)
        print(max_alignment_score)
        return [str(max_alignment_score)] + Task.reverse_dp(v, w, dp, max_alignment_score)

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
            b = f.read()
            v, w = b.split('\n')
            print(v, w)

        with open(output_file, 'w') as f:
            f.write('\n'.join(Task.find_best(v, w)))


if __name__ == "__main__":
    Task.solve()
