//
// Created by Anarsiel on 02/12/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <set>
#include <string>

using namespace std;

string makeShift(const string &s) {
    string sn = "";
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '0') {
            sn.push_back('1');
        }
        if (s[i] == '1') {
            sn.push_back('2');
        }
        if (s[i] == '2') {
            sn.push_back('0');
        }
    }
    return sn;
}

int main() {
    freopen("telemetry.in", "r", stdin);
    freopen("telemetry.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int x, k;
    cin >> x >> k;

    vector<string> codes = {""};

    int cnt = 0;
    for (int i = 0; i < x; i++) {
        int codesSize = int(codes.size());

        for (int h = 1; h < k; h++) {
            if (h % 2 != 0) {
                for (int j = 0; j < codesSize; j++) {
                    codes.push_back(codes[codesSize - j - 1] + to_string(h));
                }
            } else {
                for (int j = 0; j < codesSize; j++) {
                    codes.push_back(codes[j] + to_string(h));
                }
            }
            cnt++;
        }

        for (int j = 0; j < codesSize; j++) {
            codes[j] = codes[j] + "0";
        }
        cnt++;

    }
    for (int i = 0; i < codes.size(); i++)
        cout << codes[i] << endl;
}