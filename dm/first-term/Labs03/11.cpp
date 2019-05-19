//
// Created by Anarsiel on 03/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>

using namespace std;

int n;
vector<vector<int>> answ;

void f(vector<int> &v, int current) {
    if (current == n + 1) {
        answ.push_back(v);
//        for (int i = 0; i < v.size(); ++i) {
//            cout << v[i] << ' ';
//        }
//        cout << endl;
        return;
    }

    f(v, current + 1);
    v.push_back(current);
    f(v, current + 1);
    v.pop_back();
}



int main() {
    freopen("subsets.in", "r", stdin);
    freopen("subsets.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    cin >> n;

    vector<int> v;
    f(v, 1);
    sort(answ.begin(), answ.end());
    for (auto arr : answ) {
        for (int x : arr) {
            cout << x << ' ';
        }
        cout << endl;
    }
    return 0;
}