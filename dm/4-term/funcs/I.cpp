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
#include <deque>

using namespace std;

const long long MODULE = 104857601;

long long REL(long long x){
    return MODULE - x;
}

long long MOD(long long x) {
    return x % MODULE;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    long long n, k;

    cin >> k >> n;
    --n;

    vector<long long> a(k << 1), c((k << 1) + 1);
    for (auto i = 0; i < k; ++i) {
        cin >> a[i];
    }

    for (auto i = 1; i < k + 1; ++i) {
        long long x;
        cin >> x;
        c[i] = MODULE - x;
    }

    vector<long long> v((k << 1) + 1, 0);
    for (c[0] = 1; n >= k; n /= 2, c = v) {
        v.assign((k << 1) + 1, 0);

        for (auto i = k; i < a.size(); ++i) {
            long long x = 0;

            for (auto j = 1; j < k + 1; ++j) {
                x += MOD(REL(c[j]) * a[i - j]);
                x = MOD(x);
            }

            a[i] = x;
        }

        for (auto i = 0; i < v.size(); i += 2) {
            long long x = 0;
            
            for (auto j = 0; j < i + 1; ++j) {
                long long kaef = ((j&1) == 0 ? c[j] : REL(c[j]));

                x += MOD(kaef * c[i - j]);
                x = MOD(x);
            }

            v[i >> 1] = x;
        }

        for (auto i = 0; i < k; ++i) {
            a[i] = a[(i << 1) + (n&1)];
        }
    }

    cout << a[n] << endl;
    return 0;
}
/*
3 5
1 2 3
4 5 6
 */