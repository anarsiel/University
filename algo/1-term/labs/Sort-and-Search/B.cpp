//
// Created by Anarsiel on 08/11/2018.
//

#include <iostream>
#include <vector>

using namespace std;

int lowerBound(const vector<int> &v, const int &x) {
    int n = int(v.size());
    int l = 0, r = n;

    while (l != r - 1) {
        int m = (l + r) / 2;

        if (x <= v[m]) {
            r = m;
        } else {
            l = m;
        }
    }

    if (l == n - 1 || v[r] != x)
        return -1;
    if (l == 0 && v[l] == x)
        return 1;
    return r + 1;
}

int upperBound(const vector<int> &v, const int &x) {
    int n = int(v.size());
    int l = 0, r = n;

    while (l != r - 1) {
        int m = (l + r) / 2;

        if (x < v[m]) {
            r = m;
        } else {
            l = m;
        }
    }

    if (v[l] != x)
        return -1;

    return l + 1;
}

int main() {
    ios_base::sync_with_stdio(false);
    freopen("binsearch.in", "r", stdin);
    freopen("binsearch.out", "w", stdout);

    int n;
    cin >> n;
    vector<int> v(n);
    for (int i = 0; i < n; i++)
        cin >> v[i];

    int m;
    cin >> m;

    for (int i = 0; i < m; i++) {
        int x;
        cin >> x;
        cout << lowerBound(v, x) << " " << upperBound(v, x) << endl;
    }
    return 0;
}
