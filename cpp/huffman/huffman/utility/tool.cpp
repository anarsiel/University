//
// Created by Anarsiel on 2019-06-08.
//

#include <string>
#include <iostream>
#include <vector>
#include <cstring>

#include "../lib/headers/encoder.h"
#include "../lib/headers/decoder.h"
#include "../lib/headers/huffman.h"

#include "headers/reader.h"
#include "headers/writer.h"

std::string const documentation = "\nUsage: ./tool (-e|-d|-r) <from> <to>\n"
                                  " : -e for encoding\n"
                                  " : -d for decoding\n"
                                  " : -r for encoding and decoding using buffer\n";

int main(int argc, char **argv) {
    if (argc != 4) {
        throw std::runtime_error("wrong amount of keys:" + documentation);
    }

    if (strcmp(argv[1], "-e") == 0) {
        encoder e = encoder(argv[2], argv[3]);
        try {
            e.encode();
        } catch (...) {
            std::cout << "Error: File not found";
        }
    } else if (strcmp(argv[1], "-d") == 0) {
        try {
            decoder d = decoder(argv[2], argv[3]);
            d.decode();
        } catch (...) {
            std::cout << "Error";
        }
    } else if (strcmp(argv[1], "-r") == 0) {
        try {
            {encoder e = encoder(argv[2], "../utility/buffer.buf");
                e.encode();}
            {decoder d = decoder("../utility/buffer.buf", argv[3]);
                d.decode();}
        } catch (...) {
            std::cout << "Error";
        }
    } else {
        throw std::runtime_error("wrong key:" + documentation);
    }
    return 0;

}