#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <cstdio>
#include <set>

using namespace std;

vector<vector<pair<int, int> > > v;
bool used[20000];
set<int> answer;
int time = 0;
vector<int> fup;
vector<int> tin;

void dfs(int current, int pred){
    tin[current] = ++time;
    used[current] = true;
    fup[current] = tin[current];
    int cnt = 0;
    for(int i = 0; i < v[current].size(); i++){
        int u = v[current][i].first;
        if (u == pred) continue;
        if (!used[u]){
            dfs(u, current);
            cnt++;
            fup[current] = min(fup[u], fup[current]);
            if (fup[u] >= tin[current] and pred != -1) {
                answer.insert(current);
            }
        } else {
            fup[current] = min(fup[current], tin[u]);
        }
    }
    if (cnt > 1 and pred == -1)
        answer.insert(current);
}

int main()
{
    freopen("points.in", "r", stdin);
    freopen("points.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    int n, m;
    cin >> n >> m;
    v.resize(n);
    tin.resize(n);
    fup.resize(n);
    for(int i = 0; i < m; i++){
        int a, b;
        cin >> a >> b;
        --a, --b;
        v[a].push_back(make_pair(b, i));
        v[b].push_back(make_pair(a, i));
    }
    for(int i = 0; i < n; i++){
        if(!used[i])
            dfs(i, -1);
    }
    cout << answer.size() << endl;
    for(set<int>::iterator it = answer.begin(); it != answer.end(); ++it) {
        cout << (*it) + 1 << ' ';
    }
    return 0;
}