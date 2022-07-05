import copy
import os

import numpy as np
import matplotlib.pyplot as plt


def get_files(directory):
    return os.listdir(directory)


def is_file_legit(filename):
    return "legit" in filename


class HyperParams:
    # alphas = [0.001, 0.003, 0.005, 0.01, 0.1, 1, 10]
    alphas = [0.001, 0.01, 0.1, 10]
    ns = [1, 2, 3]

    # alphas = [1]
    # ns = [3]


class Bayes:

    def __init__(self, k, alpha):
        self.k = k
        self.alpha = alpha

    def safe_inc(self, dict, klass, word):
        if word in dict[klass].keys():
            self.counts[klass][word] += 1
        else:
            self.counts[klass][word] = 1

    def safe_get(self, dict, key):
        if key in dict.keys():
            return dict[key]
        else:
            return 0

    def run(self, train_lines, test_lines, fines=None):
        # fines = [int(x) for x in input().split()]
        if fines is None:
            fines = [1 for _ in range(self.k)]
        q = 2

        klasses = []
        messages = []
        klass_words = [[] for _ in range(self.k)]
        words = set()

        for line in train_lines:
            c, msg = int(line[0]), line[1:]
            klasses.append(c)
            messages.append(msg)

            was = set()
            for word in msg:
                if word in was:
                    continue

                was.add(word)
                words.add(word)
                klass_words[c].append(word)

        self.counts = [dict() for _ in range(self.k)]
        for klass, words_ in enumerate(klass_words):
            for word in words_:
                self.safe_inc(self.counts, klass, word)

        ps = [dict() for _ in range(self.k)]
        for klass in range(self.k):
            for word in words:
                ps[klass][word] = (self.safe_get(self.counts[klass],
                                                 word) + alpha) / (
                                          klasses.count(klass) + alpha * q)

        #
        # testing
        #

        ind = 0
        probs_by_line = []
        cs = []
        for line in test_lines:
            print(ind)
            ind += 1
            c, current_line_words = int(line[0]), line[1:]
            cs.append(c)

            klass_probs, sum_probs = [], 0.0
            for klass in range(self.k):
                prob = np.log(1.0 * klasses.count(klass) / len(klasses))

                for word in words:
                    if word in current_line_words:
                        prob += np.log(ps[klass][word])
                    else:
                        prob += np.log(1 - ps[klass][word])

                prob += np.log(fines[klass])
                klass_probs.append(prob)
                sum_probs += prob
            arr = []
            for j in range(self.k):
                klass_probs[j] -= sum_probs
                arr.append(klass_probs[j])
            probs_by_line.append(arr)

        return probs_by_line, cs


def parse_file(filename):
    file = open(filename, mode='r')
    all_of_it = file.read().split()[1:]
    file.close()

    klass = "1"  # spam
    if is_file_legit(filename):
        klass = "0"  # legit
    return [klass] + all_of_it


def parse_part(part_number):
    path = "messages/part" + str(part_number + 1) + "/"
    files = get_files(path)

    messages = []
    for file in files:
        file = path + file

        message = parse_file(file)
        messages.append(message)
    return messages


def parse_parts(cnt_parts=10):
    messages_by_parts = []
    for i in range(cnt_parts):
        messages = parse_part(i)
        messages_by_parts.append(messages)
    return messages_by_parts


def get_test_train_lines(messages_by_parts, test_part_number, cnt_parts=10):
    train_lines, test_lines = [], []
    for part in range(cnt_parts):
        if part == test_part_number:
            test_lines += messages_by_parts[part]
        else:
            train_lines += messages_by_parts[part]
    return train_lines, test_lines


def draw_roc(probs_by_line):
    probs_by_line.sort(key=lambda x: -x[1])
    probs_by_line = list(
        map(lambda x: [x[0] / (x[0] + x[1]), x[1] / (x[0] + x[1])],
            probs_by_line)
    )

    cnt_legit, cnt_spam = 0, 0
    for ab in probs_by_line:
        a, b = ab[0], ab[1]
        if a > b:
            cnt_legit += 1
        else:
            cnt_spam += 1
    y_delta, x_delta = 1 / cnt_spam, 1 / cnt_legit

    xs, ys, x, y = [], [], 0, 0
    for ab in probs_by_line:
        a, b = ab[0], ab[1]
        if a > b:
            x += x_delta
        else:
            y += y_delta

        xs.append(x)
        ys.append(y)
    plt.title("ROC")
    plt.xlabel("fp")
    plt.ylabel("tp")
    plt.plot(xs, ys)
    plt.show()


def get_acc(probs, cs):
    cntOK = 0
    for idx, ab in enumerate(probs):
        a, b = ab[0], ab[1]
        if a > b:
            c = 0
        else:
            c = 1

        if c == cs[idx]:
            cntOK += 1
    return cntOK / len(cs)


def modify(messages_by_parts, n):
    if n == 1:
        return messages_by_parts
    messages_by_partsN = [[] for _ in range(len(messages_by_parts))]
    for part_idx in range(len(messages_by_parts)):
        for message_idx in range(len(messages_by_parts[part_idx])):
            messageN = [messages_by_parts[part_idx][message_idx][0]]
            for word_idx in range(1,
                                  len(messages_by_parts[part_idx][message_idx]),
                                  n):
                Nwords = []
                for i in range(word_idx, word_idx + n):
                    if i >= len(messages_by_parts[part_idx][message_idx]): break

                    Nwords += [messages_by_parts[part_idx][message_idx][i]]
                if Nwords != []:
                    messageN.append(" ".join(Nwords))
            messages_by_partsN[part_idx].append(messageN)
    return messages_by_partsN


def draw_l_dependency(train_lines, test_lines):
    l_spam, l_legit = 1, 1
    xx, yy = [], []
    while l_legit <= 91:
        bayes = Bayes(2, 0.01)
        probs_by_line, cs = bayes.run(train_lines, test_lines, [np.exp(l_legit), np.exp(l_spam)])
        acc = get_acc(probs_by_line, cs)

        xx.append(l_legit)
        yy.append(acc)

        cnt_legitF, cnt_spamF = 0, 0
        for idx, ab in enumerate(probs_by_line):
            a, b = ab[0], ab[1]
            if cs[idx] and a > b:
                 cnt_spamF += 1

        if cnt_spamF == 0:
            break
        l_legit += 10

    plt.xlabel("l_legit")
    plt.ylabel("accuracy")
    plt.plot(xx, yy)
    plt.show()


cnt_parts = 10
messages_by_parts = parse_parts(cnt_parts)

best_probs, best_acc, best_alpha, best_n, best_trainl, best_testl = None, 0, 0, 0, None, None
for alpha in HyperParams.alphas:
    bayes = Bayes(2, alpha)
    for n in HyperParams.ns:
        messages_by_partsN = modify(messages_by_parts, n)
        for test_part_number in range(cnt_parts):
            train_lines, test_lines = get_test_train_lines(messages_by_partsN,
                                                           test_part_number,
                                                           cnt_parts)

            probs_by_line, cs = bayes.run(train_lines, test_lines)

            acc = get_acc(probs_by_line, cs)
            if best_acc < acc:
                best_acc, best_probs, best_alpha, best_n, best_trainl, best_testl \
                    = acc, probs_by_line, alpha, n, train_lines, test_lines


print(f"Best acc: {best_acc}\n")
draw_l_dependency(best_trainl, best_testl)
print(f"Best alpha: {best_alpha}\n")
print(f"Best n: {best_n}\n")
draw_roc(best_probs)
