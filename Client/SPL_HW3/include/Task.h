//
// Created by Shay on 27/12/2021.
//

#ifndef SPL_HW3_TASK_H
#define SPL_HW3_TASK_H

#include <iostream>
#include <mutex>
#include <thread>
#include "../include/connectionHandler.h"

class Task{
private:
    ConnectionHandler* _connectionHandler;
    bool _terminated;
    bool login;
public:
    Task(ConnectionHandler* connectionHandler);
    void run();
    bool isTerminated();
    bool isLogIn();
};

#endif //SPL_HW3_TASK_H
