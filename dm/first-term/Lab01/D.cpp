//
// Created by Anarsiel on 08/11/2018.
//

#include <iostream>
#include <vector>
#include <set>

using namespace std;

long long getBit(long long x, long long index) {
    return ((x >> index) & 1);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    long long n;
    cin >> n;
    vector<vector<long long>> table(73, vector<long long>(n + 1, 0));
    vector<long long> numbers(n);

    for (long long i = 0; i < n + 1; i++) {
        long long x;
        cin >> x;

        if (i < n)
            numbers[i] = x;

        for (long long j = 0; j < 73; j++) {
            table[j][i] = getBit(x, j);
        }
    }

    for (long long i = 0; i < 73; i++) {
        for (long long j = 0; j < 73; j++) {
            bool equal = true;

            for (long long k = 0; k < n; k++) {
                if (table[i][k] != table[j][k])
                    equal = false;
            }

            if (equal && table[i][n] != table[j][n]) {
                cout << "Impossible" << endl;
                return 0;
            }
        }
    }

    bool notBeginingAnd = false;
    cout << "(1&" << char(126) << "1)";
    for (long long i = 0; i < 73; ++i) {
        if (table[i][n] == 1) {
            cout << char(124) << "(";
            for (long long j = 0; j < n; ++j) {
                if (notBeginingAnd)
                    cout << "&";

                if (table[i][j] == 0) {
                    cout << char(126);
                }
                cout << j + 1;
                notBeginingAnd = true;
            }
            cout << ")";
        }
        notBeginingAnd = false;
    }
    return 0;
}
/*
2
48 83
68
*/