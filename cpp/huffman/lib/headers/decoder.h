#ifndef LIB_DECODER_H
#define LIB_DECODER_H

//
// Created by Anarsiel on 2019-06-01.
//

#include <vector>
#include <unordered_map>

#include "reader.h"
#include "writer.h"

struct decoder {

    decoder(std::string const &input_file_name, std::string const &output_file_name);

    void decode();

private:
    reader r;
    writer w;

    std::string input_file_name;
    std::string output_file_name;

    size_t max_key_length;
    std::unordered_map<std::vector<bool>, char> keys_backwards;

    uint32_t decode_uint32();
    void decode_keys_backwards();
};

#endif //LIB_DECODER_H
