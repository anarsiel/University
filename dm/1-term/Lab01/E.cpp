//
// Created by Anarsiel on 07/11/2018.
//

#include <iostream>
#include <vector>

using namespace std;

int depthMax = 0, depth = 0;
vector<int> m;
vector<vector<int>> prevv;
vector<bool> used;

void dfsDepth(int vertex) {
    used[vertex] = true;
    depth++;
    depthMax = max(depthMax, depth);

    for (int to: prevv[vertex]) {
        dfsDepth(to);
    }
    depth--;
}

int getBit(int x, int bit) {
    return ((x >> bit) & 1);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    m.resize(n);
    prevv.resize(n);
    used.resize(n, false);
    vector<vector<int>> answ(n);

    int cntVariables = 0;
    vector<int> variables;

    for (int i = 0; i < n; i++) {
        cin >> m[i];

        for (int j = 0; j < m[i]; j++) {
            int a;
            cin >> a;
            a--;
            prevv[i].push_back(a);
        }

        if (m[i] > 0) {
            for (int j = 0; j < (1 << m[i]); j++) {
                int a;
                cin >> a;
                answ[i].push_back(a);
            }
        } else {
            cntVariables++;
            variables.push_back(i);
        }
    }
    dfsDepth(n - 1);
    cout << depthMax - 1 << endl;

    vector<int> currentValues(n, 0);

    for (int i = 0; i < (1 << cntVariables); i++) {
        for (int j = 0; j < cntVariables; j++) {
            currentValues[variables[j]] = getBit(i, cntVariables - j - 1);
        }
        for (int j = 0; j < n; j++) {
            int number = 0;
            for (int k : prevv[j]) {
                number *= 2;
                number += currentValues[k];
            }

            if (prevv[j].size() > 0) {
                currentValues[j] = answ[j][number];
            }
        }
        cout << currentValues[n - 1];

        for (int j = 0; j < currentValues.size(); j++)
            currentValues[j] = 0;
    }
    return 0;
}
/*
5
0
0
2 1 2
1 1 0 1
0
2 3 4
1 0 0 1
 */