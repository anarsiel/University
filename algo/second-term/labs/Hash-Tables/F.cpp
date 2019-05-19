//
// Created by Anarsiel on 2019-03-23.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    vector<string> v = {"AaAaAa", "AaAaBB", "AaBBAa", "AaBBBB",
                        "BBAaAa", "BBAaBB", "BBBBAa", "BBBBBB"};

    int k;
    cin >> k;
    for (int i = 0; i < k; ++i) {
        for (int j = 0; j < (int) v.size(); ++j)
            cout << v[j];
        cout << endl;

        next_permutation(v.begin(), v.end());
    }
    return 0;
}
