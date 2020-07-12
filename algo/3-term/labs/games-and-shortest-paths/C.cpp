#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <cstdio>
#include <set>
#include <map>

using namespace std;

struct Edge {
    int a, b, weight;

    Edge(int a, int b, int weight) : a(a), b(b), weight(weight) {}
};

int main() {
    ios_base::sync_with_stdio(false);

    int n;
    vector<Edge> edges;
    const int INF = static_cast<const int>(1e9 + 239);

    cin >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            int w;
            cin >> w;
            if (w != 100000)
                edges.emplace_back(i, j, w);
        }
    }
    int m = edges.size();

    int was;
    int d[n];
    int p[n];
    for (int i = 0; i < n; ++i) {
        p[i] = -1;
    }

    for (int i = 0; i < n; ++i) {
        was = -239;
        for (int j = 0; j < m; ++j) {
            if (d[edges[j].b] > d[edges[j].a] + edges[j].weight) {
                was = edges[j].b;
                d[edges[j].b] = max(-INF, d[edges[j].a] + edges[j].weight);
                p[edges[j].b] = edges[j].a;
            }
        }
    }

    if (was == -239) {
        cout << "NO";
        return 0;
    }

    int v = was;
    for (int i = 0; i < n; ++i)
        v = p[v];

    vector<int> cycle;
    for (int cur = v ;; cur = p[cur]) {
        if (cur == v && cycle.size() > 1)
            break;
        cycle.push_back(cur);
    }
    reverse(cycle.begin(), cycle.end());

    cout << "YES" << endl << cycle.size() << endl;
    for (size_t i = 0; i < cycle.size(); ++i)
        cout << cycle[i] + 1 << ' ';
    return 0;
}
/*
2
0 -1
-1 0
 */
