#ifndef LIB_ENCODER_H
#define LIB_ENCODER_H

//
// Created by Anarsiel on 2019-05-30.
//

#include <string>
#include <vector>
#include <algorithm>
#include <functional>

#include "reader.h"
#include "writer.h"

struct encoder {

    encoder() = default;
    encoder(std::string const &input_file_name, std::string const &outputput_file_name);

    void encode();

private:
    reader r;
    writer w;
    std::vector<std::pair<uint8_t, uint32_t> > count;

    std::vector<uint8_t> uint32_to_chars(uint32_t x);
    void convert_32bools_to_4chars(std::vector<bool> const &bools, std::vector<uint8_t> &chars);
};

#endif //LIB_ENCODER_H
