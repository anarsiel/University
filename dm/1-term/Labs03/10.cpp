//
// Created by Anarsiel on 03/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>

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
    freopen("partition.in", "r", stdin);
    freopen("partition.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n;
    cin >> n;

    vector<int> v(n, 1);
    while (true) {
        for (int i = 0; i < v.size() - 1; i++)
            cout << v[i] << "+";
        cout << v[v.size() - 1] << endl;

        if (v[0] == n) break;
        nextSumPartition(v);
    }
    return 0;
}