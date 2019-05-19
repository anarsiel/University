//
// Created by Anarsiel on 28/11/2018.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> p;

int get(int vertex) {
    if (p[vertex] == vertex)
        return vertex;

    return p[vertex] = get(p[vertex]);
}

void unite(int a, int b) {
    a = get(a);
    b = get(b);

    if (a == b) return;

    p[a] = b;
}

int main() {
    freopen("rmq.in", "r", stdin);
    freopen("rmq.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    cin >> n >> m;
    p.resize(n + 1);
    for (int i = 0; i < n + 1; i++) {
        p[i] = i;
    }

    vector<pair<int, pair<int, int>>> v(m);

    for (int i = 0; i < m; i++) {
        int l, r, value;
        cin >> l >> r >> value;
        l--, r--;
        v[i] = {value, {l, r}};
    }
    sort(v.begin(), v.end());
    reverse(v.begin(), v.end());
    vector<int> answ(n, 0);

    for (int i = 0 ; i < m; i++) {
        int l = v[i].second.first;
        int r = v[i].second.second;
        int value = v[i].first;
        l = get(l);

        for (int ind = l; ind <= r; ind = get(ind)) {
            answ[ind] = value;
            unite(ind, ind + 1);
        }
    }

    for (int i = 0; i < n; i++) {
        cout << answ[i] << " ";
    }
    return 0;
}
/*
3 2
1 2 1
2 3 2

5
3
1 5 1
1 2 2
4 5 2

 */
