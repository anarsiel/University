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
    freopen("chaincode.in", "r", stdin);
    freopen("chaincode.out", "w", stdout);
//    ios_base::sync_with_stdio(false);
//    cin.tie();
//    cout.tie();

    int x;
    cin >> x;

    vector<string> codes = {""};
    set<string> st;

    string code;

    for (int i = 0; i < x; ++i) {
        code += '0';
    }
    cout << code << endl;
    st.insert(code);

    while (true) {
        code = code.substr(1) + "1";
        if (st.count(code) == 1) {
            code.pop_back();
            code += "0";
            if (st.count(code) == 1) {
                return 0;
            }
            st.insert(code);
            cout << code << endl;
        } else {
            st.insert(code);
            cout << code << endl;
        }
    }
}