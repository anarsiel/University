//
// Created by Anarsiel on 2019-05-23.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdio>

using namespace std;

int r;

struct Matrix {
    int a = 0, b = 0, c = 0, d = 0;

    Matrix() {}

    Matrix(int a, int b, int c, int d) : a(a), b(b), c(c), d(d) {}
};

Matrix operator*(Matrix const &a, Matrix const &b) {
    Matrix c = Matrix();
    c.a = a.a * b.a + a.b * b.c;
    c.a %= r;

    c.b = a.a * b.b + a.b * b.d;
    c.b %= r;

    c.c = a.c * b.a + a.d * b.c;
    c.c %= r;

    c.d = a.c * b.b + a.d * b.d;
    c.d %= r;
    return c;
}

struct Node {
    Matrix mul;

    Node() {}

    Node(Matrix mul) : mul(std::move(mul)) {}
};

vector<Node> tree;
vector<Matrix> a;
int sz;

Matrix E = Matrix(1, 0, 0, 1);

void build() {
    int _n = static_cast<int>(a.size());
    sz = 1;
    while (sz < _n) {
        sz *= 2;
    }
    tree.resize(sz * 2);
    for (int i = _n + sz; i < int(tree.size()); i++) {
        tree[i] = Node(E);
    }
    for (int i = sz; i < _n + sz; i++)
        tree[i] = Node(a[i - sz]);
    for (int i = sz - 1; i > 0; i--)
        tree[i] = Node(tree[2 * i].mul * tree[2 * i + 1].mul);
}

Node get(int a, int b, int l, int r, int v) {
    if (l <= a && b <= r)
        return tree[v];

    int m = (a + b) / 2;
    Node left = (a > r || m < l ? Node(E) : get(a, m, l, r, 2 * v));
    Node right = (m + 1 > r || b < l ? Node(E) : get(m + 1, b, l, r, 2 * v + 1));
    return Node(left.mul * right.mul);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, m;
    cin >> r >> n >> m;

    a.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i].a;
        cin >> a[i].b;
        cin >> a[i].c;
        cin >> a[i].d;
    }
    build();

    for (int i = 0; i < m; ++i) {
        int l, r;
        cin >> l >> r;
        Node lol = get(sz, 2 * sz - 1, l + sz - 1, r + sz - 1, 1);

        cout << lol.mul.a << ' ' << lol.mul.b << '\n'
        << lol.mul.c << ' ' << lol.mul.d << '\n' << '\n';
        cout << '\n';
    }
    return 0;
}
/*
3 4 4
0 1
0 0

2 1
1 2

0 0
0 2

1 0
0 2

1 4
2 3
1 3
2 2

-------------

3 0 4

1 4
2 3
1 3
2 2
 */
