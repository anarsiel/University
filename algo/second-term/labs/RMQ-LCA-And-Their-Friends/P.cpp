//
// Created by Anarsiel on 2019-06-03.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

static const int MX_SZ = static_cast<const int>(3e5 + 239);

typedef long long ll;

vector<vector<int>> v(MX_SZ);
vector<bool> used;
vector<ll> tree, diff;
int n, m, ind = -1, start = 0;
vector<int> p, one_elem, depths, sizes, index_in_tree;

int get_sz(int u) {
    int result = 0;

    for (auto v : v[u]) {
        if (p[u] != v) {
            result += get_sz(v) + 1;
        }
    }
    return (sizes[u] = result + 1);
}

void d_s_v(int u) {
    if (used[u]) {
        return;
    }
    used[u] = true;
    for (auto v : v[u]) {
        if (!used[v]) {
            p[v] = u;
            depths[v] = depths[u] + 1;
            d_s_v(v);
        }
    }
}

void push(int v, int len) {
    if (len != 1) {
        diff[2 * v] += diff[v];
        diff[2 * v + 1] += diff[v];
    }

    tree[v] += diff[v];
    diff[v] = 0;
}

void build(int vertex, int start) {
    index_in_tree[vertex] = ++ind;
    one_elem[vertex] = start;

    int mx_sz = -1, mx_ind = 0;
    for (auto urtex : v[vertex]) {
        if (mx_sz < sizes[urtex] && p[vertex] != urtex) {
            mx_sz = sizes[urtex];
            mx_ind = urtex;
        }
    }

    if (mx_sz != -1) {
        build(mx_ind, start);
    }

    for (auto v : v[vertex]) {
        if (p[vertex] != v && mx_ind != v) {
            build(v, v);
        }
    }
}

ll get(int v, int l, int r, int L, int R) {
    push(v, r - l + 1);
    if (l >= L && r <= R) return tree[v];
    if (r < L || l > R) return 0;

    int m = (l + r) / 2;
    return get(2 * v, l, m, L, R)
            + get(2 * v + 1, m + 1, r, L, R);
}

void add(int v, int l, int r, int L, int R, ll d) {
    push(v, r - l + 1);
    if (L <= l && r <= R) {
        diff[v] += d;
        push(v, r - l + 1);
        return;
    }
    if (R < l || r < L) {
        return;
    }

    int m = (l + r) / 2;
    add(2 * v, l, m, L, R, d);
    add(2 * v + 1, m + 1, r, L, R, d);
}

void add_to_path(int l, int r, ll d) {
    while (one_elem[l] != one_elem[r]) {
        if (depths[one_elem[l]] < depths[one_elem[r]]) {
            swap(l, r);
        }
        add(1, 0, MX_SZ - 1, index_in_tree[one_elem[l]], index_in_tree[l], d);
        l = p[one_elem[l]];
    }

    if (depths[l] > depths[r]) swap(l, r);

    add(1, 0, MX_SZ - 1, index_in_tree[l], index_in_tree[r], d);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    used.assign(MX_SZ, false);
    p.assign(MX_SZ, 0);
    depths.assign(MX_SZ, 0);
    sizes.assign(MX_SZ, 0);
    one_elem.assign(MX_SZ, 0);
    index_in_tree.assign(MX_SZ, 0);
    tree.assign(4 * MX_SZ + 239, 0LL);
    diff.assign(4 * MX_SZ + 239, 0LL);

    cin >> n;

    for (int i = 0; i < n - 1; i++) {
        int a, b;
        cin >> a >> b;
        --a, --b;

        v[a].push_back(b);
        v[b].push_back(a);
    }

    d_s_v(start);
    get_sz(start);
    build(start, start);

    cin >> m;
    for (int i = 0; i < m; i++) {
        string c;
        cin >> c;

        if (c == "+") {
            int a, b;
            ll d;
            cin >> a >> b >> d;
            add_to_path(a - 1, b - 1, d);
            continue;
        }

        int a;
        cin >> a;
        --a;
        cout << get(1, 0, MX_SZ - 1, index_in_tree[a], index_in_tree[a]) << '\n';
    }

    return 0;
}
/*
5
1 2
1 3
3 4
3 5
5
+ 2 5 1
? 3
+ 1 1 2
? 1
? 3
 */
