//
// Created by Anarsiel on 2019-06-01.
//

#include <algorithm>
#include <functional>

#include "../headers/decoder.h"

decoder::decoder(std::string const &input_file_name, std::string const &output_file_name)
        : r(input_file_name), w(output_file_name), input_file_name(input_file_name), output_file_name(output_file_name) {}

void decoder::decode_keys_backwards() {
    // tree decode
    std::vector<uint8_t> tree;
    size_t tree_length = decode_uint32();

    for (size_t i = 0; i < tree_length; ++i) {
        tree.push_back(r.read_char());
    }

    // values decode
    size_t values_length = decode_uint32();
    std::vector<uint8_t> values_in_dfs_order(values_length);
    for (size_t i = 0; i < values_length; ++i) {
        values_in_dfs_order[i] = r.read_char();
    }

    // decode keys;
    bool was_down = true;
    std::vector<bool> bools;
    size_t index = 0;
    max_key_length = 0;
    for (size_t i = 0; i < tree.size(); ++i) {
        if (tree[i] == 'L') {
            bools.push_back(0);
            was_down = true;
        } else if (tree[i] == 'R') {
            bools.push_back(1);
            was_down = true;
        } else {
            if (was_down) {
                was_down = false;
                keys_backwards[bools] = values_in_dfs_order[index++];
                max_key_length = std::max(max_key_length, bools.size());
            }
            bools.pop_back();
        }
    }
}

void decoder::decode() {
    decode_keys_backwards();
    size_t text_size = decode_uint32();

    std::vector<bool> cur_key;
    std::vector<bool> buf_for_char(8);
    std::string output = "";
    while (!r.is_eof()) {
        size_t c = static_cast<size_t>(r.read_char());
        for (size_t i = 0; i < 8; ++i) {
            buf_for_char[7 - i] = ((c & 1) != 0);
            c >>= 1;
        }

        for (size_t i = 0; i < 8; ++i) {
            if (text_size == 0) break;
            cur_key.push_back(buf_for_char[i]);
            text_size--;

            if (keys_backwards.find(cur_key) != keys_backwards.end()) {
                w.write_char(keys_backwards[cur_key]);
                output.push_back(keys_backwards[cur_key]);
                cur_key.clear();
            }

            if (max_key_length < cur_key.size()) {
                throw std::runtime_error("Ooops! File is broken.");
            }
        }
    }

    if (text_size != 0) {
        throw std::runtime_error("Ooops! File is broken.");
    }
}

uint32_t decoder::decode_uint32() {
    uint32_t a, b, c, d;
    a = static_cast<uint32_t>(r.read_char());
    b = static_cast<uint32_t>(r.read_char());
    c = static_cast<uint32_t>(r.read_char());
    d = static_cast<uint32_t>(r.read_char());

    return (a << 24) + (b << 16) + (c << 8) + d;
}

