#include <iostream>
#include <cstdio>
#include <cstdlib>

using namespace std;

int* values = new int[16];
int tail = 0;
int length = 1;

void doubleArraySizeIncrease() {
    int doubledLength = length * 2;
    int* doubledValues = new int[doubledLength];

    memcpy(doubledValues, values, length * sizeof(int));

    length = doubledLength;
    delete[] values;
    values = doubledValues;
}

void doubleArraySizeDecrease() {
    int doubledLength = length / 2;
    int* doubledValues = new int[doubledLength];

    memcpy(doubledValues, values, length / 2 * sizeof(int));

    length = doubledLength;
    delete[] values;
    values = doubledValues;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        char commandType;
        cin >> commandType;

        if (commandType == '+') {
            int currentNumber;
            cin >> currentNumber;

            if (tail == length - 1) {
                doubleArraySizeIncrease();
            }

            values[tail++] = currentNumber;
        } else {
            cout << values[tail - 1] << endl;
            tail--;

            if (2 * tail < length) {
                doubleArraySizeDecrease();
            }
        }
    }
    return 0;
}
