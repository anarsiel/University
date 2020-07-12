//
// Created by Anarsiel on 05/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

void genNext(vector<int> &v, int n, int k) {
    vector<int> arr = v;
    arr.push_back(n + 1);
    int i = k - 1;

    while (i >= 0 && (arr[i + 1] - arr[i] < 2)) i--;

    if (i >= 0) {
        arr[i]++;
        for (int j = i + 1; j < k; j++) {
            arr[j] = arr[j - 1] + 1;
        }

        v = arr;
        v.pop_back();
    } else {
        v.clear();
    }
}

int main() {
    freopen("nextchoose.in", "r", stdin);
    freopen("nextchoose.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, k;
    cin >> n >> k;

    vector<int> v(k);
    for (int i = 0; i < k; i++)
        cin >> v[i];

    genNext(v, n, k);
    if (v.size() == 0)
        cout << -1;
    else {
        for (int x : v)
            cout << x << " ";
    }
//    bool isLast = true;
//
//    for (int i = k - 1, j = n; i > -1; i--, j--) {
//        if (v[i] != j) {
//            isLast = false;
//        }
//    }
//
//    if (isLast) {
//        cout << -1;
//        return 0;
//    }


    return 0;
}