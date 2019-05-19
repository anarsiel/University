//
// Created by Anarsiel on 09/11/2018.
//

#include <iostream>
#include <vector>
#include <algorithm>

#define ll long long

using namespace std;

vector<ll> sort(vector<ll> &v) {
    ll n = (ll)(v.size());
    if (v.size() == 1)
        return v;

    vector<ll> left(n / 2);
    vector<ll> right(n / 2 + n % 2);

    for (ll i = 0; i < n / 2; i++) {
        left[i] = v[i];
    }

    for (ll i = 0; i < n / 2 + n % 2; i++) {
        right[i] = v[n / 2 + i];
    }

    sort(left);
    sort(right);

    ll indexL = 0, indexR = 0;

    for (ll i = 0; i < n; i++) {
        if (indexL == (ll)(left.size())) {
            v[i] = right[indexR++];
        } else if (indexR == (ll)(right.size())) {
            v[i] = left[indexL++];
        } else {
            if (left[indexL] <= right[indexR]) {
                v[i] = left[indexL++];
            } else {
                v[i] = right[indexR++];
            }
        }
    }
    return v;
}

vector<ll> a, b, t;
ll n, m, p;

ll howManyCookiesAvailable(ll kassa, ll timer) {
    if (a[kassa] == 0) {
        if (timer - t[kassa] - b[kassa] >= 0) {
            return 1000000;
        }
        return -1;
    } else {
        return (timer - t[kassa] - b[kassa]) / a[kassa];
    }
}

ll howManyCookiesAvailableAllInAll(ll timer) {
    vector<ll> cookies;

    for (ll i = 0; i < m; i++) {
        ll x = howManyCookiesAvailable(i, timer);
        if (x != -1)
            cookies.push_back(x);
    }

    sort(cookies);
    reverse(cookies.begin(), cookies.end());

    ll answ = 0;
    for (ll i = 0; i < min(n, (ll)(cookies.size())); i++) {
        answ += max(0LL, cookies[i]);
    }

    return answ;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("supermarket.in", "r", stdin);
    freopen("supermarket.out", "w", stdout);

    cin >> m;
    a.resize(m);
    b.resize(m);
    t.resize(m);

    for (ll i = 0; i < m; i++)
        cin >> a[i] >> b[i] >> t[i];

    cin >> n >> p;

    if (p == 0) {
        cout << 0;
        return 0;
    }

    ll l = -1;
    ll r = (ll)(2e10);

    while (l != r - 1) {
        ll m = (l + r) / 2;

        if (howManyCookiesAvailableAllInAll(m) >= p) {
            r = m;
        } else {
            l = m;
        }
    }
    cout << r;
    return 0;
}
/*
2
100 10 40
10 100 50
2 2

3
1 2 0
5 2 1
2 10 1
3 5
*/
