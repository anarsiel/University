//
// Created by Anarsiel on 2020-01-06.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int main() {
    freopen("cycles.in", "r", stdin);
    freopen("cycles.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n, m;
    cin >> n >> m;

    set<vector<int>> sv;
    vector<pair<int, int>> vp;
    vector<int> vi;

    for (int i = 0; i < n;) {
        int x;
        cin >> x;
        vp.emplace_back(x, ++i);
    }

    sort(vp.begin(), vp.end());
    reverse(vp.begin(), vp.end());

    for (int i = 0; i < m; ++i) {
        int cnt;
        cin >> cnt;

        vi.assign(cnt, 0);
        for (int j = 0; j < cnt; ++j) {
            cin >> vi[j];
        }

        sort(vi.begin(), vi.end());
        sv.insert(vi);
    }

    int answ = 0;
    vector<int> v;
    for (int i = 0; i < n; ++i) {
        v.push_back(vp[i].second);
        sort(v.begin(), v.end());

        bool good = false;
        for (int ilon = 0; ilon < (1 << v.size()); ++ilon) {
            vector<int> vi2;

            for (int i = 0; i < v.size(); ++i) {
                if ((ilon >> i) & 1) {
                    vi2.push_back(v[i]);
                }
            }

            if (sv.count(vi2)) {
                good = true;
                break;
            }
        }

        if (good) {
            auto it = find(v.begin(), v.end(), vp[i].second);
            v.erase(it);
        } else
            answ += vp[i].first;
    }
    cout << answ << endl;
    return 0;
}
/*
3 1
10 20 30
3 1 3 2
 */