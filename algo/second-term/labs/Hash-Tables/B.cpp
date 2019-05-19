#include <iostream>
#include <cstdio>
#include <vector>

typedef long long ll;

using namespace std;

const ll MOD = 1e9 + 239;

struct Element{
    Element(const string &key, const string &value) {
        this->key = key;
        this->value = value;
    }

    string key;
    string value;
};

vector<vector<Element>> mp(1e6);

ll mod(ll x) {
    return (x % MOD + MOD) % MOD;
}

ll mod(ll x, ll mod) {
    return (x % mod + mod) % mod;
}

int getHash(string s) {
    ll hash = 0;
    ll p = 23909;
    ll p_pow = 1;

    for (int i = 0; i < (int) s.size(); ++i) {
        hash += (s[i] + 1) * p_pow;
        hash = mod(hash);

        p_pow *= p;
        p_pow = mod(p_pow);
    }
    return (int) mod(hash, 999931LL);
}

string existsMp(const string &x) {
    int hash = getHash(x);
    hash %= (int) mp.size();

    for (int j = 0; j < (int) mp[hash].size(); j++) {
        if (mp[hash][j].key == x) {
            return mp[hash][j].value;
        }
    }
    return "none";
}

void insertMp(const string &key, const string &value) {
    int hash = getHash(key);
    hash %= (int) mp.size();

    for (int j = 0; j < (int) mp[hash].size(); j++) {
        if (mp[hash][j].key == key) {
            mp[hash][j].value = value;
            return;
        }
    }
    Element element = Element(key, value);
    mp[hash].push_back(element);
}

void deleteSt(string x) {
    int hash = getHash(x);
    hash %= (int) mp.size();

    for (int j = 0; j < (int) mp[hash].size(); j++) {
        if (mp[hash][j].key == x) {
            swap(mp[hash][j], mp[hash][mp[hash].size() - 1]);
            mp[hash].pop_back();
        }
    }
}

std::string file_name = "map";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    string cmd;
    string key;

    while (cin >> cmd >> key) {
        if (cmd == "put") {
            string value;
            cin >> value;
            insertMp(key, value);
        } else if (cmd == "delete") {
            deleteSt(key);
        } else if (cmd == "get") {
            cout << existsMp(key) << '\n';
        }
    }
    return 0;
}
