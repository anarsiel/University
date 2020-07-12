//
// Created by Anarsiel on 04/11/2018.
//

#include <iostream>
#include <vector>
#include <set>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, k;
    cin >> n >> k;

    vector<vector<int>> v(k, vector<int> (n));

    for (int i = 0; i < k; i++) {
        for (int j = 0; j < n; j++)
            cin >> v[i][j];
    }

    vector<int> trueString(n, 0);

    for (int kek = 0; kek < 239; kek++) {
        for (int i = 0; i < k; i++) {
            bool currentScopeValue = false;
            int withoutDenyIndex = -1;
            for (int j = 0; j < n; j++) {
                if ((v[i][j] == 1 && trueString[j] == 1) || (v[i][j] == 0 && trueString[j] == 0))
                    currentScopeValue = true;

                if (v[i][j] == 1)
                    withoutDenyIndex = j;
            }

            if (!currentScopeValue && withoutDenyIndex != -1) {
                trueString[withoutDenyIndex] = 1;
            }
        }
    }

    for (int i = 0; i < k; i++) {
        bool currentScopeValue = false;
        for (int j = 0; j < n; j++) {
            if ((v[i][j] == 1 && trueString[j] == 1) || (v[i][j] == 0 && trueString[j] == 0))
                currentScopeValue = true;
        }
        if (!currentScopeValue) {
            cout << "YES" << endl;
            return 0;
        }
    }
//    for (int i = 0; i < n; i++)
//        cout << trueString[i] << ' ' << endl;
    cout << "NO" << endl;
    return 0;
}/*
3 3
1 0 -1
0 1 0
-1 0 1

1 2
1
0
*/