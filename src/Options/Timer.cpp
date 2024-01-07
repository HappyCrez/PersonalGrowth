#include "Timer.h"

Timer::Timer() { }

void Timer::start() {
    int option = 0;
    while (option != return_option) {
        showMenu();
        option = Input::getInstance().numberInBounds(setTimer_option, return_option);
        callFunctionByOption(option);
    }
}

void Timer::showMenu() {
    std::cout << std::endl << "# Timer" << std::endl;
    std::cout << setTimer_option << ") Settings" << std::endl;
    std::cout << play_option << ") Play timer" << std::endl;
    std::cout << return_option << ") Return" << std::endl;
}

void Timer::callFunctionByOption(int option) {
    switch (option) {
    case setTimer_option:
        setTimer();
        break;
    case play_option:
        playTimer();
        break;
    case return_option:
        std::cout << "Return to menu.." << std::endl;
        break;
    }
}

void Timer::setTimer() {
    std::cout << "Input time in minets (1..120)" << std::endl;
    timerMinets = Input::getInstance().numberInBounds(1, 120);
}

void Timer::playTimer() {
    auto start = std::chrono::system_clock::now();
    auto current_time = start;
    std::chrono::duration<double> time_passed = current_time - start;
    int target = timerMinets * 60;

    while (time_passed.count() < target) {
        clearConsole();
        std::cout << "# Timer" << std::endl;
        std::cout << "time " << time_passed.count() << std::endl;
        std::cout << "left " << target - time_passed.count() << std::endl;
        std::this_thread::sleep_for (std::chrono::milliseconds(100));
        time_passed = current_time - start;
        current_time = std::chrono::system_clock::now();
    }
    std::cout << "Timer was finished!";
}