//
// Created by Anarsiel on 2019-05-02.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <set>

using namespace std;


typedef long long ll;
const ll mod = static_cast<const ll>(1e9 + 7);

int n, m, k;
vector<vector<int>> graf, graft;
vector<int> topSort, color;
vector<bool> used, terminals, ok;
bool has_cycle = false;

void dfs(int v) {
    used[v] = true;
    ok[v] = true;
    for (int i = 0; i < graft[v].size(); i++) {
        int w = graft[v][i];
        if (!used[w]) {
            dfs(w);
        }
    }

}

void top_sort(int v) {
    if (color[v] == 2) {
        return;
    }

    if (has_cycle) {
        return;
    }

    color[v] = 1;
    for (int i = 0; i < graf[v].size(); i++) {
        int w = graf[v][i];
        if (ok[w]) {
            if (color[w] == 0) {
                top_sort(w);
            }
            if (color[w] == 1) {
                has_cycle = true;
                return;
            }
        }
    }
    color[v] = 2;
    topSort.push_back(v);
}

string file_name = "problem3";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();


    cin >> n >> m >> k;
    terminals.resize(n);
    color.resize(n, 0);
    used.resize(n);
    ok.resize(n);
    vector<int> d(n, 0);
    graf.resize(n);
    graft.resize(n);

    for (int i = 0; i < k; i++) {
        int a;
        cin >> a;
        a--;
        terminals[a] = true;
    }


    cerr << endl;
    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--, b--;

        graf[a].push_back(b);
        graft[b].push_back(a);
    }

    for (int i = 0; i < n; i++) {
        if (terminals[i] && (!used[i])) {
            dfs(i);
        }
    }

    d[0] = 1;
    int answ = 0;
    top_sort(0);
    if (has_cycle) {
        cout << -1;
        return 0;
    }

    reverse(topSort.begin(), topSort.end());
    for (int i = 0; i < topSort.size(); i++) {
        int v = topSort[i];
        for (int j = 0; j < graf[v].size(); j++) {
            if (ok[graf[v][j]])
                d[graf[v][j]] += (d[v] % mod);
        }
    }

    for (int i = 0; i < n; i++) {
        if (terminals[i]) answ += d[i], answ %= mod;
    }
    cout << answ;
    return 0;
}
/*
1 1 1
1
1 1 a

3 5 1
3
1 2 a
1 2 b
2 3 a
2 3 b
2 3 c
 */