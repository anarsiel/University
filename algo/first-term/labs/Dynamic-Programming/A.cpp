#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

long long n;
vector<vector<long long>> dp;
vector<long long> v;
vector<vector<long long>> ind;

const long long INF = 1e18 + 239;

long long f(long long l, long long r) {
//    cout << l << ' ' << r << endl;
    if (ind[l][r] == -1) {
        if (l == r - 1) {
            dp[l][r] = 0;
            ind[l][r] = r;
        } else {
            dp[l][r] = INF;
            for (long long i = l + 1; i < r; i++) {
                f(l, i);
                f(i, r);
                long long x = v[l] * v[i] * v[r] + dp[l][i] + dp[i][r];

                if (x < dp[l][r]) {
                    dp[l][r] = x;
                    ind[l][r] = i;
                }
            }
        }
    }

    return ind[l][r];
}

string ans(long long l, long long r) {
    if (r == l)
        return "";

    if (r - l == 1)
        return "A";

    long long index = f(l, r);

    return "(" + ans(l, index) + ans(index, r) + ")";
}

int main() {
    freopen("matrix.in", "r", stdin);
    freopen("matrix.out", "w", stdout);

    cin >> n;
    dp.resize(n + 1, vector<long long> (n + 1, -1));
    ind.resize(n + 1, vector<long long> (n + 1, -1));
    v.resize(n + 1);

    cin >> v[0] >> v[1];
    for (long long i = 0; i < n - 1; i++) {
        long long a;
        cin >> a >> v[i + 2];
    }

    cout << ans(0, n) << endl;
//    cout << dp[0][n];

//    for (long long i = 0; i <= n; i++) {
//        for (long long j = 0; j <= n; j++) {
//            cout << dp[i][j] << ' ';
//        }
//
//        cout << endl;
//    }
    return 0;
}
/*
5
1 2
2 3
3 4
4 5
5 6
((((AA)A)A)A)

10
20 40
40 300
300 150
150 70
70 12
12 356
356 43
43 228
228 1488
1488 36
((A(A(A(AA))))((((AA)A)A)A))
(((((AA)A)A)A)((((AA)A)A)A))

5
20 40
40 300
300 150
150 70
70 12
(A(A(A(AA))))

((((AA)A)A)A)
16799
 */
