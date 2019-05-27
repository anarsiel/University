//
// Created by Anarsiel on 2019-05-27.
//
#define _FORTIFY_SOURCE 0
#pragma GCC optimize("Ofast")
#pragma GCC optimize("no-stack-protector")
#pragma GCC optimize("unroll-loops")
#pragma GCC target("sse,sse2,sse3,ssse3,popcnt,abm,mmx,tune=native")
#pragma GCC optimize("fast-math")

#include <iostream>
#include <math.h>
#include <algorithm>
#include <iomanip>
#include <vector>
#include <set>
#include <map>
#include <deque>
#include <stack>
#include <unordered_map>

using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<ll, ll> pl;
typedef pair<ld, ld> pd;

const ll N = 5 * 1e6 + 100;
const ll M = 1e4 + 100;
const int INF = 1e9 + 123;
using namespace std;


#define  x first
#define  y second
#define pb push_back
#define eb emplace_back

int R;

struct Event {
    int x;
    int y1;
    int y2;
    int type;

    Event(int _x, int _y1, int _y2, int _type) {
        x = _x;
        y1 = _y1;
        y2 = _y2;
        type = _type;
    }
};

struct Node {
    int value;
    int d;
};

vector<Event> a;
Node t[N];

bool cmp(Event a, Event b) {
    if (a.x == b.x) {
        return a.type > b.type;
    }

    return a.x < b.x;
}

void push(int v) {
    if (t[v].d == 0) {
        return;
    }

    t[v * 2 + 1].d += t[v].d;
    t[v * 2 + 2].d += t[v].d;
    t[v].value += t[v].d;
    t[v].d = 0;
}


void add_on_segment(int qleft, int qright, int x, int v, int left, int right) {
    push(v);

    if (left >= qleft && right <= qright) {
        t[v].d += x;
        push(v);
        return;
    }

    if (right < qleft || left > qright) {
        return;
    }

    int mid = (left + right) / 2;

    add_on_segment(qleft, qright, x, v * 2 + 1, left, mid);
    add_on_segment(qleft, qright, x, v * 2 + 2, mid + 1, right);

    t[v].value = max(t[v * 2 + 1].value, t[v * 2 + 2].value);
}

int query_max(int v, int l, int r, int qleft, int qright) {
    push(v);

    if (l >= qleft && r <= qright) {
        return t[v].value;
    }

    if (r < qleft || l > qright) {
        return -INF;
    }

    int mid = (l + r) / 2;
    push(v);
    int s1 = query_max(v * 2 + 1, l, mid, qleft, qright);
    int s2 = query_max(v * 2 + 2, mid + 1, r, qleft, qright);
    return max(s1, s2);
}

int find_max(int qleft, int qright, int value) {
    if (qleft == qright) {
        return qleft;
    }

    int mid = (qleft + qright) / 2;
    if (query_max(0, 0, R, qleft, mid) == value) {
        return find_max(qleft, mid, value);
    } else {
        return find_max(mid + 1, qright, value);
    }
}

int main() {
    int n;
    cin >> n;

    int minNegativeX = 0;
    int minNegativeY = 0;

    for (int i = 0; i < n; i++) {
        pair<int, int> leftUp;
        pair<int, int> rightDown;

        cin >> leftUp.x >> leftUp.y;
        cin >> rightDown.x >> rightDown.y;

        minNegativeX = min(minNegativeX, leftUp.x);
        minNegativeX = min(minNegativeX, rightDown.x);

        minNegativeY = min(minNegativeY, leftUp.y);
        minNegativeY = min(minNegativeY, rightDown.y);

        a.emplace_back(leftUp.x, rightDown.y, leftUp.y, 1);
        a.emplace_back(rightDown.x, rightDown.y, leftUp.y, -1);
    }

    for (int i = 0; i < (int)a.size(); i++) {
        a[i].y1 += abs(minNegativeY);
        a[i].y2 += abs(minNegativeY);
        a[i].x += abs(minNegativeX);
    }


    sort(a.begin(), a.end(), cmp);
/*
    for (auto event : a) {
        cout << event.type << " " << event.x << " " << event.y1 << " " << event.y2 << endl;
    }
    return 0;
    */

    int ans = 0;
    int numOfEvent = 0;
    int ansY = 0;

    /*for (int j = (i == 0 ? 0 : a[i - 1].x); j < a[i].x; j++) {
    ans = max(ans, max(0, 0, 2 * 1e5 + 100, ))
    }*/

    R = static_cast<int>(1e6 + 100) + minNegativeY;

    for (int i = 0; i < (int)a.size(); i++) {
        Event event = a[i];
        int left = min(event.y1, event.y2);
        int right = max(event.y1, event.y2);
        if (event.type == 1) {
            add_on_segment(left, right, 1, 0, 0, R);
        } else {
            add_on_segment(left, right, -1, 0, 0, R);
        }
        int query = query_max(0, 0, R, 0, R);
        if (query > ans) {
            ans = query;
            numOfEvent = i;
            ansY = find_max(0, R, query);
        }
    }

    /*for (auto event : a) {
        cout << event.type << " " << event.x << " " << event.y1 << " " << event.y2 << endl;
    }
    cout << endl;

    cout << minNegativeX << " " << minNegativeY << endl;*/
    cout << ans << endl;
    cout << a[numOfEvent].x + minNegativeX << " " << ansY + minNegativeY << endl;

    return 0;
}
/*
2
0 0 3 3
1 1 4 4

1
0 0 1 1

4
0 0 1 1
0 1 1 2
1 0 2 1
1 1 2 2
 */
