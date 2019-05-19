//
// Created by Anarsiel on 15/11/2018.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <time.h>

using namespace std;

int k, vSz;

int MOD(int x, int mod) {
    return (x % mod + mod) % mod;
}

void quickSort(vector<int> &v) {
    int n = vSz;
    if (n == 0)
        return;
    if (n == 1) {
        return;
    }

    int rrrrrrandom = rand();
    int index = MOD(rrrrrrandom, vSz);
//    cout << k << ' ' << index << " " << vSz << " ";
//    if (vSz < 30) {
//        cout << " || ";
//        for (int i = 0; i < vSz; i++)
//            cout << v[i] << ' ';
//    }
    int left = 0, right = 0, middle = 0;
    for (int i = 0; i < vSz; i++) {
        if (v[i] < v[index])
            left++;
        if (v[i] > v[index])
            right++;
        if (v[i] == v[index])
            middle++;
    }
//    cout << "lmr: " << left << " " << middle << " " << right << endl;
    int vSzNew = 0;
    if (k > left) {
        k -= left;

        if (k > middle) {
            k -= middle;
            int x = v[index];
            for (int i = 0; i < vSz; i++) {
                if (v[i] > x)
                    v[vSzNew++] = v[i];
            }
            vSz = vSzNew;
            quickSort(v);
        } else {
            cout << v[index] << endl;
            exit(0);
        }
    } else {
        int x = v[index];
        for (int i = 0; i < vSz; i++) {
            if (v[i] < x)
                v[vSzNew++] = v[i];
        }
        vSz = vSzNew;
        quickSort(v);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("kth.in", "r", stdin);
    freopen("kth.out", "w", stdout);

    srand((unsigned int)(time(NULL)));

    int n;
    int a, b, c, a1, a2;
//    cin >> n >> k;
//
//    vector<int> v(n);
//    for (int i = 0; i < n; i++)
//        cin >> v[i];
//
//    vSz = n;
//
//    quickSort(v);
//    cout << v[0];
//    return 0;
    cin >> n >> k >> a >> b >> c >> a1 >> a2;

    vector<int> v(n);
    v[0] = a1;
    v[1] = a2;

    for (int i = 2; i < n; i++) {
        v[i] = a * v[i - 2] + b * v[i - 1] + c;
    }
    vSz = n;

    quickSort(v);
    // cout << v[vSz] << endl;
    cout << v[0];
    return 0;
}
/*
5 3
2 3 5 1 2

5 3
200000 300000 5 1 2
*/
