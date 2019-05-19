#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

void f(int x, int n) {
    vector<int> s;
    while (x > 0) {
        s.push_back(x % 2);
        x /= 2;
    }

    while (s.size() < n) {
        s.push_back(0);
    }

    for (int i = int(s.size() - 1); i > -1; i--)
        cout << s[i];
    cout << endl;
}

int main() {
    freopen("allvectors.in", "r", stdin);
    freopen("allvectors.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int x;
    cin >> x;

    for (int i = 0; i < (1 << x); i++)
        f(i, x);
    return 0;
}