//
// Created by Anarsiel on 04/11/2018.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("mtf.in", "r", stdin);
    freopen("mtf.out", "w", stdout);

    string s;
    cin >> s;

    int n = int(s.size());
    vector<string> v(n);
    vector<int> position(26);

    for (int i = 0; i < 26; i++)
        position[i] = i + 1;

    for (int i = 0; i < n; i++) {
        char c = s[i];
//        if (position[c - 'a'] == -1)
//            position[c - 'a'] = i + 1;

        cout << position[c - 'a'] << ' ';

        for (int j = 0; j < 26; j++) {
            if (position[j] < position[c - 'a'])
                position[j]++;
        }
        position[c - 'a'] = 1;
    }
    return 0;
}