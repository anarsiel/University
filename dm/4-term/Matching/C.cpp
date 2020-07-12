//
// Created by Anarsiel on 18/06/2020.
//

#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_set>
#include <unordered_map>

using namespace std;

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

#define mp             make_pair
#define pb             push_back
#define fi             first
#define se             se
#define sz(x)          (long long)((x).size())
#define all(x)         (x).begin(), (x).end()
#define sci(x)         long long x;       scanf("%d", &x);
#define scii(x, y)     long long x, y;    scanf("%d %d", &x, &y);
#define sciii(x, y, z) long long x, y, z; scanf("%d %d %d", &x, &y, &z);
#define TC(x)          sci(x); while(x --)
#define eprlong longf(...)   fprlong longf(stderr, __VA_ARGS__)
#define debug(x)       { cerr << #x << " = " << x << endl; }
#define rep(i, x, y)   for (__typeof(x) i = x; i <= y; i ++)
#define repi(i, x, y)  for (__typeof(x) i = x; i >= y; i --)
#define fore(itr, x)   for (__typeof(x.begin()) itr = x.begin(); itr != x.end(); itr ++)
#define forei(itr, x)  for (__typeof(x.end()) itr = x.end() - 1; itr != x.begin() - 1; itr --)
typedef long long ll;
typedef pair<long long, long long> ii;
typedef pair<ii, long long> iii;
typedef vector<long long> vi;
typedef vector<ii> vii;
typedef vector<iii> viii;
const long long inf = 0;
const double eps = 0;
const long long ms = 0;
const long long md = 0;

bool dfs(long long vertex, vector<unordered_map<long long, long long>> &urtexes, vector<long long> &coinc,
         vector<bool> &marked) {
    if (not marked.at(vertex)) {
        marked.at(vertex) = 1;
        for (auto p : urtexes.at(vertex)) {
            long long j = p.first;
            if (coinc.at(j) == -1 || dfs(coinc.at(j), urtexes, coinc, marked)) {
                coinc.at(j) = vertex;
                return 1;
            }
        }
    }
    return 0;
}

vector<long long> f2(vector<unordered_map<long long, long long>> &urtexes, long long Axs, long long Bxs,
                     vector<long long> &order) {
    vector<long long> matching(Bxs + 1, -1);
    vector<bool> visited(Axs + 1);

    for (long long i = 1; i <= Axs; ++i) {
        visited.assign(visited.size(), 0);
        dfs(order.at(i), urtexes, matching, visited);
    }

    return matching;
}

void
f3(long long vertex, long long ch, long long prev, vector<unordered_set<long long>> &edges1matching,
   vector<unordered_set<long long>> &edges2matching,
   vector<long long> &curPath, vector<bool> &visited1, vector<bool> &visited2) {
    curPath.push_back(vertex);
    visited1.at(vertex) = 1;
    long long next = -1;
    for (long long t : edges1matching.at(vertex)) {
        if (t == prev) continue;

        next = t;
    }

    if (next != -1)
        f3(next, ch ^ 3, vertex, edges2matching, edges1matching, curPath, visited2, visited1);
}

void
f4(long long i, vector<unordered_set<long long>> &edgesAmatching, vector<unordered_set<long long>> &edgesBmatching,
   vector<bool> &visitedA, vector<bool> &visitedB) {

    if (visitedA.at(i))
        return;

    visitedA.at(i) = 1;
    long long b = *edgesAmatching.at(i).begin();
    visitedB.at(b) = 1;
    auto itrb = edgesBmatching.at(b).begin();
    if ((*itrb) == i)
        itrb++;
    long long next = *itrb;

    edgesBmatching.at(b).erase(next);
    edgesAmatching.at(next).erase(b);
    f4(next, edgesAmatching, edgesBmatching, visitedA, visitedB);
}

void f7(vector<unordered_set<long long>> &edgesAmatching, vector<unordered_set<long long>> &edgesBmatching,
        vector<bool> &visitedA, vector<bool> &visitedB, const vector<long long> &wA) {
    for (long long i = 1; i < edgesAmatching.size(); ++i) {
        if (visitedA.at(i)) continue;
        if (edgesAmatching.at(i).size() != 1) continue;

        vector<long long> path;
        f3(i, 1, -239, edgesAmatching, edgesBmatching, path, visitedA, visitedB);
        if (path.size() % 2 == 0) {
            for (long long i = 1; i < path.size() - 1; i += 2) {
                edgesBmatching.at(path.at(i)).erase(path.at(i + 1));
                edgesAmatching.at(path.at(i + 1)).erase(path.at(i));
            }
        } else {
            if (wA.at(path.at(0)) > wA.at(*(--path.end()))) {
                for (long long j = 1; j < path.size(); j += 2) {
                    edgesBmatching.at(path.at(j)).erase(path.at(j + 1));
                    edgesAmatching.at(path.at(j + 1)).erase(path.at(j));
                }
            } else {
                for (long long j = 0; j < path.size() - 1; j += 2) {
                    edgesAmatching.at(path.at(j)).erase(path.at(j + 1));
                    edgesBmatching.at(path.at(j + 1)).erase(path.at(j));
                }
            }
        }
    }
}

int main() {
    long long n, m, e;
    scanf("%lld", &n);
    scanf("%lld", &m);
    scanf("%lld", &e);

    vector<unordered_map<long long, long long>> edgesA(n + 1, unordered_map<long long, long long>());
    vector<unordered_map<long long, long long>> edgesB(m + 1, unordered_map<long long, long long>());

    vector<unordered_set<long long>> eMH(n + 1, unordered_set<long long>());
    vector<unordered_set<long long>> eMG(m + 1, unordered_set<long long>());

    vector<long long> aA(n + 1);
    vector<long long> bB(m + 1);
    vector<pair<long long, long long>> ordera(n + 1);
    ordera[0] = {1000000239, 0};

    vector<pair<long long, long long>> orderb(m + 1);
    orderb[0] = {1000000239, 0};
    vector<long long> orderA(n + 1);
    vector<long long> orderB(m + 1);

    for (long long i = 1; i <= n; ++i) {
        scanf("%lld", &aA.at(i));
    }

    for (long long i = 1; i <= m; ++i) {
        scanf("%lld", &bB.at(i));
    }

    for (long long i = 1; i <= m; ++i) {
        orderb[i] = {bB[i], i};
    }

    for (long long i = 1; i <= n; ++i) {
        ordera[i] = {aA[i], i};
    }

    long long a, b;
    for (long long i = 1; i <= e; ++i) {
        scanf("%lld", &a);
        scanf("%lld", &b);

        edgesA.at(a).insert({b, i});
        edgesB.at(b).insert({a, i});
    }

    sort(ordera.begin(), ordera.end());
    for (long long i = ordera.size() - 1; i >= 0; --i) {
        orderA[ordera.size() - 1 - i] = ordera[i].second;
    }

    sort(orderb.begin(), orderb.end());
    for (long long i = orderb.size() - 1; i >= 0; --i) {
        orderB[orderb.size() - 1 - i] = orderb[i].second;
    }

    vector<long long> matchingA = f2(edgesA, n, m, orderA);
    vector<long long> matchingB = f2(edgesB, m, n, orderB);

    for (long long i = 1; i < matchingA.size(); ++i) {
        if (matchingA.at(i) == -1) continue;

        eMG.at(i).insert(matchingA.at(i));
        eMH.at(matchingA.at(i)).insert(i);
    }
    for (long long i = 1; i < matchingB.size(); ++i) {
        if (matchingB.at(i) == -1) continue;

        eMH.at(i).insert(matchingB.at(i));
        eMG.at(matchingB.at(i)).insert(i);
    }


    vector<bool> visitedA(n + 1, 0);
    vector<bool> visitedB(m + 1, 0);

    f7(eMH, eMG, visitedA, visitedB, aA);

    f7(eMG, eMH, visitedB, visitedA, bB);

    for (long long i = 1; i < eMH.size(); ++i) {
        if (eMH.at(i).size() != 2) continue;

        f4(i, eMH, eMG, visitedA, visitedB);
    }

    vector<long long> res;
    long long sum = 0;
    for (long long i = 1; i < eMH.size(); ++i) {
        if (eMH.at(i).size() != 1) continue;

        res.push_back(edgesA.at(i).at(*eMH.at(i).begin()));
        sum += aA.at(i) + bB.at(*eMH.at(i).begin());
    }

    cout << sum << endl << res.size() << endl;
    for (auto x : res) {
        cout << x << ' ';
    }

    return 0;
}