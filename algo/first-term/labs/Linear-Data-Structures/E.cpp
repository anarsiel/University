#include <iostream>
#include <cstdio>
#include <cstdlib>

using namespace std;

char* values = new char[1];
int tail = -1;
int length = 1;

void doubleArraySizeIncrease() {
    int doubledLength = length * 2;
    char* doubledValues = new char[doubledLength];

    memcpy(doubledValues, values, length * sizeof(char));

    length = doubledLength;
    delete[] values;
    values = doubledValues;
}

void doubleArraySizeDecrease() {
    int doubledLength = length / 2;
    char* doubledValues = new char[doubledLength];

    memcpy(doubledValues, values, length / 2 * sizeof(char));

    length = doubledLength;
    delete[] values;
    values = doubledValues;
}

char getLast() {
    return (tail >= 0 ? values[tail] : '.');
}

void deleteLast() {
    tail--;

    if (2 * tail < length) {
        doubleArraySizeDecrease();
    }
}

void push(char c) {
    if (tail == length - 1) {
        doubleArraySizeIncrease();
    }

    values[++tail] = c;
}

void clear() {
    delete[] values;
    values = new char[1];
    length = 1;
    tail = -1;
}

bool isClear() {
    return tail == -1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    string s;
    while (cin >> s) {
        int sLen = int(s.size());

        for (int i = 0; i < sLen; i++) {
            char c = s[i];

            if (c == '(' || c == '[') {
                push(c);
            } else {
                if ((c == ')' && getLast() == '(') || (c == ']' && getLast() == '[')) {
                    deleteLast();
                } else {
                    cout << "NO" << endl;
                    break;
                }
            }

            if (i == sLen - 1 && isClear()) {
                cout << "YES" << endl;
            } else if (i == sLen - 1 && !isClear()) {
                cout << "NO" << endl;
            }
        }
        clear();
    }
    return 0;
}