//
// Created by Anarsiel on 27/05/2020.
//

#include <iostream>

using namespace std;

long long mod = 239LL;

long long MOD(long long x) {
    return x % mod;
}

long long POW(long long x, long long pow) {
    if (pow == 0LL) {
        return 1LL;
    }

    if (pow % 2LL == 1LL) {
        return 1LL * POW(x, pow - 1LL) * x;
    }

    long long y = POW(x, pow / 2LL);
    return MOD(1LL * y * y);
}

long long cnt_pr(long long x) {
    long long x_copy = x;
    for (long long i = 2; 1LL * i * i <= x_copy; ++i) {
        bool was = false;
        while (x_copy % i == 0LL) {
            x_copy /= i;
            was = true;
        }

        x -= was * 1LL * x / i;
    }

    if (x_copy > 1LL) {
        return 1LL * x - 1LL * x / x_copy;
    }
    return x;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    long long a, b, n, m;
    cin >> a >> b >> n >> m;

    mod = n;
    long long x = 1LL * a * m * POW(m, cnt_pr(n) - 1LL);

    mod = m;
    long long y = 1LL * b * n * POW(n, cnt_pr(m) - 1LL);

    cout << (0LL + x + y) % (1LL * n * m) << endl;
    return 0;
}