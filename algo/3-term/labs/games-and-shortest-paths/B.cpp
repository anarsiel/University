#include <bits/stdc++.h>

using namespace std;

void dijkstra_with_set(int start, const vector<vector<pair<int, int> > >& v){
	vector<long long> d(v.size(), 1e18 + 239);
	d[start] = 0;
	set<pair<long long, int> > s;
	s.insert(make_pair(0LL, start));
	while (!s.empty()){
	 	int vertex = (*s.begin()).second;
	 	s.erase(s.begin());
	 	for (int i = 0; i < v[vertex].size(); i++){
	 		int u = v[vertex][i].first;
	 		int w = v[vertex][i].second;
	 	 	if (d[vertex] + w < d[u]){
	 	 	 	s.erase(make_pair(d[u], u));
	 	 	 	d[u] = d[vertex] + w;
	 	 	 	s.insert(make_pair(d[u], u));
	 	 	}
	 	}
	}

	for (int i = 0; i < v.size(); ++i)
	    cout << d[i] << ' ';
	cout << endl;
}

int main(){
	ios_base::sync_with_stdio(false);

	int n, m;
	cin >> n >> m;

	vector<vector<pair<int, int> > > v(n);

	for (int i = 0; i < m; i++){
	 	int a, b, l;
	 	cin >> a >> b >> l;
	 	a--, b--;
	 	v[a].push_back({b, l});
	 	v[b].push_back({a, l});
	}

	int st = 0;
	dijkstra_with_set(st, v);
	return 0;
}
