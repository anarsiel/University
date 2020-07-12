//
// Created by Anarsiel on 12/11/2018.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int getBit(const int &x, const int& index) {
    return (x >> index) & 1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
//    freopen(".in", "r", stdin);
//    freopen(".out", "w", stdout);

    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<pair<int, int>>> v(k);

    for (int i = 0; i < k; i++) {
        int r;
        cin >> r;
        vector<pair<int, int>> vv(r);

        for (int j = 0; j < r; j++) {
            int a, b;
            cin >> a >> b;
            if (a > b)
                swap(a, b);
            vv[j] = {a - 1, b - 1};
        }

        v[i] = vv;
    }

    vector<int> arr(n);

    for (int mask = 0; mask < (1 << n); mask++) {
        for (int i = 0; i < n; i++) {
            arr[i] = getBit(mask, i);
        }

        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < int(v[i].size()); ++j) {
                if (arr[v[i][j].first] > arr[v[i][j].second])
                    swap(arr[v[i][j].first], arr[v[i][j].second]);
            }
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] == 0 && arr[i - 1] == 1) {
                cout << "No";
                return 0;
            }
        }
    }

    cout << "Yes";
    return 0;
}
/*
4 6 3
2 1 2 3 4
2 1 4 2 3
2 1 2 3 4
*/
