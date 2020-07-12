//
// Created by Anarsiel on 30/11/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

int main() {
    freopen("gray.in", "r", stdin);
    freopen("gray.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int x;
    cin >> x;

    vector<string> codes = {""};

    for (int i = 0; i < x; i++) {
        int codesSize = int(codes.size());
        for (int j = 0; j < codesSize; j++) {
            codes.push_back("1" + codes[codesSize - j - 1]);
        }

        for (int j = 0; j < codesSize; j++) {
            codes[j] = "0" + codes[j];
        }
    }
    for (int i = 0; i < codes.size(); i++)
        cout << codes[i] << endl;
    return 0;
}