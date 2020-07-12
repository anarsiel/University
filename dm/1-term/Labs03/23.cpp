//
// Created by Anarsiel on 05/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>
#include <string>

using namespace std;

int toInt(char c) {
    return c - '0';
}

int main() {
    freopen("nextvector.in", "r", stdin);
    freopen("nextvector.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    string s;
    cin >> s;

//    s = "";
//    for (int i = 0; i < 200000; i++)
//        s.push_back('1');

    int allSum = 0;
    for (int i = 0; i < s.size(); i++) {
        allSum += toInt(s[i]);
    }

    if (allSum == s.size()) {
        for (int i = 0; i < s.size() - 1; i++) {
            cout << 1;
        }
        cout << 0 << endl;
        cout << '-';
        return 0;
    }

    if (allSum == 0) {
        cout << "-" << endl;
        for (int i = 0; i < s.size() - 1; i++)
            cout << 0;
        cout << 1;
        return 0;
    } else if (s == "1") {
        cout << 0 << endl << 10;
        return 0;
    }

    int n = s.size();

    int cntZero = 0;
    while (s[cntZero] == '0')
        cntZero++;
    s = s.substr(cntZero, n - cntZero);
    n = s.size();

    int sum = 0;
    for (int i = 1; i < n; i++) {
        sum += toInt(s[i]);
    }
//    cout << s << endl << sum << ' ' << n << endl;

    if (sum == n - 1) {
        for (int i = 0; i < cntZero; i++)
            cout << 0;
        for (int i = 0; i < n - 1; i++)
            cout << 1;
        cout << 0;

        cout << endl;
        for (int i = 0; i < cntZero - 1; i++)
            cout << 0;
        cout << 1;
        for (int i = 0; i < n; ++i)
            cout << 0;
        return 0;
    }

    if (sum == 0) {
        for (int i = 0; i < cntZero; i++)
            cout << 0;

        for (int i = 0; i < n - 1; i++)
            cout << "1";

        for (int i = 0; i < cntZero; i++)
            cout << 0;
        cout << endl << s.substr(0, n - 1) << 1;
    } else if (sum == n - 1) {
        for (int i = 0; i < cntZero; i++)
            cout << 0;
        cout << s.substr(0, n - 1) << 0;
        cout << endl;
        for (int i = 0; i < cntZero; i++)
            cout << 0;
        cout << 1;
        for (int i = 0; i < n; ++i)
            cout << 0;
    } else {
        int index = n - 1;
        while (s[index] == '0') {
            index--;
        }

        for (int i = 0; i < cntZero; i++)
            cout << 0;

        for (int i = 0; i < index; i++) {
            cout << s[i];
        }
        cout << 0;
        for (int i = index + 1; i < n; i++)
            cout << 1;
        cout << endl;

        for (int i = 0; i < cntZero; i++)
            cout << 0;
        index = n - 1;
        while (s[index] == '1') {
            index--;
        }
        for (int i = 0; i < index; i++) {
            cout << s[i];
        }
        cout << 1;
        for (int i = index + 1; i < n; i++)
            cout << 0;
    }
    return 0;
}