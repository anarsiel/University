//
// Created by Anarsiel on 24/11/2018.
//

#include <iostream>
#include <vector>

using namespace std;

vector<int> p;

int get(int vertex) {
    if (p[vertex] == vertex)
        return vertex;

    return p[vertex] = get(p[vertex]);
}

void merge(int a, int b) {
    a = get(a);
    b = get(b);

    if (a == b) return;

    p[a] = b;
}

int main() {
    freopen("cutting.in", "r", stdin);
    freopen("cutting.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m, k;
    cin >> n >> m >> k;

    p.resize(n);
    for (int i = 0; i < n; ++i)
        p[i] = i;

    vector<string> answ;
    vector<string> type(k);
    vector<pair<int, int>> requests(k);

    for (int i = 0; i < m; i++) {
        int a;
        cin >> a >> a;
    }

    for (int i = 0; i < k; i++)
        cin >> type[i] >> requests[i].first >> requests[i].second;

    for (int i = k - 1; i > -1; --i) {
        requests[i].first--;
        requests[i].second--;

        if (type[i] == "ask") {
            answ.push_back(get(requests[i].first) == get(requests[i].second) ? "YES" : "NO");
        } else {
            merge(requests[i].first, requests[i].second);
        }
    }
    for (int i = int(answ.size()) - 1; i > -1; i--)
        cout << answ[i] << endl;
    return 0;
}
/*
3 3 7
1 2
2 3
3 1
ask 3 3 cut 1 2 ask 1 2 cut 1 3 ask 2 1 cut 2 3 ask 3 1
 */
