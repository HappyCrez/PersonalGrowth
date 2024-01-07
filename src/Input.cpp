#include "Input.h"

Input& Input::getInstance() {
    static Input instance;
    return instance;
}

int Input::number() {
    int input;
    std::cin >> input;
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    return input; 
}

int Input::numberInBounds(int left, int right) {
    int input;
    bool incorrect = false;
    do {
        if (incorrect) std::cout << "Incorrect input" << std::endl << std::endl;
        input = number();
        incorrect = true;
    } while (input < left || input > right);
    return input;
}

std::string Input::name() {
    std::string str;
    bool incorrect = false;
    do {
        if (incorrect) std::cout << "Very short name" << std::endl << std::endl;
        str = lineOnlyEnglishLetters();
        incorrect = true;
    } while (str.length() < 3);
    return str;
}

std::string Input::lineOnlyEnglishLetters() {
    std::string str;
    bool incorrect = false;
    do {
        if (incorrect) std::cout << "Incorrect input" << std::endl << std::endl;
        str = line();
        incorrect = true;
    } while (isLineIncorrect(str));
    return str;
}

std::string Input::line() {
    std::string str;
    std::cin >> str;
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    return str;
}

bool Input::isLineIncorrect(std::string line) {
    int len = static_cast<int>(line.length());
    for (int i = 0; i < len; i++)
        if ( (line[i] < 'a' || line[i] > 'z') && (line[i] < 'A' || line[i] > 'Z') &&
             (line[i] < '0' || line[i] > '9') && line[i] != '#' && line[i] != '.')
            return true;
    return false;
}

std::string Input::date() {
    std::string str = "";
    
    std::cout << "Month 1..12" << std::endl;
    int month = numberInBounds(1, 12);
    
    int max_day_index = (month == 2) ? 28 : 30 + ((month > 7) ? month+1 : month) % 2;
    std::cout << "Day 1.." << max_day_index << std::endl;
    int day = numberInBounds(1, max_day_index);
    
    int year = 2024; // TODO::Calculate year
    
    if (day < 10) str += "0";
    str += std::to_string(day) + ".";
    if (month < 10) str += "0";
    str += std::to_string(month) + "." + std::to_string(year);
    return str;
}

bool Input::confirmation() {
    std::string answer = line();
    if (answer.compare("yes") == 0) return true;
    if (answer.compare("Yes") == 0) return true;
    if (answer.compare("y") == 0)   return true;
    if (answer.compare("Y") == 0)   return true;
    return false;
}