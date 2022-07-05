import numpy as np
import pandas as pd
import sklearn.tree as tree
from random import randint
import matplotlib.pyplot as ppl


class HyperParams:
    criterion = ["gini", "entropy"]
    splitter = ["best", "random"]
    depth = list(range(1, 21))  # + [30, 50]
    steps = range(0, 56)
    steps_to_draw = [1, 2, 3, 5, 8, 13, 21, 34, 55]
    # depth = list(range(1, 5))  # + [30, 50]


def parse_dataset(file_name):
    print("parsing", file_name, sep=" ", end="\n")
    dataset = pd.read_csv(file_name)
    data_X, data_Y, data_Y_NP = dataset.values[:, :-1], [], dataset.values[:, -1]
    for y_NP in data_Y_NP:
        y = 1
        if y_NP == 'N':
            y = -1
        data_Y.append(y)

    return data_X, data_Y


def build_tree():
    return tree.DecisionTreeClassifier(
        criterion=HyperParams.criterion[randint(0, 1)],
        splitter=HyperParams.splitter[randint(0, 1)],
        max_depth=HyperParams.depth[randint(0, 19)]
    )


def get_f(x, bs, tree_fits):
    sum = 0
    for idx, tree_fit in enumerate(tree_fits):
        sum += tree_fit.predict([x])[0] * bs[idx]
    return sum


def draw_step_plot(x, y, fits, b, step, dataset_name):
    ppl.scatter(x[:, 0], x[:, 1], c=y, s=20, cmap=ppl.cm.Paired)
    ax = ppl.gca()
    x_lim = ax.get_xlim()
    y_lim = ax.get_ylim()
    x_coords = np.linspace(x_lim[0], x_lim[1], 30)
    y_coords = np.linspace(y_lim[0], y_lim[1], 30)
    y_mesh, x_mesh = np.meshgrid(y_coords, x_coords)
    x_for_graph = np.vstack([x_mesh.ravel(), y_mesh.ravel()]).T
    predict = [get_f(value, b, fits) for value in x_for_graph]
    predict = np.array(predict).reshape(x_mesh.shape)
    ax.contourf(x_mesh, y_mesh, predict, levels=[-100, 0, 100], alpha=0.2,
                colors=['blue', 'red'])

    ax.contour(x_mesh, y_mesh, predict, levels=[0])
    ppl.title(f"{dataset_name.split('.')[0]}. Step # {step}.")
    ppl.show()


def get_acc(data_X, data_Y, tree_fits, bs):
    acc = 0
    for idx, x in enumerate(data_X):
        if get_f(x, bs, tree_fits) < 0:
            pred = -1
        else:
            pred = 1

        if data_Y[idx] == pred:
            acc += 1
    return acc / len(data_X)


def count_error(data_Y, preds, ws):
    err = 0
    for idx, pred in enumerate(preds):
        if pred != data_Y[idx]:
            err += ws[idx]

    return err


def draw_plot(xs, ys, title, label_x, label_y):
    ppl.title(title)
    ppl.xlabel(label_x)
    ppl.ylabel(label_y)
    ppl.plot(xs, ys)
    ppl.show()


dataset_name = "chips.csv"
data_X, data_Y = parse_dataset(dataset_name)
n = len(data_X)

w_init = 1.0 / n
ws = [w_init for _ in range(n)]
bs, tree_fits, accs = [], [], []
for step in HyperParams.steps:
    tree_fit = build_tree().fit(data_X, data_Y, sample_weight=ws)
    tree_fits.append(tree_fit)
    preds = tree_fit.predict(data_X)

    err, b = count_error(data_Y, preds, ws), 0
    if err != 0:
        b = 0.5 * np.log((1 - err) / err)
    bs.append(b)

    sum_weights = 0.0
    for idx, y in enumerate(data_Y):
        ws[idx] *= np.exp(-b * y * preds[idx])
        sum_weights += ws[idx]

    for i in range(n):
        ws[i] /= sum_weights

    accs.append(get_acc(data_X, data_Y, tree_fits, bs))

    if step in HyperParams.steps_to_draw:
        draw_step_plot(data_X, data_Y, tree_fits, bs, step, dataset_name)

draw_plot(HyperParams.steps, accs, dataset_name.split('.')[0], "steps", "accs")

dataset_name = "geyser.csv"
data_X, data_Y = parse_dataset(dataset_name)
n = len(data_X)

w_init = 1.0 / n
ws = [w_init for _ in range(n)]
bs, tree_fits, accs = [], [], []
for step in HyperParams.steps:
    tree_fit = build_tree().fit(data_X, data_Y, sample_weight=ws)
    tree_fits.append(tree_fit)
    preds = tree_fit.predict(data_X)

    err, b = count_error(data_Y, preds, ws), 0
    if err != 0:
        b = 0.5 * np.log((1 - err) / err)
    bs.append(b)

    sum_weights = 0.0
    for idx, y in enumerate(data_Y):
        ws[idx] *= np.exp(-b * y * preds[idx])
        sum_weights += ws[idx]

    for i in range(n):
        ws[i] /= sum_weights

    accs.append(get_acc(data_X, data_Y, tree_fits, bs))

    if step in HyperParams.steps_to_draw:
        draw_step_plot(data_X, data_Y, tree_fits, bs, step, dataset_name)

draw_plot(HyperParams.steps, accs, dataset_name.split('.')[0], "steps", "accs")
