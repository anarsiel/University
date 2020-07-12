#include <random>

//
// Created by Anarsiel on 2019-11-24.
//

#include <iostream>
#include <vector>
#include <functional>
#include <time.h>
#include <algorithm>

using namespace std;

vector<vector<int>> g;

struct Tree {
    int root;
    int n, m;
    vector<vector<int>> edges;

    Tree(int n, int m) {
        this->n = n;
        this->m = m;

        edges.resize(n);
    }
};

struct Dfs {
    Tree tree;
    vector<int> used;
    vector<int> vertexesInTopSort;
    bool treeHasCycle = false;

    Dfs(Tree &tree) {
        this->tree = tree;
        used.resize(tree.n, 0);
    }

     void run(int vertex) {
        used[vertex] = 1;

        for (int to : tree.edges[vertex]) {
            if (used[to] == 0) {
                run(to);
            } else if (used[to] == 1){
                treeHasCycle = true;
            }
        }

        vertexesInTopSort.add(vertex);
        used.set(vertex, 2);
    }
};

bool isHam(vector<int> &v) {
    int n = v.size();
    for (int i = 0; i < n; ++i) {
        int cur = v[i];
        int next = v[(i + 1) % n];

        bool has = false;

        for (int j = 0; j < g[cur].size(); ++j) {
            if (g[cur][j] == next) {
                has = true;
                break;
            }
        }

        if (!has) {
            return false;
        }
    }
    return true;
}

int main() {
    freopen("guyaury.in", "r", stdin);
    freopen("guyaury.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;

    g.resize(n);
    for (int i = 1; i < n; ++i) {
        for (int j = 0; j < i; ++j) {
            char c;
            cin >> c;
            if (c == '1')
                g[i].push_back(j);
            else
                g[j].push_back(i);
        }
    }

    vector<int> v(n);
    for (int i = 0; i < n; ++i) {
        v[i] = i;
    }

    for (;;) {
//        mt19937 int_random(static_cast<unsigned int>(time(0)));
//        auto between_random = std::bind(std::uniform_int_distribution<int>(0, 1), int_random);

        random_shuffle(v.begin(), v.end());

        if (isHam(v)) {
            for (auto x : v) {
                cout << x + 1 << ' ';
            }
            return 0;
        }
    }
}
/*

3
1 01

4
1
11
101
 */