#include "Container.h"

Container::Container() {
    group_list = FileHelper::getInstance().readGroupList();
    task_list = FileHelper::getInstance().readTaskList();
    // for (Group group : FileHelper::getInstance().readGroupList())
    //     group_list.push_back(group);
    // for (Task task : FileHelper::getInstance().readTaskList())
    //     task_list.push_back(task);
}

Container& Container::getInstance() {
    static Container instance;
    return instance;
}

std::vector<Group>& Container::getGroupList() {
    return group_list;
}

std::vector<Task>& Container::getTaskList() {
    return task_list;
}

void Container::add(Group group) {
    group_list.push_back(group);
    FileHelper::getInstance().updateGroupList(group_list);
}

void Container::add(Task task) {
    task_list.push_back(task);
    FileHelper::getInstance().updateGroupList(group_list);
    FileHelper::getInstance().updateTaskList(task_list);
}

void Container::removeGroup(int group_index) {
    for (int64 id : group_list[group_index].taskIdList) {
        int delete_index = -1;
        for (int i = 0; i < task_list.size(); i++) {
            Task task = task_list.at(i);
            if (task.id == id) {
                delete_index =  i;
            }
        }
        if (delete_index == -1) continue;
        task_list.erase(task_list.begin() + delete_index);
    }
    group_list.erase(group_list.begin() + group_index);
    
    FileHelper::getInstance().updateGroupList(group_list);
    FileHelper::getInstance().updateTaskList(task_list);
}

void Container::removeTask(int task_index) {
    int64 task_id = task_list[task_index].id;
    for (Group group : group_list) {
        std::vector<int64> *id_list = &(group.taskIdList);
        int delete_index = -1;
        for (int i = 0; i < group.taskIdList.size(); i++) {
            if (group.taskIdList.at(i) == task_id) {
                delete_index = i;
                break;
            }
        }
        if (delete_index == -1) continue;
        group.taskIdList.erase(group.taskIdList.begin() + delete_index);
    }
    task_list.erase(task_list.begin() + task_index);

    FileHelper::getInstance().updateGroupList(group_list);
    FileHelper::getInstance().updateTaskList(task_list);
}