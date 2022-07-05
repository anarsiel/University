import numpy as np

def safe_inc(dict, klass, word):
    if word in dict[klass].keys():
        counts[klass][word] += 1
    else:
        counts[klass][word] = 1


def safe_get(dict, key):
    if key in dict.keys():
        return counts[klass][key]
    else:
        return 0


k = int(input())
fines = [int(x) for x in input().split()]
alpha = int(input())
q = 2
train_msgs_cnt = int(input())

klasses = []
messages = []
klass_words = [[] for _ in range(k)]
words = set()

for i in range(train_msgs_cnt):
    line = input().split()
    c, sz, msg = int(line[0]) - 1, line[1], line[2:]
    klasses.append(c)
    messages.append(msg)

    was = set()
    for word in msg:
        if word in was:
            continue

        was.add(word)
        words.add(word)
        klass_words[c].append(word)

counts = [dict() for _ in range(k)]
for klass, words_ in enumerate(klass_words):
    for word in words_:
        safe_inc(counts, klass, word)

ps = [dict() for _ in range(k)]
for klass in range(k):
    # print(f"klass: {klass}", end=" ")
    for word in words:
        ps[klass][word] = (safe_get(counts[klass], word) + alpha) / (
                    klasses.count(klass) + alpha * q)
        # print(ps[klass][word], end=" ")
    # print()

n = int(input())
for i in range(n):
    line = input().split()
    n, current_line_words = line[0], line[1:]

    klass_probs, sum_probs = [], 0.0
    for klass in range(k):
        prob = np.log(1.0 * klasses.count(klass) / len(klasses))

        for word in words:
            if word in current_line_words:
                prob += np.log(ps[klass][word])
            else:
                prob += np.log(1 - ps[klass][word])

        prob += np.log(fines[klass])
        klass_probs.append(prob)
        sum_probs += np.exp(prob)

    for j in range(k):
        klass_probs[j] -= np.log(sum_probs) # zero division here, RE 3
                                    # if sum_probs != 0 or try catch fails, TL 3

        print(np.exp(klass_probs[j]), end=" ")
    print()
