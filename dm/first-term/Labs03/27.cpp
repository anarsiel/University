//
// Created by Anarsiel on 05/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

void genNext(string &s) {
    int cntClose = 0, cntOpen = 0;
    int n = s.size();

    for (int i = s.size() - 1; i > -1; i--) {
        if (s[i] == '(') {
            cntOpen++;
            if (cntClose > cntOpen)
                break;
        } else
            cntClose++;
    }

    for (int i = n - 1; i >= n - cntOpen - cntClose; i--)
        s.pop_back();

    if (s == "") {
        s = "-";
    } else {
        s += ')';
        for (int j = 0; j < cntOpen; j++)
            s += '(';
        for (int j = 0; j < cntClose - 1; j++)
            s += ')';
    }
}

int main() {
    freopen("nextbrackets.in", "r", stdin);
    freopen("nextbrackets.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    string s;
    cin >> s;

    genNext(s);
    cout << s;
    return 0;
}