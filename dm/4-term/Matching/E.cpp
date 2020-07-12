//
// Created by Anarsiel on 02/03/2020.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <cmath>

using namespace std;

int n, m;
double v;
vector<vector<int>> g;
vector<int> mt;
vector<bool> used;

struct Task {
    int h, m, a, b, c, d;

    Task() = default;

    Task(int h, int m, int a = 0, int b = 0, int c = 0, int d = 0)
            : h(h), m(m), a(a), b(b), c(c), d(d) {}

    void read() {
        string time;
        cin >> time >> a >> b;// >> c >> d;

        h = stoi(time.substr(0, 2));
        m = stoi(time.substr(3, 2));
    }

};

bool comp(Task &a, Task &b) {
    return a.h < b.h || (a.h == b.h && a.m < b.m);
}

bool try_kuhn(int v) {
    if (used[v]) return false;
    used[v] = true;
    for (size_t i = 0; i < g[v].size(); ++i) {
        int to = g[v][i];
        if (mt[to] == -1 || try_kuhn(mt[to])) {
            mt[to] = v;
            return true;
        }
    }
    return false;
}

void correct_minutes(int &h, int &m) {
    h += m / 60;
    m %= 60;
}

bool is_zero(double d, double eps = 1e07) {
    return fabs(d - round(d)) < eps;
}

bool can_after(Task &a, Task &b) {
    int h, m;
    double dtime = sqrt((a.a - b.a)*(a.a - b.a) + (a.b - b.b)*(a.b - b.b)) / v;
    int time = int(dtime);

    h = a.h + int(time / 60);
    m = a.m + time % 60;
    correct_minutes(h, m);

    return h < b.h || (h == b.h && m < b.m) || (h == b.h && m == b.m && is_zero(dtime));
}

int main() {
    freopen("ufo.in", "r", stdin);
    freopen("ufo.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    cin >> n >> v;
    v /= 60;

    m = n;

    g.resize(n);
    mt.assign(m, -1);

    vector<Task> tasks(n);

    for (int i = 0; i < n; ++i)
        tasks[i].read();

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            if (i == j) continue;
            if (can_after(tasks[i], tasks[j])) {
                g[i].push_back(j);
            }
        }
    }

    for (int v = 0; v < n; ++v) {
        used.assign(n, false);
        try_kuhn(v);
    }

    int answ = 0;

    vector<int> mt2(n, -1);

    for (int i = 0; i < n; ++i) {
        if (mt[i] == -1) continue;

        mt2[mt[i]] = i;
    }

    vector<bool> usedd(n, false);
    for (int i = 0; i < n; ++i) {
        if (usedd[i]) continue;
        if (mt[i] != -1) continue;

        int cur = i;
        ++answ;
        for (;;) {
            usedd[cur] = true;
            int next = mt2[cur];
//            cout << cur << ' ';

            if (next != -1) {
                cur = next;
            } else {
//                cout << endl;
                break;
            }
        }
    }
    cout << answ;
    return 0;
}