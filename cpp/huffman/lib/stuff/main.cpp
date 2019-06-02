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
//    reader r = reader("/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/file.in");
//    writer w = writer("/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/file.out");
//    while (!r.is_eof()) {
//        char c = r.read_char();
//        cout << c << endl;
//        w.write_char(c);
//    }

    string input_file_name = "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/file.in";
    string output_file_name = "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/file.out";
    encoder e = encoder(input_file_name, output_file_name);
    e.encode();

    return 0;
}