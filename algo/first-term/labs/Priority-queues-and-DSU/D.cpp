//
// Created by Anarsiel on 23/11/2018.
//

#include <iostream>
#include <vector>

using namespace std;

int n;
vector<int> closest;
vector<int> a;

int mod(int x, int mod = n) {
    return (x % mod + mod) % mod;
}

int get(int vertex) {
    if (closest[vertex] == vertex)
        return vertex;

    return closest[vertex] = get(closest[vertex]);
}

void unite(int next) {
    next = get(next);

    closest[next] = get(mod(next + 1));
}

int main() {
    freopen("parking.in", "r", stdin);
    freopen("parking.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n;
    closest.resize(n);
    a.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
        closest[i] = i;
    }

    for (int i = 0; i < n; i++) {
        int space = get(a[i]);
        unite(space);
        cout << space + 1 << " ";
    }

    return 0;
}
