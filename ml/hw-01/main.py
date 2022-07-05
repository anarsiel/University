import collections

import pandas as pd
import numpy as np
import matplotlib.pyplot as ppl


def minmax(dataset):
    minmax = list()
    for i in range(len(dataset[0])):
        if i == len(dataset[0]) - 1:
            continue
        value_min = dataset[:, i].min()
        value_max = dataset[:, i].max()
        minmax.append([value_min, value_max])
    return minmax


def normalize(dataset, minmax):
    for row in dataset:
        for i in range(len(row)):
            if i == len(row) - 1:  # exclude labels
                continue
            row[i] = (row[i] - minmax[i][0]) / (minmax[i][1] - minmax[i][0])

    return dataset


def minkovskiy_distance(a, b, p):
    distance = 0
    for i in range(len(a) - 1):
        distance += abs(a[i] - b[i]) ** p

    return distance ** (1 / p)


def euclidean_distance(a, b):
    return minkovskiy_distance(a, b, 1)


def manhattan_distance(a, b):
    return minkovskiy_distance(a, b, 2)


def default_kernel(x, k, p1, p2, do_abs=False):
    if abs(x) > 1:
        return 0
    if do_abs:
        x = abs(x)
    return k * (1 - x ** p1) ** p2


def uniform_kernel(a):
    return default_kernel(a, 1 / 2, 0, 0)


def triangular_kernel(a):
    return default_kernel(a, 1, 1, 1, True)


def epanechnikov_kernel(a):
    return default_kernel(a, 3 / 4, 2, 1)


def biweight_kernel(a):
    return default_kernel(a, 15 / 16, 2, 2)


def tricube_kernel(a):
    return default_kernel(a, 70 / 81, 3, 3, True)


def launch(window_width, distance, kernel, data_X, data_Y_naive,
           data_Y_one_hot):
    def launch_naive(weights, data_Y):
        result = 0
        for idx in range(weights.shape[0]):
            result += data_Y[idx] * weights[idx]

        if (np.sum(weights) == 0):
            return 0
        return result / np.sum(weights)

    def launch_one_hot(weights, data_Y):
        result = [0] * data_Y.shape[1]
        for one_hot_idx in range(data_Y.shape[1]):
            for idx in range(data_Y.shape[0]):
                result += data_Y[idx] * weights[idx]

            if (np.sum(weights) == 0):
                result[one_hot_idx] = 0
            else:
                result[one_hot_idx] /= np.sum(weights)

        return result

    def get_distance(test_X, data_X):
        dist_array = np.empty(data_X_learn.shape[0])

        for idx in range(data_X.shape[0]):
            dist_array[idx] = distance(test_X, data_X[idx])

        return dist_array

    def get_weights(distance):
        weights = np.empty(distance.shape[0])

        for idx in range(distance.shape[0]):
            weights[idx] = kernel(distance[idx] / window_width)

        return weights

    tps, tps2 = 0, 0
    fps, fps2 = 0, 0
    tns, tns2 = 0, 0
    fns, fns2 = 0, 0

    for test_element_index in range(data_X.shape[0]):
        test_element_naive = (data_X[test_element_index],
                              data_Y_naive[test_element_index])
        test_element_one_hot = (data_X[test_element_index],
                                data_Y_one_hot[test_element_index, :])

        data_X_learn = np.concatenate(
            (data_X[:test_element_index],
             data_X[test_element_index + 1:]),
            axis=0
        )

        data_Y_learn_naive = np.concatenate(
            (data_Y_naive[:test_element_index],
             data_Y_naive[test_element_index + 1:]),
            axis=0
        )

        data_Y_learn_one_hot = np.concatenate(
            (data_Y_one_hot[:test_element_index],
             data_Y_one_hot[test_element_index + 1:]),
            axis=0
        )

        distances = get_distance(test_element_naive[0], data_X_learn)
        weights = get_weights(distances)

        naive_result = launch_naive(weights, data_Y_learn_naive)
        one_hot_result = launch_one_hot(weights, data_Y_learn_one_hot)

        if naive_result == test_element_naive[1]:
            tps += 1
            fps += 0
            tns += 2
            fns += 0
        else:
            tps += 0
            fps += 1
            tns += 1
            fns += 1

        if np.argmax(one_hot_result) == np.argmax(test_element_one_hot[1]):
            tps2 += 1
            fps2 += 0
            tns2 += 2
            fns2 += 0
        else:
            tps2 += 0
            fps2 += 1
            tns2 += 1
            fns2 += 1

    presicion = tps / (tps + fps)
    recall = tps / (tps + fns)
    presicion2 = tps2 / (tps2 + fps2)
    recall2 = tps2 / (tps2 + fns2)

    f1_naive, f1_one_hot = 0, 0

    if presicion + recall != 0:
        f1_naive = 2 * (presicion * recall) / (presicion + recall)

    if presicion2 + recall2 != 0:
        f1_one_hot = 2 * (presicion2 * recall2) / (presicion2 + recall2)

    return f1_naive, f1_one_hot


filename = 'red-wine-quality.csv'
dataset = pd.read_csv(filename).to_numpy()

class5_indexes = [i for i, x in enumerate(dataset) if x[-1] == 5.][:100]
class6_indexes = [i for i, x in enumerate(dataset) if x[-1] == 6.][:100]
class7_indexes = [i for i, x in enumerate(dataset) if x[-1] == 7.][:100]

dataset5 = dataset[class5_indexes]
dataset6 = dataset[class6_indexes]
dataset7 = dataset[class7_indexes]
dataset = np.concatenate((dataset5, dataset6, dataset7), axis=0)

min_max = minmax(dataset)
dataset = normalize(dataset, min_max)

data_X, data_Y = [], []
for dataline in dataset:
    x, y = dataline[:-2], dataline[-1]
    data_X.append(x), data_Y.append(y)

data_X = np.array(data_X, np.float)
data_Y_naive = np.array(data_Y, np.float)
data_Y_one_hot = pd.get_dummies(np.array(data_Y, np.float)).to_numpy()

# width, width_change, iterations_count = 0.2, 0.2, 10

widths = np.arange(0.1, 1, 0.1)
distance_funcs = [euclidean_distance, manhattan_distance]
kernel_funcs = [uniform_kernel, triangular_kernel, epanechnikov_kernel,
                biweight_kernel, tricube_kernel]

for kernel in kernel_funcs:
    print(kernel)

    f1s_naive, f1s_one_hot = [], []
    for width in widths:
        print(width)
        f1_naive, f1_one_hot = launch(width, manhattan_distance, kernel, data_X,
                                      data_Y_naive, data_Y_one_hot)

        f1s_naive.append(f1_naive)
        f1s_one_hot.append(f1_one_hot)

    ppl.xlabel('window width')
    ppl.ylabel('f1')
    ppl.title(kernel.__name__)
    ppl.plot(widths, f1s_naive, label='Naive')
    ppl.plot(widths, f1s_one_hot, label='One Hot')
    ppl.show()
