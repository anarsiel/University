//
// Created by Anarsiel on 2019-05-30.
//

#include "reader.h"

void reader::read_buffer() {
    my_file_reader.read(buffer, BUFFER_SIZE);
    start_index = 0;
    end_index = static_cast<size_t>(my_file_reader.gcount());
}

reader::reader(std::string const &file_name) :
my_file_reader(file_name, std::ifstream::binary), start_index(0), end_index(0), bof(true) {
    if  (my_file_reader.fail()) {
        my_file_reader.close();
        throw std::runtime_error("Oh-la-la! File not found.");
    }

    read_buffer();
}

reader::~reader() {
    my_file_reader.close();
}

bool reader::is_eof() const {
    return start_index == end_index && my_file_reader.eof();
}

char reader::read_char() {
    if (is_eof()) {
        throw std::runtime_error("Surprise! Unexpected end of file.");
    }

    if (start_index == end_index) {
        read_buffer();
    }
    return buffer[start_index++];
}