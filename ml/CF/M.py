import numpy

n, x1, x2 = int(input()), [], []

for _ in range(n):
    a, b = [int(x) for x in input().split()]
    x1.append(a)
    x2.append(b)

array1 = numpy.array(x1)
order1 = array1.argsort()
ranks1 = order1.argsort()
array2 = numpy.array(x2)
order2 = array2.argsort()
ranks2 = order2.argsort()

s = 0
for i in range(n):
    s += (ranks1[i] - ranks2[i]) ** 2

print(1 - 6/(n * (n - 1) * (n + 1)) * s)