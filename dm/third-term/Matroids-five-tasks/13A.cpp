#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <unordered_set>
#include <queue>

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

#define mp             make_pair
#define pb             push_back
#define fi             first
#define se             se
#define sz(x)          (int)((x).size())
#define fill(x, y)     memset(x, y, sizeof(y))
#define all(x)         (x).begin(), (x).end()
#define sci(x)         int x;       scanf("%d", &x);
#define scii(x, y)     int x, y;    scanf("%d %d", &x, &y);
#define sciii(x, y, z) int x, y, z; scanf("%d %d %d", &x, &y, &z);
#define TC(x)          sci(x); while(x --)
#define eprintf(...)   fprintf(stderr, __VA_ARGS__)
#define debug(x)       { cerr << #x << " = " << x << endl; }
#define rep(i, x, y)   for (__typeof(x) i = x; i <= y; i ++)
#define repi(i, x, y)  for (__typeof(x) i = x; i >= y; i --)
#define fore(itr, x)   for (__typeof(x.begin()) itr = x.begin(); itr != x.end(); itr ++)
#define forei(itr, x)  for (__typeof(x.end()) itr = x.end() - 1; itr != x.begin() - 1; itr --)
typedef long long      ll;
typedef pair<int, int> ii;
typedef pair<ii, int>  iii;
typedef vector<int>    vi;
typedef vector<ii>     vii;
typedef vector<iii>    viii;
const   int            inf = 0;
const   double         eps = 0;
const   int            ms  = 0;
const   int            md  = 0;

struct Pair {
    int fi, se;

    Pair(int f, int s) {
        fi = f;
        se = s;
    }
};

struct Obj {
    int c;
    Pair p = Pair(0, 0);
    vector<int> vii;
    vector<int> vio;

    Obj() {}

    Obj(int c, int a, int b) {
        this->c = c;
        p = Pair(a, b);
    }
};

void dfs(vector<vector<int>> const &vvi, vector<int> &comps, int vertex, int component) {
    if (comps[vertex] != 0) {
        return;
    }

    comps[vertex] = component;
    for (int obj : vvi[vertex]) {
        dfs(vvi, comps, obj, component);
    }
}

int main() {
    freopen("rainbow.in", "r", stdin);
    freopen("rainbow.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int n, m;
    scanf("%d %d", &n, &m);

    vector<Obj> objs;

    for (int i = 0; i < m; ++i) {
        int a, aa, aaa;
        scanf("%d %d %d", &a, &aa, &aaa);

        objs.emplace_back(aaa, a - 1, aa - 1);
    }

    unordered_set<int> other;
    for (int i = 0; i < m; ++i)
        other.insert(i);

    bool pupa;
    unordered_set<int> st, hSI1, hSI2;
    do {
        for (auto obj : st) {
            objs[obj].vii.clear();
            objs[obj].vio.clear();
        }

        for (auto obj : other) {
            objs[obj].vii.clear();
            objs[obj].vio.clear();
        }

        for (auto x : st) {
            vector<bool> colors;
            colors.assign(239, false);

            vector<int> comps;
            comps.assign(n, 0);

            int cmp_id = 1;
            vector<vector<int>> graph_arcs(n, vector<int>());
            for (auto y : st) {
                if (y != x) {
                    colors[objs[y].c] = true;
                    graph_arcs[objs[y].p.fi].pb(objs[y].p.se);
                    graph_arcs[objs[y].p.se].pb(objs[y].p.fi);
                }
            }

            for (int node = 0; node < n; ++node) {
                if (comps[node] == 0) {
                    dfs(graph_arcs, comps, node, cmp_id);
                    ++cmp_id;
                }
            }

            for (auto cur_arc : other) {
                if (comps[objs[cur_arc].p.fi] != (comps[objs[cur_arc].p.se])) {
                    objs[x].vio.pb(cur_arc);
                    objs[cur_arc].vii.pb(x);
                }
                if (!colors[objs[cur_arc].c]) {
                    objs[x].vii.pb(cur_arc);
                    objs[cur_arc].vio.pb(x);
                }
            }
        }

        hSI1.clear();
        hSI2.clear();

        vector<bool> colors;
        colors.assign(239, false);

        vector<int> comps;
        comps.assign(n, 0);

        int cmp_id = 1;
        vector<vector<int>> v(n, vector<int>());
        for (int cur_arc : st) {
            colors[objs[cur_arc].c] = true;
            v[objs[cur_arc].p.fi].pb(objs[cur_arc].p.se);
            v[objs[cur_arc].p.se].pb(objs[cur_arc].p.fi);
        }

        for (int node = 0; node < n; ++node) {
            if (comps[node] == 0) {
                dfs(v, comps, node, cmp_id);
                cmp_id++;
            }
        }

        for (auto x : other) {
            if (comps[objs[x].p.fi] != (comps[objs[x].p.se]))
                hSI1.insert(x);

            if (!colors[objs[x].c])
                hSI2.insert(x);
        }

        pupa = false;
        int prev[objs.size()];
        for (int i = 0; i < objs.size(); ++i)
            prev[i] = -1;

        bool used[objs.size()];
        for (int i = 0; i < objs.size(); ++i)
            used[i] = false;

        queue<int> q;
        for (auto obj : hSI1) {
            q.push(obj);
            used[obj] = true;
        }

        while (!q.empty()) {
            int t = q.front();
            q.pop();

            if (hSI2.find(t) != hSI2.end()) {
                while (prev[t] != -1) {
                    other.erase(t);
                    st.erase(prev[t]);
                    other.insert(prev[t]);
                    st.insert(t);
                    t = prev[prev[t]];
                }

                other.erase(t);
                st.insert(t);
                pupa = true;
                break;
            }
            for (auto obj : objs[t].vio) {
                if (!used[obj]) {
                    used[obj] = true;
                    q.push(obj);
                    prev[obj] = t;
                }
            }
        }
    } while (pupa);

    printf("%d\n", st.size());
    for (auto obj : st) {
        printf("%d ", ++obj);
    }
}
/*
4 5
1 2 1
3 1 1
2 3 1
1 4 2
3 4 3
 
4 6
1 2 1
2 1 2
2 3 1
3 2 2
3 4 1
4 3 2
 */