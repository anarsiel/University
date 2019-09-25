#ifndef LIB_HUFFMAN_H
#define LIB_HUFFMAN_H

//
// Created by Anarsiel on 2019-05-30.
//

#include <algorithm>
#include <functional>
#include <string>
#include <vector>
#include <queue>
#include <unordered_map>

struct huffman {

    void do_huffman(std::vector<std::pair<uint8_t, uint32_t> > const &count);

    std::string const &get_tree() const;

    std::vector<uint8_t> const &get_values_in_dfs_order() const;

    std::unordered_map<uint8_t, std::vector<bool> > const &get_keys() const;

    uint32_t get_tree_length() const;

private:
    std::string tree;
    std::vector<uint8_t> values_in_dfs_order;
    std::unordered_map<uint8_t, std::vector<bool> > keys;
    std::unordered_map<std::vector<bool>, uint8_t> keys_backwards;

    struct qux;
    void build_tree(std::vector<bool> &cur_key);
};

#endif //LIB_HUFFMAN_H