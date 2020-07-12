//
// Createdepth by Anarsiel on 05/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>

using namespace std;

int main() {
    freopen("brackets2num2.in", "r", stdin);
    freopen("brackets2num2.out", "w", stdout);
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

    long long k = 0, depth = 0;
    vector<bool> v;

    for (long long i = 0; i < 2 * n; ++i) {
        if (s[i] == '(') {
            v.push_back(false);
            depth++;
        } else if (s[i] == '[') {
            if (v.size() > 0  && v[v.size() - 1] == 0)
                k += dp[2 * n - i - 1][depth - 1] * (1 << ((2 * n - i - 1 - (depth + 1) + 2) / 2));
            k += dp[2 * n - i - 1][depth + 1]* (1 << ((2 * n - i - 1 - (depth + 1)) / 2));
            v.push_back(true);
            depth++;
        } else if (s[i] == ']') {
            if (v.size() > 0 && v[v.size() - 1] == 0)
                k += dp[2 * n - i - 1][depth - 1] * (1 << ((2 * n - i - 1 - (depth + 1) + 2) / 2));
            k += dp[2 * n - i - 1][depth + 1] * (1 << ((2 * n - i - 1 - (depth + 1)) / 2));
            k += dp[2 * n - i - 1][depth + 1] * (1 << ((2 * n - i - 1 - (depth + 1)) / 2));
            v.pop_back();
            depth--;
        } else {
            k += dp[2 * n - i - 1][depth + 1] * (1 << ((2 * n - i - 1 - (depth + 1)) / 2));
            v.pop_back();
            depth--;
        }

    }
    cout << k;
    return 0;
}