//
// Created by Anarsiel on 2019-05-22.
//

#include <iostream>
#include <vector>
#include <cstdio>

using namespace std;

const int MAX_P = 30;
vector<int> a;
vector<int> maxk;
vector<vector<int>> d(MAX_P);

int get_min(int l, int r) {
    r--, l--;
    if (r < l)
        swap(r, l);
    int k = maxk[r - l + 1];
    return min(d[k][r - (1 << k) + 1], d[k][l]);
}

int main() {
    freopen("sparse.in", "r", stdin);
    freopen("sparse.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m, a1;
    cin >> n >> m >> a1;
    int u1, v1;
    cin >> u1 >> v1;
    for (int i = 0; i < MAX_P; i++)
        d[i].resize(n);
    a.push_back(a1);
    for (int i = 1; i < n; i++)
        a.push_back((23 * a.back() + 21563) % 16714589);
    for (int i = 0; i < n; i++) {
        d[0][i] = a[i];
    }

    for (int p = 1; p < MAX_P; p++) {
        for (int i = 0; i < n; i++) {
            if (i + (1 << (p - 1)) >= n) {
                d[p][i] = d[p - 1][i];
            } else {
                d[p][i] = min(d[p - 1][i + (1 << (p - 1))], d[p - 1][i]);
            }
        }
    }
    maxk.resize(n + 1, 0);

//    for (int i = 0; i < MAX_P; i++){
//        for (int j = 0; j < n; j++){
//            cout << d[i][j] << ' ';
//        }
//        cout << endl;
//    }

    for (int i = 2; i < n + 1; i++)
        maxk[i] = maxk[i / 2] + 1;

    long long sum = 0;
    for (int i = 1; i < m + 1; i++) {
        int blabla = get_min(u1, v1);
        sum = blabla;
        if (i == m)
            break;
        u1 = ((17 * u1 + 751 + blabla + 2 * i) % n) + 1;
        v1 = ((13 * v1 + 593 + blabla + 5 * i) % n) + 1;
    }
    cout << u1 << " " << v1 << " " << sum;
    return 0;
}
/*
10 8 12345
3 9
 */
