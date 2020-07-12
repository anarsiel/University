//
// Created by Anarsiel on 11/05/2020.
//

#pragma GCC optimize("Ofast,no-stack-protector")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx,avx,avx2,tune=native")
#pragma GCC optimize("unroll-loops")
#pragma GCC optimize("fast-math")
#pragma GCC optimize("section-anchors")
#pragma GCC optimize("profile-values,profile-reorder-functions,tracer")
#pragma GCC optimize("vpt")
#pragma GCC optimize("rename-registers")
#pragma GCC optimize("move-loop-invariants")
#pragma GCC optimize("unswitch-loops")
#pragma GCC optimize("function-sections")
#pragma GCC optimize("data-sections")
#pragma GCC optimize("branch-target-load-optimize")
#pragma GCC optimize("branch-target-load-optimize2")
#pragma GCC optimize("btr-bb-exclusive")

#include <iostream>
#include <vector>

using namespace std;

const int INF = 1e9 + 239;

struct 

int main() {
    int n, m;
    cin >> n >> m;



    vector<vector<int>> table(n + 1, vector<int>(n + 1));

    vector<int> a(n + 1, 0), b(n + 1, 0), c(n + 1, 0), d(n + 1, 0);
    for (int i = 0; i < n; ++i) {
        c[0] = i + 1;
        int j0 = 0;

        vector<int> minimum(n + 1, INF);
        vector<bool> was(n + 1, false);

        for (;;) {
            was[j0] = true;

            int i0 = c[j0], diff = INF, j1 = 0;
            for (int j = 1; j < n + 1; ++j) {
                if (was[j]) continue;

//                for (int kk = 0; kk < table.size(); ++kk) {
//                    for (int kkk = 0; kkk < table.size(); ++kkk)
//                        cout << table[kk][kkk];
//                    cout << endl;
//                }
//                cout << endl;

                int cur = table[i0][j] - a[i0] - b[j];

                if (cur < minimum[j]) {
                    minimum[j] = cur;
                    d[j] = j0;
                }

                if (minimum[j] < diff) {
                    diff = minimum[j];
                    j1 = j;
                }
            }

            for (int j = 0; j < n + 1; ++j) {
                int wass = was[j];

                if (wass) {
                    a[c[j]] += diff;
                    b[j] -= diff;
                } else {
                    minimum[j] -= diff;
                }
            }
            j0 = j1;

            if (!c[j0])
                break;
        }

        for (;;) {
            int nextt = d[j0];
            c[j0] = c[nextt];
            j0 = nextt;

            if (!j0)
                break;
        }
    }

    long long sum = 0;
    vector<int> pairs(n + 1);
    for (int j = 1; j < n + 1; ++j) {
        pairs[c[j]] = j;
    }

    for (int i = 1; i < pairs.size(); ++i) {
        sum += table[i][pairs[i]];
    }

    cout << sum << endl;
    for (int i = 1; i < pairs.size(); ++i) {
        cout << i << ' ' << pairs[i] << endl;
//        cout << pairs[i] << ' ';
    }
}
/*
3
3 2 1
1 3 2
2 1 3

 */