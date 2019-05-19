//
// Created by Anarsiel on 05/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
    freopen("nextperm.in", "r", stdin);
    freopen("nextperm.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n;
    cin >> n;
    int factorial = 1;
    for (int i = 1; i <= n; i++)
        factorial *= i;

    vector<int> v(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }

    if (n == 1) {
        cout << 0 << endl << 0;
        return 0;
    }

    bool isFirst = true;
    bool isLast = true;

    for (int i = 0; i < n; i++) {
        if (v[i] != i + 1) {
            isFirst = false;
        }

        if (v[i] != n - i) {
            isLast = false;
        }
    }

    if (isFirst) {
        swap(v[v.size() - 1], v[v.size() - 2]);

        for (int i = 0; i < n; i++) {
            cout << 0 << ' ';
        }
        cout << endl;

        for (int i = 0; i < n; i++) {
            cout << v[i] << ' ';
        }
        cout << endl;

        return 0;
    } else if (isLast) {
        swap(v[v.size() - 1], v[v.size() - 2]);

        for (int i = 0; i < n; i++) {
            cout << v[i] << ' ';
        }
        cout << endl;

        for (int i = 0; i < n; i++) {
            cout << 0 << ' ';
        }
        cout << endl;
        return 0;
    }

    vector<int> vv = v;

    int index = n - 1;
    for (int j = n - 2; j > -1; j--) {
        if (v[j] > v[j + 1]) {
            index = j;
            break;
        }
    }

    int mxInd = -1;
    for (int i = n - 1; i > index; i--) {
        if ((mxInd == -1 && v[i] < v[index]) || (v[mxInd] < v[i] && v[i] < v[index])) {
            mxInd = i;
        }
    }

    swap(v[mxInd], v[index]);
    for (int i = index + 1; i < n; i++) {
        if (i < n - i + index)
            swap(v[i], v[n - i + index]);
    }

    for (int x : v) {
        cout << x << ' ';
    }
    cout << endl;

    v = vv;

    for (int i = 0; i < 1; i++) {
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