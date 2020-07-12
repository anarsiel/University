//
// Created by Anarsiel on 04/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>

using namespace std;

long long factorial(long long x) {
    long long answ = 1;

    for (long long i = 2; i <= x; i++)
        answ *= i;

    return answ;
}

long long C_IZ_N_PO_KA(long long n, long long k) {
    return factorial(n) / (factorial(k) * factorial(n - k));
}

int main() {
    freopen("choose2num.in", "r", stdin);
    freopen("choose2num.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    long long n, k;
    cin >> n >> k;
    vector<vector<long long>> dp(n + 1, vector<long long>(n + 1));

    dp[0][1] = 1;
    for (long long i = 1; i <= n; i++) {
        for (long long j = 1; j <= k; j++)
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
    }

//    for (int i = 0; i < n + 1; i++) {
//        for (int j = 0; j < k + 1; j++) {
//            cout << dp[i][j] << " ";
//        }
//        cout << endl;
//    }
//
//    cout << endl;
//    cout << C_IZ_N_PO_KA(2, 1);
//    return 0;

    vector<long long> v(k + 2, 0);
    for (long long i = 1; i < k + 1; i++) {
        cin >> v[i];
    }

    long long answ = 0;
    for (long long i = 1; i <= k; i++) {
        for (long long j = v[i - 1] + 1; j < v[i]; j++) {
            answ += dp[n - j][k - i + 1];
        }
    }

    cout << answ;
    return 0;
}