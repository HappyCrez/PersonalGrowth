#pragma once

struct Group {
    std::string name;
    std::vector<int64> taskIdList;

    Group(std::string name) {
        Group::name = name;
    }

    Group(std::string name, std::vector<int64> taskIdList) {
        Group::name = name;
        Group::taskIdList = taskIdList;
    }

    std::string toString() {
        std::string context = name + "\n";
        for (int64 id : taskIdList) {
            context += std::to_string(id) + ", ";
        }
        return context;
    }
};

struct Task {
    std::string name;
    std::string date;
    int64 id;

    Task(std::string name, std::string date, int64 id) : Task(name, date) {
        Task::id = id;
    }

    Task(std::string name, std::string date) {
        Task::name = name;
        Task::date = date;
        Task::id = static_cast<int64>(std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::system_clock::now().time_since_epoch()).count());
    }

    std::string toString() {
        return name + "\n" + date + "\n" + std::to_string(id);
    }
};