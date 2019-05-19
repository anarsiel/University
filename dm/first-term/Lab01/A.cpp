#include <iostream>
#include <vector>
#include <set>

using namespace std;

bool getBit(int x, int bitInd) {
    return ((x >> bitInd) & 1) == 1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<int> a(m), b(m);
    set<pair<int, int>> st;

    for (int i = 0; i < m; i++) {
        cin >> a[i] >> b[i];

        st.insert({min(a[i], b[i]), max(a[i], b[i])});
    }

//    auto it = st.begin();
//    while (it != st.end()) {
//        int f = (*it).first;
//        int s = (*it).second;
//        cout << f << ' ' << s << endl;
//
//        it++;
//    }

    for (int mask = 0; mask < (1 << n); mask++) {
        bool fRes = true;

        auto it = st.begin();
        while (it != st.end()) {
            int f = (*it).first;
            int s = (*it).second;

            bool ff = bool (f > 0 ? getBit(mask, abs(f) - 1) : !getBit(mask, abs(f) - 1));
            bool ss = bool (s > 0 ? getBit(mask, abs(s) - 1) : !getBit(mask, abs(s) - 1));

            fRes = fRes && (ff || ss);
            it++;
        }

        if (fRes) {
            cout << "NO" << endl;
            return 0;
        }
    }
    cout << "YES" << endl;
    return 0;
}
/*
2 4
-1 2
-2 -2
2 -1
1 1

2 3
-1 2
-2 -2
2 -1
*/