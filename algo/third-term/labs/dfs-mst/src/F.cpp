#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <cstdio>
#include <set>

using namespace std;

bool used[10000];
int cond[10000];
vector<pair<int, int> > tout;
vector<vector<int> > g(10000), reversed(10000);
int time = 0;

void dfs(int current){
    used[current] = true;
    for(int i = 0; i < g[current].size(); i++){
        if (!used[g[current][i]]){
            dfs(g[current][i]);
        }
    }
    tout.push_back(make_pair(time, current));
    time++;
}

void dfs2(int current, int cnt){
    cond[current] = cnt;
    for(int i = 0; i < reversed[current].size(); i++){
        if (!cond[reversed[current][i]]){
            dfs2(reversed[current][i], cnt);
        }
    }
}

int main()
{
    freopen("condense2.in", "r", stdin);
    freopen("condense2.out", "w", stdout);
    int n, m;
    cin >> n >> m;
    for(int i = 0; i < m; i++){
        int l, k;
        cin >> l >> k;
        l--, k--;
        g[l].push_back(k);
        reversed[k].push_back(l);
    }
    for(int i = 0; i < n; i++){
        if(!used[i]){
            dfs(i);
        }

    }
    int cnt = 0;
    reverse(tout.begin(), tout.end());
    for(int i = 0; i < n; i++){
        if (cond[tout[i].second] == 0){
            cnt++;
            dfs2(tout[i].second, cnt);
        }
    }

    set<pair<int, int> > edges_used;
    int es = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < g[i].size(); j++) {
            if (cond[i] != cond[g[i][j]]) {
                if (edges_used.find(make_pair(cond[i], cond[g[i][j]])) == edges_used.end() &&
                    edges_used.find(make_pair(cond[g[i][j]], cond[i])) == edges_used.end()) {
                    es++;
                }
                edges_used.insert(make_pair(cond[i], cond[g[i][j]]));
            }
        }
    }
    cout << es;
    return 0;
}