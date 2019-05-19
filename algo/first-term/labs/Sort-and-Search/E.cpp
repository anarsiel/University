//
// Created by Anarsiel on 09/11/2018.
//

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

bool operator <= (pair<string, int> a, pair<string, int> b) {
    return a.first < b.first || (a.first == b.first && a.second < b.second);
}

vector<pair<string, int>> sort(vector<pair<string, int>> &v) {
    int n = int(v.size());
    if (v.size() == 1)
        return v;

    vector<pair<string, int>> left(n / 2);
    vector<pair<string, int>> right(n / 2 + n % 2);

    for (int i = 0; i < n / 2; i++) {
        left[i] = v[i];
    }

    for (int i = 0; i < n / 2 + n % 2; i++) {
        right[i] = v[n / 2 + i];
    }

    sort(left);
    sort(right);

    int indexL = 0, indexR = 0;

    for (int i = 0; i < n; i++) {
        if (indexL == int(left.size())) {
            v[i] = right[indexR++];
        } else if (indexR == int(right.size())) {
            v[i] = left[indexL++];
        } else {
            if (left[indexL] <= right[indexR]) {
                v[i] = left[indexL++];
            } else {
                v[i] = right[indexR++];
            }
        }
    }
    return v;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("radixsort.in", "r", stdin);
    freopen("radixsort.out", "w", stdout);

    int n, m, k;
    cin >> n >> m >> k;

    vector<string> strings(n);
    vector<pair<string, int>> v(n);
    for (int i = 0; i < n; i++) {
        cin >> strings[i];
        v[i] = {strings[i].substr(m - k), i};
    }

    sort(v);

    for (int i = 0; i < n; i++)
        cout << strings[v[i].second] << endl;
    return 0;
}
/*
3 3 1
bbb
aba
baa

3 3 2
bbb
aba
baa

 3 3 3
 bbb
 aba
 baa
*/
