//
// Created by Anarsiel on 27/05/2020.
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

using namespace std;

long long mod = 239;

long long MOD(long long x) {
    return ((x % mod) + mod) % mod;
}

long long POW(long long x, long long pow) {
    if (pow == 0) {
        return 1;
    }

    if (pow % 2 == 1) {
        return POW(x, pow - 1) * x;
    }

    long long y = POW(x, pow / 2);
    return MOD(y * y);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    for (int i = 0; i < n; ++i) {
        int x;
        cin >> x;

    }
    return 0;
}