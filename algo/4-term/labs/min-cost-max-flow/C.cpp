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
#include <vector>

using namespace std;

const int INF = 1e10 + 239;

void f(long long &a, long long &b) {
    long long c = min(a, b);
    a -= c;
    b -= c;
}

long long A(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k, k2);
    f(n, n2);
    f(b, b2);

    f(k2, n);
    f(n2, b);
    f(b2, k);

    return k + n + b;
}

long long B(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k2, n);
    f(n2, b);
    f(b2, k);

    f(k, k2);
    f(n, n2);
    f(b, b2);
    return k + n + b;
}

long long C(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k2, n);
    f(n, n2);
    f(b, b2);

    f(k, k2);
    f(n2, b);
    f(b2, k);

    return k + n + b;
}

long long D(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k, k2);
    f(n2, b);
    f(b, b2);

    f(k2, n);
    f(n, n2);
    f(b2, k);

    return k + n + b;
}

long long E(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k, k2);
    f(n, n2);
    f(b2, k);

    f(k2, n);
    f(n2, b);
    f(b, b2);

    return k + n + b;
}

long long F(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k2, n);
    f(n2, b);
    f(b, b2);

    f(k, k2);
    f(n, n2);
    f(b2, k);

    return k + n + b;
}

long long G(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k2, n);
    f(n, n2);
    f(b2, k);

    f(k, k2);
    f(n2, b);
    f(b, b2);

    return k + n + b;
}

long long H(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k, k2);
    f(n2, b);
    f(b2, k);

    f(k2, n);
    f(n, n2);
    f(b, b2);

    return k + n + b;
}

long long K(long long k, long long n, long long b, long long k2, long long n2, long long b2) {
    f(k, k2);
    f(n, n2);
    f(b, b2);

    f(k2, n);
    f(n2, b);
    f(b2, k);

    return k + n + b;
}


long long minnn(vector<long long> v) {
    long long answ = INF;
    for (auto x : v) {
        answ = min(x, answ);
    }
    return answ;
}

int main() {
    long long k, n, b, k2, n2, b2;
    cin >> k >> n >> b >> k2 >> n2 >> b2;

    vector<long long > v = {A(k, n, b, k2, n2, b2),
                            B(k, n, b, k2, n2, b2),
                            C(k, n, b, k2, n2, b2),
                            D(k, n, b, k2, n2, b2),
                            E(k, n, b, k2, n2, b2),
                            F(k, n, b, k2, n2, b2),
                            G(k, n, b, k2, n2, b2),
                            K(k, n, b, k2, n2, b2)};

    cout << minnn(v);
}

/*
3 0 0
0 3 0
 */