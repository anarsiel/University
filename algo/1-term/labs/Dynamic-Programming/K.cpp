//
// Created by Anarsiel on 23/12/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

int mod = 1e9;

int MOD(int x) {
    return (x % mod + mod) % mod;
}

vector<vector<int>> dp;

int get(int i, int j) {
    int n = (int) dp.size();
    if (i < 0 || j < 0 || i >= n || j >= n) {
        return 0;
    }

    return dp[i][j];
}

int main() {
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;
    vector<int> v(n);
    dp.resize(n, vector<int> (n, 0));
    for (int i = 0; i < n; i++)
        cin >> v[i];

    for (int len = 1; len <= n; len++) {
        for (int i = 0; i < n; i++) {
            int j = i + len - 1;
            if (j >= n) continue;

            if (v[i] == v[j]) {
                dp[i][j] += get(i + 1, j - 1) + 1;
                dp[i][j] = MOD(dp[i][j]);
            }
            dp[i][j] += MOD(MOD(get(i + 1, j) + get(i, j - 1)) - get(i + 1, j - 1));
            dp[i][j] = MOD(dp[i][j]);
        }
    }
    cout << dp[0][n - 1];
}
