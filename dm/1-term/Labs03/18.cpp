//
// Created by Anarsiel on 05/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>

using namespace std;

int main() {
    freopen("brackets2num.in", "r", stdin);
    freopen("brackets2num.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    long long n;
    string s;
    cin >> s;
    n = s.size() / 2;

    vector<vector<long long>> dp(2 * n + 1, vector<long long> (2 * n + 1, 0));

    dp[0][0] = 1;
    for (long long i = 1; i < 2 * n + 1; i++) {
        for (long long j = 0; j < 2 * n + 1; j++) {
            dp[i][j] = (j > 0 ? dp[i - 1][j - 1] : 0) + (j < 2 * n ? dp[i - 1][j + 1] : 0);
        }
    }

    long long k = 0;
    long long balance = 0;

    for (long long i = 0; i < 2 * n; ++i) {
        if (s[i] == '(') {
            balance++;
        } else {
            k += dp[2 * n - i - 1][balance + 1];
            balance--;
        }
    }
    cout << k;
    return 0;
}