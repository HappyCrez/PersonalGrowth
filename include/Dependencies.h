#pragma once
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <limits>
#include <chrono>
#include <vector>
#include <string>
#include <cstdint>
#include <fstream>
#include <exception>
#include <algorithm>
#include <thread>
#include <stdlib.h>

typedef long long int64;

#ifdef _WIN32
static inline void clearConsole() {
	system("cls");
}

#else // UNIX:
static inline void clearConsole() {
	system("clear");
}
#endif