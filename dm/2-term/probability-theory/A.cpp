#include <iostream>
#include <vector>

using namespace std;

std::string file_name = "exam";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();
    cout.precision(20);

    int k, n;
    cin >> k >> n;

    vector<int> p(k), m(k);
    int cnt_students = 0;

    for (int i = 0; i < k; ++i) {
        cin >> p[i] >> m[i];
        cnt_students += p[i];
    }

    long double answ = 0;

    for (int i = 0; i < k; ++i) {
        answ += 1.0 * p[i] * m[i] / (cnt_students * 100);
    }
    cout << answ;
    return 0;
}
