n, m, k = [int(x) for x in input().split()]
cs = [int(x) for x in input().split()]

c_to_indexes = [[] for _ in range(m)]
for idx, c in enumerate(cs):
    c_to_indexes[c - 1].append(idx + 1)

parts = [[] for _ in range(k)]
next_part = 0
for c in range(m):
    for idx in c_to_indexes[c]:
        parts[next_part].append(idx)
        next_part = (next_part + 1) % k

for part in parts:
    print(len(part), end=' ')
    print(*part, sep=' ')