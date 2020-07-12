//
// Created by Anarsiel on 05/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>
#include <string>

using namespace std;

void nextSumPartition(vector<int> &v) {
    v[v.size() - 1]--;
    v[v.size() - 2]++;
    if (v[v.size() - 1] < v[v.size() - 2]) {
        v[v.size() - 2] += v[v.size() - 1];
        v.pop_back();
    } else {
        while (v[v.size() - 2] * 2 <= v[v.size() - 1]) {
            v.push_back(v[v.size() - 1] - v[v.size() - 2]);
            v[v.size() - 2] = v[v.size() - 3];
        }
    }
}

int main() {
    freopen("part2num.in", "r", stdin);
    freopen("part2num.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, num;
    string s;
    cin >> s;
    vector<int> check;
    n = 0;

    string cur = "";
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '+') {
            int x = stoi(cur);
            check.push_back(x);
            n += x;
            cur = "";
        } else {
            cur += s[i];
        }
    }
    int x = stoi(cur);
    n += stoi(cur);
    check.push_back(x);

    int cnt = 0;
    vector<int> v(n, 1);
    while (true) {
        if (v.size() == check.size()) {
            bool flag = true;
            for (int i = 0; i < v.size(); i++) {
                if (v[i] != check[i])
                    flag = false;
            }

            if (flag) {
                cout << cnt;
                return 0;
            }
        }

        if (v[0] == n) break;
        cnt++;
        nextSumPartition(v);
    }
//    cout << cnt;
    return 0;
}