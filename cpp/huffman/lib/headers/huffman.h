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

    void do_huffman(std::vector<std::pair<char, uint32_t>> const &count);

    std::string const &get_tree() const;

    std::unordered_map<char, std::vector<bool>> const &get_keys() const;

    size_t get_tree_size() const;

private:
    std::string tree;
    std::unordered_map<char, std::vector<bool>> keys;
    std::unordered_map<std::vector<bool>, char> keys_backwards;

    struct qux;
    void build_tree(std::vector<bool> &cur_key);
};

#endif //LIB_HUFFMAN_H