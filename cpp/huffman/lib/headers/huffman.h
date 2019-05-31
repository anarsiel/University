#ifndef LIB_HUFFMAN_H
#define LIB_HUFFMAN_H

//
// Created by Anarsiel on 2019-05-30.
//

#include <string>
#include <vector>
#include <queue>
#include <unordered_map>

struct huffman {

    huffman() = default;

    void do_huffman(std::vector<std::pair<char, uint32_t>> const &count);

    std::unordered_map<char, std::vector<bool>> get_keys();

private:
    struct cmp {
        bool operator() (const std::pair<std::string, uint32_t>& a, const std::pair<std::string, uint32_t>& b) const {
            return a.second > b.second;
        }
    };
    std::unordered_map<char, std::vector<bool>> keys;
};

#endif //LIB_HUFFMAN_H