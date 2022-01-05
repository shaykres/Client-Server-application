//
// Created by Shay on 27/12/2021.
//
#include "../include/Task.h"

using std::string;
Task::  Task(ConnectionHandler* connectionHandler) :_connectionHandler(connectionHandler){
    _terminated= false;
    login= false;
}

void Task::run() {
    while(!_terminated) {
        std::string answer;
       // std::cout << "dgdggdggdgdgdggggggd "<< std::endl;
        // Get back an answer: by using the expected number of bytes (len bytes + newline delimiter)
        // We could also use: connectionHandler.getline(answer) and then get the answer without the newline char at the end
        if (!_connectionHandler->getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        } else {
            int len = answer.length();
            // A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
            // we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.
            answer.resize(len - 1);
            std::cout << "Reply: " << answer  << std::endl << std::endl;
            if (std::equal(answer.begin(), answer.end(), "ACK 2 ") ) {
                login = true;
               // std::cout << "user log in" << std::endl;
            }

            if (std::equal(answer.begin(), answer.end(), "ACK 3 ") ) {
                std::cout << "Exiting...\n" << std::endl;
                _terminated = true;
            }
        }
    }
}

bool Task::isTerminated() {
    return _terminated;
}

bool Task::isLogIn() {
    return login;
}
