#include <iostream>

using namespace std;

struct Node{
    int value;
    Node* left;
    Node* right;
    Node* parent;

    Node() {}

    Node(int _value) {
        value = _value;
    }
};

Node* root;
bool treeIsEmpty() {
    return (root->left == nullptr) && (root->right == nullptr);
}

bool find(int x) {
    if (treeIsEmpty()) {
        return false;
    }

    Node* cur = root->right;

    bool exist = false;

    while (true) {
        if (cur->value > x) {
            if (cur->left != nullptr) {
                cur = cur->left;
            } else {
                exist = false;
                break;
            }
        } else if (cur->value < x) {
            if (cur->right != nullptr) {
                cur = cur->right;
            } else {
                exist = false;
                break;
            }
        } else {
            exist = true;
            break;
        }
    }

    return exist;
}

void exists(int x) {
    cout << (find(x) ? "true" : "false") << endl;
}

void insert(int x) {
    if (find(x)) return;

    if (treeIsEmpty()) {
        Node* n = new Node(x);
        n->left = nullptr;
        n->right = nullptr;

        n->parent = root;
        root->right = n;
        return;
    }

    Node* cur = root->right;

    while (true) {
        if (cur->value < x) {
            if (cur->right == nullptr) {
                Node* n = new Node(x);
                n->left = nullptr;
                n->right = nullptr;

                cur->right = n;
                n->parent = cur;
                break;
            } else {
                cur = cur->right;
            }
        } else {
            if (cur->left == nullptr) {
                Node* n = new Node(x);
                n->left = nullptr;
                n->right = nullptr;

                cur->left = n;
                n->parent = cur;
                break;
            } else {
                cur = cur->left;
            }
        }
    }
}

Node* next(int x) {
    if (treeIsEmpty()) {
        return nullptr;
    }

    Node* current = root->right;
    Node* successor = nullptr;

    while (current != nullptr) {
//        cout << current->value << endl;
        if (current->value > x) {
//            cout << 1;
            successor = current;
            current = current->left;
        } else {
//            cout << 0;
            current = current->right;
        }
    }
    return successor;
}

void nextPrint(int x) {
    Node* node = next(x);
    if (node == nullptr)
        cout << "none" << endl;
    else
        cout << node->value << endl;
}

Node* prev(int x) {
    if (treeIsEmpty()) {
        return nullptr;
    }

    Node* current = root->right;
    Node* successor = nullptr;

    while (current != nullptr) {
        if (current->value < x) {
            successor = current;
            current = current->right;
        } else {
            current = current->left;
        }
    }
    return successor;
}

void prevPrint(int x) {
    Node* node = prev(x);
    if (node == nullptr)
        cout << "none" << endl;
    else
        cout << node->value << endl;
}

void remove(int x) {
    if (!find(x)) return;

    Node* cur = root->right;

    while (true) {
        if (cur->value > x) {
            cur = cur->left;
        } else if (cur->value < x) {
            cur = cur->right;
        } else {
            break;
        }
    }

    Node* parent = cur->parent;

    if (cur->left == nullptr && cur->right == nullptr) {
        if (parent->left == cur) {
            parent->left = nullptr;
        } else {
            parent->right = nullptr;
        }
    } else if (cur->left == nullptr) {
        if (parent->left == cur) {
            parent->left = cur->right;
        } else {
            parent->right = cur->right;
        }
    } else if (cur->right == nullptr) {
        if (parent->left == cur) {
            parent->left = cur->left;
        } else {
            parent->right = cur->left;
        }
    } else {
        int nextValue = next(x)->value;
        remove(nextValue);
        cur->value = nextValue;
    }
}

void printTree(Node* cur) {
    if (cur == nullptr) return;

    printTree(cur->left);
    cout << "<-" << cur->value << "-> ";
    printTree(cur->right);
}

int main() {
    root = new Node();
    root->left = nullptr;
    root->right = nullptr;

    string operation;
    int x;

    while (cin >> operation >> x) {
        if (operation == "insert") {
            insert(x);
        } else if (operation == "delete") {
            remove(x);
        } else if (operation == "exists") {
            exists(x);
        } else if (operation == "next") {
            nextPrint(x);
        } else if (operation == "prev") {
            prevPrint(x);
        }

//        cout << "TREE:" << endl;
        printTree(root);
        cout << endl;
    }
    return 0;
}

/*
insert 2
insert 5
insert 3
exists 2
exists 4
*/
