//
// Created by Anarsiel on 24/12/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

int mod = 999999937;

int MOD(int x) {
    return (x % mod + mod) % mod;
}

void multiply(vector<vector<int>> &a, vector<vector<int>> &b, vector<vector<int>> &result) {
    result.resize(a.size(), vector<int> (b[0].size()));

    for (int i = 0; i < result.size(); i++) {
        for (int j = 0; j < result[0].size(); j++) {
            for (int k = 0; k < b.size(); k++) {
                result[i][j] += MOD(a[i][k] * b[k][j]);
                result[i][j] = MOD(result[i][j]);
            }
        }
    }
}

void powMatrix(vector<vector<int>> &a, int n, vector<vector<int>> &res) {
    cout << n << endl;
    res = a;

//    if (n == 0) {
//        for (int i = 0; i < res.size(); i++) {
//            for (int j = 0; j < res[0].size(); j++) {
//                res[i][j] = (i == j);
//            }
//        }
//
//        return;
//    }

    if (n == 1) {
        return;
    }

    if (n % 2 == 0) {
        vector<vector<int>> res1;
        powMatrix(a, n / 2, res1);

        multiply(res1, res1, res);
    } else {
        vector<vector<int>> res1;

        powMatrix(a, n - 1, res1);
        multiply(res1, a, res);
    }
}

int main() {
    vector<vector<int>> dp(5, vector<int> (5)), x(5, vector<int> (1, 1));

    dp[0] = {1, 1, 1, 1, 1};
    dp[1] = {1, 1, 1, 1, 1};
    dp[2] = {1, 1, 1, 1, 1};
    dp[3] = {1, 1, 0, 1, 0};
    dp[4] = {1, 1, 0, 1, 0};

    vector<vector<int>> res;

    powMatrix(dp, 3, res);
    for (int i = 0; i < res.size(); i++) {
        for (int j = 0; j < res[0].size(); j++) {
            cout << res[i][j] << ' ';
        }
        cout << endl;
    }

    return 0;

    int N;
    while (cin >> N) {
        vector<vector<int>> res1;
        powMatrix(dp, N, res1);

        vector<vector<int>> res2;
        multiply(res1, x, res2);

        int answ = 0;
        for (int i = 0; i < N; i++) {
            answ += res2[0][i];
        }
        cout << answ;
    }
}
