//
// Created by Anarsiel on 16/10/2018.
//

#include <iostream>
#include <cstdio>
#include <cstdlib>

using namespace std;

struct deqque {
    int* queue = new int[1];

    int head = -1; // actual head is + 1
    int tail = -1; // actual tail
    int length = 1;

    void doubleArraySizeIncrease() {
        int doubledLength = length * 2;
        int* doubledValues = new int[doubledLength];

        memcpy(doubledValues, &queue[head + 1], length * sizeof(int));

        length = doubledLength;
        delete[] queue;
        queue = doubledValues;
        tail -= head + 1;
        head = -1;
    }

    void doubleArraySizeDecrease() {
        int doubledLength = length / 2;
        int* doubledValues = new int[doubledLength];

        memcpy(doubledValues, &queue[head + 1], length / 2 * sizeof(int));

        length = doubledLength;
        delete[] queue;
        queue = doubledValues;
        tail -= head + 1;
        head = -1;
    }

    void fitLeft() {
        if (head == -1) return;

        int* doubledValues = new int[length];
        memcpy(doubledValues, &queue[head + 1], (tail - head) * sizeof(int));

        tail -= head + 1;
        head = -1;
        delete[] queue;
        queue = doubledValues;
    }

    void pushRight(int element) {
        if (tail == length - 1) {
            fitLeft();
        }

        if (tail == length - 1) {
            doubleArraySizeIncrease();
        }

        queue[++tail] = element;
    }

    void popRight() {
        tail--;

        if (tail == head) {
            tail = -1;
            head = -1;
        }

        if ((tail - head) * 2 < length && length > 10) {
            doubleArraySizeDecrease();
        }

    }

    void popLeft() {
        head++;

        if (tail == head) {
            tail = -1;
            head = -1;
        }

        if ((tail - head) * 2 < length && length > 10) {
            doubleArraySizeDecrease();
        }
    }

    void print() {
        cout << "length: " << length << " head: " << head << " tail: " << tail << endl;

        for (int i = 0; i <= head; i++) {
            cout << "# ";
        }
        for (int i = head + 1; i <= tail; i++)
            cout << queue[i] << ' ';

        for (int i = tail + 1; i < length; i++) {
            cout << "# ";
        }
        cout << endl;
    }

    int getRight() {
        return queue[tail];
    }

    int getLeft() {
        return queue[head + 1];
    }

    bool isClear() {
        return head == tail;
    }
};

deqque q, qMin;
int n, m, k;
int a, b, c;
int x[1000];
int last, beforeLast;

int getNextX(int ind) {
    if (ind < k)
        return x[ind];

    return (a * beforeLast + b * last + c);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n >> m >> k;
    cin >> a >> b >> c;

    for (int i = 0; i < k; i++) {
        cin >> x[i];
    }

    last = x[1];
    beforeLast = x[0];
    for (int i = 0; i < m; i++) {
        int current = getNextX(i);
        q.pushRight(current);

        while (!qMin.isClear() && qMin.getRight() > current) {
            qMin.popRight();
        }

        qMin.pushRight(current);
        if (i > 1) {
            beforeLast = last;
            last = current;
        }
    }
    long long sumMin = qMin.getLeft();

//    q.print();
//    qMin.print();
//    cout << "MIN: " << qMin.getLeft() << endl;
//    cout << endl;

    for (int i = m; i < n; i++) {
        int current = getNextX(i);

        if (q.getLeft() == qMin.getLeft()) {
            qMin.popLeft();
        }
        q.popLeft();

        q.pushRight(current);
        while (!qMin.isClear() && qMin.getRight() > current) {
            qMin.popRight();
        }
        qMin.pushRight(current);
        sumMin += qMin.getLeft();

        beforeLast = last;
        last = current;
//        if (i < 50) {
//            q.print();
//            qMin.print();
//        }
    }

    cout << sumMin << endl;
    return 0;
}

/*
10 3 2
1 1 0
0 1

10 3 2
1 -1 0
0 1

10000 3 2
1 1 0
0 1

1000000 15 5
283471207 23947205 3
17625384 939393931 1838388 912740247 290470294

 1881734974026477
 1879262596173354
 1879262596173354
 1879262596173354
 */
