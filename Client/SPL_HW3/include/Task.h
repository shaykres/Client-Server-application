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
    int _id;
    std::mutex& _mutex;
    ConnectionHandler& _connectionHandler;
    bool _terminated;
public:
    Task(int id,ConnectionHandler& connectionHandler, std::mutex& mutex);
    void run();
};

#endif //SPL_HW3_TASK_H
