//
// Created by Anarsiel on 18/12/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

const int INF = 1e9 + 239;
vector<vector<int>> dp;
vector<int> m;
vector<int> answ;

void getAnsw(int k, int s) {
    if (dp[k][s] == 0)
        return;

    if (dp[k - 1][s] == dp[k][s]) {
        getAnsw(k - 1, s);
    } else {
        getAnsw(k - 1, s - m[k]);
        answ.push_back(k);
    }
}

int main() {
    freopen("knapsack.in", "r", stdin);
    freopen("knapsack.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n, mMax;
    cin >> n >> mMax;

    vector<int> c(n + 1);
    m.resize(n + 1);

    for (int i = 1; i <= n; i++) {
        cin >> m[i];
    }

    for (int i = 1; i <= n; i++) {
        cin >> c[i];
    }

    dp.resize(n + 1, vector<int>(mMax + 1, -INF));

    for (int i = 0; i <= n; i++)
        dp[i][0] = 0;
    for (int i = 0; i <= mMax; i++)
        dp[0][i] = 0;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= mMax; j++) {
            if (j >= m[i]) {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - m[i]] + c[i]);
            } else {
                dp[i][j] = dp[i - 1][j];
            }
        }
    }

    getAnsw(n, mMax);

    cout << answ.size() << endl;

    for (int i = 0; i < int(answ.size()); i++) {
        cout << answ[i] << ' ';
    }
}
