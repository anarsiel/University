def get_inner(by_classes):
    inner = 0
    for c in range(len(by_classes)):
        by_classes[c].sort()

        # noinspection PyRedeclaration
        prev_update = 0
        for idx in range(1, len(by_classes[c])):
            cur = prev_update
            cur += (by_classes[c][idx] - by_classes[c][idx - 1]) * idx
            prev_update = cur
            inner += cur

    return inner * 2

n, m = int(input()), int(input())

by_classes = [[] for _ in range(n)]
all_as_one = [[]]
for _ in range(m):
    x, y = [int(x) for x in input().split()]
    by_classes[y - 1].append(x)
    all_as_one[0].append(x)

inner = get_inner(by_classes)
outer = get_inner(all_as_one) - inner
print(inner)
print(outer)


