//
// Created by Anarsiel on 2020-01-04.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

multiset<long long> p1sumwu(vector<pair<long long, long long>> dw) {
    long long t = 1;
    long long n = dw.size();

    multiset<long long> s;

    for (long long i = 0; i < n; ++i) {
        if (dw[i].first == 0) continue;

        s.insert(dw[i].second);

        if (dw[i].first >= t)
            ++t;
        else {
            s.erase(s.begin());
        }
    }

    return s;
}

int main() {
    freopen("schedule.in", "r", stdin);
    freopen("schedule.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    long long n;
    cin >> n;

    vector<pair<long long, long long>> dw;

    long long allSum = 0;
    for (long long i = 0; i < n; ++i) {
        long long d, w;
        cin >> d >> w;

        dw.emplace_back(d, w);
        allSum += w;
    }
    sort(dw.begin(), dw.end());
    auto s = p1sumwu(dw);
    for (auto x : s) {
        allSum -= x;
    }
    cout << allSum;
    return 0;
}
/*
2
1 1
1 2
 */