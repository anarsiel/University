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

void pop() {
    list->tail->print();

    list->tail = (list->tail)->previous;
    list->tail->next = nullptr;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int n;
    cin >> n;

    list->tail = list;

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
    }
    return 0;
}
