//
// Created by Anarsiel on 26/04/2020.
//

#pragma GCC optimize("Ofast,no-stack-protector")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx,avx,avx2,tune=native")
#pragma GCC optimize("unroll-loops")
#pragma GCC optimize("fast-math")
#pragma GCC optimize("section-anchors")
#pragma GCC optimize("profile-values,profile-reorder-functions,tracer")
#pragma GCC optimize("vpt")
#pragma GCC optimize("rename-registers")
#pragma GCC optimize("move-loop-invariants")
#pragma GCC optimize("unswitch-loops")
#pragma GCC optimize("function-sections")
#pragma GCC optimize("data-sections")
#pragma GCC optimize("branch-target-load-optimize")
#pragma GCC optimize("branch-target-load-optimize2")
#pragma GCC optimize("btr-bb-exclusive")

#include <iostream>
#include <algorithm>
#include <deque>

using namespace std;

long long rm = 499122177;
const long long MODULE = 998244353;
long long MOD(long long x) {
    return ((x % MODULE) + MODULE) % MODULE;
}

long long n, m;
deque<long long> p;

long long gcd(long long a, long long b, long long &x, long long &y) {
    if (a == 0) {
        x = 0, y = 1;
        return b;
    }

    long long _x, _y1;
    long long d = gcd(b%a, a, _x, _y1);
    x = _y1 - (b / a) * _x;
    y = _x;
    return d;
}

long long traverse(long long a) {
    long long x, y;

    if (gcd(a, MODULE, x, y) == 1)
        return x;
    return 0;
}

deque<long long> vector_multyply(const deque<long long> &v) {
    deque<long long> vec_step(v.size() + n);

    for (auto i = 0; i < v.size(); ++i) {
        if (v[i] == 0) continue;

        for (auto j = 0; j < n + 1; ++j) {
            if (i + j >= m) break;

            vec_step[i + j] += MOD(v[i] * p[j]);
            vec_step[i + j] = MOD(vec_step[i + j]);
        }
    }

    return vec_step;
}

void cnt_s() {
    deque<long long> d(10239, 0), A_tmp(p);
    d[0] = 1;

    if (m > 1) {
        for (auto j = 0; j < min(A_tmp.size(), d.size()); ++j) {
            d[j] = MOD(d[j] + A_tmp[j] * rm);
        }

        A_tmp = vector_multyply(A_tmp);
    }
    
    for (auto i = 0; i < m - 2; ++i) {
        int tmp = MOD(MOD(rm * MOD(MOD(traverse((i + 2) << 1)) * (((i + 2) << 1) - 3))));
        rm = tmp;

        if (!(i&1))
            tmp = MOD(-tmp);

        for (auto j = 0; j < min(A_tmp.size(), d.size()); ++j) {
            d[j] += MOD(A_tmp[j] * tmp);
            d[j] = MOD(d[j]);
        }

        A_tmp = vector_multyply(A_tmp);
    }

    for (auto i = 0; i < m; ++i) {
        cout << d[i] << ' ';
    }
    cout << endl;
}

void cnt_e() {
    deque<long long> d(p);
    deque<long long> dd(10239LL, 0LL);
    dd[0] = 1ll;

    long long kaef = 1LL;

    for (auto i = 1; i < m; ++i) {
        kaef = MOD(traverse(i) * kaef);

        for (auto j = 0; j < min(d.size(), dd.size()); ++j) {
            dd[j] += MOD(d[j] * kaef);
            dd[j] = MOD(dd[j]);
        }

        d = vector_multyply(d);
    }

    for (auto i = 0; i < m; ++i) {
        cout << dd[i] << ' ';
    }
    cout << endl;
}

void lnr() {
    deque<long long> d(10239, 0);
    deque<long long> a_x(p);
    long long rm = 1LL;

    if (m > 1) {
        for (auto j = 0; j < min(a_x.size(), d.size()); ++j) {
            d[j] += MOD(a_x[j] * rm);
            d[j] = MOD(d[j]);
        }

        a_x = vector_multyply(a_x);
    }

    for (auto i = 0; i < m - 2; ++i) {
        long long as = traverse(i + 2);
        rm = ( (i&1) ? MOD(as) : MOD(-as));

        for (auto j = 0; j < a_x.size(); ++j) {
            d[j] += MOD(a_x[j] * rm);
            d[j] = MOD(d[j]);
        }

        a_x = vector_multyply(a_x);
    }

    for (auto i = 0; i < m; ++i) {
        cout << d[i] << ' ';
    }
    cout << endl;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    cin >> n >> m;
    p.assign(n + 1, 0);
    for (auto i = 0; i < n + 1; ++i) {
        cin >> p[i];
    }

    cnt_s();
    cnt_e();
    lnr();
    return 0;
}

/*
1 4
0 1
 */