//
// Created by Anarsiel on 2019-04-25.
//

#include <iostream>
#include <vector>
#include <map>
#include <set>

using namespace std;

std::string file_name = "problem1";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    string s;
    cin >> s;

    int n, m, k;
    cin >> n >> m >> k;

    set<int> terminals;
    for (int i = 0; i < k; ++i){
        int a;
        cin >> a;
        a--;
        terminals.insert(a);
    }

    vector<map<char, int>> v(n);

    for (int i = 0; i < m; ++i) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--, b--;

        v[a][c] = b;
    }

    int cur = 0;
    for (int i = 0; i < s.size(); ++i) {
        auto it = v[cur].find(s[i]);
        if (it != v[cur].end()) {
            cur = (*it).second;
        } else {
            cout << "Rejects\n";
            return 0;
        }

        if (i == s.size() - 1 && terminals.count(cur) == 0) {
            cout << "Rejects\n";
            return 0;
        }
    }
    cout << "Accepts\n";
    return 0;
}
/*
abacaba
2 3 1
2
1 2 a
2 1 b
2 1 c
 */