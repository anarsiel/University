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
    freopen("num2choose.in", "r", stdin);
    freopen("num2choose.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    long long n, k, m;
    cin >> n >> k >> m;
    vector<vector<int>> dp(n + 1, vector<int> (n + 1));
    vector<int> answ;

    dp[0][1] = 1;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= k; j++)
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
    }

    int number = 1;
    while (k > 0) {
        if (m < (dp[n - 1][k])) {
            answ.push_back(number);
            k--;
            number++;
            n--;
        } else {
            m -= (dp[n - 1][k]);
            n--;
            number++;
        }
    }
    for (int x : answ)
        cout << x << ' ';
    return 0;
}