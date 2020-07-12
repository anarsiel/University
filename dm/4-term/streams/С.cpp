//
// Created by Anarsiel on 08/04/2020.
//

#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

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

const int MAXN = 100239;
const int INF = 1e9 + 239;

struct edge {
    int v, cap, flow;
};

int n, s, t;

vector<int> x, ad, r;

vector<edge> edges;
vector<int> g[MAXN];

void add_edge(int a, int b, int cap) {
    edge e1 = {b, cap, 0};
    edge e2 = {a, 0, 0};
    g[a].push_back(edges.size());
    edges.push_back(e1);
    g[b].push_back(edges.size());
    edges.push_back(e2);
}

bool bfs() {
    int a = 0, b = 0;
    r[b++] = s;

    x.assign(x.size(), -1);
    x[s] = 0;
    while (a < b && x[t] == -1) {
        int v = r[a++];
        for (size_t i = 0; i < g[v].size(); ++i) {
            int id = g[v][i],
                    to = edges[id].v;
            if (x[to] == -1 && edges[id].flow < edges[id].cap) {
                r[b++] = to;
                x[to] = x[v] + 1;
            }
        }
    }
    return x[t] != -1;
}

int dfs(int v, int flow_value) {
    if (!flow_value) return 0;
    if (v == t) return flow_value;

    for (; ad[v] < g[v].size(); ++ad[v]) {
        int id = g[v][ad[v]], to = edges[id].v;

        if (x[to] != x[v] + 1)
            continue;

        if (int upd = dfs(to, min(flow_value, edges[id].cap - edges[id].flow))) {
            edges[id].flow += upd;
            edges[id ^ 1].flow -= upd;
            return upd;
        }
    }
    return 0;
}

int find_flow(int _s, int _t) {
    s = _s, t = _t;

    int flow = 0;
    while (true) {
        if (!bfs())
            break;

        ad.assign(ad.size(), 0);
        while (int dfs_res = dfs(s, INF)) {
            if (dfs_res == INF)
                return flow;
            flow += dfs_res;
        }
    }
    return flow;
}

vector<int> path1, path2;

bool flag1 = false, flag2 = false;

void do_dfs(int vertex) {
    if (flag1)
        return;

    path1.push_back(vertex);
    if (vertex == t) {
        flag1 = true;
        return;
    }

    for (int to : g[vertex]) {
        if (!flag1 && edges[to].flow == 1) {
            edges[to].flow = 0;

            do_dfs(edges[to].v);
        }
    }
}

void do_dfs2(int vertex) {
    if (flag2)
        return;

    path2.push_back(vertex);
    if (vertex == t) {
        flag2 = true;
        return;
    }

    for (int to : g[vertex]) {
        if (!flag2 && edges[to].flow == 1) {
            edges[to].flow = 0;

            do_dfs2(edges[to].v);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int m;
    cin >> n >> m >> s >> t;
    --s, --t;

//    usedd.resize(m, false);

    x.resize(n);
    r.resize(n);
    ad.resize(n);

    for (int i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;

        --a, --b;
        add_edge(a, b, 1);
    }

    auto flow = find_flow(s, t);

    if (flow < 2) {
        cout << "NO\n";
        return 0;
    }

    do_dfs(s);
    do_dfs2(s);

    if (!flag1 || !flag2) {
        cout << "NO\n";
        return 0;
    }

    cout << "YES\n";

    for (auto xx : path1)
        cout << xx + 1 << ' ';
    cout << endl;

    for (auto xx : path2)
        cout << xx + 1<< ' ';
    return 0;
}
/*
2
2
1 2 1
2 1 3

6
5
1 2 5
2 3 2
3 4 7
4 5 7
5 6 5

6
5
1 2 5
3 2 2
3 4 7
4 5 7
5 6 5
 */
