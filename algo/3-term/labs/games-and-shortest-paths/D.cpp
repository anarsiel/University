//
// Created by Anarsiel on 2019-11-09.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <cstdio>
#include <set>
#include <map>

using namespace std;

const int INF = 1e9+239;

struct Edge {
    int a, b, weight;

    Edge(int a, int b, int weight) : a(a), b(b), weight(weight) {}
};

int main() {
    ios_base::sync_with_stdio(false);

    int n, m, k, s;
    cin >> n >> m >> k >> s;
    s--;

    vector<Edge> edges;
    vector<int> answ(n,  INF);

    for (int i = 0; i < m; ++i) {
        int a, b, w;
        cin >> a >> b >> w;
        a--, b--;

        edges.emplace_back(a, b, w);
    }


    vector<bool> first_upd_done(n, false);

    answ[s] = 0;
    for (int i = 0; i < k; ++i) {
        vector<int> _answ(n, INF);
        for (int j = 0; j < m; ++j) {
            Edge edge = edges[j];

            if (answ[edge.a] != INF) {
                _answ[edge.b] = min(_answ[edge.b], answ[edge.a] + edge.weight);
            }
        }
        answ = _answ;
    }

    for (int i = 0; i < n; ++i)
        cout << (answ[i] == INF ? -1 : answ[i]) << endl;
    return 0;
}