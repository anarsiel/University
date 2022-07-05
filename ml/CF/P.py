def safe_inc(d, key):
    if key in d.keys():
        d[key] += 1
    else:
        d[key] = 1

k1, k2 = [int(x) for x in input().split()]
n = int(input())
x1s, x2s = [], []
for _ in range(n):
    a, b = [int(x) for x in input().split()]
    x1s.append(a - 1)
    x2s.append(b - 1)

x1sx, x2sx, mp = [0 for _ in range(k1)], [0 for _ in range(k2)], {}
for i in range(n):
    safe_inc(mp, (x1s[i], x2s[i]))
    x1sx[x1s[i]] += 1.0 / n
    x2sx[x2s[i]] += 1.0 / n

answer = 0.0
for k in mp.keys():
    tmp = x1sx[k[0]] * x2sx[k[1]] * n
    answer += 1.0 * ((mp[k] - tmp) ** 2) / tmp - tmp

print(answer + n)