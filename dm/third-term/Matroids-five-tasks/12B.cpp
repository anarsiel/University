#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Edge {
    int id;
    long long w;

    int v, u;

    Edge(int id, long long w, int v, int u) : id(id), w(w), v(v), u(u) {}
};

bool comp(const Edge &a, const Edge &b) {
    return a.w < b.w || (a.w == b.w && a.id < b.id);
}

vector<int> pr;

int get(int a) {
    if (a == pr[a])
        return a;

    return pr[a] = get(pr[a]);
}

void unite(int a, int b) {
    a = get(a);
    b = get(b);
    if (a == b) return;

    pr[a] = b;
}

int main() {
    freopen("destroy.in", "r", stdin);
    freopen("destroy.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n, m;
    long long s;
    cin >> n >> m >> s;

    pr.resize(n);
    for (int i = 0; i < n; ++i)
        pr[i] = i;

    vector<Edge> edges;
    for (int i = 1; i <= m; ++i) {
        int v, u;
        long long w;
        cin >> v >> u >> w;
        v--, u--;
        edges.emplace_back(i, w, v, u);
    }

    vector<bool> inMST(m, false);
    for (auto& edge : edges) {
        edge.w = -edge.w;
    }

    sort(edges.begin(), edges.end(), comp);

    for (auto edge : edges) {
        if (get(edge.u) != get(edge.v)) {
            inMST[edge.id - 1] = true;

            unite(edge.u, edge.v);
        }
    }

    vector<int> answ;
    for (auto& edge : edges) {
        edge.w = -edge.w;
    }
    sort(edges.begin(), edges.end(), comp);

    long long curS = 0;
    for (int i = 0; i < edges.size(); ++i) {
        if (!inMST[edges[i].id - 1]) {
            if (curS + edges[i].w <= s) {
                curS = curS + edges[i].w;
                answ.push_back(edges[i].id);
            } else {
                break;
            }
        }
    }

    sort(answ.begin(), answ.end());
    cout << answ.size() << endl;

    for (auto x : answ)
        cout << x << ' ';
    return 0;
}
/*
2
1 1
1 2
 */