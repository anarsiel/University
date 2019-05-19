//
// Created by Anarsiel on 06/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <deque>

using namespace std;

void nextSumPartition(deque<int> &v) {
    v[v.size() - 1]--;
    v[v.size() - 2]++;
    if (v[v.size() - 1] < v[v.size() - 2]) {
        v[v.size() - 2] += v[v.size() - 1];
        v.pop_back();
    } else {
        while (v[v.size() - 2] * 2 <= v[v.size() - 1]) {
            v.push_back(v[v.size() - 1] - v[v.size() - 2]);
            v[v.size() - 2] = v[v.size() - 3];
        }
    }
}

deque<int> sort(deque<int> &v) {
    int n = int(v.size());
    if (v.size() == 1 || v.size() == 0)
        return v;

    deque<int> left(n / 2);
    deque<int> right(n / 2 + n % 2);

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
//    freopen("nextsetpartition.in", "r", stdin);
//    freopen("nextsetpartition.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n, k;
    deque<deque<int>> deque1;
    deque<int> suD;

    while (cin >> n >> k) {
        if (n == 0 && n == k) return 0;

        deque1.clear();
        suD.clear();
        deque1.resize(k);

        string s1;
        getline(cin, s1);

        for (int i = 0; i < k; i++) {
            string s2, cur;
            getline(cin, s2);

            for (int j = 0; j < s2.size(); j++) {
                if (s2[j] == ' ') {
                    deque1[i].push_back(stoi(cur));
                    cur = "";
                } else {
                    cur.push_back(s2[j]);
                }
            }
            deque1[i].push_back(stoi(cur));
        }


        bool flag = false;
        for (int i = k - 1; i > -1; i--) {
            int mn = 1e9 + 239, ex = -1;

            for (int j = 0; j < suD.size(); j++)
                if (suD[j] < mn && suD[j] > deque1[i].back())
                    mn = suD[j], ex = j;

            if (ex != -1) {
                deque1[i].push_back(mn);
//                deque1.pop_front()
                for (int j = ex; j < suD.size() - 1; j++)
                    suD[j] = suD[j + 1];
                suD.pop_back();
//                suD.pop_front();
                break;
            }

            int last = deque1[i].size() - 1;

            for (int j = last; j > -1; j--)  {
                int mn = 1e9 + 239, ex = -1;

                for (int h = 0; h < suD.size(); h++)
                    if (suD[h] < mn && suD[h] > deque1[i][j])
                        mn = suD[h], ex = h;
                if  (ex != -1 && j > 0) {
                    flag = true;
                    swap(deque1[i][j], suD[ex]);
                    break;
                } else {
                    suD.push_back(deque1[i].back());
                    deque1[i].pop_back();
                }
            }

            if (flag)
                break;
        }

        suD = sort(suD); // it is faster

        for (int i = 0; i < suD.size(); i++) {
            deque<int> cur;
            cur.push_back(suD[i]);
            deque1.push_back(cur);
        }

        for (int i = 0; i < deque1.size(); ) {
            if (!deque1[i].empty()) {
                i++;
            } else {
                for (int j = i; j < deque1.size() - 1; j++)
                    deque1[j] = deque1[j + 1];
                deque1.pop_back();
            }
        }

        cout << n << " " << deque1.size() << endl;

        for (auto x : deque1) {
            for (auto xx : x)
                cout << xx << ' ';
            cout << endl;
        }
        cout << endl;
    }
}
/*
5 2
1 2 3
4 5

5 2
1 3 5
2 4

5 1
1 2 3 4 5

5 5
1
2
3
4
5

0 0
*/