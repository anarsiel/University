//
// Created by Anarsiel on 19/12/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

vector<vector<bool>> dp;

const int INF = 1e9 + 239;

int main() {
    ios_base::sync_with_stdio(false);

    string s, f;
    cin >> s >> f;

    int wasStar = false;

    string nS = "";

    int n = (int) s.size();
    int m = (int) f.size();
    for (int i = 0; i < n; i++) {
        if (s[i] != '*') {
            wasStar = false;
            nS.push_back(s[i]);
        } else {
            if (!wasStar) {
                nS.push_back('*');
            }
            wasStar = true;
        }
    }
    s = nS;
    n = (int) s.size();

    if (m == 0 && n != 0) {
        if (s[0] == '*' && n == 1)
            cout << "YES";
        else
            cout << "NO";

        return 0;
    }

//    cout << s << " " << f << endl;
    dp.resize(n + 2, vector<bool>(m + 2, false));

    if (m == 0) {
        for (int i = 0; i < n; ++i) {
            if (s[i] != '*') {
                cout << 'N' << 'O' << endl;
                return 0;
            }
        }
        cout << 'Y' << 'E' << 'S' << endl;
        return 0;
    }
    if (n == 0) {
        cout << 'N' << 'O' << endl;
        return 0;
    }
//    cout << s << " " << f << endl;
    dp.resize(n + 2, vector<bool>(m + 2, false));

// для каждого и поддерживаем минимальтный индекс жди с кототрого будет тру

    vector<int> was(n, 1e9);

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (s[i] == '?') {
                if (i != 0 && j != 0) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (i == 0 || (i == 1 && s[0] == '*')) dp[i][j] = (j == 0);
                }
            } else if (s[i] == '*') {
                if (i != 0 && was[i - 1] <= j) dp[i][j] = true;
                if (i == 0) dp[i][j] = true;
            } else if (s[i] == f[j]) {
                if (i != 0 && j != 0) dp[i][j] = dp[i - 1][j - 1];
                if (i == 0 && j == 0) dp[i][j] = true;
                if (i == 1 && j == 0 && s[0] == '*') dp[i][j] = 1;
            }
            if (dp[i][j]) was[i] = min(was[i], j);
        }
    }

//    cout << "  ";
//    for (int i = 0; i < m; i++)
//        cout << f[i] << ' ';
//    cout << endl;
//
//    for (int i = 0; i < n; i++) {
//        cout << s[i] << ' ';
//        for (int j = 0; j < m; j++) {
//            cout << dp[i][j] * 1 << ' ';
//        }
//        cout << endl;
//    }
//
//    for (int i = 0; i < n + 1; i++)
//        cout << starWars[i] << ' ';
//    cout << endl;
//
//    for (int i = 0; i < n + 1; i++)
//        cout << wasTrue[i] << ' ';
//    cout << endl;

    cout << (dp[n - 1][m - 1] ? "YES" : "NO");
}

/*

***?***
a
k?t?n
kitten

 */
