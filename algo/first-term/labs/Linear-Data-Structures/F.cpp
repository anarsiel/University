#include <iostream>
#include <cstdio>
#include <cstdlib>

using namespace std;

struct Node {
    Node (){}

    Node (int _value) {
        value = _value;
    }

    void print() {
        cout << value << endl;
    }

    int value;
    Node* next;
    Node* previous;
    Node* tail;
};

Node* list = new Node();

void push(int number) {
    Node* newNode = new Node(number);

    if (list->tail == list) {
        list->tail = newNode;
        (list->tail)->previous = list;
    } else {
        (list->tail)->next = newNode;
        newNode->previous = list->tail;
        list->tail = newNode;
    }
}

int getLast() {
    return list->tail->value;
}

void pop() {
    //list->tail->print();

    list->tail = (list->tail)->previous;
    list->tail->next = nullptr;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    list->tail = list;

    char c;
    while (cin >> c) {
        if ('0' <= c && c <= '9') {
            push(int(c - '0'));
        } else if (c == '+') {
            int b = getLast();
            pop();
            int a = getLast();
            pop();

            push(a + b);
        } else if (c == '-') {
            int b = getLast();
            pop();
            int a = getLast();
            pop();

            push(a - b);
        } else if (c == '*') {
            int b = getLast();
            pop();
            int a = getLast();
            pop();

            push(a * b);
        }
    }
    cout << getLast() << endl;
    return 0;
}
