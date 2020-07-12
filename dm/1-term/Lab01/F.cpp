//
// Created by Anarsiel on 04/11/2018.
//

#include <iostream>
#include <vector>

using namespace std;

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
    int n;
    cin >> n;

    n = (1 << n);

    string s = "";

    vector<string> inputstr(n);

    for (int i = 0; i < n; i++) {
        cin >> inputstr[i];
        string c;
        cin >> c;
        s.push_back(c[0]);
    }

    int len = int(s.length());

    string kekosAbrekos;
    kekosAbrekos.push_back(s[0]);
    for (int j = 0; j < len; j++) {
        string nnew;
        for (int k = 0; k < s.length() - 1; k++) {
            nnew.push_back(char((s[k] - '0' + s[k + 1] - '0') % 2 + '0')); //its not a bug
        }
        if (nnew.length() == 0)
            break;

        kekosAbrekos.push_back(nnew[0]);
        s = nnew;
    }

    for (int j = 0; j < kekosAbrekos.length(); j++) {
        cout << inputstr[j] << " " << kekosAbrekos[j] << endl;
    }
    return 0;
}
