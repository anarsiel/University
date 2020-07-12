//
// Created by Anarsiel on 04/11/2018.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("huffman.in", "r", stdin);
    freopen("huffman.out", "w", stdout);

    int n;
    cin >> n;

    vector<long long> v(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }

    multiset<long long> mSet;
    for (int i = 0; i < n; ++i) {
        mSet.insert(v[i]);
    }

    long long otvet = 0LL;
    for (int i = 0; i < n - 1; i++) {
        long long a = *mSet.begin();
        mSet.erase(mSet.begin());

        long long b = *mSet.begin();
        mSet.erase(mSet.begin());

        mSet.insert(a + b);
        otvet += a + b;
    }
    cout << otvet << endl;
    return 0;
}