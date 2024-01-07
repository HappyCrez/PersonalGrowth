#pragma once
#include "Dependencies.h"
#include "Timer.h"
#include "ToDoList.h"

class Application {
private:
    bool live;
    Timer timer;
    ToDoList to_do_list;
    
public:
    enum options {timer_option = 1, to_do_list_option, exit_option};
    Application();
    void showMenu();
    void startAppByOption(int option);
    
    void close();
    bool isOpen();
};