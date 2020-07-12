#include <iostream>

using namespace std;

struct Node {
    int value, y, cnt;

    Node *l, *r;

    Node(int value) : value(value), y(rand()), cnt(1), l(nullptr), r(nullptr) {}

    Node() {}
};

typedef Node *pNode;

void print(pNode t) {
    if (!t) return;

    print(t->l);
    cout << t->value << ' ';
    print(t->r);
    return;
}

int get_cnt(pNode t) {
    return (t ? t->cnt : 0);
}

void recalc(pNode &t) {
    if (!t) return;

    t->cnt = 1 + get_cnt(t->l) + get_cnt(t->r);
    return;
}

void Split_value(pNode t, pNode &l, pNode &r, int x) {
    if (!t) {
        l = r = nullptr;
        return;
    }

    pNode a, b;
    if (t->value > x) {
        Split_value(t->l, a, b, x);
        t->l = b;

        l = a;
        recalc(t);
        r = t;
    } else {
        Split_value(t->r, a, b, x);                  // t->x == x goes to left
        t->r = a;

        r = b;
        recalc(t);
        l = t;
    }
    return;
}

void Split_cnt(pNode t, pNode &l, pNode &r, int x) {
    if (!t) {
        l = r = nullptr;
        return;
    }

    pNode a, b;
    if (get_cnt(t->l) >= x) {
        Split_cnt(t->l, a, b, x);
        t->l = b;

        l = a;
        recalc(t);
        r = t;
    } else {
        Split_cnt(t->r, a, b, x - get_cnt(t->l) - 1);
        t->r = a;

        r = b;
        recalc(t);
        l = t;
    }
    return;
}

void Merge(pNode &t, pNode l, pNode r) {
    if (!l) {
        t = r;
        return;
    }
    if (!r) {
        t = l;
        return;
    }

    if (l->y > r->y) {
        Merge(l->r, l->r, r);
        t = l;
    } else {
        Merge(r->l, l, r->l);
        t = r;
    }

    recalc(t);
    return;
}

void Insert(pNode &t, int value) {
    pNode a, b;

    Split_value(t, a, b, value);

    pNode n = new Node(value);

    Merge(a, a, n);
    Merge(t, a, b);
    return;
}

void Delete(pNode &t, int value) {
    pNode a, b, c;

    Split_value(t, a, b, value - 1);
    Split_value(b, b, c, value);

    Merge(t, a, c);
    return;
}

void Get(pNode t, int k) {
    pNode a, b, c;

    Split_cnt(t, a, b, k - 1);
    Split_cnt(b, b, c, 1);

    cout << b->value << endl;

    Merge(a, a, b);
    Merge(t, a, c);
    return;
}

int main() {
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;

    pNode t = nullptr;

    for (int i = 0; i < n; i++) {
        string a;
        int b;
        cin >> a >> b;
        if (a == "-1") {
            Delete(t, b);
        } else if (a == "0") {
            Get(t, get_cnt(t) - b + 1);
        } else {
            Insert(t, b);
        }

        // print(t);
        // cout << endl;
    }
    return 0;
}
