//
// Created by Anarsiel on 2019-05-31.
//

#include <iostream>
#include <unordered_map>
#include "../headers/encoder.h"
#include "../headers/huffman.h"

encoder::encoder(std::string const &input_file_name, std::string const &outputput_file_name) : r(input_file_name), w(outputput_file_name) {}

void encoder::encode() {
    std::unordered_map<char, uint32_t> unordered_map;

    while (!r.is_eof()) {
        char c = r.read_char();
        ++unordered_map[c];
    }
    r.jmp_to_stream_beg();

    count.resize(unordered_map.size());
    size_t index = 0;
    for (auto x : unordered_map) {
        count[index++] = x;
    }

    huffman h;
    h.do_huffman(count);

    std::unordered_map<char, std::vector<bool>> keys = h.get_keys();
    std::string tree = h.get_tree();
    std::vector<char> tree_length = uint32_to_chars(h.get_tree_length());
    std::vector<char> values_in_dfs_order = h.get_values_in_dfs_order();
    std::vector<char> values_size = uint32_to_chars(values_in_dfs_order.size());

    // tree length
    for (char c : tree_length) {
        w.write_char(c);
    }

    // tree
    for (size_t i = 0; i < tree.size(); ++i) {
        w.write_char(tree[i]);
    }

    // values_size
    for (char c : values_size) {
        w.write_char(c);
    }

    // values
    for (char c : values_in_dfs_order) {
        w.write_char(c);
    }

    // encoded text length
    uint32_t enc_text_length = 0;
    for (size_t i = 0; i < count.size(); ++i) {
        enc_text_length += keys[count[i].first].size() * count[i].second;
    }
    std::vector<char> chars_enc_text_length = uint32_to_chars(enc_text_length);
    for (char c : chars_enc_text_length) {
        w.write_char(c);
    }

    // encoded text
    size_t this_index = 0, other_index = 0;
    std::vector<bool> bools(32);
    std::vector<char> chars(4);
    while (!r.is_eof()) {
        char c = r.read_char();
        std::vector<bool> other_bools = keys[c];
        other_index = 0;

        while (other_index != other_bools.size()) {
            if (this_index == 32) {
                convert_32bools_to_4chars(bools, chars);

                for (size_t i = 0; i < chars.size(); ++i) {
                    w.write_char(chars[i]);
                }
                this_index = 0;
                continue;
            }

            bools[this_index++] = other_bools[other_index++];
        }
    }

    if (this_index != 0) {
        convert_32bools_to_4chars(bools, chars);

        for (size_t i = 0; i < chars.size(); ++i) {
            w.write_char(chars[i]);
        }
    }
}

std::vector<char> encoder::uint32_to_chars(uint32_t x) {
    std::vector<bool> bools(32);
    for (size_t i = 0; i < 32; ++i) {
        bools[31 - i] = ((x & 1) != 0);
        x >>= 1;
    }
    std::vector<char> result;
    convert_32bools_to_4chars(bools, result);
    return result;
}

void encoder::convert_32bools_to_4chars(std::vector<bool> const &bools, std::vector<char> &chars) {
    chars.assign(4, 0);
    for (size_t i = 0; i < 8; ++i) {
        chars[0] += static_cast<char>(bools[i] * (1 << (7 - i % 8)));
        chars[1] += static_cast<char>(bools[i + 8] * (1 << (7 - i % 8)));
        chars[2] += static_cast<char>(bools[i + 16] * (1 << (7 - i % 8)));
        chars[3] += static_cast<char>(bools[i + 24] * (1 << (7 - i % 8)));
    }
}