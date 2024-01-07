#include "ToDoList.h"

ToDoList::ToDoList() { }

void ToDoList::start() {
    int option = -1;
    while (option != return_option) {
        std::cout << std::endl;
        showMenu();
        option = Input::getInstance().numberInBounds(showList_option, return_option);
        callFunctionByOption(option);
    }
    std::cout << std::endl;
}

void ToDoList::showMenu() {
    std::cout << "# To do list" << std::endl;
    std::cout << showList_option        << ") Show list"    << std::endl;
    std::cout << createGroup_option     << ") Create group" << std::endl;
    std::cout << createTask_option      << ") Create task"  << std::endl;
    std::cout << deleteGroup_option     << ") Delete group" << std::endl;
    std::cout << deleteTask_option      << ") Delete task"  << std::endl;
    std::cout << return_option          << ") Return"       << std::endl;
}

void ToDoList::callFunctionByOption(int option) {
    switch (option) {
    case showList_option:
        showList();
        break;
    case createGroup_option:
        createGroup();
        break;
    case createTask_option:
        createTask();
        break;
    case deleteGroup_option:
        deleteGroup();
        break;
    case deleteTask_option:
        deleteTask();
        break;
    case return_option:
        std::cout << "Return to menu.." << std::endl;
        break;
    default:
        break;
    }
}

void ToDoList::showList() {
    std::vector<Group> group_list = Container::getInstance().getGroupList();
    std::vector<Task> task_list = Container::getInstance().getTaskList();
    int group_count = static_cast<int>(group_list.size()),
        task_count = static_cast<int>(task_list.size()); 
    for (int group_index = 0; group_index < group_count; group_index++) {
        Group group = group_list[group_index];
        std::cout << std::endl << "### " << group_index + 1 << " | " << group.name << " ###" << std::endl;
        
        int task_num = 1;
        for (int task_index = 0; task_index < task_count; task_index++) {
            Task task = task_list[task_index];
            for (int64 id : group.taskIdList)
                if (task.id == id) {
                    std::cout << task_num << ") "
                    << task.name << " : " << task.date << std::endl;
                    
                    task_num++;
                }
        }
    }
}

void ToDoList::createGroup() {
    std::cout << "# Create Group" << std::endl;
    std::cout << "Group name:" << std::endl;
    std::string name = Input::getInstance().name();
    
    Container::getInstance().add(Group(name));
}

void ToDoList::createTask() {
    showList();
    int group_index = requestGroupIndex();

    std::cout << "# Create Task" << std::endl;
    std::cout << "Task name:" << std::endl;
    std::string name = Input::getInstance().name();

    std::cout << "Task planner date:" << std::endl;
    std::string date = Input::getInstance().date();

    Task task(name, date);

    std::vector<Group>* group_list = &(Container::getInstance().getGroupList());
    (*group_list).at(group_index - 1).taskIdList.push_back(task.id);
    Container::getInstance().add(task);
}

void ToDoList::deleteGroup() {
    showList();
    std::cout << std::endl << "# Delete group" << std::endl;
    int group_index = requestGroupIndex();
    std::cout << "All task of this group will be deleted, are you sure? (Y/N)" << std::endl;
    bool answer = Input::getInstance().confirmation();
    if (answer) Container::getInstance().removeGroup(group_index);
}

void ToDoList::deleteTask() {
    std::vector<Task> task_list = Container::getInstance().getTaskList();
    int size = static_cast<int>(task_list.size());
    std::cout << std::endl << "### Delete task ###" << std::endl;
    for (int i = 0; i < size; i++) {
        Task task = task_list.at(i);
        std::cout << i+1 << ") " << task.name << std::endl;
    }
    int deleteIndex = Input::getInstance().numberInBounds(1, size);
    Container::getInstance().removeTask(deleteIndex - 1);
}

int ToDoList::requestGroupIndex() {
    int group_count = static_cast<int>(Container::getInstance().getGroupList().size());
    std::cout << std::endl << "# Input group index" << std::endl;
    int group_index = Input::getInstance().numberInBounds(1, group_count); 
    return group_index - 1;
}