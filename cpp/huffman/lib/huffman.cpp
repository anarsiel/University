//
// Created by Anarsiel on 2019-05-31.
//

#include "headers/huffman.h"
#include <string>
#include <algorithm>
#include <unordered_map>

struct huffman::qux {
    bool operator() (const std::pair<std::string, uint32_t>& a, const std::pair<std::string, uint32_t>& b) const {
        return a.second > b.second;
    }
};

void huffman::build_tree(std::vector<bool> &cur_key) {
    if (keys_backwards.find(cur_key) != keys_backwards.end()) {
        tree.push_back('U');
        cur_key.pop_back();
        return;
    }

    tree.push_back('L');
    cur_key.push_back(false);
    build_tree(cur_key);

    tree.push_back('R');
    cur_key.push_back(true);
    build_tree(cur_key);


    tree.push_back('U');
    cur_key.pop_back();
    return;
}

void huffman::do_huffman(std::vector<std::pair<char, uint32_t>> const &count) {
    std::unordered_map<char, std::vector<bool>> _keys;

    std::priority_queue<std::pair<std::string, uint32_t>,
    std::vector<std::pair<std::string, uint32_t>>, qux> priority_queue;

    if (count.size() == 0) {
        return;
    }

    if (count.size() == 1) {
        keys[count[0].first] = {false};
        tree = "LU";
        return;
    }

    for (size_t i = 0; i < count.size(); ++i) {
        priority_queue.push({std::string(1, count[i].first), count[i].second});
    }

    for (size_t i = 0; i < count.size() - 1; ++i) {
        auto min1 = priority_queue.top();
        priority_queue.pop();
        auto min2 = priority_queue.top();
        priority_queue.pop();

        for (size_t j = 0; j < min1.first.size(); ++j) {
            _keys[min1.first[j]].push_back(0);
        }

        for (size_t j = 0; j < min2.first.size(); ++j) {
            _keys[min2.first[j]].push_back(1);
        }

        priority_queue.push({min1.first + min2.first, min1.second + min2.second});
    }

    for (auto key : _keys) {
        auto v = key.second;
        std::reverse(v.begin(), v.end());
        keys[key.first] = v;
        keys_backwards[v] = key.first;
    }

    std::vector<bool> cur_key = {};
    build_tree(cur_key);
    tree.pop_back();
}

std::string const &huffman::get_tree() const {
    return tree;
}

std::unordered_map<char, std::vector<bool>> const &huffman::get_keys() const {
    return keys;
}

size_t huffman::get_tree_size() const {
    return tree.size();
}
