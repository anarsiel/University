//
// Created by Anarsiel on 05/12/2018.
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
    freopen("num2part.in", "r", stdin);
    freopen("num2part.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, num;
    cin >> n >> num;

    int cnt = 0;
    vector<int> v(n, 1);
    while (true) {
        if (cnt == num) {
            for (int i = 0; i < v.size() - 1; i++)
                cout << v[i] << "+";
            cout << v[v.size() - 1] << endl;
//            return 0;
        }

        if (v[0] == n) break;
        cnt++;
        nextSumPartition(v);
    }
//    cout << cnt;
    return 0;
}