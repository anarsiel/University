#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

unsigned int a, b;
unsigned int cur = 0;
unsigned int nextRand() {
    cur = cur * a + b;
    return cur >> 8;
}

int main() {
    unsigned m, q;
    cin >> m >> q >> a >> b;
    unsigned answ = 0;

    unsigned n = (1 << 24) + 2;
    vector<unsigned> prefix_sum(n + 239, 0), suf(n + 239, 0);

    for (unsigned i = 0; i < m; ++i) {
        unsigned x = nextRand(), l = nextRand(), r = nextRand();
        if (l > r) swap(l, r);

        suf[l] += x;
        suf[r + 1] -= x;
    }

    prefix_sum[0] = suf[0];
    unsigned cur = suf[0];
    for (unsigned i = 1; i <= n; ++i) {
        cur += suf[i];
        prefix_sum[i] = prefix_sum[i - 1] + cur;
    }

    for (unsigned i = 0; i < q; ++i) {
        unsigned l = nextRand(), r = nextRand();
        if (l > r) swap(l, r);
        answ += prefix_sum[r] - (l == 0 ? 0 : prefix_sum[l - 1]);
    }
    cout << answ;
    return 0;
}
/*
5 5
13 239
 */
