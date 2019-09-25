//
// Created by Anarsiel on 2019-06-08.
//

#include <string>
#include <iostream>
#include <vector>

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
    if (strcmp(argv[1], "4") == 0) {
        throw std::runtime_error("wrong amount of keys:" + documentation);
    }

    if (strcmp(argv[1], "-e") == 0) {
        encoder e = encoder(argv[2], argv[3]);
        e.encode();
    } else if (strcmp(argv[1], "-d") == 0) {
        decoder d = decoder(argv[2], argv[3]);
        d.decode();
    } else if (strcmp(argv[1], "-r") == 0) {
        {encoder e = encoder(argv[2], "../buffer.buf");
        e.encode();}

        {decoder d = decoder("../buffer.buf", argv[3]);
        d.decode();}
    } else {
        throw std::runtime_error("wrong key:" + documentation);
    }
    return 0;

}