#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<vector<int>> g;

bool hasEdge(int a, int b) {
    return g[a][b] == 1;
}

int main() {
    freopen("chvatal.in", "r", stdin);
    freopen("chvatal.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;

    g.resize(n, vector<int>(n, 0));
    for (int i = 1; i < n; ++i) {
        for (int j = 0; j < i; ++j) {
            char c;
            cin >> c;
            g[j][i] = g[i][j] = (c == '0' ? 0 : 1);
        }
    }

//    for (int i = 1; i < n; ++i) {
//        for (int j = 0; j < n; ++j) {
//            cout << g[i][j] << ' ';
//        }
//        cout << endl;
//    }

    deque<int> v;
//    for (int i = n - 1; i >= 0; --i)
//        v.push_back(i);
    for (int i = 0; i < n; ++i)
        v.push_back(i);
//    v.push_back(1);
//    v.push_back(3);
//    v.push_back(2);
//    v.push_back(0);

    for (int h = 0; h < n * (n - 1); ++h) {
//        for (int i = 0; i < v.size(); ++i)
//            cout << v[i] << ' ';
//        cout << endl;

        int a = v[0];
        int b = v[1];

        if (hasEdge(a, b)) {
            v.pop_front();
            v.push_back(a);
            continue;
        }

        bool was = false;
        for (int i = 2; i < n; ++i) {
            if (hasEdge(a, v[i]) && hasEdge(b, v[(i + 1) % n])) {
                for (int j = 0; 1 + j < i - j; ++j) {
                    swap(v[1 + j], v[i - j]);
                }
                was = true;
                break;
            }
        }

        if (!was) {
            for (int i = 2; i < n; ++i) {
                if (hasEdge(a, v[i])) {
                    for (int j = 0; 1 + j < i - j; ++j) {
                        swap(v[1 + j], v[i - j]);
                    }
                    was = true;
                    break;
                }
            }
        }

        a = v.front();
        v.pop_front();
        v.push_back(a);
    }

    cout << endl;
    for (int i = 0; i < n; ++i)
        cout << v[i] + 1 << ' ';
    return 0;
}
/*
4
1
11
101
 */