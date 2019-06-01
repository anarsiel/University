#ifndef LIB_READER_H
#define LIB_READER_H

//
// Created by Anarsiel on 2019-05-30.
//

#include <cstddef>
#include <fstream>

struct reader {

    explicit reader(std::string const &file_name);
    ~reader();

    char read_char();

    bool is_eof() const;

private:
    std::ifstream my_file_reader;

    size_t start_index;
    size_t end_index;

    const static size_t BUFFER_SIZE = 239;
    char buffer[BUFFER_SIZE];

    void read_buffer();
};

#endif //LIB_READER_H
