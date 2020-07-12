//
// Created by Anarsiel on 2019-05-06.
//

#include <iostream>
#include <vector>
#include <map>
#include <set>
#include <queue>

using namespace std;

vector<vector<int>> g1, g2;
set<pair<int, int>> used;
vector<bool> v1, v2;

bool bfs(int vertex1, int vertex2) {
    used.insert({min(vertex1, vertex2), max(vertex1, vertex2)});

    queue<pair<int, int>> queue;
    queue.push(make_pair(vertex1, vertex2));
    while (!queue.empty()) {
        int go1 = queue.front().first;
        int go2 = queue.front().second;
        queue.pop();

        if (v1[go1] != v2[go2]) {
            return false;
        }

        for (int c = 0; c < 26; c++) {
            int v1 = g1[go1][c], v2 = g2[go2][c];

            if (used.count({min(v1, v2), max(v1, v2)}) == 0) {
                queue.push(make_pair(v1, v2));
                used.insert({min(v1, v2), max(v1, v2)});
            }
        }
    }

    return true;
}

std::string file_name = "equivalence";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, m, k;
    cin >> n >> m >> k;

    v1.assign(n + 1, false);
    for(int i = 0; i < k; i++) {
        int a;
        cin >> a;
        a--;
        v1[a] = true;
    }

    g1.assign(n + 1, vector<int> (26, n));

    for(int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--;
        b--;
        g1[a][c - 'a'] = b;
    }


    int n2, m2, k2;
    cin >> n2 >> m2 >> k2;

    v2.assign(n2 + 1, false);
    for(int i = 0; i < k2; i++) {
        int a;
        cin >> a;
        a--;
        v2[a] = true;
    }

    g2.assign(n2 + 1, vector<int> (26, n2));
    for(int i = 0; i < m2; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--, b--;
        g2[a][c - 'a'] = b;
    }

    cout << (bfs(0, 0) ? "YES" : "NO");
    return 0;
}
/*
1 1 1
 1
 1 1 a
 2 2 2
 1 2
 1 2 a
 2 2 a
 */