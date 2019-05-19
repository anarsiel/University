//
// Created by Anarsiel on 14/12/2018.
//

#include <iostream>
#include <fstream>
#include <deque>

using namespace std;

int const INF = 1e9 + 239;

int bS(deque<int> &d, int x) {
    int n = int(d.size());
    int l = -1, r = n - 1;

    while (l != r - 1) {
        int m = (l + r) / 2;
//        cout << l << " " << r;
        if (d[m] >= x)
            r = m;
        else
            l = m;
    }

    return r;
}

deque<int> nvp(deque<int> a)  {
    int n = int(a.size());
    deque<int> d(n + 1, INF), index(n + 1, -1), prev(n);
    int length = 0;

    d[0] = -INF;

    for (int i = 0; i < n; i++) {
        int j = bS(d, a[i]);

        if (d[j - 1] < a[i] && a[i] < d[j]) {
            d[j] = a[i];
            index[j] = i;
            prev[i] = index[j - 1];
            length = max(length, j);
        }
    }

    deque<int> answ;

    int indexx = index[length];

    while (indexx != -1) {
        answ.push_front(a[indexx]);
        indexx = prev[indexx];
//        cout << indexx;
    }

    return answ;
}

int main() {
    freopen("lis.in", "r", stdin);
    freopen("lis.out", "w", stdout);
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;

    deque<int> v(n);
    for (int i = 0; i < n; i++)
        cin >> v[i];
//    cout << "kek" << endl;
    deque<int> answ = nvp(v);
//    cout << "kek" << endl;

    cout << int(answ.size()) << endl;
    for (int i = 0; i < int(answ.size()); i++)
        cout << answ[i] << ' ';
}

/*
20
1 6 3 8 3 4 7 8 9 2 4 19 2 34 5 2 0 1 3 5
8
1 3 4 7 8 9 19 34
 */
