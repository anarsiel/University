#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;

    vector<int> wanted(n);
    int mx = 0;
    for (int i = 0; i < n; ++i) {
        cin >> wanted[i];
        mx = max(mx, wanted[i]);
    }

    vector<char> is_prime(mx + 1, true);
    is_prime[0] = is_prime[1] = false;
    for (int i = 2; i <= mx; ++i) {
        if (is_prime[i] && i * 1ll * i <= mx) {
            for (int j = i * i; j <= mx; j += i) {
                is_prime[j] = false;
            }
        }
    }

    for (int i = 0; i < n; ++i) {
        cout << (is_prime[wanted[i]] ? "YES" : "NO") << '\n';
    }
    return 0;
}
