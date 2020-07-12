//
// Created by Anarsiel on 27/05/2020.
//

#include <iostream>
#include <vector>

using namespace std;

const int SIZE = 1000000 + 1;

bool boools[SIZE];
vector<int> v[SIZE];
bool is_prime[SIZE];
int order[300001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    for (int i = 0; i < n; ++i) {
        cin >> order[i];
        boools[order[i]] = true;
    }

    is_prime[0] = true;
    is_prime[1] = true;
    for (int i = 2; i <= SIZE; ++i) {
        if (!is_prime[i] && i * 1ll * i <= SIZE) {
            for (int j = i * i; j <= SIZE; j += i) {
                if (boools[j])
                    v[j].push_back(i);

                is_prime[j] = true;
            }
        }
    }

    for (int i = 0; i < n; ++i) {
        int x = order[i];
        if (!is_prime[x]) {
            cout << x << '\n';
            continue;
        }

        for (int d : v[x]) {
            while (x % d == 0) {
                cout << d << ' ';
                x /= d;
            }
        }
        if (x != 1)
            cout << x;
        cout << '\n';
    }
    return 0;
}
