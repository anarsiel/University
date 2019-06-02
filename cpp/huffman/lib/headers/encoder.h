#ifndef LIB_ENCODER_H
#define LIB_ENCODER_H

//
// Created by Anarsiel on 2019-05-30.
//

#include <string>
#include <vector>

#include "reader.h"
#include "writer.h"

struct encoder {

    encoder() = default;
    encoder(std::string const &input_file_name, std::string const &outputput_file_name);

    void encode();

private:
    reader r;
    writer w;

    std::string input_file_name;
    std::string output_file_name;

    std::vector<std::pair<char, uint32_t>> count;

    std::vector<char> uint32_to_chars(uint32_t x);
    void convert_32bools_to_4chars(std::vector<bool> const &bools, std::vector<char> &chars);
};

#endif //LIB_ENCODER_H
