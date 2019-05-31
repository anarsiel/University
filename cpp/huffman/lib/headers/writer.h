#ifndef LIB_WRITER_H
#define LIB_WRITER_H

//
// Created by Anarsiel on 2019-05-30.
//

#include <cstddef>
#include <fstream>

struct writer {

    writer(std::string const &file_name);
    ~writer();

    void write_char(char c);

private:
    std::ofstream my_file_writer;

    size_t start_index;

    const static size_t BUFFER_SIZE = 239;
    char buffer[BUFFER_SIZE];

    void write_buffer();
};

#endif //LIB_WRITER_H
