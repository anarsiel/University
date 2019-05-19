//
// Created by Anarsiel on 09/11/2018.
//

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("antiqs.in", "r", stdin);
    freopen("antiqs.out", "w", stdout);

    int n;
    cin >> n;

    vector<int> v(n);
    for (int i = 0; i < n; ++i)
        v[i] = i + 1;

    for (int i = 0; i < n; ++i)
        swap(v[i], v[i / 2]);

    for (int x : v)
        cout << x << " ";
    return 0;
}
/*

*/
