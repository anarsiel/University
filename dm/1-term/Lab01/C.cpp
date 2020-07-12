//
// Created by Anarsiel on 02/11/2018.
//

#include <iostream>
#include <vector>

using namespace std;

bool f(int a, int b) {
    for(int i = 0; a > 0; i++) {
        if ((a & 1) > (b & 1)) {
            //cout << i << endl;
            return false;
        }

        a >>= 1;
        b >>= 1;
    }
    return true;
}

bool isPowerOfTwo(int x) {
    if (x == 0) return true;

    for (int i = 0; i < 100; i++) {
        if (x % 2 == 0)
            x /= 2;
        else
            return x == 1;
    }
    return x == 1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    vector<bool> post(5, false);

    for (int i = 0; i < n; i++) {
        int a;
        cin >> a;

        string s;
        cin >> s;

        if (s[0] != '0')
            post[0] = true;

        if (s[s.length() - 1] != '1')
            post[1] = true;

        for (int j = 0; j < s.length(); j++) {
            if (s[j] == s[s.length() - 1 - j])
                post[3] = true;
        }

        for (int k = 0; k < (1 << a); k++) {
            for (int j = k + 1; j < (1 << a); j++) {
                if (f(k, j) && (s[k] - '0' > s[j] - '0')) {
//                    cout << endl << k << " " << j << endl;
                    post[2] = true;
                }
            }
        }

        string kekosAbrekos;
        kekosAbrekos.push_back(s[0]);
        for (int j = 0; j < (1 << a); j++) {
            string nnew;
            for (int k = 0; k < s.length() - 1; k++) {
                nnew.push_back(char((s[k] - '0' + s[k + 1] - '0') % 2 + '0')); //its not a bug
            }


            kekosAbrekos.push_back(nnew[0]);

            s = nnew;
        }

        for (int j = 0; j < kekosAbrekos.length(); j++) {
            if (kekosAbrekos[j] == '1' && !isPowerOfTwo(j))
                post[4] = true;
        }
    }

    for (int i = 0; i < 5; i++)
        cout << !post[i] << " ";
    cout << endl;

//    cout << (post[0] && post[1] && post[2] && post[3] && post[4] ? "YES" : "NO");
    return 0;
}

/*
3
2 0001
2 0101
2 1111
 */