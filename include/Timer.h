#pragma once
#include "Dependencies.h"
#include "Input.h"

class Timer {
private:
    enum options {setTimer_option = 1, play_option, return_option};
    int timerMinets = 25;

    void showMenu();
    void callFunctionByOption(int option);

    void setTimer();
    void playTimer();
public:
    Timer();
    void start();
};