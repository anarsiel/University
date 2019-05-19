//
// Created by Anarsiel on 13/11/2018.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
//    cin.tie(nullptr);
//    cout.tie(nullptr);
//    freopen(".in", "r", stdin);
//    freopen(".out", "w", stdout);

    int n;
    cin >> n;

    vector<vector<pair<int, int>>> v(11);
    for (int i = 1; i <= 15; i += 2)
        v[0].push_back({i, i + 1});

    for (int i = 2; i <= 14; i += 4)
        v[1].push_back({i, i + 2});
    for (int i = 1; i <= 13; i += 4)
        v[1].push_back({i, i + 2});

    for (int i = 1; i <= 4; i++)
        v[2].push_back({i, i + 4});
    for (int i = 9; i <= 12; i++)
        v[2].push_back({i, i + 4});

    for (int i = 1; i <= 8; i++)
        v[3].push_back({i, i + 8});

    v[4].push_back({6, 11});
    v[4].push_back({7, 10});
    v[4].push_back({4, 13});
    v[4].push_back({8, 12});
    v[4].push_back({2, 3});
    v[4].push_back({5, 9});
    v[4].push_back({14, 15});

    v[5].push_back({2, 5});
    v[5].push_back({3, 9});
    v[5].push_back({8, 14});
    v[5].push_back({12, 15});

    v[6].push_back({3, 5});
    v[6].push_back({6, 7});
    v[6].push_back({10, 11});
    v[6].push_back({12, 14});
    v[6].push_back({4, 9});
    v[6].push_back({8, 13});

    v[7].push_back({7, 9});
    v[7].push_back({4, 6});
    v[7].push_back({8, 10});
    v[7].push_back({11, 13});

    for (int i = 4; i <= 12; i += 2) {
        v[8].push_back({i, i + 1});
    }

    v[9].push_back({7, 8});
    v[9].push_back({9, 10});

    int m = 0, k = 0;
    for (int i = 0; i < 10; i++) {
        int cnt = 0;

        for (int j = 0; j < int(v[i].size()); j++) {
            if (v[i][j].first <= n && v[i][j].second <= n)
                cnt++;
        }

        if (cnt != 0)
            k++;
        m += cnt;
    }

    cout << n << ' ' << m << ' ' << k << endl;

    for (int i = 0; i < 10; i++) {
        int cnt = 0;

        for (int j = 0; j < int(v[i].size()); j++) {
            if (v[i][j].first <= n && v[i][j].second <= n)
                cnt++;
        }

        if (cnt > 0) {
            cout << cnt << " ";
            for (int j = 0; j < int(v[i].size()); j++) {
                if (v[i][j].first <= n && v[i][j].second <= n)
                    cout << v[i][j].first << ' ' << v[i][j].second << " ";
            }
            cout << endl;
        }
    }
    return 0;
}
/*
4
4 6 3
2 1 2 3 4
2 1 4 2 3
2 1 2 3 4
*/
