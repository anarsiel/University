//
// Created by Anarsiel on 12/11/2018.
//
#include <iostream>
#include <vector>

using namespace std;

long long cntInversions = 0LL;

vector<int> sort(vector<int> &v) {
    int n = int(v.size());
    if (v.size() == 1)
        return v;

    vector<int> left(n / 2);
    vector<int> right(n / 2 + n % 2);

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
                cntInversions += left.size() - indexL;
            }
        }
    }
    return v;
}

unsigned int cur = 0;
int a, b;

unsigned int nextRand24() {
    cur = cur * a + b;
//    cout << cur << " ";
    return (cur >> 8);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("invcnt.in", "r", stdin);
    freopen("invcnt.out", "w", stdout);

    int n, m;
    cin >> n >> m >> a >> b;

    vector<int> v(n);
    for (int i = 0; i < n; ++i) {
        v[i] = nextRand24();
        v[i] %= m;
    }

    sort(v);
    cout << cntInversions;
    return 0;
}
