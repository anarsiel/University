#include <iostream>
#include <cstdio>
#include <vector>

typedef long long ll;

using namespace std;

const ll MOD = 1e9 + 239;
vector<vector<int>> st(1e6);

ll mod(ll x) {
    return (x % MOD + MOD) % MOD;
}

ll mod(ll x, ll mod) {
    return (x % mod + mod) % mod;
}

int getHash(int x) {
//    string s = to_string(x);
//    ll hash = 0;
//    ll p = 23909;
//    ll p_pow = 1;
//
//    for (int i = 0; i < (int) s.size(); ++i) {
//        hash += (s[i] + 1) * p_pow;
//        hash = mod(hash);
//
//        p_pow *= p;
//        p_pow = mod(p_pow);
//    }
    return (int) mod(x, 999931LL);
}

bool existsSt(int x) {
    int hash = getHash(x);
    hash %= (int) st.size();

    for (int j = 0; j < (int) st[hash].size(); j++) {
        if (st[hash][j] == x) {
            swap(st[hash][j], st[hash][st[hash].size() - 1]);
            return true;
        }
    }
    return false;
}

void insertSt(int x) {
    if (existsSt(x)) return;

    int hash = getHash(x);
    hash %= (int) st.size();

    st[hash].push_back(x);
}

void deleteSt(int x) {
    int hash = getHash(x);
    hash %= (int) st.size();

    for (int j = 0; j < (int) st[hash].size(); j++) {
        if (st[hash][j] == x) {
            swap(st[hash][j], st[hash][st[hash].size() - 1]);
            st[hash].pop_back();
        }
    }
}

std::string file_name = "set";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    cin.tie();
    cout.tie();

    string cmd;
    int x;

    while (cin >> cmd >> x) {
        if (cmd == "insert") {
            insertSt(x);
        } else if (cmd == "delete") {
            deleteSt(x);
        } else if (cmd == "exists") {
            cout << (existsSt(x) ? "true" : "false") << '\n';
        }
    }
    return 0;
}
