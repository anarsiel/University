#include <iostream>
#include <vector>

using namespace std;

int main() {
    freopen("isheap.in", "r", stdin);
    freopen("isheap.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;
    vector<int> v((unsigned int)n + 1);
    for (int i = 1; i <= n; ++i)
        cin >> v[i];

    for (int i = 1; i <= n; ++i)
        if ((2 * i < n && v[i] > v[2 * i]) || (2 * i + 1 < n && v[i] > v[2 * i + 1])) {
//            cout << v[i] << " " << v[2 * i] <<  " " << v[2 * i + 1] << endl;
            cout << "NO";
            return 0;
        }

    cout << "YES";
    return 0;
}
/*
 *
 * 5 1 0 1 2 0
 * 5 1 3 2 5 4
 */
