#include <iostream>

using namespace std;

struct Node {
    int x, y, cnt;

    Node *l, *r;

    Node() {
        x = 0;
        y = -1;
        l = NULL;
        r = NULL;
        cnt = 1;
    }

    Node(int _x) {
        x = _x;
        y = rand();
        l = NULL;
        r = NULL;
        cnt = 1;
    }
};

typedef Node *pNode;

void print(pNode t) {
    if (t == NULL)
        return;
    print(t->l);
    cout << t->x << " ";
    print(t->r);
}

int cnt(pNode t) {
    if (!t)
        return 0;

    return t->cnt;
}

void recalc(pNode t) {
    t->cnt = cnt(t->l) + 1 + cnt(t->r);
}

void Split(pNode t, pNode &l, pNode &r, int k) {
    if (t == NULL) {
        l = NULL;
        r = NULL;
        return;
    }

    if (cnt(t->l) >= k) {
        pNode a, b;
        Split(t->l, a, b, k);

        t->l = b;
        recalc(t);
        l = a;
        r = t;
    } else {
        pNode a, b;
        Split(t->r, a, b, k - (cnt(t->l) + 1));

        t->r = a;
        recalc(t);
        r = b;
        l = t;
    }
}

void Merge(pNode &t, pNode &l, pNode &r) {
    if (!l) {
        t = r;
        return;
    }
    if (!r) {
        t = l;
        return;
    }

    if (l->y < r->y) {
        pNode a;
        Merge(a, l->r, r);

        l->r = a;
        recalc(l);
        t = l;
    } else {
        pNode a;
        Merge(a, l, r->l);

        r->l = a;
        recalc(r);
        t = r;
    }
}

void Forward(pNode &t, int l, int r) {
    pNode a, b, c;

    Split(t, a, b, l - 1);
    Split(b, b, c, r - l + 1);

    Merge(a, a, c);
    Merge(t, b, a);
}

void Insert(pNode &t, int k) {
    pNode n = new Node(k);

    Merge(t, t, n);
}

int main() {
    ios_base::sync_with_stdio(false);

    pNode t = NULL;

    int n, m;
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        Insert(t, i);
    }

    for (int i = 0; i < m; i++) {
        int l, r;
        cin >> l >> r;
        Forward(t, l, r);
    }

    print(t);
    return 0;
}
