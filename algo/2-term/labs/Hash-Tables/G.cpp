//
// Created by Anarsiel on 2019-03-24.
//

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<vector<int>> v, v1, v2;
vector<bool> was;
vector<int> under_tree_size;

void dfs_count_under_tree_size(int vertex) {
    was[vertex] = true;
    under_tree_size[vertex] = 1;

    for (auto to : v[vertex]) {
        if (!was[to]) {
            dfs_count_under_tree_size(to);
            under_tree_size[vertex] += under_tree_size[to];
        }
    }
}

void dfs_count_hash1(int vertex, int depth) {
    was[vertex] = true;
    v1[depth].push_back(under_tree_size[vertex]);

    for (auto to : v[vertex]) {
        if (!was[to]) {
            dfs_count_hash1(to, depth + 1);
        }
    }
}

void dfs_count_hash2(int vertex, int depth) {
    was[vertex] = true;
    v2[depth].push_back(under_tree_size[vertex]);

    for (auto to : v[vertex]) {
        if (!was[to]) {
            dfs_count_hash2(to, depth + 1);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    v.resize(n);
    was.resize(n, false);
    under_tree_size.resize(n, 0);

    for (int i = 0; i < n - 1; ++i) {
        int a, b;
        cin >> a >> b;
        a--, b--;

        v[a].push_back(b);
        v[b].push_back(a);
    }

    if (n % 2 == 0) {
        cout << "NO" << endl;
        return 0;
    }

    dfs_count_under_tree_size(0);

    int center = -1;
    for (int i = 1; i < n; ++i) {
        if (v[i].size() == 2) {
            if (under_tree_size[i] - 1 == n - under_tree_size[i]) {
                center = i;
                break;
            }
        }
    }

    if (center == -1) {
        if (v[0].size() == 2) {
            if (under_tree_size[v[0][0]] == under_tree_size[v[0][1]]) {
                center = 0;
            }
        }
    }

//    for (int i = 0; i < under_tree_size.size(); ++i) {
//        cout << under_tree_size[i] << ' ';
//    }
//    cout << endl;

    if (center == -1) {
        cout << "NO" << endl;
        return 0;
    }

    under_tree_size.resize(n, 0);
    was.assign(n, false);
    was[center] = true;
    dfs_count_under_tree_size(v[center][0]);
    dfs_count_under_tree_size(v[center][1]);

    was.assign(n, false);
    was[center] = true;
    v1.resize(n);
    v2.resize(n);
    dfs_count_hash1(v[center][0], 0);
    dfs_count_hash2(v[center][1], 0);

    for (int i = 0; i < n; ++i) {
        if (v1[i].size() > 0) {
            sort(v1[i].begin(), v1[i].end());
        }

        if (v2[i].size() > 0) {
            sort(v2[i].begin(), v2[i].end());
        }
    }

    if (v1 == v2) {
        cout << "YES" << endl;
    } else {
        cout << "NO" << endl;
    }
    return 0;
}
