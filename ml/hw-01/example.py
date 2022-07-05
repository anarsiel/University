from math import sqrt

import pandas as pd
from matplotlib import pyplot as plt


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


# d(p, q) = sqrt((p_1 - q_1)^2 + (p_2 - q_2)^2 + ...)
def euclidean_distance(row1, row2):
    distance = 0.0
    for i in range(len(row1) - 1):
        distance += (row1[i] - row2[i]) ** 2
    return sqrt(distance)


def  get_neighbors(train, test_row, num_neighbors):
    distances = list()
    for train_row in train:
        dist = euclidean_distance(test_row, train_row)
        distances.append((train_row, dist))
    distances.sort(key=lambda tup: tup[1])

    neighbors = list()
    for i in range(num_neighbors):
        neighbors.append(distances[i][0])
    return neighbors


def predict(train, test_row, num_neighbors):
    neighbors = get_neighbors(train, test_row, num_neighbors)
    labels = [row[-1] for row in neighbors]
    prediction = max(set(labels), key=labels.count)
    return prediction


def draw_plot(dataset, feature1N, feature2N, target):
    iris_type_to_color = {
        "Iris-setosa": "g",
        "Iris-versicolor": "b",
        "Iris-virginica": "y"
    }

    colored_irises = list(map(lambda x: iris_type_to_color[x], dataset[:, -1]))
    colored_irises.append("r")

    x = list(dataset[:, feature1N])
    y = list(dataset[:, feature2N])

    x.append(target[feature1N])
    y.append(target[feature2N])

    plt.scatter(x, y, c=colored_irises)
    plt.show()


filename = 'iris.csv'

dataset = pd.read_csv(filename)

min_max = minmax(dataset.values)
normalized_dataset_values = normalize(dataset.values, min_max)

num_neighbors = 5

target = [5.0, 3.4, 1.6, 0.7, None]  # write label to predict as `None`
normalized_target = normalize([target.copy()], min_max)

label = predict(normalized_dataset_values, normalized_target, num_neighbors)

print('Item to predict: %s, Predicted: %s' % (target[:-1], label))

draw_plot(dataset.values, 2, 1, target)