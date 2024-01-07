#include "FileHelper.h"

FileHelper& FileHelper::getInstance() {
    static FileHelper instance;
    return instance;
}

std::vector<Group> FileHelper::readGroupList() {
    std::vector<Group> group_list;
    std::vector<std::string> file_content = readFile("groupList");
    
    std::vector<int64> id_list;
    std::string delimiter = ", ";
    std::string id;
    int size = static_cast<int>(file_content.size());
    for (int i = 0; i < size - 1; i += 2) {
        id_list.clear();
        size_t pos = 0;
        while ((pos = file_content[i + 1].find(delimiter)) != std::string::npos) {
            id = file_content[i + 1].substr(0, pos);
            id_list.push_back( static_cast<int64>(std::atoll(id.c_str())) );
            file_content[i + 1].erase(0, pos + delimiter.length());
        }
        group_list.push_back(Group(file_content[i], id_list));
    }
    return group_list;
}

std::vector<Task> FileHelper::readTaskList() {
    std::vector<Task> task_list;
    std::vector<std::string> file_content = readFile("taskList");
    int size = static_cast<int>(file_content.size()), i = 0;
    for (int i = 0; i < size - 2; i += 3) {
        Task task(file_content[i], file_content[i+1], static_cast<int64>(std::atoll(file_content[i+2].c_str())) );
        task_list.push_back(task);
    }
    return task_list;
}

std::vector<std::string> FileHelper::readFile(std::string filename) {
    std::vector<std::string> file_content;
    std::string line;
    std::ifstream file("Resources/" + filename + ".txt");
    if (file.is_open()) {
        while (std::getline(file, line)) {
            file_content.push_back(line);
        }
    }
    file.close();
    return file_content;
}
    
void FileHelper::updateGroupList(std::vector<Group> group_list) {
    std::ofstream file ("Resources/groupList.txt");
    std::string line;
    if (file.is_open())
        for (Group group : group_list)
            file << group.toString() << std::endl;
}

void FileHelper::updateTaskList(std::vector<Task> task_list) {
    std::ofstream file ("Resources/taskList.txt");
    std::string line;
    if (file.is_open()) {
        for (Task task : task_list)
            file << task.toString() << std::endl;
    }
}