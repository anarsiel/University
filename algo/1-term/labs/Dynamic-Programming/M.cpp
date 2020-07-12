//
// Created by Anarsiel on 18/12/2018.
//

#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

struct Node {
    long long to, weight;

    Node(long long a, long long b) {
        to = a;
        weight = b;
    }

    Node() {}
};

vector<vector<Node>> v;
const long long INF = 1e18;

long long getbit(long long x, long long bit) {
    return ((x >> bit) & 1);
}

long long getWeight(long long a, long long b) {
    for (long long i = 0; i < int(v[a].size()); i++) {
        if (v[a][i].to == b) {
            return v[a][i].weight;
        }
    }
    return INF;
}

int main() {
    freopen("salesman.in", "r", stdin);
    freopen("salesman.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    long long n, m;
    cin >> n >> m;

    v.resize(n);

    for (long long i = 0; i < m; i++) {
        long long a, b, c;
        cin >> a >> b >> c;

        a--, b--;
        v[a].push_back(Node(b, c));
        v[b].push_back(Node(a, c));
    }

    vector<vector<long long>> dp((1 << n), vector<long long>(n, INF));

    for (long long i = 0; i < n; i++)
        dp[1 << i][i] = 0;

    for (long long mask = 0; mask < (1 << n); mask++) {
        for (long long from = 0; from < n; from++) {
            if (!getbit(mask, from)) continue;

            for (long long to = 0; to < n; to++) {
                if (getbit(mask, to)) continue;

                long long toMask = mask + (1 << to);
//                cout << getWeight(from, to) << endl;
                dp[toMask][to] = min(dp[toMask][to], dp[mask][from] + getWeight(from, to));
            }
        }
    }

    long long answ = INF;
    for (long long i = 0; i < n; i++) {
        answ = min(dp[(1 << n) - 1][i], answ);
    }

    cout << (answ >= INF ? -1 : answ) << endl;
    return 0;
}
/*
4 6
1 2 20 1 3 42 1 4 35 2 3 30 2 4 34 3 4 12

 4 3 1 2 1  1 3 1 1 4 1
 */
