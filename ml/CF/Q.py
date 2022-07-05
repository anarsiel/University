from math import log

def safe_inc(d, key):
    if key in d.keys():
        d[key] += 1.0
    else:
        d[key] = 1.0

k1k1, k2 = [int(x) for x in input().split()]
n = int(input())

xs, ys = [], []
for _ in range(n):
    x, y = [int(x) for x in input().split()]
    xs.append(x)
    ys.append(y)

mp1, mp2 = {}, {}
for i in range(n):
    safe_inc(mp1, xs[i])
    safe_inc(mp2, (xs[i], ys[i]))

for k in mp1.keys():
    mp1[k] /= n

for k in mp2.keys():
    mp2[k] /= n

answer = 0.0
for k in mp2.keys():
    answer += (log(mp1[k[0]]) - log(mp2[k])) * mp2[k]

print(answer)