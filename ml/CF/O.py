k, n = int(input()), int(input())

xs, ys = [], []
for _ in range(n):
    x, y = [int(x) for x in input().split()]
    xs.append(x)
    ys.append(y)


s2 = 0
for i in range(len(xs)):
    s2 += 1.0 * (ys[i] ** 2)
s2 /= n

x_to_cnt, x_to_prob = [0 for _ in range(k)], [0 for _ in range(k)]
for i in range(n):
    x = xs[i] - 1
    x_to_cnt[x] += 1.0 * ys[i]
    x_to_prob[x] += 1.0

s3 = 0
for i in range(k):
    y, p = x_to_cnt[i], x_to_prob[i]
    y /= n
    p /= n
    if p != 0:
        s3 += y * y / p

print(s2 - s3)