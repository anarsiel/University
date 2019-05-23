#include <iostream>

using namespace std;

const int size_arr = 500000;

struct Node {
    int left;
    int right;
    int status;
    int cntFree;
    int leftFree;
};

Node tree[size_arr];

int pow2(int n) {
    int res = 1;
    while (res < n) {
        res *= 2;
    }

    return res;
}

void treeBuild(int i, int tl, int tr) {
    if (tr == tl) {
        tree[i].status = 0;
        tree[i].left = tr;
        tree[i].right = tl;
        tree[i].cntFree = 1;
        tree[i].leftFree = tl;
        return;
    }

    int tm = (tl + tr) / 2;
    treeBuild(2 * i + 1, tl, tm);
    treeBuild(2 * i + 2, tm + 1, tr);

    tree[i].cntFree = tree[2 * i + 1].cntFree + tree[2 * i + 2].cntFree;
    tree[i].left = tree[2 * i + 1].left;
    tree[i].right = tree[2 * i + 2].right;
    tree[i].leftFree = tree[2 * i + 1].leftFree;
}

void set(int i, int v, int x) {
    if (tree[v].left == tree[v].right) {
        tree[v].status = x;
        tree[v].cntFree = (x == 0) ? 1 : 0;
        if (tree[v].status == 1) {
            tree[v].leftFree = INT32_MAX;
        } else {
            tree[v].leftFree = tree[v].left;
        }
        return;
    }
    int m = (tree[v].left + tree[v].right) / 2;
    if (i <= m) {
        set(i, 2 * v + 1, x);
    } else {
        set(i, 2 * v + 2, x);
    }
    tree[v].cntFree = tree[2 * v + 1].cntFree + tree[2 * v + 2].cntFree;
    tree[v].leftFree = min(tree[2 * v + 1].leftFree, tree[2 * v + 2].leftFree);
}

int leftFree(int left, int right, int v) {
    if (tree[v].left > right || tree[v].right < left) {
        return INT32_MAX;
    }
    if (left <= tree[v].left && tree[v].right <= right) {
        return tree[v].leftFree;
    }
    return min(leftFree(left, right, 2 * v + 1), leftFree(left, right, 2 * v + 2));
}

int cntFree(int left, int right, int v) {
    if (tree[v].left > right || tree[v].right < left) {
        return 0;
    }
    if (left <= tree[v].left && tree[v].right <= right) {
        return tree[v].cntFree;
    }
    return cntFree(left, right, 2 * v + 1) + cntFree(left, right, 2 * v + 2);
}

int enter(int i, int n) {
    int free = cntFree(i, i, 0);
    if (free == 1) {
        return i;
    }
    int answer = INT32_MAX;
    if (i + 1 < n) {
        answer = leftFree(i + 1, n - 1, 0);
    }
    if (answer == INT32_MAX) {
        answer = leftFree(0, i, 0);
    }
    return answer;
}

int main() {
//    freopen("parking.in", "r", stdin);
//    freopen("parking.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    cin >> n;
    int bitSize = pow2(n + 1);
    treeBuild(0, 0, bitSize - 1);
    cin >> m;
    string s;
    for (int i = 0; i < m; i++) {
        cin >> s;
        if (s == "enter") {
            int x;
            cin >> x;
            x = enter(x - 1, n);
            cout << x + 1 << '\n';
            set(x, 0, 1);
        } else if (s == "exit") {
            int x;
            cin >> x;
            set(x - 1, 0, 0);
        }
    }
    return 0;
}
/*
3 5
enter 1
enter 1
exit 1
enter 2
enter 2
 */
