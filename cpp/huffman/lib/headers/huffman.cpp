//
// Created by Anarsiel on 2019-05-31.
//

#include "huffman.h"
#include <string>
#include <algorithm>
#include <unordered_map>

//bool operator <(std::pair<char, uint32_t> a, std::pair<char, uint32_t> b) {
//    return a.second > b.second;
//}

void huffman::do_huffman(std::vector<std::pair<char, uint32_t>> const &count) {
    std::unordered_map<char, std::vector<bool>> _keys;
    std::priority_queue<std::pair<std::string, uint32_t>, std::vector<std::pair<std::string, uint32_t>>, cmp> priority_queue;

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
    }
};

std::unordered_map<char, std::vector<bool>> huffman::get_keys() {
    return keys;
}