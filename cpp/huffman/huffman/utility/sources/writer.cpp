//
// Created by Anarsiel on 2019-05-30.
//

#include <algorithm>
#include <functional>

#include "../headers/writer.h"

writer::writer(std::string const &file_name) :
        my_file_writer(file_name, std::ofstream::binary), start_index(0), file_name(file_name) {}

writer::~writer() {
    write_buffer();
    my_file_writer.close();
}

void writer::write_buffer() {
    my_file_writer.write(reinterpret_cast<const char *>(buffer), start_index);
    start_index = 0;
}

void writer::write_char(uint8_t c) {
    if (start_index == BUFFER_SIZE) {
        write_buffer();
    }

    buffer[start_index++] = c;
}