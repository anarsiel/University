import math

n, x1, x2 = int(input()), [], []

for _ in range(n):
    a, b = [int(x) for x in input().split()]
    x1.append(a)
    x2.append(b)

middle1, middle2 = sum(x1) / n, sum(x2) / n

numerator, denominator1, denominator2 = 0, 0, 0
for i in range(n):
    a, b = x1[i], x2[i]

    numerator += (a - middle1) * (b - middle2)
    denominator1 += (a - middle1) ** 2
    denominator2 += (b - middle2) ** 2

try:
    print(numerator / math.sqrt(denominator1 * denominator2))
except:
    print(0)
