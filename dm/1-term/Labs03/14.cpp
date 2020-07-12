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
    freopen("perm2num.in", "r", stdin);
    freopen("perm2num.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    long long n, k;
    cin >> n;

    vector<long long> cnt = {0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880,
                             3628800, 39916800, 479001600, 6227020800,
                             87178291200, 1307674368000, 20922789888000, 355687428096000, 6402373705728000};
    vector<long long> v(n + 1);
    vector<bool> booll(n + 1, false);
    for (int i = 1; i < n + 1; i++)
        cin >> v[i];

    long long num = 0LL;

    for (int i = 1; i < n; i++) {
        for (int j = 1; j < v[i]; j++) {
            if (!booll[j]) {
                num += cnt[n - i];
            }
        }
        booll[v[i]] = true;
    }

    cout << num;
    return 0;
}