//
// Created by Anarsiel on 2019-05-30.
//

#include "../headers/reader.h"

void reader::read_buffer() {
    my_file_reader.read(buffer, BUFFER_SIZE);
    start_index = 0;
    end_index = my_file_reader.gcount();
    eof = (end_index == 0);
}

reader::reader(std::string const &file_name) :
my_file_reader(file_name, std::ifstream::binary), start_index(0), end_index(0), file_name(file_name), eof(false) {
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
    return eof;
}

char reader::read_char() {
    if (is_eof()) {
        throw std::runtime_error("Surprise! Unexpected end of file.");
    }

    char c = buffer[start_index++];

    if (start_index == end_index) {
        read_buffer();
    }
    return c;
}

void reader::jmp_to_stream_beg() {
    my_file_reader.clear();
    my_file_reader.seekg(0, my_file_reader.beg);
    eof = false;
    read_buffer();
}