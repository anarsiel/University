//
// Created by Anarsiel on 01/12/2018.
//

#include <iostream>
#include <vector>

using namespace std;

const int cnst = int(1e6) + 3;
vector<int> p, messagesRead, messagesBalance, rankk;

int getFather(int vertex) {
    if (p[vertex] == vertex) {
        return vertex;
    }

    return getFather(p[vertex]);
}

int getCntUnreadMessages(int vertex) {
    if (p[vertex] == vertex) {
        return messagesBalance[vertex];
    }
    int balance = getCntUnreadMessages(p[vertex]);
    return balance + messagesBalance[vertex];
}

void unite(int a, int b) {
    a = getFather(a);
    b = getFather(b);

    if (a == b) return;

    if (rankk[a] < rankk[b]) {
        p[a] = b;
        messagesBalance[a] -= messagesBalance[b];
    } else if (rankk[a] > rankk[b]) {
        swap(a, b);
        p[a] = b;
        messagesBalance[a] -= messagesBalance[b];
    } else {
        p[a] = b;
        messagesBalance[a] -= messagesBalance[b];
        rankk[b]++;
    }
}

int main() {
//    freopen(".in", "r", stdin);
//    freopen(".out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    cin >> n >> m;
    p.resize(n);
    messagesBalance.resize(n, 0);
    rankk.resize(n, 1);
    long long zerg = 0;

    for (int i = 0; i < n; ++i) {
        p[i] = i;
    }
    messagesRead.resize(n, 0);

    for (int i = 0; i < m; ++i) {
        int type;
        cin >> type;

        if (type == 1) {
            int x;
            cin >> x;
            x =  int((x + zerg)) % n;
            zerg = (30 * zerg + 239) % cnst;

            x = getFather(x);
            messagesBalance[x]++;
        } else if (type == 2) {
            int x, y;
            cin >> x >> y;
            x = int((x + zerg)) % n;
            y = int((y + zerg)) % n;

            if (getFather(x) != getFather(y)) {
                unite(x, y);
                zerg = (13 * zerg + 11) % cnst;
            }
        } else if (type == 3) {
            int x;
            cin >> x;
            x = int((x + zerg)) % n;

            int cntUnreadMessages = getCntUnreadMessages(x);
            cntUnreadMessages -= messagesRead[x];
            cout << cntUnreadMessages << '\n';

            messagesRead[x] += cntUnreadMessages;
            zerg = (100500 * zerg + cntUnreadMessages) % cnst;
        }
    }
    return 0;
}
