//
// Created by Anarsiel on 04/11/2018.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <map>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("lzw.in", "r", stdin);
    freopen("lzw.out", "w", stdout);

    string s;
    cin >> s;

    map<string, int> mp;
    for (int i = 0; i < 26; i++) {
        string str = "a";
        str[0] += i;
        mp[str] = i;
    }

    int n = int(s.size());
    string t;
    int newNumber = 26;
    for (int i = 0; i < n; i++) {
        char c = s[i];

        string newS = t + c;
        if (i == n - 1) {
            if (mp.count(newS) > 0) {
                cout << mp[newS] << endl;
            } else {
                cout << mp[t] << ' ';
                t = "";
                t += c;
                cout << mp[t] << endl;
            }
            continue;
        }
        if (mp.count(newS) > 0) {
            t = newS;
        } else {
            cout << mp[t] << ' ';
            mp[newS] = newNumber++;
            t = "";
            t += c;
        }
    }
    return 0;
}