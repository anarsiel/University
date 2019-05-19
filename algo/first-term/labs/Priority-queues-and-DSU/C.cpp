//
// Created by Anarsiel on 27/11/2018.
//

#include <iostream>
#include <vector>

using namespace std;

int cntPush = 0;

vector<pair<int, int>> queue;
vector<int> pushNumberToQueueNumber;

void siftDown(int i) {
    while (2 * i + 1 < int(queue.size())) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int elementToSwap = left;
        if (right < int(queue.size()) && queue[right] < queue[left])
            elementToSwap = right;

        if (queue[i].first <= queue[elementToSwap].first) break;

        swap(queue[i], queue[elementToSwap]);
        swap(pushNumberToQueueNumber[queue[i].second],
                pushNumberToQueueNumber[queue[elementToSwap].second]);
        i = elementToSwap;
    }
}

void siftUp(int i) {
    while (queue[i] < queue[(i - 1) / 2]) {
        swap(queue[i], queue[(i - 1) / 2]);
        swap(pushNumberToQueueNumber[queue[i].second],
                pushNumberToQueueNumber[queue[(i - 1) / 2].second]);
        i = (i - 1) / 2;
    }
}

pair<int, int> extractMin() {
    auto mn = queue[0];

    pushNumberToQueueNumber[queue[0].second] = -1;
    queue[0] = queue[queue.size() - 1];
    pushNumberToQueueNumber[queue[0].second] = 0;

//    pushNumberToQueueNumber.pop_back();
    queue.pop_back();

    siftDown(0);
    return mn;
}

void push(int x) {
    queue.emplace_back(x, cntPush);
    pushNumberToQueueNumber.push_back(int(queue.size()) - 1);

    siftUp(int(queue.size() - 1));
}

void decreaseKey(int x, int key) {
    x--;
    queue[pushNumberToQueueNumber[x]].first = key;
    siftUp(pushNumberToQueueNumber[x]);
}

int main() {
    freopen("priorityqueue2.in", "r", stdin);
    freopen("priorityqueue2.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    string command;
    while (cin >> command) {
        if (command == "push") {
            int x;
            cin >> x;
            push(x);
        } else if (command == "extract-min") {
            if (queue.empty()) {
                cout << "*" << endl;
                cntPush++;
                pushNumberToQueueNumber.push_back(-1);
                continue;
            }
            auto p = extractMin();
            cout << p.first << ' ' << p.second + 1 << endl;
            pushNumberToQueueNumber.push_back(-1);
        } else {
            int x, v;
            cin >> x >> v;
            if (x - 1 > int(pushNumberToQueueNumber.size())
            || pushNumberToQueueNumber[x - 1] == -1) {
                cntPush++;
                pushNumberToQueueNumber.push_back(-1);
                continue;
            }
            decreaseKey(x, v);
            pushNumberToQueueNumber.push_back(-1);
        }
        cntPush++;
    }
    return 0;
}
/*
push 3
push 4
push 2
extract-min
decrease-key 2 1
extract-min
extract-min
extract-min
 */
