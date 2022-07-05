import copy
import random

import pandas as pd
import numpy as np
import matplotlib.pyplot as ppl
from sklearn.decomposition import PCA
from sklearn.metrics import adjusted_rand_score


def minmax(dataset):
    minmax = list()
    for i in range(len(dataset[0])):
        if i == 0:
            minmax.append([1, -1])
            continue
        value_min = dataset[:, i].min()
        value_max = dataset[:, i].max()
        minmax.append([value_min, value_max])
    return minmax


def normalize(dataset, minmax):
    for row in dataset:
        for i in range(len(row)):
            if i == 0:  # exclude data_Y
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


def display_clusters(data_X, data_Y, title=""):
    colours = ["r", "g", "b", "c", "m", "y", "k", "w"]

    unique_ys = np.unique(data_Y)
    for idx, y in enumerate(unique_ys):
        cur_xs = data_X[data_Y == y, 0]
        cur_ys = data_X[data_Y == y, 1]
        ppl.scatter(cur_xs, cur_ys, color=colours[idx], alpha=0.5, label=y)
    ppl.title(title)
    ppl.xlabel("xs")
    ppl.ylabel("ys")
    ppl.legend()
    ppl.show()


def get_initial_centroids(cnt, data_X):
    indexes = random.sample(range(0, len(data_X)), cnt)
    return data_X[indexes]


def get_dist(x, y):
    return np.linalg.norm(x - y)


def get_diff(x, y):
    diff = [0 for _ in range(len(x))]
    for i in range(len(x)):
        diff[i] = -x[i] + y[i]
    return diff


def get_dist_to_centroids(centroids, data_X):
    dist_by_centroid = []
    for centroid in centroids:
        dist = []
        for x in data_X:
            dist.append(get_dist(x, centroid))
        dist_by_centroid.append(dist)
    return dist_by_centroid


def get_closest_idxs(dist_by_centroid):
    closest_to_centroid_idxs = [[] for _ in range(len(dist_by_centroid))]

    for idx in range(len(dist_by_centroid[0])):
        min_c, min_dist = None, None
        for c in range(len(dist_by_centroid)):
            if min_dist == None or min_dist > dist_by_centroid[c][idx]:
                min_c, min_dist = c, dist_by_centroid[c][idx]
        closest_to_centroid_idxs[min_c].append(idx)

    return closest_to_centroid_idxs


def get_centroid_shifts(centroids, data_X, closest_to_centroid_idxs):
    shift_by_centroid = []
    for idx, centroid in enumerate(centroids):
        closest = data_X[closest_to_centroid_idxs[idx]]
        diff_sum = [0 for _ in range(len(data_X[0]))]

        if len(closest) != 0:
            for x in closest:
                diff = get_diff(centroid, x)
                for i in range(len(diff)):
                    diff_sum[i] += diff[i]

            for i in range(len(diff_sum)):
                diff_sum[i] /= len(closest)

        shift_by_centroid.append(diff_sum)

    return shift_by_centroid


def shift_centroids(centroids, shift_by_centroid, eps=1e-5):
    shifted_centroids, is_shift_big = copy.deepcopy(centroids), False
    for idx, shift in enumerate(shift_by_centroid):
        for i in range(len(shift)):
            shifted_centroids[idx][i] += shift[i]
            if abs(shift[i]) > eps:
                is_shift_big = True

    return shifted_centroids, is_shift_big


def get_labels_by_closest(data_X, closest_to_centroid_idxs):
    labels = []
    for i in range(len(data_X)):
        for j in range(len(closest_to_centroid_idxs)):
            if i in closest_to_centroid_idxs[j]:
                labels.append(j + 1)
                break
    return labels


def run_kmean(data_X, clusters_cnt, iter_count):
    centroids = get_initial_centroids(clusters_cnt, data_X)

    for i in range(iter_count):
        dist_by_centroid = get_dist_to_centroids(centroids, data_X)
        closest_to_centroid_idxs = get_closest_idxs(dist_by_centroid)

        # data_Y = get_labels_by_closest(data_X, closest_to_centroid_idxs)
        # display_clusters(data_X_reduced, data_Y, f"#{i}")

        shift_by_centroid = get_centroid_shifts(centroids, data_X,
                                                closest_to_centroid_idxs)
        centroids, is_shift_big = shift_centroids(centroids, shift_by_centroid)

        if not is_shift_big or i == iter_count - 1:
            print(f'finished after {i + 1} iterations')
            break

    dist_by_centroid = get_dist_to_centroids(centroids, data_X)
    closest_to_centroid_idxs = get_closest_idxs(dist_by_centroid)

    return get_labels_by_closest(data_X, closest_to_centroid_idxs)


def dunn(data_X, data_Y):
    unique_labels = np.unique(data_Y)

    numerator, denominator = None, None
    for ck in unique_labels:
        idxsCk = np.where(data_Y == ck)
        elemsCk = data_X[idxsCk]
        for cl in unique_labels:
            if cl == ck: continue
            idxsCl = np.where(data_Y == cl)
            elemsCl = data_X[idxsCl]

            for a in elemsCk:
                for b in elemsCl:
                    dst = get_dist(a, b)
                    if numerator is None or numerator > dst:
                        numerator = dst

        for a in elemsCk:
            for b in elemsCk:
                dst = get_dist(a, b)
                if denominator is None or denominator < dst:
                    denominator = dst

    if numerator is None:
        numerator = 0
    return numerator / denominator


def main():
    # filename = 'red-wine-quality.csv'
    filename = "dataset_191_wine.csv"
    dataset = pd.read_csv(filename).to_numpy()

    min_max = minmax(dataset)
    dataset = normalize(dataset, min_max)

    data_X, data_Y = [], []
    for idx, dataline in enumerate(dataset):
        # if idx % 10 != 0: continue

        x, y = dataline[1:], dataline[0]
        data_X.append(x), data_Y.append(y)

    data_X = np.array(data_X, np.float)
    data_Y = np.array(data_Y, np.float)

    reducer = PCA(n_components=2)
    data_X_reduced = reducer.fit_transform(data_X)
    display_clusters(data_X_reduced, data_Y, "Real data_Y")

    data_Y_kmean = run_kmean(data_X,
                             clusters_cnt=3,
                             iter_count=1000)

    display_clusters(data_X_reduced, data_Y_kmean, "last data_Y")

    rands, dunns, c_counts = [], [], range(1, 11)
    for c_count in c_counts:
        data_Y_kmean = run_kmean(data_X,
                                 clusters_cnt=c_count,
                                 iter_count=10000)
        rands.append(adjusted_rand_score(data_Y, data_Y_kmean))
        dunns.append(dunn(data_X, data_Y_kmean))

    ppl.xlabel('clusters count')
    ppl.ylabel('adjusted rand scores')
    ppl.plot(c_counts, rands)
    ppl.show()

    ppl.xlabel('clusters count')
    ppl.ylabel('dunn scores')
    ppl.plot(c_counts, dunns)
    ppl.show()


if __name__ == "__main__":
    main()
