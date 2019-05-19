#include <iostream>
#include <vector>

using namespace std;

vector<int> sort(vector<int> &v) {
    int n = int(v.size());
    if (v.size() == 1)
        return v;

    vector<int> left(n / 2);
    vector<int> right(n / 2 + n % 2);

    for (int i = 0; i < n / 2; i++) {
        left[i] = v[i];
    }

    for (int i = 0; i < n / 2 + n % 2; i++) {
        right[i] = v[n / 2 + i];
    }

    sort(left);
    sort(right);

    int indexL = 0, indexR = 0;

    for (int i = 0; i < n; i++) {
        if (indexL == int(left.size())) {
            v[i] = right[indexR++];
        } else if (indexR == int(right.size())) {
            v[i] = left[indexL++];
        } else {
            if (left[indexL] <= right[indexR]) {
                v[i] = left[indexL++];
            } else {
                v[i] = right[indexR++];
            }
        }
    }
    return v;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("sort.in", "r", stdin);
    freopen("sort.out", "w", stdout);

    int n;
    cin >> n;
    vector<int> v(n);

    for (int i = 0; i < n; i++)
        cin >> v[i];

    vector<int> left(n / 2);
    vector<int> right(n / 2 + n % 2);

    for (int i = 0; i < n / 2; i++) {
        left[i] = v[i];
    }

    for (int i = 0; i < n / 2 + n % 2; i++) {
        right[i] = v[n / 2 + i];
    }

    sort(v);

    for (int i = 0; i < int(v.size()); i++)
        cout << v[i] << ' ';
    cout << endl;
    return 0;
}
