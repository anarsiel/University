//
// Created by Anarsiel on 09/11/2018.
//

#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

double getNext(double a, double b) {
    return 2 * b + 2 - a;
}

pair<double, bool> getLast(double a, double b, int n) {
    double last = 0;
    bool belowZero = false;
    for (int i = 2; i < n; i++) {
        last = getNext(a, b);
        a = b;
        b = last;

        if (last <= 0)
            belowZero = true;
    }
    return {last, belowZero};
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("garland.in", "r", stdin);
    freopen("garland.out", "w", stdout);

    int n;
    cin >> n;
    double a;
    cin >> a;

    double l = 0, r = 1e6 + 239;
    double answ = 0;

    for (int kek = 0; kek < 1000; kek++) {
        double m = (l + r) / 2;
        auto last = getLast(a, m, n);

        if (!last.second) {
            answ = last.first;
            r = m;
        } else {
            l = m;
        }
    }

    cout << setprecision(2) << fixed;
    cout << answ << endl;
    return 0;
}
