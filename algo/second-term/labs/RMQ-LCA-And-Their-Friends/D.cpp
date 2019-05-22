//
// Created by Anarsiel on 2019-05-22.
//

// It is NOT RMQ, it is modified RVQ!

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdio>

using namespace std;

struct node {
    int mn, mx;

    node() {}

    node(int _mn, int _mx) : mn(_mn), mx(_mx) {}
};

vector<node> tree;
vector<int> a;
int sz;

int min(node a, node b) {
    return min(a.mn, b.mn);
}

int max(node a, node b) {
    return max(a.mx, b.mx);
}

void build() {
    int _n = static_cast<int>(a.size());
    sz = 1;
    while (sz < _n) {
        sz *= 2;
    }
    tree.resize(static_cast<unsigned long>(sz * 2));
    for (int i = 0; i < sz * 2; i++) {
        tree[i] = node(static_cast<int>(1e9), static_cast<int>(-1e9));
    }
    for (int i = sz; i < _n + sz; i++)
        tree[i] = node(a[i - sz], a[i - sz]);
    for (int i = sz - 1; i > 0; i--)
        tree[i] = node(min(tree[2 * i], tree[2 * i + 1]), max(tree[2 * i], tree[2 * i + 1]));
}

node get(int a, int b, int l, int r, int v) {
    if (b < l || a > r)
        return node(static_cast<int>(1e9), static_cast<int>(-1e9));
    if (l <= a && b <= r)
        return tree[v];
    int m = (a + b) / 2;
    node left = get(a, m, l, r, 2 * v);
    node right = get(m + 1, b, l, r, 2 * v + 1);
    return {min(left, right), max(left, right)};
}

void update(int index, int number) {
    tree[sz + index] = node(number, number);
    for (int i = (sz + index) / 2; i > 0; i /= 2) {
        tree[i] = node(min(tree[2 * i], tree[2 * i + 1]), max(tree[2 * i], tree[2 * i + 1]));
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        a.push_back(x);
    }
    build();

    string type;
    while (cin >> type) {
        int l, r;
        cin >> l >> r;
        if (type == "min") {
            node lol = get(sz, 2 * sz - 1, l + sz - 1, r + sz - 1, 1);
            cout << lol.mn << endl;
        } else {
            update(l - 1, r);
        }
    }
    return 0;
}
/*
5
1 2 3 4 5
min 2 5
min 1 5
min 1 4
min 2 4
set 1 10
set 2 3
set 5 2
min 2 5
min 1 5
min 1 4
min 2 4
 */
