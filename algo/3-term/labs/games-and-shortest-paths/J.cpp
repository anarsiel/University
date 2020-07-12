//
// Created by Anarsiel on 2019-11-11.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <set>

using namespace std;

vector<vector<int>> g;
vector<vector<int>> r_g;

vector<bool> used;
vector<int> ans;
vector<int> v;

void dfs(int vertex) {
    used[vertex] = true;

    for (auto to : g[vertex]) {
        if (!used[to])
            dfs(to);
    }

    v.push_back(vertex);
}

int main() {
//    freopen("game.in", "r", stdin);
//    freopen("game.out", "w", stdout);

    ios_base::sync_with_stdio(false);

    int n, m;
    cin >> n >> m;
    used.resize(n, false);
    ans.resize(n, 0);

    g.resize(n);
    r_g.resize(n);

    for (int i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;
        a--, b--;

        g[a].push_back(b);
//        r_g[b].push_back(a);
    }

    for (int i = 0; i < n; ++i) {
        if (!used[i])
            dfs(i);
    }

    for (int curVertex : v) {

        vector<int> children;
        for (auto to : g[curVertex]) {
            children.push_back(ans[to]);
        }

        sort(children.begin(), children.end());

        for (int child : children) {
            if (ans[curVertex] == child)
                ++ans[curVertex];
        }
    }

    for (int i = 0; i < n; ++i)
        cout << ans[i] << '\n';
    return 0;
}