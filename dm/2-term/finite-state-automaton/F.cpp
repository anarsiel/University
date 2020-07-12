//
// Created by Anarsiel on 2019-04-26.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <set>

using namespace std;

set<int> terminals1, terminals2;
vector<vector<pair<char, int>>> v1, v2;
vector<bool> used;

void dfs(int vertex1, int vertex2) {
    if (used[vertex1]) return;

    used[vertex1] = true;
    if (terminals1.count(vertex1) != terminals2.count(vertex2)) {
        cout << "NO\n";
        exit(0);
    }

    if (v1[vertex1].size() != v2[vertex2].size()) {
        cout << "NO\n";
        exit(0);
    }

    for (int i = 0; i < v1[vertex1].size(); ++i) {
        if (v1[vertex1][i].first != v2[vertex2][i].first) {
            cout << "NO\n";
            exit(0);
        }

        dfs(v1[vertex1][i].second, v2[vertex2][i].second);
    }
}

string file_name = "isomorphism";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, m, k;
    cin >> n >> m >> k;

    for (int i = 0; i < k; ++i){
        int a;
        cin >> a;
        a--;
        terminals1.insert(a);
    }

    v1.resize(n);
    for (int i = 0; i < m; ++i) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--, b--;

        v1[a].push_back({c, b});
    }

    cin >> n >> m >> k;

    for (int i = 0; i < k; ++i){
        int a;
        cin >> a;
        a--;
        terminals2.insert(a);
    }

    v2.resize(n);
    for (int i = 0; i < m; ++i) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--, b--;

        v2[a].push_back({c, b});
    }

    used.resize(n, false);

    for (int i = 0; i < v1.size(); ++i)
        sort(v1[i].begin(), v1[i].end());

    for (int i = 0; i < v2.size(); ++i)
        sort(v2[i].begin(), v2[i].end());

    dfs(0, 0);
    cout << "YES";
    return 0;
}
/*
3 3 1
3
1 2 a
1 3 c
2 3 b
3 3 1
2
1 3 a
1 2 c
3 2 b
 */