//
// Created by Anarsiel on 30/11/2018.
//

#include <iostream>
#include <vector>

using namespace std;

vector<int> p;
vector<int> rang;
vector<int> color;

pair<int, int> get(int vertex) {
    if (p[vertex] == vertex)
        return {vertex, color[vertex]};

    auto ckwarjhgdnkj = get(p[vertex]);
    ckwarjhgdnkj.second ^= color[vertex];
    return ckwarjhgdnkj;
}

void unite(int a, int b) {
    auto aa = get(a);
    auto bb = get(b);

    a = aa.first;
    b = bb.first;
    bool toChangeColor = (aa.second == bb.second);

    if (a == b) return;

//    p[a] = b;
//    if (toChangeColor)
//        color[b] = (1+color[b]) % 2;


    if (rang[a] < rang[b]) {
        p[a] = b;
//        color[a] ^= color[b];
        if (toChangeColor)
            color[a] = (1 + color[a]) % 2;
    } else if (rang[a] > rang[b]) {
        p[b] = a;
//        color[b] ^= color[a];
        if (toChangeColor)
            color[b] = (1 + color[b]) % 2;
    } else {
        p[a] = b;
        rang[b]++;
//        color[a] ^= color[b];
        if (toChangeColor)
            color[a] = (1 + color[a]) % 2;
    }
}

int main() {
//    freopen(".in", "r", stdin);
//    freopen(".out", "w", stdout);
//    ios_base::sync_with_stdio(false);
//    cin.tie(nullptr);
//    cout.tie(nullptr);

    int n, m, shift = 0;
    cin >> n >> m;

    p.resize(n);
    color.resize(n, 0);
    rang.resize(n, 1);

    for (int i = 0; i < n; ++i)
        p[i] = i;

    for (int i = 0; i < m; i++) {
        int type, a, b;
        cin >> type >> a >> b;
        a = (a + shift) % n;
        b = (b + shift) % n;

        if (type) {
            int x = get(a).second;
            int y = get(b).second;
//            cout << x << ' ' << y << "|| ";
            bool flag = (x == y);
            cout << (flag ? "YES" : "NO") << '\n';
            if (flag) shift++;
            shift %= n;
        } else {
            unite(a, b);
        }
    }
    return 0;
}
/*
3 5
 0 1 2
 0 2 3
 1 1 2
 1 1 3
 1 1 3
 */
