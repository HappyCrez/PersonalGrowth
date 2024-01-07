#pragma once
#include "Dependencies.h"

class Input {
private:
    // Block constructor -> singletone
    Input() { }
public:
    // Block other methods to appear
    Input(Input const&)     = delete;
    void operator=(Input const&) = delete;

    static Input& getInstance();

    int number();
    int numberInBounds(int left, int right);

    std::string name();
    std::string lineOnlyEnglishLetters();
    bool isLineIncorrect(std::string line);
    std::string line();

    std::string date();

    bool confirmation();
};