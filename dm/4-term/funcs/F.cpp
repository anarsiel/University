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

const long long MODULE = 1e9 + 7;

long long MOD(long long x) {
    return ((x % MODULE) + MODULE) % MODULE;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    long long k, m;
    cin >> k >> m;
    vector<long long> c(k);
    for (auto i = 0; i < k; ++i){
        cin >> c[i];
    }
    
    vector<long long> v(m);
    deque<long long> d(m + 1);

    v[0] = d[0] = 1;
    for (auto i = 1; i < m + 1; ++i) {
        long long x = 0;
        for (auto it : c) {
            if (i - it >= 0) {
                x = MOD(x + v[i - it]);
            }
        }

        d[i] = x;
        for (auto j = 0; j < i + 1; ++j) {
            long long jw = j + i;
            if (jw >= m) continue;

            for (long long h = 0; h < (j != i) + 1; ++h) {
                v[jw] = MOD(v[jw] + MOD(x * d[j]));
            }
        }
    }

    d.pop_front();
    for (auto dd : d)
        cout << dd << ' ';
    return 0;
}
/*
2 5
1 3

1 10
2
 */