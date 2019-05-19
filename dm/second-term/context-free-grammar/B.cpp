//
// Created by Anarsiel on 2019-05-12.
//

#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>

using namespace std;

//std::string file_name = "epsilon";

int main() {
//    freopen((file_name + ".in").c_str(), "r", stdin);
//    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    string s;
    int m;
    char start;
    cin >> m >> start;
    getline(cin, s);

    unordered_map<string, string> mp;
    unordered_set<string> st;
    vector<string> v;

    for (int i = 0; i < m; ++i) {
        getline(cin, s);
        char c = s[0];
        string ss = (s.size() > 4 ? s.substr(5) : "");

        mp[ss] = c;
        if (ss == "") {
            st.insert(c + "");
        } else {
            v.push_back(ss);
        }
    }

    int last_sz;
    do {
        last_sz = st.size();


    } while (last_sz != st.size());

    for (auto it : st) {
        cout << it << endl;
    }
    return 0;
}
/*
4 S
S -> AB
A -> lef
B ->
B -> righ
 */