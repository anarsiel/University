//
// Created by Anarsiel on 2019-11-24.
//

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<vector<int>> g;

bool comparator(int a, int b) {
    cout << 1 << ' ' << a << " " << b << endl;
    cout.flush();

    string answ;
    cin >> answ;

    return (answ == "YES");
}

int main() {
//    freopen("fullham.in", "r", stdin);
//    freopen("fullham.out", "w", stdout);
//    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;

    vector<int> v(n);
    for (int i = 1; i <= n; ++i)
        v[i - 1] = i;


    stable_sort(v.begin(), v.end(), comparator);

    cout << 0 << ' ';
    for (auto x : v)
        cout << x << " ";
    return 0;
}
/*
4
1
11
101
 */