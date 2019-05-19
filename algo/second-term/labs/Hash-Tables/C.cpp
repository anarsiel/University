//
//  Created by Anarsiel on 2019-03-20.
//

#include <iostream>
#include <cstdio>
#include <vector>

typedef long long ll;

using namespace std;

const ll MOD = 1e9 + 239;

struct Node {
    Node() {}

    Node(const string &key, const string &value) {
        this->key = key;
        this->value = value;
    }

    string key;
    string value;

    Node *next, *prev;
};

Node* head = new Node();

struct Element{
    Element(const string &key, const string &value) {
        this->key = key;
        this->value = value;
    }

    string key;
    string value;

    Node *node;
};

int nodesSz;
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
//    hash %= (int) mod(hash, 239999LL);
    return (int) hash % (int)  mp.size();
}

string existsMp(const string &x) {
    int hash = getHash(x);

    for (int j = 0; j < (int) mp[hash].size(); j++) {
        if (mp[hash][j].key == x) {
            return mp[hash][j].value;
        }
    }
    return "none";
}

void insertMp(const string &key, const string &value) {
    int hash = getHash(key);
    if (existsMp(key) == value) return;

    for (int j = 0; j < (int) mp[hash].size(); j++) {
        if (mp[hash][j].key == key) {
            mp[hash][j].value = value;
            mp[hash][j].node->value = value;
            return;
        }
    }

    Node* node = new Node(key, value);
    node->next = nullptr;
    node->prev = nullptr;

    nodesSz++;
    head->next = node;
    node->prev = head;

    Element element = Element(key, value);
    element.node = node;
    head = node;
    mp[hash].push_back(element);
}

void deleteMp(const string &x) {
    if (nodesSz == 0) return;
    if (existsMp(x) == "none") return;

    int hash = getHash(x);

    for (int j = 0; j < (int) mp[hash].size(); j++) {
        if (mp[hash][j].key == x) {
            Node *node = mp[hash][j].node;

            if (head == node) {
                head = node->prev;
            }

            if (node->prev != nullptr)
                node->prev->next = node->next;
            if (node->next != nullptr)
                node->next->prev = node->prev;

            swap(mp[hash][j], mp[hash][mp[hash].size() - 1]);
            mp[hash].pop_back();
            nodesSz--;
        }
    }
}

string nextMp(const string &x) {
    int hash = getHash(x);

    for (int j = 0; j < (int) mp[hash].size(); j++) {
        if (mp[hash][j].key == x) {
            return (mp[hash][j].node->next == nullptr ? "none" : mp[hash][j].node->next->value);
        }
    }
    return "none";
}

string prevMp(const string &x) {
    int hash = getHash(x);

    for (int j = 0; j < (int) mp[hash].size(); j++) {
        if (mp[hash][j].key == x) {
            return (mp[hash][j].node->prev == nullptr ? "none" : mp[hash][j].node->prev->value);
        }
    }
    return "none";
}

std::string file_name = "linkedmap";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    string cmd;
    string key;

    head->prev = nullptr;
    head->next = nullptr;
    head->value = "none";
    head->key = "Blagoi";
    nodesSz = 0;

    while (cin >> cmd) {
        cin >> key;

        if (cmd == "put") {
            string value;
            cin >> value;
            insertMp(key, value);
        } else if (cmd == "delete") {
            deleteMp(key);
        } else if (cmd == "get") {
            cout << existsMp(key) << '\n';
        } else if (cmd == "next") {
            cout << nextMp(key) << '\n';
        } else if (cmd == "prev") {
            cout << prevMp(key) << '\n';
        }
    }
    return 0;
}
