#include "Application.h"

Application::Application() { live = true; }

void Application::showMenu() {
    std::cout << "# Menu" << std::endl;
    std::cout << timer_option       << ") Timer" << std::endl;
    std::cout << to_do_list_option  << ") To do list" << std::endl;
    std::cout << exit_option        << ") Exit" << std::endl;
}

void Application::startAppByOption(int option) {
    switch (option) {
    case timer_option:
        timer.start();
        break;
    case to_do_list_option:
        to_do_list.start();
        break;
    case exit_option:
        std::cout << "Goodbye" << std::endl;
        close();
        break;
    default:
        std::cout << "Unknown option - \"" << option << "\" " << std::endl;
        break;
    }
}

void Application::close() {
    live = false;
}

bool Application::isOpen() {
    return live;
}