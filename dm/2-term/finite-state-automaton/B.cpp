//
// Created by Anarsiel on 2019-04-25.
//

#include <iostream>
#include <vector>
#include <map>
#include <set>

using namespace std;

string s;
set<int> terminals;
vector<map<char, vector<int>>> v;
set<pair<int, int>> memory;

void dfs(int vertex, int index) {
    if (memory.count({vertex, index}) != 0) return;

    if (index == s.size()) {
        if (terminals.count(vertex) == 0) return;

        cout << "Accepts\n";
        exit(0);
    }

    auto it = v[vertex].find(s[index]);

    if (it == v[vertex].end()) return;

    for (int i = 0; i < (*it).second.size(); ++i) {
        dfs((*it).second[i], index + 1);
    }

    memory.insert({vertex, index});
}

string file_name = "problem2";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    cin >> s;

    int n, m, k;
    cin >> n >> m >> k;

    for (int i = 0; i < k; ++i){
        int a;
        cin >> a;
        a--;
        terminals.insert(a);
    }

    v.resize(n);
    for (int i = 0; i < m; ++i) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--, b--;

        v[a][c].push_back(b);
    }

    dfs(0, 0);

    cout << "Rejects\n";
    return 0;
}
/*
abacaba
4 6 1
2
1 2 a
2 1 c
2 3 b
3 2 a
2 4 b
1 4 a
 */