import math

import numpy as np


class DistanceFunctions:
    @staticmethod
    def minkovsky(a, b, p):
        distance = 0
        for i in range(len(a)):
            distance += abs(a[i] - b[i]) ** p

        return distance ** (1 / p)

    @staticmethod
    def manhattan(a, b):
        return DistanceFunctions.minkovsky(a, b, 1)

    @staticmethod
    def euclidean(a, b):
        return DistanceFunctions.minkovsky(a, b, 2)

    @staticmethod
    def chebyshev(a, b):
        distance = 0
        for i in range(len(a)):
            distance = max(distance, abs(a[i] - b[i]))

        return distance


distance_to_func = {
    "manhattan": DistanceFunctions.manhattan,
    "euclidean": DistanceFunctions.euclidean,
    "chebyshev": DistanceFunctions.chebyshev
}


class KernelFunctions:
    @staticmethod
    def default(x, k, p1, p2, do_abs=False):
        if abs(x) >= 1:
            return 0
        if do_abs:
            x = abs(x)
        return k * (1 - x ** p1) ** p2

    @staticmethod
    def uniform(a):
        return KernelFunctions.default(a, 1 / 2, 0, 0)

    @staticmethod
    def triangular(a):
        return KernelFunctions.default(a, 1, 1, 1, True)

    @staticmethod
    def epanechnikov(a):
        return KernelFunctions.default(a, 3 / 4, 2, 1)

    @staticmethod
    def biweight(a):
        return KernelFunctions.default(a, 15 / 16, 2, 2)

    @staticmethod
    def triweight(a):
        return KernelFunctions.default(a, 35 / 32, 2, 3)

    @staticmethod
    def tricube(a):
        return KernelFunctions.default(a, 70 / 81, 3, 3, True)

    @staticmethod
    def gaussian(a):
        return math.e ** (-0.5 * (a ** 2)) / (math.sqrt(2 * math.pi))

    @staticmethod
    def cosine(a):
        if abs(a) > 1:
            return 0
        return math.cos(math.pi * a / 2) * math.pi / 4

    @staticmethod
    def logistic(a):
        return 1.0 / (math.e ** a + math.e ** -a + 2)

    @staticmethod
    def sigmoid(a):
        return 2 / (math.pi * (math.e ** a + math.e ** -a))


kernel_to_func = {
    "uniform": KernelFunctions.uniform,
    "triangular": KernelFunctions.triangular,
    "epanechnikov": KernelFunctions.epanechnikov,
    "quartic": KernelFunctions.biweight,
    "triweight": KernelFunctions.triweight,
    "tricube": KernelFunctions.tricube,
    "gaussian": KernelFunctions.gaussian,
    "cosine": KernelFunctions.cosine,
    "logistic": KernelFunctions.logistic,
    "sigmoid": KernelFunctions.sigmoid
}

def launch(kernel, distance, window, window_w, data_X, data_Y, request, zero_w_value):
    def launch_naive(weights, data_Y, zero_w_value):
        result = 0
        for idx in range(weights.shape[0]):
            result += data_Y[idx] * weights[idx]

        if np.sum(weights) == 0:
            return zero_w_value
        return result / np.sum(weights)

    def get_distance(test_X, data_X):
        dist_array = np.empty(data_X.shape[0])

        for idx in range(data_X.shape[0]):
            dist_array[idx] = distance(test_X, data_X[idx])

        return dist_array

    def get_weights_fixed(distances, window_w):
        weights = np.empty(distances.shape[0])

        for idx in range(distances.shape[0]):
            weights[idx] = kernel(distances[idx] / window_w)

        return weights

    def get_weights_variable(distances, distances_sorted, window_w):
        weights = np.empty(distances.shape[0])

        for idx in range(distances.shape[0]):
            weights[idx] = kernel(distances[idx] / distances_sorted[window_w])

        return weights

    distances = get_distance(request, data_X)
    distances_sorted = sorted(distances)

    if distances_sorted[0] == 0:
        ans, cnt = 0, 0
        for i in range(len(distances)):
            if distances[i] == 0:
                ans += data_Y[i]
                cnt += 1
        return ans / cnt


    if window == "fixed":
        weights = get_weights_fixed(distances, window_w)
    else:
        weights = get_weights_variable(distances, distances_sorted, window_w)
    result = launch_naive(weights, data_Y, zero_w_value)

    return result


n, m = [int(x) for x in input().split()]

data_X, data_Y = [], []
for i in range(n):
    line = [int(x) for x in input().split()]
    data_X.append(line[:-1])
    data_Y.append(line[-1])

median = sum(data_Y) / len(data_Y)


data_X = np.array(data_X, np.float)
data_Y = np.array(data_Y, np.float)

request = [int(x) for x in input().split()]
d_f, k_f, window, window_w = input(), input(), input(), int(input())
d_f, k_f = distance_to_func[d_f], kernel_to_func[k_f]

print(launch(k_f, d_f, window, window_w, data_X, data_Y, request, median))
