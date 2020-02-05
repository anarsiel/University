#include <vector>
#include <iostream>

using namespace std;

const long long INF = 6 * 1e18;
vector<bool> used;
vector<int> cycle;
vector<vector<pair<int, long long> > >  g;

void dfs(int vertex){
    used[vertex] = 1;
    cycle.push_back(vertex);

    for (auto x : g[vertex]){
        if (!used[x.first])
            dfs(x.first);
    }
    return;
}

int main(){
//    freopen("path.in", "r", stdin);
//    freopen("path.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n, m, s;
    cin >> n >> m >> s;
    used.resize(n);
    g.resize(n);
    s--;

    for (int i = 0; i < m; i++){
        int a, b;
        long long w;
        cin >> a >> b >> w;
        a--, b--;
        g[a].push_back({b, w});
    }

    vector<long long> d(n, INF);
    d[s] = 0;

    for (int k = 0; k < n - 1; k++){
        for (int v = 0; v < n; v++){
            if (d[v] >= INF)
                continue;
            for (auto p : g[v]){
                int u = p.first;
                long long w = p.second;
                d[u] = min(d[u], d[v] + w);
            }
        }
        /*for (int i = 0; i < n; i++)
            cout << d[i] << ' ';
        cout << endl;*/
    }

    for (int i = 0; i < 5; i++){
        for (int v = 0; v < n; v++){
            if (d[v] >= INF)
                continue;
            for (auto p : g[v]){
                int u = p.first;
                long long w = p.second;
                if (d[u] > d[v] + w)
                    cycle.push_back(u);
                d[u] = min(d[u], d[v] + w);
            }
        }
    }
    for (int i = 0; i < cycle.size(); i++){
        int v1 = cycle[i];
        if (used[v1])
            continue;
        dfs(v1);
    }

    for (int i = 0; i < cycle.size(); i++)
        d[cycle[i]] = -INF;

    /*for (int i = 0; i < cycle.size(); i++)
        cout << cycle[i] << ' ';
    cout << endl;*/

    for (int i = 0; i < n; i++){
        if (d[i] >= INF)
            cout << "*";
        else if (d[i] <= -INF)
            cout << "-";
        else
            cout << d[i];
        cout << endl;
    }

    return 0;
}
