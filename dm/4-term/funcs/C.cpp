//
// Created by Anarsiel on 26/04/2020.
//

#include <iostream>
#include <vector>

using namespace std;

int main() {
    long long k;
    cin >> k;
    vector<long long> a(2390, 0), c(2390, 0);
    vector<long long> p;

    for (long long i = 0; i < k; ++i)
        cin >> a[i];

    for (long long i = 1; i < k + 1; ++i) {
        cin >> c[i];
    }

    for (long long i = 0; i < k; ++i) {
        p.push_back(a[i]);
        for (int j = i; j > 0; --j) {
            p[i] -= a[j] * c[i - j + 1];
        }

    }

    while (!p.empty() && p[p.size() - 1] == 0)
        p.pop_back();

    cout << p.size() - 1 << endl;
    for (auto pp : p)
        cout << pp << ' ';
    cout << endl << k << endl << "1 ";

    for (int i = 1; i < k + 1; ++i) {
        cout << -c[i] << ' ';
    }
    return 0;
}
/*
2
1 1
1 1
 */