#pragma once
#include "Dependencies.h"
#include "Input.h"
#include "Container.h"

class ToDoList {
private:

public:
    enum options {showList_option = 1, createGroup_option, createTask_option, deleteGroup_option, deleteTask_option, return_option};
    ToDoList();
    void start();
    void showMenu();
    void callFunctionByOption(int option);

    void showList();
    void createGroup();
    void deleteGroup();
    void createTask();
    void deleteTask();

    int requestGroupIndex();
};