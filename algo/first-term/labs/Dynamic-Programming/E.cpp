//
// Created by Anarsiel on 19/12/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

vector<vector<int>> dp;

const int INF = 1e9 + 239;

int get(int i, int j) {
    return dp[i][j];
}

int main() {
    freopen("levenshtein.in", "r", stdin);
    freopen("levenshtein.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    string s, f;
    cin >> s >> f;

    int n = (int) s.size();
    int m = (int) f.size();
    dp.resize(n + 1, vector<int> (m + 1, INF));

    for (int i = 0; i <= n; i++) {
        dp[i][0] = i;
    }

    for (int i = 0; i <= m; i++) {
        dp[0][i] = i;
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s[i - 1] == f[j - 1]) {
                dp[i][j] = min(get(i - 1, j - 1), dp[i][j]);
            }

            dp[i][j] = min(get(i - 1, j - 1) + 1,
                    min(get(i - 1, j) + 1, min(get(i, j - 1) + 1, dp[i][j])));
        }
    }

//    for (int i = 0; i < n; i++) {
//        for (int j = 0; j < m; j++)
//            cout << dp[i][j] << ' ';
//        cout << endl;
//    }

    cout << dp[n][m];
    return 0;
}
/*
4 6
1 2 20 1 3 42 1 4 35 2 3 30 2 4 34 3 4 12

 4 3 1 2 1  1 3 1 1 4 1
 */
