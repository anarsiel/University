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

const int MAXN = 100;
const int INF = 1e9 + 239;

struct edge {
    int a, b, cap, flow;
};

int n, s, t;

vector<int> x, ad, r;

vector<edge> edges;
vector<int> g[MAXN];

void add_edge(int a, int b, int cap) {
    edge e1 = {a, b, cap, 0};
    edge e2 = {b, a, cap, 0};
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
                    to = edges[id].b;
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
        int id = g[v][ad[v]], to = edges[id].b;

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

unordered_set<int> avaliable_from_s;
vector<bool> usedd(MAXN, false);

void do_dfs(int vertex) {
    if (usedd[vertex]) return;

    usedd[vertex] = true;
    avaliable_from_s.insert(vertex);

    for (int i = 0; i < g[vertex].size(); ++i) {
        auto e = edges[g[vertex][i]];

        if (e.cap - e.flow > 0) {
            do_dfs(e.b);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int m;
    cin >> n >> m;

    x.resize(n);
    r.resize(n);
    ad.resize(n);

    for (int i = 0; i < m; ++i) {
        int a, b, c;
        cin >> a >> b >> c;

        --a, --b;
        add_edge(a, b, c);
    }

    find_flow(0, n - 1);

    do_dfs(0);

    long long sum = 0;
    vector<int> indxes;
    for (int i = 0; i < edges.size(); i += 2) {
        auto edge = edges[i];
        int l = avaliable_from_s.find(edge.a) != avaliable_from_s.end();
        int r = avaliable_from_s.find(edge.b) != avaliable_from_s.end();

        if (l + r == 1) {
            indxes.push_back(i / 2);
            sum += abs(edge.flow);
        }
    }

    cout << indxes.size() << ' ' << sum << endl;
    for (auto xx : indxes)
        cout << xx + 1 << ' ';
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
