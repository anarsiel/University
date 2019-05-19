//
// Created by Anarsiel on 02/12/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

vector<string> answ;

void f(int x, int n) {
    vector<int> s;
    while (x > 0) {
        s.push_back(x % 2);
        x /= 2;
    }

    while (s.size() < n) {
        s.push_back(0);
    }

    for (int i = int(s.size() - 1); i > 0; i--) {
        if (s[i] == 1 && s[i - 1] == 1)
            return;
    }
    string ss;
    for (int i = int(s.size() - 1); i > -1; i--)
        ss.push_back(char(s[i] + '0'));
    answ.push_back(ss);
}

int main() {
    freopen("vectors.in", "r", stdin);
    freopen("vectors.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int x;
    cin >> x;

    for (int i = 0; i < (1 << x); i++) {
        f(i, x);
    }
    cout << answ.size() << endl;
    for (int i =0 ;i < answ.size(); i++)
        cout << answ[i] << endl;
    return 0;
}