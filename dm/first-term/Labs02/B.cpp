#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    freopen("bwt.in", "r", stdin);
    freopen("bwt.out", "w", stdout);

    string s;
    cin >> s;

    int n = int(s.size());
    vector<string> v(n);

    for (int i = 0; i < n; i++) {
        v[i] = s;
        string newS = s.substr(1, s.size() - 1);
        newS.push_back(s[0]);
        s = newS;
    }
    sort(v.begin(), v.end());

    for (int i = 0; i < n; ++i) {
        cout << v[i][n - 1];
    }
    cout << endl;
    return 0;
}