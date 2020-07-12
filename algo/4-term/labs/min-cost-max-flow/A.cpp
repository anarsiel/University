#pragma GCC optimize("Ofast,no-stack-protector")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx,avx,avx2,tune=native")
#pragma GCC optimize("unroll-loops")
#pragma GCC optimize("fast-math")
#pragma GCC optimize("section-anchors")
#pragma GCC optimize("profile-values,profile-reorder-functions,tracer")
#pragma GCC optimize("vpt")
#pragma GCC optimize("rename-registers")
#pragma GCC optimize("move-loop-invariants")
#pragma GCC optimize("unswitch-loops")
#pragma GCC optimize("function-sections")
#pragma GCC optimize("data-sections")
#pragma GCC optimize("branch-target-load-optimize")
#pragma GCC optimize("branch-target-load-optimize2")
#pragma GCC optimize("btr-bb-exclusive")

#include <iostream>
#include <vector>

using namespace std;

const int INF = 1e9 + 239;

struct Edge {
    int to, f_max, c, f;
    int ret;
};

int main() {
    int n, m, k = INF;
    cin >> n >> m;
    vector<vector<Edge>> g(n);
    int start = 0, stop = n - 1;

    for (int i = 0; i < m; ++i) {
        int a, b, f_max, c;
        cin >> a >> b >> f_max >> c;
        --a, --b;

        Edge r1 = {b, f_max, c, 0, int(g[b].size())};
        Edge r2 = {a, 0, -c, 0, int(g[a].size())};
        g[a].push_back(r1);
        g[b].push_back(r2);
    }

    int max_flow = 0, min_cost = 0;

    vector<int> id, d, q, p, p_rib;
    for (; max_flow < k;) {
        id.assign(n, 0);
        d.assign(n, INF);
        q.assign(n, 0);
        p.assign(n, 0);
        p_rib.assign(n, 0);

        int x = 0, y = 0;
        q[y++] = start;
        d[start] = 0;
        for (; x != y;) {
            int v = q[x++];
            id[v] = 2;

            x %= n;
            for (auto i = 0; i < g[v].size(); ++i) {
                Edge &edge = g[v][i];

                if (edge.f < edge.f_max && d[v] < d[edge.to] - edge.c) {
                    d[edge.to] = d[v] + edge.c;

                    if (!id[edge.to]) {
                        q[y++] = edge.to;

                        y %= n;
                    } else if (id[edge.to] == 2) {
                        if (x == 0)
                            x = n;

                        --x;
                        q[x] = edge.to;
                    }

                    id[edge.to] = 1;
                    p[edge.to] = v;
                    p_rib[edge.to] = i;
                }
            }
        }

        if (d[stop] == INF)
            break;

        int increment = k - max_flow;
        for (int vertex = stop; vertex != start;) {
            int pv = p[vertex], pr = p_rib[vertex];
            increment = min(increment, g[pv][pr].f_max - g[pv][pr].f);

            vertex = p[vertex];
        }

        for (int vertex = stop; vertex != start;) {
            int pv = p[vertex], pr = p_rib[vertex], r = g[pv][pr].ret;
            g[pv][pr].f += increment;
            g[vertex][r].f -= increment;
            min_cost += g[pv][pr].c * increment;

            vertex = p[vertex];
        }
        max_flow += increment;
    }

    cout << min_cost << endl;
}

/*
4 5
1 2 1 2
1 3 2 2
3 2 1 1
2 4 2 1
3 4 2 3
 */