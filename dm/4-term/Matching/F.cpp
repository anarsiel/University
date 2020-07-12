//
// Created by Anarsiel on 26/02/2020.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m;
vector<vector<int>> g;
vector<int> mt;
vector<bool> used;

bool try_kuhn(int v) {
    if (used[v]) return false;
    used[v] = true;
    for (size_t i = 0; i < g[v].size(); ++i) {
        int to = g[v][i];
        if (mt[to] == -1 || try_kuhn(mt[to])) {
            mt[to] = v;
            return true;
        }
    }
    return false;
}

void add(int i1, int j1, int i2, int j2, vector<vector<char>> &table) {
    if (i1 < 0 || j1 < 0 || i2 < 0 || j2 < 0) return;
    if (i1 >= n || j1 >= m || i2 >= n || j2 >= m) return;
    if (table[i1][j1] != '*' || table[i2][j2] != '*') return;

    int a = i1 * m + j1;
    int b = i2 * m + j2;
    g[a].push_back(b);
    // g[b].push_back(a); // ?
}

int main() {
    freopen("dominoes.in", "r", stdin);
    freopen("dominoes.out", "w", stdout);
    ios_base::sync_with_stdio(false);

//    int n;
//    cin >> n;
//
//    g.resize(n);
//    mt.assign(n, -1);

//    for (int i = 0; i < n; ++i) {
//        int to;
//        for (;;) {
//            cin >> to;
//            if (!to) break;
//
//            --to;
//            g[i].push_back(to);
//        }
//    }
    int a, b;
    cin >> n >> m >> a >> b;

    vector<vector<char>> table(n, vector<char> (m));

    g.resize(n*m);
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < m; ++j)
            cin >> table[i][j];

    int cntStars = 0;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (table[i][j] == '*') {
                ++cntStars;
            }

            add(i, j, i, j - 1, table);
            add(i, j, i, j - 1, table);
            add(i, j, i, j + 1, table);
            add(i, j, i, j + 1, table);

            add(i, j, i - 1, j, table);
            add(i, j, i + 1, j, table);
            add(i, j, i - 1, j, table);
            add(i, j, i + 1, j, table);
        }
    }

    int nm = n * m;
    mt.assign(nm, -1);

    for (int v = 0; v < nm; ++v) {
        used.assign(nm, false);
        try_kuhn(v);
    }

    int cnt = 0;
    for (int x : mt)
        if (x != -1)
            ++cnt;

    if (a >= 2 * b) {
        cout << cntStars * b;
    } else {
        cout << cnt / 2 * a + (cntStars - cnt) * b;
    }
//    cout << cnt << endl;
//    for (int i = 0; i < nm; ++i)
//        if (mt[i] != -1)
//            printf("%d %d\n", mt[i] + 1, i + 1);
    return 0;
}
/*
2 3 3 2
.**
.*.

2 3 100000 2
.*.
.*.

2 3 3 2
.*.
.*.

1 1 1 13
*
 */