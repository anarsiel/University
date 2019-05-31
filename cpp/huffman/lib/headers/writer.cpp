//
// Created by Anarsiel on 2019-05-30.
//

#include "writer.h"

writer::writer(std::string const &file_name) :
        my_file_writer(file_name, std::ofstream::binary), start_index(0),  {}

writer::~writer() {
    write_buffer();
    my_file_writer.close();
}

void writer::write_buffer() {
    my_file_writer.write(buffer, start_index);
    start_index = 0;
}

void writer::write_char(char c) {
    if (start_index == BUFFER_SIZE) {
        write_buffer();
    }

    buffer[start_index++] = c;
}