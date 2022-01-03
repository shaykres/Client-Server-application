cmake_minimum_required(VERSION 3.20)
project(SPL_HW3)

set(CMAKE_CXX_STANDARD 11)

set(Boost_INCLUDE_DIR /cygdrive/c/cygwin64/usr/include/)

find_package(Boost REQUIRED COMPONENTS system)
include_directories(${Boost_INCLUDE_DIRS})
#FIND_PACKAGE(Boost)

add_executable(SPL_HW3 src/echoClient.cpp src/connectionHandler.cpp  include/connectionHandler.h include/Task.h src/Task.cpp)
target_link_libraries(${PROJECT_NAME}
        ${Boost_LIBRARIES})
#IF (Boost_FOUND)
#    INCLUDE_DIRECTORIES(${Boost_INCLUDE_DIR})
#    ADD_DEFINITIONS( "-DHAS_BOOST" )
#ENDIF()
