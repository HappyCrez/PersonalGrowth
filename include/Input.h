#pragma once
#include "Dependencies.h"

class Input {
private:
    // Block constructor -> singletone
    Input() { }
    
    bool isLineIncorrect(std::string line);
public:
    // Block other methods to appear
    Input(Input const&)     = delete;
    void operator=(Input const&) = delete;

    static Input& getInstance();

    int number();
    int numberInBounds(int left, int right);

    std::string name();
    std::string lineOnlyEnglishLetters();
    std::string line();

    std::string date();

    bool confirmation();
};