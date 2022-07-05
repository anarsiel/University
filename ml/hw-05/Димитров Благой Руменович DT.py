from sklearn.tree import DecisionTreeClassifier
import pandas as pd
import matplotlib.pyplot as ppl


class HyperParams:
    criterion = ["gini", "entropy"]
    splitter = ["best", "random"]
    # depth = list(range(1, 21))  # + [30, 50]
    depth = list(range(1, 5))  # + [30, 50]

    min_depth, max_depth = depth[0], depth[-1]


class Accuracy:
    def __init__(self, acc, criterion, splitter, depth, dataset):
        self.acc = acc
        self.criterion = criterion
        self.splitter = splitter
        self.depth = depth
        self.dataset = dataset

    def __str__(self):
        return \
            "Acc: " + str(self.acc) + "\n" \
            + "Crit: " + str(self.criterion) + "\n" \
            + "Split: " + str(self.splitter) + "\n" \
            + "Depth: " + str(self.depth) + "\n" \
            + "Dataset: " + str(self.dataset) + "\n"


def parse_dataset(file_name):
    # print("parsing", file_name, sep=" ", end="\n")
    dataset = pd.read_csv(file_name)
    data_X, data_Y = dataset.values[:, :-1], dataset.values[:, -1]
    return data_X, data_Y


def draw_plot(name, xs, ys, xs2, ys2, x_name, y_name):
    ppl.xlabel(x_name)
    ppl.ylabel(y_name)
    ppl.title(name)
    ppl.plot(xs, ys, label='1')
    ppl.plot(xs2, ys2, label='2')
    ppl.show()


def filename_by_dataset(dataset):
    dataset_str = str(dataset + 1)
    if dataset + 1 < 10:
        dataset_str = '0' + dataset_str
    filename = 'data/' + dataset_str + '_'
    return filename


def get_acc(c, s, md, fit_X, fit_Y, test_X, test_Y):
    tree = DecisionTreeClassifier(criterion=c, splitter=s, max_depth=md)
    tree_fit = tree.fit(fit_X, fit_Y)

    cnt = 0
    for idx, x in enumerate(test_X):
        y = tree_fit.predict([x])[0]

        if y == test_Y[idx]:
            cnt += 1
    acc = (cnt / len(test_X))

    return Accuracy(acc, c, s, md, -1)


def build_tree_and_calc_accuracy(dataset, tree_cnt=80):
    def get_best(ys):
        mx_k, mx_v = -1, -1
        for k in ys.keys():
            v = ys.get(k)
            if mx_v < v:
                mx_k, mx_v = k, v
        return mx_k

    filename = filename_by_dataset(dataset)
    test_X, test_Y = parse_dataset(filename + "test.csv")
    train_X, train_Y = parse_dataset(filename + "train.csv")

    train_preds = [{} for i in range(len(train_X))]
    test_preds = [{} for i in range(len(test_X))]

    for idx in range(tree_cnt):
        tree = DecisionTreeClassifier(max_features="sqrt")
        tree_fit = tree.fit(train_X, train_Y)

        for i in range(len(train_X)):
            train_pred = tree_fit.predict([train_X[i]])[0]
            if train_pred not in train_preds[i].keys():
                train_preds[i][train_pred] = 0
            train_preds[i][train_pred] += 1

            train_test = tree_fit.predict([test_X[i]])[0]
            if train_test not in test_preds[i].keys():
                test_preds[i][train_test] = 0
            test_preds[i][train_test] += 1

    predict_train_y, predict_test_y = [], []
    for train_result in train_preds:
        predict_train_y.append(get_best(train_result))
    for test_result in test_preds:
        predict_test_y.append(get_best(test_result))

    cnt = 0
    for idx, x in enumerate(test_X):
        if predict_train_y[idx] == test_Y[idx]:
            cnt += 1
    acc = (cnt / len(test_X))

    cnt = 0
    for idx, x in enumerate(test_X):
        if predict_test_y[idx] == test_Y[idx]:
            cnt += 1
    acc_test = (cnt / len(test_X))

    print(str(dataset + 1) + ":\n",
          "Train: ", str(acc) + "\n",
          "Test: ", acc_test, end='\n\n')


dataset_count = 4
accuracies = []
for dataset in range(dataset_count):
    filename = filename_by_dataset(dataset)

    test_X, test_Y = parse_dataset(filename + "test.csv")
    train_X, train_Y = parse_dataset(filename + "train.csv")

    cur_accs = []
    for c in HyperParams.criterion:
        for s in HyperParams.splitter:
            for md in HyperParams.depth:
                acc = get_acc(c, s, md, train_X, train_Y, test_X, test_Y)
                acc.dataset = dataset
                cur_accs.append(acc)

    accuracies.append(cur_accs)

bests = []
for dataset in range(dataset_count):
    best = None
    for acc in accuracies[dataset]:
        if best is None or best.acc < acc.acc:
            best = acc
    bests.append(best)


best_min_d = None
best_max_d = None
for best in bests:
    if best_min_d is None or best_min_d.depth > best.depth:
        best_min_d = best

    if best_max_d is None or best_max_d.depth < best.depth:
        best_max_d = best

accs_min_d = [x for x in accuracies[best_min_d.dataset]
              if x.criterion == best_min_d.criterion and x.splitter == best_min_d.splitter]
accs_max_d = [x for x in accuracies[best_max_d.dataset]
              if x.criterion == best_max_d.criterion and x.splitter == best_max_d.splitter]

accs_min_d_test = []
filename = filename_by_dataset(best_min_d.dataset)
test_X, test_Y = parse_dataset(filename + "test.csv")
train_X, train_Y = parse_dataset(filename + "train.csv")
for md in HyperParams.depth:
    acc = get_acc(best_min_d.criterion, best_min_d.splitter, md,
                  train_X, train_Y, train_X, train_Y)
    accs_min_d_test.append(acc)

accs_max_d_test = []
filename = filename_by_dataset(best_max_d.dataset)
test_X, test_Y = parse_dataset(filename + "test.csv")
train_X, train_Y = parse_dataset(filename + "train.csv")
for md in HyperParams.depth:
    acc = get_acc(best_max_d.criterion, best_max_d.splitter, md,
                  train_X, train_Y, train_X, train_Y)
    accs_max_d_test.append(acc)

accs_min_d_acc = [x.acc for x in accs_min_d]
accs_min_d_depth = [x.depth for x in accs_min_d]
accs_min_d_acc_test = [x.acc for x in accs_min_d_test]
accs_min_d_depth_test = [x.depth for x in accs_min_d_test]

for d in accs_min_d_depth:
    print(d, end=" ")
print()

for acc in accs_min_d_acc:
    print(acc, end=" ")
print()

draw_plot(
    "min height " + best_min_d.criterion + " " + best_min_d.splitter + " #" + str(best_min_d.dataset),
    accs_min_d_depth,
    accs_min_d_acc,
    accs_min_d_depth_test,
    accs_min_d_acc_test,
    "depth",
    "accuracy"
)

accs_max_d_acc = [x.acc for x in accs_max_d]
accs_max_d_depth = [x.depth for x in accs_max_d]
accs_max_d_acc_test = [x.acc for x in accs_max_d_test]
accs_max_d_depth_test = [x.depth for x in accs_max_d_test]
draw_plot(
    "max height " + best_max_d.criterion + " " + best_max_d.splitter + " #" + str(best_min_d.dataset),
    accs_max_d_depth,
    accs_max_d_acc,
    accs_max_d_depth_test,
    accs_max_d_acc_test,
    "depth",
    "accuracy"
)

for i in range(21):
    build_tree_and_calc_accuracy(i)

# print("finished")
