//
// Created by Anarsiel on 03/12/2018.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>

using namespace std;

long long factorial(long long x) {
    long long answ = 1;

    for (long long i = 2; i <= x; i++)
        answ *= i;

    return answ;
}

int main() {
    freopen("num2perm.in", "r", stdin);
    freopen("num2perm.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    long long n, k;
    cin >> n >> k;
    
    vector<bool> used(n + 1, false);
    vector<long long> answ(n + 1, 0);

    long long index = 0;
    for (long long i = 1; i < n + 1; i++) {
        index = k / (factorial(n - i));
        k = k % factorial(n - i);
        
        long long curFree = 0;
        for (long long j = 1; j < n + 1; j++) {
            if (!used[j]) {
                curFree++;
                if (curFree == index + 1) {
                    answ[i] = j;
                    used[j] = true;
                }

            }
        }
    }

    for (long long x = 1; x < answ.size(); x++)
        cout << answ[x] << ' ';
    cout << endl;
    return 0;
}