#include "Dependencies.h"
#include "Application.h"
#include "Input.h"

int main(int argc, char** argv) { 
    Application app;
    Container::getInstance(); // lazy init

    int option;
    
    while (app.isOpen()) {
        app.showMenu();
        option = Input::getInstance().numberInBounds(1, 4);
        app.startAppByOption(option);
    }
    return 0;
}