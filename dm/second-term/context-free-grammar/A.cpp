//
// Created by Anarsiel on 2019-05-12.
//

#include <iostream>
#include <vector>

using namespace std;

vector<vector<vector<int>>> v(27, vector<vector<int>> (26));
string str;

bool dfs(int index, int vertex) {
    if (index == str.size()) {
        return (vertex == 26);
    }

    int cur = str[index] - 'a';

    for (int i = 0; i < v[vertex][cur].size(); ++i) {
        if (dfs(index + 1, v[vertex][cur][i])) {
            return true;
        }
    }
    return false;
}

std::string file_name = "automaton";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int m;
    char start;
    cin >> m >> start;

    for (int i = 0; i < m; ++i) {
        char c;
        string s;
        cin >> c >> s >> s;

        if (s.size() == 1) {
            v[c - 'A'][s[0] - 'a'].push_back(26);
        } else {
            v[c - 'A'][s[0] - 'a'].push_back(s[1] - 'A');
        }
    }

    cin >> m;
    for (int i = 0; i < m; ++i) {
        cin >> str;
        cout << (dfs(0, start - 'A') ? "yes" : "no") << '\n';
    }
    return 0;
}
/*
2S
S -> aA
A -> b
2
ab
aa
 */