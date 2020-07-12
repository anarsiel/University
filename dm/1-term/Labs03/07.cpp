//
// Created by Anarsiel on 02/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
    freopen("permutations.in", "r", stdin);
    freopen("permutations.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n;
    cin >> n;
    int factorial = 1;
    for (int i = 1; i <= n; i++)
        factorial *= i;

    vector<int> v(n);
    for (int i = 0; i < n; i++)
        v[i] = i + 1;

    for (int x : v) {
        cout << x << ' ';
    }
    cout << endl;

    for (int i = 0; i < factorial; i++) {
        int swapIndex = -1;
        for (int j = n - 2; j > -1; j--) {
            if (v[j] < v[j + 1]) {
                swapIndex = j;
                break;
            }
        }
        if (swapIndex == -1) {
            break;
        }

        int mx = -1;
        for (int j = swapIndex + 1; j < n; j++) {
            if (v[j] > v[swapIndex]) {
                mx = j;
            }
        }

        swap(v[mx], v[swapIndex]);
        vector<int> suff;
        for (int j = n - 1; j > swapIndex; j--)
            suff.push_back(v[j]);

        for (int j = swapIndex + 1, k = 0; j < n; j++, k++)
            v[j] = suff[k];

        for (int x : v) {
            cout << x << ' ';
        }
        cout << endl;
    }
    return 0;
}