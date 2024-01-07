#pragma once
#include "Dependencies.h"
#include "Input.h"

class Timer {
private:
    enum options {setTimer_option = 1, play_option, return_option};
    int timerMinets = 25;
public:
    Timer();
    void start(); 
    void showMenu();
    void callFunctionByOption(int option);

    void setTimer();
    void playTimer();
};