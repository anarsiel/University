//
// Created by Anarsiel on 04/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>

using namespace std;

int main() {
    freopen("num2brackets.in", "r", stdin);
    freopen("num2brackets.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    long long n, k;
    cin >> n >> k;
    k++;
    vector<vector<long long>> dp(2 * n + 1, vector<long long> (2 * n + 1, 0));

    dp[0][0] = 1;
    for (int i = 1; i < 2 * n + 1; i++) {
        for (int j = 0; j < 2 * n + 1; j++) {
            dp[i][j] = (j > 0 ? dp[i - 1][j - 1] : 0) + (j < 2 * n ? dp[i - 1][j + 1] : 0);
        }
    }

    int depth = 0;
    string s = "";

    for (int i = 0; i < 2 * n; ++i) {
        if (dp[2 * n - (i + 1)][depth + 1] >= k) {
            s += "(";
            depth++;
        } else {
            k -= dp[2 * n - (i + 1)][depth + 1];
            s += ")";
            depth--;
        }
    }
    cout << s;
    return 0;
}