//
// Created by Anarsiel on 2019-11-11.
//

#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> g;

// true - win in this vertex, else - false
bool dfs(int vertex) {
    bool res = false;

    for (auto to : g[vertex]) {
        if (!dfs(to))
            res = true;
    }

    return res;
}

int main() {
    freopen("game.in", "r", stdin);
    freopen("game.out", "w", stdout);

    ios_base::sync_with_stdio(false);

    int n, m, s;
    cin >> n >> m >> s;
    --s;

    g.resize(n);

    for (int i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;
        a--, b--;

        g[a].push_back(b);
    }

    cout << (dfs(s) ? "First":  "Second") << " player wins";
    return 0;
}