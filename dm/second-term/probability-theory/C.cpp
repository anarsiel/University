//
// Created by Anarsiel on 2019-04-03.
//

#include <iostream>
#include <vector>

typedef long double ld;

using namespace std;

std::string file_name = "lottery";

int main() {
    freopen((file_name + ".in").c_str(), "r", stdin);
    freopen((file_name + ".out").c_str(), "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();
    cout.precision(20);

    int n, m;
    cin >> n >> m;
    vector<ld> a(m), b(m);

    for (int i = 0; i < m; ++i) {
        cin >> a[i] >> b[i];
    }
    a.push_back(1);

    ld answer = 0;
    ld cur_p = 1.0 / a[0];
    for (int i = 1; i < m; ++i) {
        answer += cur_p * (a[i] - 1) * b[i - 1] / a[i];
        cur_p *= (1.0 / a[i]);
    }
    answer += cur_p * b[m - 1];
    cout << n - answer;
    return 0;
}
/*
50 4
2 50
2 100
3 150
3 200

50 4
2 50
2 100
2 200
2 400
 */