//
// Created by Anarsiel on 03/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

bool next_combination (vector<int> & v, int n) {
    int k = int(v.size());
    for (int i = k - 1; i >= 0; i--)
        if (v[i] < n - k + i + 1) {
            v[i]++;
            for (int j = i + 1; j < k; j++)
                v[j] = v[j - 1]+1;
            return true;
        }
    return false;
}

int main() {
    freopen("choose.in", "r", stdin);
    freopen("choose.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, k;
    cin >> n >> k;

    vector<int> v(k);

    for (int i = 0; i < k; i++)
        v[i] = i + 1;

    do {
        for (int x : v)
            cout << x << " ";
        cout << endl;
    } while (next_combination(v, n));
    return 0;
}