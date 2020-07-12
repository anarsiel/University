//
// Created by Anarsiel on 2019-05-22.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdio>

using namespace std;

struct node {
    long long sum;

    node() {}

    node(long long sum) : sum(sum) {}
};

vector<node> tree;
vector<long long> a;
long long sz;

void build() {
    long long _n = static_cast<long long>(a.size());
    sz = 1;
    while (sz < _n) {
        sz *= 2;
    }
    tree.resize(static_cast<unsigned long>(sz * 2));
    for (long long i = 0; i < sz * 2; i++) {
        tree[i] = node(0);
    }
    for (long long i = sz; i < _n + sz; i++)
        tree[i] = node(a[i - sz]);
    for (long long i = sz - 1; i > 0; i--)
        tree[i] = node(tree[2 * i].sum + tree[2 * i + 1].sum);
}

node get(long long a, long long b, long long l, long long r, long long v) {
    if (b < l || a > r)
        return node(0);
    if (l <= a && b <= r)
        return tree[v];
    long long m = (a + b) / 2;
    node left = get(a, m, l, r, 2 * v);
    node right = get(m + 1, b, l, r, 2 * v + 1);
    return node(left.sum + right.sum);
}

void update(long long index, long long number) {
    tree[sz + index] = node(number);
    for (long long i = (sz + index) / 2; i > 0; i /= 2) {
        tree[i] = node(tree[2 * i].sum + tree[2 * i + 1].sum);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    long long n;
    cin >> n;
    for (long long i = 0; i < n; i++) {
        long long x;
        cin >> x;
        a.push_back(x);
    }
    build();

    string type;
    while (cin >> type) {
        long long l, r;
        cin >> l >> r;
        if (type == "sum") {
            node lol = get(sz, 2 * sz - 1, l + sz - 1, r + sz - 1, 1);
            cout << lol.sum << endl;
        } else {
            update(l - 1, r);
        }
    }
    return 0;
}
/*
5
1 2 3 4 5
sum 2 5
sum 1 5
sum 1 4
sum 2 4
set 1 10
set 2 3
set 5 2
sum 2 5
sum 1 5
sum 1 4
sum 2 4
 */
