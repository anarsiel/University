//
// Created by Anarsiel on 03/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

void f(int n, int depth, int balance, string s) {
    if (depth == n) {
        cout << s;
        for (int i = 0; i < balance; i++)
            cout << ")";
        cout << endl;
        return;
    }
    f(n, depth + 1, balance + 1, s + "(");
    if (balance > 0)
        f(n, depth, balance - 1, s + ")");
}

int main() {
    freopen("brackets.in", "r", stdin);
    freopen("brackets.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n;
    cin >> n;
    f(n, 0, 0, "");
    return 0;
}