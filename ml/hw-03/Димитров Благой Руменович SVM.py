import copy
import random
import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import make_blobs
import pandas as pd
from sklearn.model_selection import KFold


class KernelFunctions:
    @staticmethod
    def linear():
        return lambda a, b: np.dot(a, b)

    @staticmethod
    def polynomial(power):
        return lambda a, b: np.power(np.dot(a, b) + 1, power)

    @staticmethod
    def gauss(beta):
        return lambda a, b: np.exp(-beta * np.power(np.linalg.norm(a - b), 2))


class HyperParams:
    polynomial_powers = [2, 3, 4, 5]
    gauss_betas = [1, 2, 3, 4, 5]
    support_vector_cs = [0.05, 0.1, 0.5, 1.0, 5.0, 10.0, 50.0, 100.0]

    kernel_functions = [
        KernelFunctions.linear,
        KernelFunctions.polynomial,
        KernelFunctions.gauss
    ]

def x_to_y(x, a, b, xs, ys, kernel):
    result = b
    for i in range(len(xs)):
        result += a[i] * ys[i] * kernel(xs[i], x)
    return result

def svm(с, steps, x, y, kernel):
    eps = 0.0000001
    dist = 0.5

    def get_left_right(i, j, alphas, y, c):
        if abs(y[i] - y[j]) < eps:
            return max(0, alphas[i] + alphas[j] - c), min(c, alphas[i] + alphas[j])
        else:
            return max(0, alphas[j] - alphas[i]), min(c, c + alphas[j] - alphas[i])

    alphas, b = [0] * len(x), 0
    for _ in range(steps):
        for idx in range(len(x)):
            ind_u = x_to_y(x[idx], alphas, b, x, y, kernel) - y[idx]
            if (y[idx] * ind_u < -dist and alphas[idx] < с) or (y[idx] * ind_u > dist and alphas[idx] > 0):
                j = idx
                while j == idx:
                    j = random.randint(0, len(x) - 1)

                y_j = x_to_y(x[j], alphas, b, x, y, kernel) - y[j]
                alpha_i, alpha_j = alphas[idx], alphas[j]

                left, right = get_left_right(idx, j, alphas, y, с)
                if not (abs(left - right) > eps): continue

                nu = kernel(x[idx], x[j]) * 2 - kernel(x[idx], x[idx]) - kernel(x[j], x[j])
                if nu > -eps: continue

                alphas[j] -= y[j] * (ind_u - y_j) / nu
                if alphas[j] - right >= eps:
                    alphas[j] = right
                else:
                    if left - alphas[j] >= eps:
                        alphas[j] = left

                if not (abs(alphas[j] - alpha_j) > eps): continue

                alphas[idx] += y[idx] * y[j] * (alphas[j] - alpha_j)

                b_ = b - ind_u - y[idx] * (alphas[idx] - alpha_i) * kernel(x[idx], x[idx]) - y[j] * (alphas[j] - alpha_j) * kernel(x[idx], x[j])
                b__ = b - y_j - y[idx] * (alphas[idx] - alpha_i) * kernel(x[idx], x[j]) - y[j] * (alphas[j] - alpha_j) * kernel(x[j], x[j])

                if alphas[idx] < eps or с - alphas[idx] < eps:
                    b = b_
                elif alphas[j] < eps or с - alphas[j] < eps:
                    b = b__
                else:
                    b = (b_ + b__) / 2.
    return alphas, b


def parse_dataset(file_name):
    print("parsing", file_name, sep=" ", end="\n")
    dataset = pd.read_csv(file_name)
    data_X, data_Y = dataset.values[:, :-1], dataset.values[:, -1]
    data_Y_new = data_Y
    for idx, y in enumerate(data_Y):
        data_Y_new[idx] = 1.
        if y == 'N':
            data_Y[idx] = -1.
    return data_X, data_Y_new


def run_svm(p, beta, c, kf):
    a, b = None, None
    if kf == KernelFunctions.linear:
        a, b = svm(c, 20, xs, ys, kf())
    if kf == KernelFunctions.polynomial:
        a, b = svm(c, 10, xs, ys, kf(p))
    if kf == KernelFunctions.gauss:
        a, b = svm(c, 10, xs, ys, kf(beta))

    if a is None or b is None:
        raise Exception

    print(a, b)
    return a, b

def do_predict(data_X, data_Y, a, b, xs, ys, kernel):
    data_Y_pred = data_Y
    for idx, x in enumerate(data_X):
        y_pred = x_to_y(x, a, b, xs, ys, kernel)
        if y_pred > 0:
            data_Y_pred[idx] = 1
        else:
            data_Y_pred[idx] = -1

    return data_Y_pred

def do_predict2(data_X, data_Y, a, b, xs, ys, kernel):
    data_Y_pred = data_Y
    for idx, x in enumerate(data_X):
        data_Y_pred[idx] = x_to_y(x, a, b, xs, ys, kernel)

    return data_Y_pred

def count_accuracy(data_X, data_Y, a, b, xs, ys, kernel):
    data_Y_pred = do_predict(data_X, data_Y, a, b, xs, ys, kernel)

    count = 0
    for idx in range(len(data_Y_pred)):
        if data_Y_pred[idx] == data_Y[idx]:
            count += 1
    return count / len(data_Y)


def do_linear(data_X_train, data_Y_train):
    accuracy_max, a_max, b_max = 0, None, None
    for c in HyperParams.support_vector_cs:
        a, b = svm(c, 20, data_X_train, data_Y_train, KernelFunctions.linear())

        acc = count_accuracy(X_test, Y_test, a, b, data_X_train, data_Y_train, KernelFunctions.linear())
        if acc > accuracy_max:
            accuracy_max, a_max, b_max = acc, a, b
    return [accuracy_max, a_max, b_max]


def do_polynomial(data_X_train, data_Y_train):
    accuracy_max, a_max, b_max, p_max = 0, None, None, None
    for c in HyperParams.support_vector_cs:
        for p in HyperParams.polynomial_powers:
            a, b = svm(
                c, 10, data_X_train, data_Y_train, KernelFunctions.polynomial(p)
            )

            acc = count_accuracy(X_test, Y_test, a, b, data_X_train, data_Y_train,
                                 KernelFunctions.polynomial(p))

            if acc > accuracy_max:
                accuracy_max, a_max, b_max, p_max = acc, a, b, p

    return [accuracy_max, a_max, b_max, p_max]


def do_gauss(data_X_train, data_Y_train):
    accuracy_max, a_max, b_max, beta_max = 0, None, None, None
    for c in HyperParams.support_vector_cs:
        for beta in HyperParams.gauss_betas:
            a, b = svm(
                c, 10, data_X_train, data_Y_train,
                KernelFunctions.gauss(beta),
            )

            acc = count_accuracy(X_test, Y_test, a, b, data_X_train, data_Y_train, KernelFunctions.gauss(beta))

            if acc > accuracy_max:
                accuracy_max, a_max, b_max, beta_max = acc, a, b, beta

    return [accuracy_max, a_max, b_max, beta_max]


def draw_one_plot(data_X, data_Y, xs, ys, a, b, kernel):
    plt.scatter(data_X[:, 0], data_X[:, 1], c=data_Y, s=30, cmap=plt.cm.Paired)

    ax = plt.gca()
    xlim = ax.get_xlim()
    ylim = ax.get_ylim()

    xx = np.linspace(xlim[0], xlim[1], 30)
    yy = np.linspace(ylim[0], ylim[1], 30)
    YY, XX = np.meshgrid(yy, xx)
    xy = np.vstack([XX.ravel(), YY.ravel()]).T

    Z = []
    for x in xy:
        Z.append(x_to_y(x, a, b, xs, ys, kernel))
    Z = np.array(Z).reshape(XX.shape)

    ax.contour(XX, YY, Z, colors='k', levels=[-1, 0, 1], alpha=0.5,
               linestyles=['--', '-', '--'])

    ax.contourf(XX, YY, Z, levels=[-100, 0, 100], alpha=0.2, colors=['#0000ff', '#ff0000'])
    plt.show()

#
# CHIPS
#

data_X, data_Y = parse_dataset("chips.csv")
data_X, data_Y = np.array(data_X), np.array(data_Y)

res_l_max, res_p_max, res_g_max = [0], [0], [0]

kf = KFold(4)
for train_index, test_index in kf.split(data_X):
    X_train, Y_train = data_X[train_index], data_Y[train_index]
    X_test, Y_test = data_X[test_index], data_Y[test_index]

    xs, ys = copy.deepcopy(X_train), copy.deepcopy(Y_train)
    res_l = do_linear(xs, ys)
    draw_one_plot(data_X, data_Y, xs, ys, res_l[1], res_l[2],
                  KernelFunctions.linear())

    xs, ys = copy.deepcopy(data_X), copy.deepcopy(data_Y)
    res_p = do_polynomial(xs, ys)
    draw_one_plot(data_X, data_Y, xs, ys, res_p[1], res_p[2],
                  KernelFunctions.polynomial(res_p[3]))

    xs, ys = copy.deepcopy(data_X), copy.deepcopy(data_Y)
    res_g = do_gauss(xs, ys)
    draw_one_plot(data_X, data_Y, xs, ys, res_g[1], res_g[2],
                  KernelFunctions.gauss(res_g[3]))

    if res_l[0] > res_l_max[0]:
        res_l_max = res_l

    if res_p[0] > res_p_max[0]:
        res_p_max = res_p

    if res_g[0] > res_g_max[0]:
        res_g_max = res_g
    break

draw_one_plot(data_X, data_Y, res_l_max[1], res_l_max[2],
              KernelFunctions.linear())
draw_one_plot(data_X, data_Y, res_p_max[1], res_p_max[2],
              KernelFunctions.polynomial(res_p[3]))
draw_one_plot(data_X, data_Y, res_g_max[1], res_g_max[2],
              KernelFunctions.gauss(res_g[3]))

#
# GEYSER
#

data_X, data_Y = parse_dataset("geyser.csv")
data_X, data_Y = np.array(data_X), np.array(data_Y)

res_l_max, res_p_max, res_g_max = [0], [0], [0]

kf = KFold(4)
for train_index, test_index in kf.split(data_X):
    X_train, Y_train = data_X[train_index], data_Y[train_index]
    X_test, Y_test = data_X[test_index], data_Y[test_index]

    xs, ys = copy.deepcopy(X_train), copy.deepcopy(Y_train)
    res_l = do_linear(xs, ys)
    draw_one_plot(data_X, data_Y, xs, ys, res_l[1], res_l[2],
                  KernelFunctions.linear())

    xs, ys = copy.deepcopy(data_X), copy.deepcopy(data_Y)
    res_p = do_polynomial(xs, ys)
    draw_one_plot(data_X, data_Y, xs, ys, res_p[1], res_p[2],
                  KernelFunctions.polynomial(res_p[3]))

    xs, ys = copy.deepcopy(data_X), copy.deepcopy(data_Y)
    res_g = do_gauss(xs, ys)
    draw_one_plot(data_X, data_Y, xs, ys, res_g[1], res_g[2],
                  KernelFunctions.gauss(res_g[3]))

    if res_l[0] > res_l_max[0]:
        res_l_max = res_l

    if res_p[0] > res_p_max[0]:
        res_p_max = res_p

    if res_g[0] > res_g_max[0]:
        res_g_max = res_g

draw_one_plot(data_X, data_Y, res_l_max[1], res_l_max[2],
              KernelFunctions.linear())
draw_one_plot(data_X, data_Y, res_p_max[1], res_p_max[2],
              KernelFunctions.polynomial(res_p[3]))
draw_one_plot(data_X, data_Y, res_g_max[1], res_g_max[2],
              KernelFunctions.gauss(res_g[3]))