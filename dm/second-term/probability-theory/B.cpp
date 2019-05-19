//
// Created by Anarsiel on 2019-04-01.
//

#include <iostream>
#include <vector>

typedef long double ld;

using namespace std;

std::string file_name = "shooter";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();
    cout.precision(20);

    int n, m, k;
    cin >> n >> m >> k;
    k--;

    ld ours = 0;
    ld all = 0;

    for (int i = 0; i < n; ++i) {
        ld x;
        cin >> x;

        ld cur = 1;
        for (int j = 0; j < m; ++j) {
            cur *= (1 - x);
        }

        if (i == k) {
            ours = cur;
        }

        all += cur;
    }
    cout << ours / all;
    return 0;
}
