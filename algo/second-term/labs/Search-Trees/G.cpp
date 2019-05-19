//
// Created by Anarsiel on 2019-02-23.
//

#include <iostream>

using namespace std;

struct Node {
    long long x, y;
    long long sum;

    Node *l, *r;

    Node(long long x) : x(x), y(rand()), sum(x), l(nullptr), r(nullptr) {}
};

typedef Node *pNode;

inline long long getsum(pNode t) {
    return (t ? t->sum : 0);
}

bool Exists(pNode t, long long x) {
    if (!t) return false;
    if (t->x == x) return true;

    if (t->x > x)
        return Exists(t->l, x);
    else
        return Exists(t->r, x);
}

void recalc(pNode &t) {
    // cerr << "kek1";
    if (!t) {
        // cerr << "kek7";
        return;
    }
    t->sum = t->x + getsum(t->l) + getsum(t->r);
    // cerr << "kek6";
}

void Split(pNode t, pNode &l, pNode &r, long long x) {
    // cerr << "kek2";
    if (!t) {
        l = r = nullptr;
        return;
    }
    pNode a, b;
    if (t->x > x) {
        Split(t->l, a, b, x);
        t->l = b;
        l = a;
        r = t;
        recalc(t);
    } else {
        Split(t->r, a, b, x);                  //  if t->x == x goes to left
        t->r = a;

        r = b;
        l = t;

        recalc(t);
    }
}

void Merge(pNode &t, pNode l, pNode r) {
    // cerr << "kek3";
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
}

void Insert(pNode &t, long long x) {
    if (Exists(t, x)) return;

    pNode n = new Node(x);
    pNode a, b;
    Split(t, a, b, x);

    Merge(a, a, n);
    Merge(t, a, b);
    return;
}

long long get(pNode t, long long l, long long r) {
    pNode a, b, c;
    Split(t, a, b, l - 1);
    Split(b, b, c, r);

    long long answ = (b ? b->sum : 0);

    Merge(b, b, c);
    Merge(t, a, b);
    return answ;
}

int main() {
    ios_base::sync_with_stdio(false);

    pNode t = nullptr;

    bool was_question = false;
    long long pred_answer = 0;

    long long k;
    cin >> k;
    for (long long i = 0; i < k; i++) {
        char c;
        cin >> c;
        if (c == '+') {
            long long value;
            cin >> value;
            if (was_question) {
                Insert(t, (pred_answer + value) % int(1e9));
                was_question = false;
                continue;
            }
            Insert(t, value);
        } else {
            was_question = true;
            long long l, r;
            cin >> l >> r;
            pred_answer = get(t, l, r);
            cout << pred_answer << endl;
        }
    }
    return 0;
}
