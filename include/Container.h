#pragma once
#include "Dependencies.h"
#include "FileHelper.h"
#include "Items.h"

class Container {
private:
    std::vector<Group> group_list;
    std::vector<Task> task_list;

    // Singletone
    Container();

public:
    // Block other methods to appear
    Container(Container const&)         = delete;
    void operator=(Container const&)    = delete;

    static Container& getInstance();

    std::vector<Group>& getGroupList();
    std::vector<Task>& getTaskList();

    void add(Group group);
    void add(Task task);

    void removeGroup(int group_index);
    void removeTask(int task_index);
};