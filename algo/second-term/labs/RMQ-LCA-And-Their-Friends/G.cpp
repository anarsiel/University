//
// Created by Anarsiel on 2019-05-23.
//

#include <iostream>

using namespace std;

const long long inf = LONG_LONG_MAX;

const unsigned size_arr = 5000000;
long long a[size_arr];

struct Node {
    int left;
    int right;
    long long value;
    bool isSet = false;
    long long change = 0;
};

Node tree[size_arr];

int pow2(int n) {
    int res = 1;
    while (res < n) {
        res *= 2;
    }
    return res;
}

bool isList(int v) {
    return tree[v].left == tree[v].right;
}

void treeBuild(int i, int treeLeft, int treeRight) {
    if (treeRight == treeLeft) {
        tree[i].value = a[treeLeft];
        tree[i].left = treeRight;
        tree[i].right = treeLeft;
        return;
    }
    int treeMid = (treeLeft + treeRight) / 2;
    treeBuild(2 * i + 1, treeLeft, treeMid);
    treeBuild(2 * i + 2, treeMid + 1, treeRight);
    tree[i].value = min(tree[2 * i + 1].value, tree[2 * i + 2].value);
    tree[i].left = tree[2 * i + 1].left;
    tree[i].right = tree[2 * i + 2].right;
}

void push(int v) {
    if (tree[v].isSet) {
        if (isList(2 * v + 1)) {
            tree[2 * v + 1].value = tree[v].change;
            tree[2 * v + 1].isSet = false;
            tree[2 * v + 1].change = 0;
        } else {
            tree[2 * v + 1].isSet = true;
            tree[2 * v + 1].change = tree[v].change;
        }
        if (isList(2 * v + 2)) {
            tree[2 * v + 2].value = tree[v].change;
            tree[2 * v + 2].isSet = false;
            tree[2 * v + 2].change = 0;
        } else {
            tree[2 * v + 2].isSet = true;
            tree[2 * v + 2].change = tree[v].change;
        }
        tree[v].isSet = false;
        tree[v].value = tree[v].change;
        tree[v].change = 0;
    } else {
        tree[2 * v + 1].change += tree[v].change;
        tree[2 * v + 2].change += tree[v].change;
        tree[v].value += tree[v].change;
        tree[v].change = 0;
    }
}

void set(int left, int right, long long x, int v) {
    push(v);
    if (tree[v].left > right || tree[v].right < left) {
        return;
    }
    if (left <= tree[v].left && tree[v].right <= right) {
        if (isList(v)) {
            tree[v].value = x;
            tree[v].isSet = false;
            tree[v].change = 0;
        } else {
            push(v);
            tree[v].isSet = true;
            tree[v].change = x;
        }
        return;
    }
    push(v);
    set(left, right, x, 2 * v + 1);
    set(left, right, x, 2 * v + 2);

    push(2 * v + 1);
    push(2 * v + 2);
    tree[v].value = min(tree[2 * v + 1].value, tree[2 * v + 2].value);
}

void add(int left, int right, long long x, int v) {
    push(v);
    if (tree[v].left > right || tree[v].right < left) {
        return;
    }
    if (left <= tree[v].left && tree[v].right <= right) {
        if (isList(v)) {
            tree[v].value += x;
            tree[v].change = 0;
        } else {
            push(v);
            tree[v].change = x;
        }
        return;
    }
    push(v);
    add(left, right, x, 2 * v + 1);
    add(left, right, x, 2 * v + 2);

    push(2 * v + 1);
    push(2 * v + 2);
    tree[v].value = min(tree[2 * v + 1].value, tree[2 * v + 2].value);
}

long long min(int left, int right, int v) {
    push(v);
    if (tree[v].left > right || tree[v].right < left) {
        return inf;
    }
    if (left <= tree[v].left && tree[v].right <= right) {
        push(v);
        return tree[v].value;
    }
    push(v);
    long long ans = min(min(left, right, 2 * v + 1), min(left, right, 2 * v + 2));
    push(2 * v + 1);
    push(2 * v + 2);
    tree[v].value = min(tree[2 * v + 1].value, tree[2 * v + 2].value);
    return ans;
}

int main() {
    int n;
    cin >> n;

    int binSize = pow2(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = n; i < binSize; i++) {
        a[i] = inf;
    }

    treeBuild(0, 0, binSize - 1);

    string s;
    while (cin >> s) {
        if (s == "min") {
            int left, right;
            cin >> left >> right;
            cout << min(left - 1, right - 1, 0) << "\n";
        }
        if (s == "set") {
            int left, right;
            long long x;
            cin >> left >> right >> x;
            set(left - 1, right - 1, x, 0);
        }
        if (s == "add") {
            int left, right;
            long long x;
            cin >> left >> right >> x;
            add(left - 1, right - 1, x, 0);
        }
    }
    return 0;
}
/*
5 1 2 3 4 5 min 2 5 min 1 5 min 1 4 min 2 4 set 1 3 10 add 2 4 4 min 2 5 min 1 5 min 1 4 min 2 4
 */
