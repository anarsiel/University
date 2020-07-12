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
#include <vector>

using namespace std;

const long long MODULE = 998244353;

long long MOD(long long x) {
    return ((x % MODULE) + MODULE) % MODULE;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    long long n, m;
    cin >> n >> m;
    vector<long long> p(1001, 0), q(1001, 0);
    for (auto i = 0; i <= n; ++i)
        cin >> p[i];
    for (auto i = 0; i <= m; ++i)
        cin >> q[i];

    cout << max(n, m) << endl;
    for (auto i = 0; i < max(n, m) + 1; ++i) {
        cout << (MOD(p[i] + q[i])) << ' ';
    }
    cout << endl << n + m << endl;

    for (auto i = 0; i < n + m + 1; ++i) {
        long long x = 0;
        for (auto j = 0; j < i + 1; ++j) {
            if (j >= 1001 || i - j >= 1001)
                continue;

            x = MOD(x + MOD(p[j] * q[i - j]));
        }

        cout << x << ' ';
    }
    cout << endl;

    vector<long long> v;
    for (auto i = 0; i < 1000; ++i) {
        long long x = 0;
        for (auto j = 0; j < i; ++j) {
            x = MOD(x + v[j] * q[i - j]);
        }

        v.push_back(MOD(p[i] - x));
    }

    for (auto vv : v)
        cout << vv << ' ';
    return 0;
}
/*
3 2
0 1 2 3
1 2 3

1 3
1 2
1 4 5 2
 */