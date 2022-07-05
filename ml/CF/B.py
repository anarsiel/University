def get_f1(prec, recall):
    return 2.0 * prec * recall / (prec + recall)


k = int(input())
cm = [[int(x) for x in input().split()] for _ in range(k)]

sums = [sum(line) for line in cm]
all_sum = sum(sums)

micro_f, precW, recallW = 0, 0, 0
for idx, line in enumerate(cm):
    TP = line[idx]
    FN = sums[idx] - TP
    FP = -TP
    for line2 in cm:
        FP += line2[idx]

    if TP == 0:
        continue

    prec = 1.0 * TP / (TP + FP)
    recall = 1.0 * TP / (TP + FN)
    f = get_f1(prec, recall)
    micro_f += sums[idx] * f / all_sum

    precW += TP * sums[idx] / ((TP + FP) * all_sum)
    recallW += TP / all_sum
macro_f = get_f1(precW, recallW)

print(macro_f, micro_f, sep='\n')
