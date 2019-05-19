//
// Created by Anarsiel on 2019-05-15.
//

#include <iostream>
#include <vector>
#include <map>
#include <memory.h>

using namespace std;

const int MOD = 1000000007;
int dp[26][100][100];

struct Rule {
    char lef, righ;
    bool is_terminal;
};
vector<vector<Rule>> v;

string word;
long long f(int c, int l, int r) {
    if (dp[c][l][r] == -1) {
        dp[c][l][r] = 0;
        for (auto u : v[c]) {
            if (u.is_terminal) {
                if (l + 1 == r && word[l] == u.lef)
                    dp[c][l][r] = (dp[c][l][r] + 1) % MOD;
            } else {
                for (int i = l + 1; i < r; ++i)
                    dp[c][l][r] = static_cast<int>
                            ((f(u.lef - 'A', l, i) * f(u.righ - 'A', i, r) + dp[c][l][r]) % MOD);
            }
        }
    }
    return dp[c][l][r];
}

std::string file_name = "nfc";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    string s, a, b;
    int n;
    cin >> n >> s;

    v.assign(26, vector<Rule>(0));
    memset(dp, -1, sizeof(dp));
    for (int i = 0, c; i < n; ++i) {
        cin >> a >> b;
        getline(cin, b);

        c = a[0] - 'A';
        Rule to{};
        to.lef = b[1];
        if (b.length() > 2)
            to.righ = b[2];

        to.is_terminal = (b.length() <= 2);
        v[c].push_back(to);
    }

    cin >> word;
    cout << f(s[0] - 'A', 0, static_cast<int>(word.length()));
    return 0;
}
/*
4 S
S -> AB
S -> AA
A -> a
B -> a
aa
 */