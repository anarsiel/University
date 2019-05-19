//
// Created by Anarsiel on 2019-04-04.
//

#include <iostream>
#include <vector>
#include <math.h>

typedef long double ld;

using namespace std;

vector<vector<ld>> square(const vector<vector<ld>> &v) {
    size_t n = v.size();

    vector<vector<ld>> answ(n, vector<ld> (n, 0));

    for (size_t i = 0; i < n; ++i) {
        for (size_t j = 0; j < n; ++j) {
            for (size_t k = 0; k < n; ++k) {
                answ[i][j] += v[i][k] * v[k][j];
            }
        }
    }

    return answ;
}

const ld eps = 0.00000001;

bool eq(const vector<vector<ld>> &a) {
    size_t n = a.size();
    for (size_t i = 0; i < n; ++i) {
        ld mn = a[0][i];
        ld mx = a[0][i];

        for (size_t j = 0; j < n; ++j) {
            mn = min(mn, a[j][i]);
            mx = max(mx, a[j][i]);
        }

        if (abs(mx - mn) > eps)
            return false;
    }

    return true;
}

std::string file_name = "markchain";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();
    cout.precision(20);

    size_t n;
    cin >> n;

    vector<vector<ld>> v(n, vector<ld> (n));
    for (size_t i = 0; i < n; ++i)
        for (size_t j = 0; j < n; ++j)
            cin >> v[i][j];

    for (;;) {
        vector<vector<ld>> v_squared = square(v);

        if (eq(v_squared)) break;

        v = v_squared;
    }

    for (size_t i = 0; i < n; ++i)
        cout << v[0][i] << '\n';
    return 0;
}
/*

*/