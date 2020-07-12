//
// Created by Anarsiel on 16/11/2018.
//

#include <iostream>

typedef long long ll;

using namespace std;

ll n;
ll sdvig;

ll MOD(ll x) {
    return (x % n + n) % n;
}

int cnt = 0;
ll ask(ll index) {
    if (cnt == 10) exit(1);
    cout << "? " << index + 1 << endl;
    ll value;
    cin >> value;
    cnt++;

    return value;
}

void finish(ll index) {
//    if (cnt == 10) exit(1);
    cout << "! " << index + 1 << endl;
}

int main() {
    ll x, m;
    cin >> x >> m;
    n = (ll)1e18 - m;
//    cout << n << endl;

    sdvig = ask(0) - 1;  // сдвиг назад

    x -= sdvig;
    if (x < 1) x += (ll)1e18;

    ll l = max(x - m - 2, -1LL), r = min(x + 2, n);

    while (l != r - 1) {
        ll medium = (l + r) / 2;
        ll medium2 = medium;
        medium = MOD(medium);

        ll value = ask(medium) - sdvig;
        if (value < 1) value += (ll)1e18;

        if (value > x) {
            r = medium2;
        } else if (value < x) {
            l = medium2;
        } else {
            finish(medium2);
            return 0;
        }
    }
    finish(-2);
    return 0;
}
