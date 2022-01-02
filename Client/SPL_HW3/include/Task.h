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
public:
    Task(ConnectionHandler* connectionHandler);
    void run();
};

#endif //SPL_HW3_TASK_H
