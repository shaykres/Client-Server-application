# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.20

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /cygdrive/c/Users/Shay/AppData/Local/JetBrains/CLion2021.2/cygwin_cmake/bin/cmake.exe

# The command to remove a file.
RM = /cygdrive/c/Users/Shay/AppData/Local/JetBrains/CLion2021.2/cygwin_cmake/bin/cmake.exe -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/SPL_HW3.dir/depend.make
# Include the progress variables for this target.
include CMakeFiles/SPL_HW3.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/SPL_HW3.dir/flags.make

CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.o: CMakeFiles/SPL_HW3.dir/flags.make
CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.o: ../src/echoClient.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.o"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.o -c /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/echoClient.cpp

CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.i"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/echoClient.cpp > CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.i

CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.s"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/echoClient.cpp -o CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.s

CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.o: CMakeFiles/SPL_HW3.dir/flags.make
CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.o: ../src/connectionHandler.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.o"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.o -c /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/connectionHandler.cpp

CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.i"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/connectionHandler.cpp > CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.i

CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.s"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/connectionHandler.cpp -o CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.s

CMakeFiles/SPL_HW3.dir/src/Task.cpp.o: CMakeFiles/SPL_HW3.dir/flags.make
CMakeFiles/SPL_HW3.dir/src/Task.cpp.o: ../src/Task.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/SPL_HW3.dir/src/Task.cpp.o"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/SPL_HW3.dir/src/Task.cpp.o -c /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/Task.cpp

CMakeFiles/SPL_HW3.dir/src/Task.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/SPL_HW3.dir/src/Task.cpp.i"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/Task.cpp > CMakeFiles/SPL_HW3.dir/src/Task.cpp.i

CMakeFiles/SPL_HW3.dir/src/Task.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/SPL_HW3.dir/src/Task.cpp.s"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/src/Task.cpp -o CMakeFiles/SPL_HW3.dir/src/Task.cpp.s

# Object files for target SPL_HW3
SPL_HW3_OBJECTS = \
"CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.o" \
"CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.o" \
"CMakeFiles/SPL_HW3.dir/src/Task.cpp.o"

# External object files for target SPL_HW3
SPL_HW3_EXTERNAL_OBJECTS =

SPL_HW3.exe: CMakeFiles/SPL_HW3.dir/src/echoClient.cpp.o
SPL_HW3.exe: CMakeFiles/SPL_HW3.dir/src/connectionHandler.cpp.o
SPL_HW3.exe: CMakeFiles/SPL_HW3.dir/src/Task.cpp.o
SPL_HW3.exe: CMakeFiles/SPL_HW3.dir/build.make
SPL_HW3.exe: /usr/lib/libboost_system.dll.a
SPL_HW3.exe: CMakeFiles/SPL_HW3.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Linking CXX executable SPL_HW3.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/SPL_HW3.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/SPL_HW3.dir/build: SPL_HW3.exe
.PHONY : CMakeFiles/SPL_HW3.dir/build

CMakeFiles/SPL_HW3.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/SPL_HW3.dir/cmake_clean.cmake
.PHONY : CMakeFiles/SPL_HW3.dir/clean

CMakeFiles/SPL_HW3.dir/depend:
	cd /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3 /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3 /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug /cygdrive/c/Users/Shay/CLionProjects/SPL_HW3/cmake-build-debug/CMakeFiles/SPL_HW3.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/SPL_HW3.dir/depend
