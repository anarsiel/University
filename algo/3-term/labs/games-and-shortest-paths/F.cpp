//
// Created by Anarsiel on 2019-11-09.
//

#include <iostream>
#include <set>

#include <vector>

using namespace std;

long long dijkstra_with_set(int start, int finish, const vector<vector<pair<int, int> > > &v) {
    vector<long long> d(v.size(), 1e18 + 239);
    d[start] = 0;
    set<pair<long long, int> > s;
    s.insert(make_pair(0LL, start));
    while (!s.empty()) {
        int vertex = (*s.begin()).second;
        s.erase(s.begin());
        for (int i = 0; i < v[vertex].size(); i++) {
            int u = v[vertex][i].first;
            int w = v[vertex][i].second;
            if (d[vertex] + w < d[u]) {
                s.erase(make_pair(d[u], u));
                d[u] = d[vertex] + w;
                s.insert(make_pair(d[u], u));
            }
        }
    }

    if (d[finish] == 1e18 + 239) {
        return -1;
    }
    return d[finish];
}


int main() {
    ios_base::sync_with_stdio(false);

    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, int> > > v(n);

    for (int i = 0; i < m; i++) {
        int a, b, l;
        cin >> a >> b >> l;
        a--, b--;
        v[a].push_back({b, l});
        v[b].push_back({a, l});
    }

    int a, b, c;
    cin >> a >> b >> c;
    --a, --b, --c;

    long long ab = dijkstra_with_set(a, b, v);
    long long ac = dijkstra_with_set(a, c, v);
    long long bc = dijkstra_with_set(b, c, v);

    if ((ab == -1 && ac == -1) || (bc == -1 && ac == -1) || (ab == -1 && bc == -1)) {
        cout << -1;
        return 0;
    }

    if (ab == -1) {
        cout << bc + ac;
        return 0;
    }

    if (bc == -1) {
        cout << ab + ac;
        return 0;
    }

    if (ac == -1) {
        cout << bc + ab;
        return 0;
    }

    long long answ = ab + bc;
    answ = min(answ, ab + ac);
    answ = min(answ, bc + ac);
    cout << answ;
    return 0;
}