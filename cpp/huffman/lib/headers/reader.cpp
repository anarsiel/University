//
// Created by Anarsiel on 2019-05-30.
//

#include "reader.h"

reader::reader(std::string const &file_name) :
            my_file_reader(file_name, std::ifstream::binary), start_index(0), end_index(0) {}

reader::~reader() {
    my_file_reader.close();
}

void reader::read_buffer() {
    my_file_reader.get(buffer, BUFFER_SIZE);
}

bool reader::is_eof() {
    return start_index == end_index && my_file_reader.eof();
}

char reader::read_char() {
    if (start_index == end_index) {
        read_buffer();
        start_index = 0;
        end_index = static_cast<size_t>(my_file_reader.gcount());
    }

    if (is_eof()) {
        throw std::runtime_error("Error: surprise! Unexpected end of file.");
    }
    return buffer[start_index++];
}