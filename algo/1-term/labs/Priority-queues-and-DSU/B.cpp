#include <iostream>
#include <vector>

using namespace std;

vector<int> p;
vector<int> jopa, mn, mx;

int get(int vertex) {
    if (p[vertex] == vertex)
        return vertex;

    return p[vertex] = get(p[vertex]);
}

void unite(int a, int b) {
    a = get(a);
    b = get(b);

    if (a == b) return;

    p[a] = b;
    jopa[b] += jopa[a];
    mn[b] = min(mn[b], mn[a]);
    mx[b] = max(mx[b], mx[a]);
}

int main() {
    freopen("dsu.in", "r", stdin);
    freopen("dsu.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;
    p.resize(n);
    jopa.resize(n, 1);
    mn.resize(n);
    mx.resize(n);

    for (int i = 0; i < n; ++i) {
        p[i] = mn[i] = mx[i] = i;
    }
    string type;
    while (cin >> type) {
        if (type == "union") {
            int a, b;
            cin >> a >> b;
            a--, b--;
            unite(a, b);
        } else {
            int a;
            cin >> a;
            a--;
            cout << mn[get(a)] + 1 << " " << mx[get(a)] + 1 << " " << jopa[get(a)] << endl;
        }
    }
    return 0;
}
/*
5
union 1 2
get 3
get 2
union 2 3
get 2
union 1 3
get 5
union 4 5
get 5
union 4 1
get 5
 */
