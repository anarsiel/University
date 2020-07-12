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
    Node* head;
};

Node* list = new Node();

void push(int number) {
    Node* newNode = new Node(number);

    if (list->tail == list) {
        list->tail = newNode;
        list->head = newNode;

        list->tail->previous = list;
        list->tail->next = list;
    } else {
        (list->tail)->next = newNode;
        newNode->previous = list->tail;
        list->tail = newNode;
        list->tail->next = list;
    }
}

void pop() {
    list->head->print();

    if (list->head == list->tail) {
        list = new Node();
        list->head = list;
        list->tail = list;
    } else {
        list->head = (list->head)->next;
        list->tail->previous = list;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    list->tail = list;
    list->head = list;

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
        //cout << list->head->value << ' ' << list->tail->value << endl;
    }
    return 0;
}
