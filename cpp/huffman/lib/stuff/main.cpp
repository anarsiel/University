//
// Created by Anarsiel on 2019-06-01.
//

#include <iostream>

#include "../headers/decoder.h"
#include "../headers/encoder.h"
#include "../headers/huffman.h"
#include "../headers/reader.h"
#include "../headers/writer.h"

using namespace std;

int main() {
    string input_file_name = "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/file.in";
    string output_file_name = "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/file.out";
//    string tmp = "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/tmp.out";
    {
        encoder e = encoder(input_file_name, output_file_name);
        e.encode();
    }

    {
        decoder d = decoder(output_file_name, input_file_name);
        d.decode();
    }
    return 0;
}