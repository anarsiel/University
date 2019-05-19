//
// Created by Anarsiel on 06/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

void nextMultiperm(vector<int> &v) {
    int n = v.size();
    int i = n - 2;

    while ((i > -1) && (v[i] >= v[i + 1]))
        i--;

    if (i > -1) {
        int ii = i + 1;
        while (ii < n - 1 && v[ii + 1] > v[i])
            ii++;

        swap(v[i], v[ii]);

        for (int g = i + 1, gg = n - 1; g < n; g++, gg--) {
            if (g < gg)
                swap(v[g], v[gg]);
        }
        return;
    }
    for (int k = 0; k < n; k++)
        v[k] = 0;
}

int main() {
    freopen("nextmultiperm.in", "r", stdin);
    freopen("nextmultiperm.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n;
    cin >> n;
    vector<int> v(n);
    for (int i = 0; i < n; i++)
        cin >> v[i];

    nextMultiperm(v);
    for (int x : v)
        cout << x << " ";
    return 0;
}
/*
6
1 3 2 1 3 2
*/