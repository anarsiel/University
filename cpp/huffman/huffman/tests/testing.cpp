//
// Created by Anarsiel on 2019-06-01.
//

#include <random>
#include <algorithm>
#include <functional>
#include "lib/headers/decoder.h"
#include "lib/headers/encoder.h"
#include "utility/headers/reader.h"

#include "gtest/gtest.h"

void encode(std::string const &input_file_name, std::string const &output_file_name) {
    encoder e = encoder(input_file_name, output_file_name);
    e.encode();
}

void decode(std::string const &input_file_name, std::string const &output_file_name) {
    decoder d = decoder(input_file_name, output_file_name);
    d.decode();
}

bool files_are_equal(std::string const &file_name1, std::string const &file_name2) {
    reader r1 = reader(file_name1);
    reader r2 = reader(file_name2);

    while (!r1.is_eof() && !r2.is_eof()) {
        uint8_t a = r1.read_char();
        uint8_t b = r2.read_char();

        if (a != b) return false;
    }

    return r1.is_eof() && r2.is_eof();
}

static std::string const &input_path = "../../tests/inputs";
static std::string const &output_path = "../../tests/outputs";
static std::string const &buffer_path = "../../tests/buffer.buf";

static std::string const &input_file_ending = ".in";
static std::string const &output_file_ending = ".out";

TEST(hand_made_tests, english_language_test) {
    std::string file_name = "George_Orwell_1984_eng";

    std::string input = input_path + file_name + input_file_ending;
    std::string output = output_path + file_name + output_file_ending;

    EXPECT_NO_THROW(encode(input, buffer_path));
    EXPECT_NO_THROW(decode(buffer_path, output));
    EXPECT_TRUE(files_are_equal(input, output));
}

TEST(hand_made_tests, russian_language_test) {
    std::string file_name = "Leo_Tolstoy_War_and_Peace_rus";

    std::string input = input_path + file_name + input_file_ending;
    std::string output = output_path + file_name + output_file_ending;

    EXPECT_NO_THROW(encode(input, buffer_path));
    EXPECT_NO_THROW(decode(buffer_path, output));
    EXPECT_TRUE(files_are_equal(input, output));
}

TEST(hand_made_tests, italian_language_Test) {
    std::string file_name = "Leo_Tolstoy_War_and_Peace_ital";

    std::string input = input_path + file_name + input_file_ending;
    std::string output = output_path + file_name + output_file_ending;

    EXPECT_NO_THROW(encode(input, buffer_path));
    EXPECT_NO_THROW(decode(buffer_path, output));
    EXPECT_TRUE(files_are_equal(input, output));
}

TEST(hand_made_tests, huffman_code_test) {
    std::string file_name = "huffman_text";

    std::string input = input_path + file_name + input_file_ending;
    std::string output = output_path + file_name + output_file_ending;

    EXPECT_NO_THROW(encode(input, buffer_path));
    EXPECT_NO_THROW(decode(buffer_path, output));
    EXPECT_TRUE(files_are_equal(input, output));
}

TEST(hand_made_tests, japanese_language_test) {
    std::string file_name = "Osamu_Dazai_No_longer_human_jap";

    std::string input = input_path + file_name + input_file_ending;
    std::string output = output_path + file_name + output_file_ending;

    EXPECT_NO_THROW(encode(input, buffer_path));
    EXPECT_NO_THROW(decode(buffer_path, output));
    EXPECT_TRUE(files_are_equal(input, output));
}

TEST(hand_made_tests, empty_file_test) {
    std::string file_name = "empty";

    std::string input = input_path + file_name + input_file_ending;
    std::string output = output_path + file_name + output_file_ending;

    EXPECT_NO_THROW(encode(input, buffer_path));
    EXPECT_NO_THROW(decode(buffer_path, output));
    EXPECT_TRUE(files_are_equal(input, output));
}

TEST(hand_made_tests, picture_test) {
    std::string file_name = "manowar";

    std::string input = input_path + file_name + ".jpg";
    std::string output = output_path + file_name + ".jpg";

    EXPECT_NO_THROW(encode(input, buffer_path));
    EXPECT_NO_THROW(decode(buffer_path, output));
    EXPECT_TRUE(files_are_equal(input, output));
}

//TEST(hand_made_tests, video_test) {
//    std::string file_name = "manowar_concert";
//
//    std::string input = input_path + file_name + ".mp4";
//    std::string output = output_path + file_name + ".mp4";
//
//    EXPECT_NO_THROW(encode(input, buffer_path));
//    EXPECT_NO_THROW(decode(buffer_path, output));
//    EXPECT_TRUE(files_are_equal(input, output));
//}

TEST(exception_tests, no_file_test) {
    std::string file_name = "no_file";

    std::string input = input_path + file_name + input_file_ending;
    std::string output = output_path + file_name + output_file_ending;

    EXPECT_ANY_THROW(encode(input, buffer_path));
}

static const int ONE_KB = 1024;
static const int ONE_MB = 1024 * 1024;
static const int MANY_MB = 150 * 1024 * 1024;


//TEST(random_tests, one_kb_test) {
//    std::string file_name = "small_random_test";
//
//    std::string input = input_path + file_name + input_file_ending;
//    std::string output = output_path + file_name + output_file_ending;
//
//    writer w = writer(input);
//    std::mt19937 int_random(static_cast<unsigned int>(time(0)));
//    auto between_random = std::bind(std::uniform_int_distribution<uint8_t>(0, 255), int_random);
//    for (size_t i = 0; i < ONE_KB; ++i) {
//        uint8_t c = between_random();
//        w.write_char(c);
//    }
//
//    EXPECT_NO_THROW(encode(input, buffer_path));
//    EXPECT_NO_THROW(decode(buffer_path, output));
//    EXPECT_TRUE(files_are_equal(input, output));
//}
//
//TEST(random_tests, one_mb_test) {
//    std::string file_name = "average_random_test";
//
//    std::string input = input_path + file_name + input_file_ending;
//    std::string output = output_path + file_name + output_file_ending;
//
//    writer w = writer(input);
//    std::mt19937 int_random(static_cast<unsigned int>(time(0)));
//    auto between_random = std::bind(std::uniform_int_distribution<uint8_t>(0, 255), int_random);
//    for (size_t i = 0; i < ONE_MB; ++i) {
//        uint8_t c = between_random();
//        w.write_char(c);
//    }
//
//    EXPECT_NO_THROW(encode(input, buffer_path));
//    EXPECT_NO_THROW(decode(buffer_path, output));
//    EXPECT_TRUE(files_are_equal(input, output));
//}
//
//
//
//  ! ACHTUNG !
//
//  This test is huge, so run it only under release mode.
//  To change debug mode on release (OS X), please follow these steps:
//  (Settings -> Build, Execution, Deployment -> CMake) and change Debug on Release.
//

//TEST(random_tests, many_mb_test) {
//    std::string file_name = "big_random_test";
//
//    std::string input = input_path + file_name + input_file_ending;
//    std::string output = output_path + file_name + output_file_ending;
//
//    writer w = writer(input);
//    std::mt19937 int_random(static_cast<unsigned int>(time(0)));
//    auto between_random = std::bind(std::uniform_int_distribution<uint8_t>(0, 255), int_random);
//    for (size_t i = 0; i < MANY_MB; ++i) {
//        uint8_t c = between_random();
//        w.write_char(c);
//    }
//
//    EXPECT_NO_THROW(encode(input, buffer_path));
//    EXPECT_NO_THROW(decode(buffer_path, output));
//    EXPECT_TRUE(files_are_equal(input, output));
//}