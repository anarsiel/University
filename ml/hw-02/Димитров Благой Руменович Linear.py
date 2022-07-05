import copy
import math

import numpy as np
import random
import matplotlib.pyplot as mtp


def LSM(x, y):
    x_ = x.transpose()
    x_dotx = x_.dot(x)

    E = [[0] * len(x_dotx)] * len(x_dotx)
    for i in range(len(x_dotx)):
        E[i][i] = math.pow(1, -10)
    return np.linalg.inv(x_dotx + E).dot(x_).dot(y)


def get_gradient(data_X, data_Y, ws):
    XdotW = np.dot(data_X, ws)

    gradient = []
    for i in range(len(data_X)):
        gradient.append(2 * data_X[i] * (XdotW - data_Y))

    return gradient


def get_smape(data_X, data_Y, ws):
    smape = 0
    for i in range(len(data_X)):
        result = np.dot(ws, data_X[i])
        smape += (abs(result - data_Y[i])) / (
                    abs(data_Y[i]) + abs(result))

    return smape / len(data_X)


def get_smape_steps(ws, data_X, data_Y, steps_count):
    for k in range(1, steps_count):
        rand_idx = random.randint(1, len(data_X)) - 1
        grad = np.array(get_gradient(data_X[rand_idx], data_Y[rand_idx], ws))

        x = (np.dot(data_X[rand_idx], ws) - data_Y[rand_idx]) / np.dot(data_X[rand_idx], grad)
        ws -= x * grad / k

    return get_smape(X_test, Y_test, ws)


def get_next_x_from_file(x, file):
    X, Y = [], []
    for i in range(x):
        vec = list(map(int, next(file).split()))
        X.append(vec[:-1] + [1])
        Y.append(vec[-1])
    return X, Y


def parse_file(filename):
    file = open(filename, "r")
    attr_count = int(next(file))
    X_train, Y_train = get_next_x_from_file(int(next(file)), file)

    X_train = np.array(X_train)
    Y_train = np.array(Y_train)

    X_test, Y_test = get_next_x_from_file(int(next(file)), file)

    return attr_count, X_train, Y_train, X_test, Y_test


if __name__ == '__main__':
    filename = "data/1.txt"
    attr_count, X_train, Y_train, X_test, Y_test = parse_file(filename)

    ws = np.array([
        random.uniform(-1 / (2 * len(X_train)), 1 / (2 * len(X_train)))
        for _ in range(len(X_train[0]))
    ])

    smapes = []
    steps = range(500, 150000, 10000)
    for steps_count in steps:
        smapes.append(
            get_smape_steps(copy.deepcopy(ws), X_train, Y_train, steps_count)
        )

    mtp.xlabel("steps")
    mtp.ylabel("smapes")
    mtp.plot(steps, smapes)
    mtp.show()

    w_ls = LSM(X_train, Y_train)
    print("LSM SMAPE", get_smape(X_test, Y_test, w_ls), sep=": ")
