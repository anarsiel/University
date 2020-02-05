//
// Created by Anarsiel on 2020-01-05.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

vector<vector<int>> g;
vector<int> mt, w;
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

int main() {
    freopen("matching.in", "r", stdin);
    freopen("matching.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;

    w.resize(n);
    g.resize(n);
    mt.assign(n, -1);
    vector<pair<int, int>> wi(n);
    for (int i = 0; i < n; ++i) {
        cin >> w[i];
        wi[i] = {w[i], i};
    }

    sort(wi.begin(), wi.end());
    reverse(wi.begin(), wi.end());

    for (int i = 0; i < n; ++i) {
        int cnt;
        cin >> cnt;

        for (int j = 0; j < cnt; ++j) {
            int to;
            cin >> to;
            --to;

            g[i].push_back(to);
        }
    }

    for (int i = 0; i < n; ++i) {
        int v = wi[i].second;
        used.assign(n, false);
        try_kuhn(v);
    }

    vector<int> mt2(n, -1);

    for (int i = 0; i < n; ++i) {
        mt2[mt[i]] = i;
    }

    for (int i = 0; i < n; ++i)
        cout << mt2[i] + 1 << ' ';
    return 0;
}
/*
4
1 3 2 4
4 1 2 3 4
2 1 4
2 1 4
2 1 4

2 1
4 2
1 3
3 4
 */