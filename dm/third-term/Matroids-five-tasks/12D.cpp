//
// Created by Anarsiel on 2020-01-06.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int main() {
    freopen("check.in", "r", stdin);
    freopen("check.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n, m;
    cin >> n >> m;

    set<vector<int>> s;
    vector<vector<int>> vv(m);
    vector<int> cnt(m);
    
    bool firstIsOk = false;
    for (int i = 0; i < m; ++i) {
        cin >> cnt[i];

        vv[i].resize(cnt[i]);
        for (int j = 0; j < cnt[i]; ++j)
            cin >> vv[i][j];

        sort(vv[i].begin(), vv[i].end());

        s.insert(vv[i]);

        if (cnt[i] == 0)
            firstIsOk = true;
    }

    if (!firstIsOk) {
        cout << "NO" << endl;
        return 0;
    }

    for (int i = 0; i < m; ++i) {
        for (int ilon = 1; ilon < (1 << cnt[i]); ++ilon) {
            vector<int> reqieredVector;
            for (int j = 0; j < cnt[i]; ++j)
                if (1 & (ilon >> j))
                    reqieredVector.push_back(vv[i][j]);
                
            sort(reqieredVector.begin(), reqieredVector.end());
            if (!s.count(reqieredVector)) {
                // sec not ok
                cout << "NO" << endl;
                return 0;
            }
        }
    }

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < m; ++j) {
            if (cnt[i] <= cnt[j]) continue;

            bool thirdIsOkNow = false;
            for (int h = 1; h <= n; h++) {
                if (!count(vv[i].begin(), vv[i].end(), h) ||
                    count(vv[j].begin(), vv[j].end(), h)) continue;

                vector<int> reqieredVector;
                
                for (auto x : vv[j])
                    reqieredVector.push_back(x);
                reqieredVector.push_back(h);

                sort(reqieredVector.begin(), reqieredVector.end());

                if (s.count(reqieredVector)) {
                    thirdIsOkNow = true;
                    break;
                }
            }

            if (!thirdIsOkNow) {
                cout << "NO" << endl;
                return 0;
            }
        }
    }

    cout << "YES" << endl;
    return 0;
}

/*
2 4
0
1 1
1 2
2 1 2

2 3
0
1 1
2 1 2
 */