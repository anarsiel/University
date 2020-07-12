#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

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

int main() {
    ios_base::sync_with_stdio(false);

    int n, k;
    cin >> n >> k;

    g.resize(n);
    mt.assign(k, -1);

    for (int i = 0; i < n; ++i) {
        int to;
        for (;;) {
            cin >> to;
            if (!to) break;

            --to;
            g[i].push_back(to);
        }
    }

    for (int v = 0; v < n; ++v) {
        used.assign(n, false);
        try_kuhn(v);
    }

    int cnt = 0;
    for (int x : mt)
        if (x != -1)
            ++cnt;

    cout << cnt << endl;
    for (int i = 0; i < k; ++i)
        if (mt[i] != -1)
            printf("%d %d\n", mt[i] + 1, i + 1);
    return 0;
}
