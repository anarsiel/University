//
// Created by Anarsiel on 01/12/2018.
//

#include <iostream>
#include <vector>

using namespace std;

vector<int> p;
vector<int> p2;

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
}

int get2(int vertex) {
    if (p2[vertex] == vertex)
        return vertex;
//    cout << 1;
    return p2[vertex] = get2(p2[vertex]);
}

    // void unite2(int a, int b) {
    //    a = get2(a);
    //    b = get2(b);
    //    if (a == b) return;
    //
    //    p2[a] = b;
    //}

int main() {
    freopen("restructure.in", "r", stdin);
    freopen("restructure.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, q;
    cin >> n >> q;
    p.resize(n);
    p2.resize(n);  //  последний справа подряд идущий, относящийся к данному множеству

    for (int i = 0; i < n; ++i) {
        p[i] = p2[i] = i;
    }

    for (int i = 0; i < q; ++i) {
        int type, a, b;
        cin >> type >> a >> b;
//        cerr << i << " ";
        a--, b--;

        if (type == 1) {
            unite(a, b);
        } else if (type == 2) {
            a = get2(a);
            for (int j = a + 1; j <= b; j = max(get2(j), j + 1)) {
                unite(j - 1, j);
                p2[j - 1] = j;
                if (j == n - 1) break;
            }
        } else {
            cout << (get(a) == get(b) ? "YES" : "NO") << endl;
        }
    }
    return 0;
}
/*
8 6
 3 2 5
 1 2 5
 3 2 5
 2 4 7
 2 1 2
 3 1 7
 */
