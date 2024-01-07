#pragma once
#include "Dependencies.h"
#include "Items.h"

class FileHelper {
private:
    // Singletone
    FileHelper() { }
public:
    // Block other methods to appear
    FileHelper(FileHelper const&)       = delete;
    void operator=(FileHelper const&)   = delete;

    static FileHelper& getInstance();

    std::vector<Group> readGroupList();
    std::vector<Task> readTaskList();

    std::vector<std::string> readFile(std::string filename);

    void updateGroupList(std::vector<Group> group_list);
    void updateTaskList(std::vector<Task> task_list);
};