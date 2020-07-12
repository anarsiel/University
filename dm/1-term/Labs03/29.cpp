//
// Created by Anarsiel on 06/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

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
    freopen("nextpartition.in", "r", stdin);
    freopen("nextpartition.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    string s;
    cin >> s;

    string cur;
    vector<int> v;
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '=' || s[i] == '+') {
            v.push_back(stoi(cur));
            cur.clear();
        } else {
            cur += s[i];
        }
    }
    v.push_back(stoi(cur));
    cur.clear();

    int x = v[0];
    for (int i = 1; i < v.size(); i++) {
        v[i - 1] = v[i];
    }
    v.pop_back();

    if (v.size() == 1) {
        cout << "No solution" << endl;
        return 0;
    }

    nextSumPartition(v);
    cout << x << "=" << v[0];
    for (int i = 1; i < v.size(); i++)
        cout << "+" << v[i];
}
/*
5=1+1+3
5=5
*/