# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release"

# Include any dependencies generated for this target.
include CMakeFiles/lib.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/lib.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/lib.dir/flags.make

CMakeFiles/lib.dir/sources/reader.cpp.o: CMakeFiles/lib.dir/flags.make
CMakeFiles/lib.dir/sources/reader.cpp.o: ../sources/reader.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/lib.dir/sources/reader.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/lib.dir/sources/reader.cpp.o -c "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/reader.cpp"

CMakeFiles/lib.dir/sources/reader.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lib.dir/sources/reader.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/reader.cpp" > CMakeFiles/lib.dir/sources/reader.cpp.i

CMakeFiles/lib.dir/sources/reader.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lib.dir/sources/reader.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/reader.cpp" -o CMakeFiles/lib.dir/sources/reader.cpp.s

CMakeFiles/lib.dir/sources/writer.cpp.o: CMakeFiles/lib.dir/flags.make
CMakeFiles/lib.dir/sources/writer.cpp.o: ../sources/writer.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/lib.dir/sources/writer.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/lib.dir/sources/writer.cpp.o -c "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/writer.cpp"

CMakeFiles/lib.dir/sources/writer.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lib.dir/sources/writer.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/writer.cpp" > CMakeFiles/lib.dir/sources/writer.cpp.i

CMakeFiles/lib.dir/sources/writer.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lib.dir/sources/writer.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/writer.cpp" -o CMakeFiles/lib.dir/sources/writer.cpp.s

CMakeFiles/lib.dir/sources/encoder.cpp.o: CMakeFiles/lib.dir/flags.make
CMakeFiles/lib.dir/sources/encoder.cpp.o: ../sources/encoder.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/lib.dir/sources/encoder.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/lib.dir/sources/encoder.cpp.o -c "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/encoder.cpp"

CMakeFiles/lib.dir/sources/encoder.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lib.dir/sources/encoder.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/encoder.cpp" > CMakeFiles/lib.dir/sources/encoder.cpp.i

CMakeFiles/lib.dir/sources/encoder.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lib.dir/sources/encoder.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/encoder.cpp" -o CMakeFiles/lib.dir/sources/encoder.cpp.s

CMakeFiles/lib.dir/sources/huffman.cpp.o: CMakeFiles/lib.dir/flags.make
CMakeFiles/lib.dir/sources/huffman.cpp.o: ../sources/huffman.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/lib.dir/sources/huffman.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/lib.dir/sources/huffman.cpp.o -c "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/huffman.cpp"

CMakeFiles/lib.dir/sources/huffman.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lib.dir/sources/huffman.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/huffman.cpp" > CMakeFiles/lib.dir/sources/huffman.cpp.i

CMakeFiles/lib.dir/sources/huffman.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lib.dir/sources/huffman.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/huffman.cpp" -o CMakeFiles/lib.dir/sources/huffman.cpp.s

CMakeFiles/lib.dir/sources/decoder.cpp.o: CMakeFiles/lib.dir/flags.make
CMakeFiles/lib.dir/sources/decoder.cpp.o: ../sources/decoder.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object CMakeFiles/lib.dir/sources/decoder.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/lib.dir/sources/decoder.cpp.o -c "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/decoder.cpp"

CMakeFiles/lib.dir/sources/decoder.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lib.dir/sources/decoder.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/decoder.cpp" > CMakeFiles/lib.dir/sources/decoder.cpp.i

CMakeFiles/lib.dir/sources/decoder.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lib.dir/sources/decoder.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/sources/decoder.cpp" -o CMakeFiles/lib.dir/sources/decoder.cpp.s

CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.o: CMakeFiles/lib.dir/flags.make
CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.o: ../tests/gtest/gtest-all.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_6) "Building CXX object CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.o -c "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/gtest/gtest-all.cc"

CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/gtest/gtest-all.cc" > CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.i

CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/gtest/gtest-all.cc" -o CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.s

CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.o: CMakeFiles/lib.dir/flags.make
CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.o: ../tests/gtest/gtest_main.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_7) "Building CXX object CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.o -c "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/gtest/gtest_main.cc"

CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/gtest/gtest_main.cc" > CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.i

CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/gtest/gtest_main.cc" -o CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.s

CMakeFiles/lib.dir/tests/testing.cpp.o: CMakeFiles/lib.dir/flags.make
CMakeFiles/lib.dir/tests/testing.cpp.o: ../tests/testing.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_8) "Building CXX object CMakeFiles/lib.dir/tests/testing.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/lib.dir/tests/testing.cpp.o -c "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/testing.cpp"

CMakeFiles/lib.dir/tests/testing.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lib.dir/tests/testing.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/testing.cpp" > CMakeFiles/lib.dir/tests/testing.cpp.i

CMakeFiles/lib.dir/tests/testing.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lib.dir/tests/testing.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/tests/testing.cpp" -o CMakeFiles/lib.dir/tests/testing.cpp.s

# Object files for target lib
lib_OBJECTS = \
"CMakeFiles/lib.dir/sources/reader.cpp.o" \
"CMakeFiles/lib.dir/sources/writer.cpp.o" \
"CMakeFiles/lib.dir/sources/encoder.cpp.o" \
"CMakeFiles/lib.dir/sources/huffman.cpp.o" \
"CMakeFiles/lib.dir/sources/decoder.cpp.o" \
"CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.o" \
"CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.o" \
"CMakeFiles/lib.dir/tests/testing.cpp.o"

# External object files for target lib
lib_EXTERNAL_OBJECTS =

lib: CMakeFiles/lib.dir/sources/reader.cpp.o
lib: CMakeFiles/lib.dir/sources/writer.cpp.o
lib: CMakeFiles/lib.dir/sources/encoder.cpp.o
lib: CMakeFiles/lib.dir/sources/huffman.cpp.o
lib: CMakeFiles/lib.dir/sources/decoder.cpp.o
lib: CMakeFiles/lib.dir/tests/gtest/gtest-all.cc.o
lib: CMakeFiles/lib.dir/tests/gtest/gtest_main.cc.o
lib: CMakeFiles/lib.dir/tests/testing.cpp.o
lib: CMakeFiles/lib.dir/build.make
lib: CMakeFiles/lib.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_9) "Linking CXX executable lib"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/lib.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/lib.dir/build: lib

.PHONY : CMakeFiles/lib.dir/build

CMakeFiles/lib.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/lib.dir/cmake_clean.cmake
.PHONY : CMakeFiles/lib.dir/clean

CMakeFiles/lib.dir/depend:
	cd "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib" "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib" "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release" "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release" "/Users/admin/Documents/University/#GitHub/cpp/huffman/lib/cmake-build-release/CMakeFiles/lib.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/lib.dir/depend

