//
// Created by Anarsiel on 2019-05-01.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <set>

using namespace std;


typedef long long ll;
const ll mod = 1e9 + 7;

set<int> terminals;
vector<vector<pair<char, int>>> v;

string file_name = "problem4";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, m, k, l;
    cin >> n >> m >> k >> l;

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

        v[a].push_back({c, b});
    }

    vector<vector<ll>> dp(n, vector<ll> (l + 1));

    dp[0][0] = 1;
    for (int j = 0; j < l; ++j) {
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h < v[i].size(); ++h) {
                auto p = v[i][h];

                dp[p.second][j + 1] += dp[i][j];
                dp[p.second][j + 1] %= mod;
            }
        }
    }

    ll answ = 0;
    for (int i = 0; i < n; ++i) {
        if (terminals.count(i) > 0) {
            answ += dp[i][l];
            answ %= mod;
        }
    }
    cout << answ;
    return 0;
}
/*
3 6 1 1
3
1 2 a
1 2 b
2 3 a
2 3 b
2 3 c
1 3 q
 */