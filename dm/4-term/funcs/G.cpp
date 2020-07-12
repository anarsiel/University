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

const long long MODULE = 998244353;


long long MOD(long long x) {
    return x % MODULE;
}

long long REL(long long x){
    return MOD(MODULE - x);
}

int main() {
    std::ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie();

    long long n, k;
    vector<vector<long long>> vv;

    cin >> k >> n;
    vector<long long> d(n, 1);
    vv.resize(k, vector<long long>(k, 0));
    
    for (auto i = 0; i < k; ++i) {
        vv[i][0] = 1;
    }

    for (auto i = 0; i < k; ++i) {
        for (auto j = 0; j < i; ++j) {
            vv[i][j + 1] = MOD(vv[i - 1][j] + vv[i - 1][j + 1]);
        }
    }

    vector<long long> v((k + 1) >> 1);
    for (auto i = 0; i < v.size(); ++i) {
        v[i] = (i & 1) ? vv[k - 1 - i][i] : REL(vv[k - 1 - i][i]);
    }

    for (auto i = 1; i < k - 1; ++i) {
        int x = 0;
        for (auto j = 0; j < i; ++j) {
            x += MOD(d[j] * d[i - 1 - j]);
            x = MOD(x);
        }

        d[i] = x;
    }

    for (auto i = k - 1; i < n; ++i) {
        int x = 0;
        for (auto j = 1; j < ((k + 1) >> 1); ++j) {
            x += MOD(v[j] * d[i - j]);
            x = MOD(x);
        }
        
        d[i] = x;
    }

    for (auto dd : d) {
        cout << dd << ' ';
    }
    cout << endl;
    return 0;
}
/*
4 5

7 6
 */