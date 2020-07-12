//
// Created by Anarsiel on 17/02/2019.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

struct Node {
    int x, y;
    Node *left, *right, *parent;
    int index;

    Node() {}

    Node(int x, int y, int index) {
        this->x = x;
        this->y = y;
        this->index = index;

        left = NULL;
        right = NULL;
    }
};

Node *root = new Node();

Node *last = root;

void insert(Node *node) {
    if (last == root) {
        root->right = node;
        node->parent = root;
        last = node;
        return;
    }

    while (last != root && last->y > node->y) {
        last = last->parent;
    }

    if (last->right == NULL) {
        last->right = node;
        node->parent = last;
        last = node;
        return;
    }

    (last->right)->parent = node;
    node->left = last->right;
    node->parent = last;
    last->right = node;

    last = node;
}

vector<vector<int>> answ;

void printAnswer(Node* node) {
//    cout << node->x << ' ';
    answ[node->index].push_back(node->parent == root ? 0 : node->parent->index + 1);
    answ[node->index].push_back(node->left == NULL ? 0 : node->left->index + 1);
    answ[node->index].push_back(node->right == NULL ? 0 : node->right->index + 1);

    if (node->left != NULL)
        printAnswer(node->left);
    if (node->right != NULL)
        printAnswer(node->right);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie();
    cout.tie();

    int n;
    cin >> n;

    vector<pair<pair<int, int>, int>> v(n);

    for (int i = 0; i < n; ++i) {
        cin >> v[i].first.first >> v[i].first.second;
        v[i].second = i;
    }

    sort(v.begin(), v.end());

    for (int i = 0; i < n; ++i) {
        Node* node = new Node(v[i].first.first, v[i].first.second, v[i].second);
        insert(node);
    }

    answ.resize(n);
    printAnswer(root->right);

    cout << "YES" << endl;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < int(answ[i].size()); j++)
            cout << answ[i][j] << ' ';
        cout << endl;
    }

    return 0;
}
/*
7
5 4
2 2
3 9
0 5
1 3
6 6
4 11
 */
