//
// Created by Anarsiel on 2019-05-31.
//

#include <iostream>
#include <vector>
#include <string>
#include "headers/huffman.h"
#include "headers/reader.h"
#include "headers/writer.h"

using namespace std;

int main() {
    reader r = reader("/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/file.in");
    writer w = writer("/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/stuff/file.out");
    
    vector<char> v;
    while (!r.is_eof()) {
        char c = r.read_char();
        v.push_back(c);
    }

    for (size_t i = 0; i < v.size(); ++i) {
        w.write_char(v[i]);
    }

//
//    TESTING huffman.cpp .h
//
//    huffman huf;
//    vector<pair<char, uint32_t>> count = {};
//    huf.do_huffman(count);
//    unordered_map<char, vector<bool>> keys = huf.get_keys();
//    for (auto x : keys) {
//                            cout << x.first << ": ";
//        for (auto y : x.second) {
//            cout << y;
//        }
//        cout << endl;
//    }
//
//
//    cout << huf.get_tree();


    return 0;
}