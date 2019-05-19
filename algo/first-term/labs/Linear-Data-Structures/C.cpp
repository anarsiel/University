#include <iostream>
#include <cstdio>
#include <cstdlib>

using namespace std;

int* values = new int[1];
int head = -1; // actual head is + 1
int tail = -1; // actual tail
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

void fitLeft() {
    if (head == -1) return;

    int* doubledValues = new int[length];
    memcpy(doubledValues, &values[head + 1], (tail - head) * sizeof(int));

    tail -= head + 1;
    head = -1;
    delete[] values;
    values = doubledValues;
}

void push(int element) {
    if (tail == length - 1) {
        fitLeft();
    }

    if (tail == length - 1) {
        doubleArraySizeIncrease();
    }

    values[++tail] = element;
}

void pop() {
    cout << values[++head] << endl;

    if ((tail - head) * 2 < length && length > 10) {
        fitLeft();
        doubleArraySizeDecrease();
    }
}

void print() {
    cout << "length: " << length << " head: " << head << " tail: " << tail << endl;

    for (int i = 0; i <= head; i++) {
        cout << "# ";
    }
    for (int i = head + 1; i <= tail; i++)
        cout << values[i] << ' ';

    for (int i = tail + 1; i < length; i++) {
        cout << "# ";
    }
    cout << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        char commandType;
        cin >> commandType;

        if (commandType == '+') {
            int currentNumber;
            cin >> currentNumber;

            push(currentNumber);

        } else {
            pop();
        }
        //print();
    }
    return 0;
}

/*
6
+ 1
+ 2
-
+ 3
+ 4
-
 */