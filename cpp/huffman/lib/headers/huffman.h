#ifndef LIB_HUFFMAN_H
#define LIB_HUFFMAN_H

//
// Created by Anarsiel on 2019-05-30.
//

#include <string>
#include <vector>
#include <queue>
#include <unordered_map>
#include <unordered_set>

struct huffman {

    huffman() = default;

    void do_huffman(std::vector<std::pair<char, uint32_t>> const &count);

    std::unordered_map<char, std::vector<bool>> get_keys();

    std::string get_tree();

private:
    std::string tree;
    std::unordered_map<char, std::vector<bool>> keys;
    std::unordered_map<std::vector<bool>, char> keys_backwards;

    struct qux;
    void build_tree(std::vector<bool> &cur_key);
};

#endif //LIB_HUFFMAN_H