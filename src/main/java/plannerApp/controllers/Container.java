package plannerApp.controllers;

import java.util.ArrayList;

import plannerApp.javafxWidget.GroupItem;
import plannerApp.javafxWidget.TaskItem;

public class Container {
    
    private final static ArrayList<GroupItem> groupList = new ArrayList<GroupItem>();;
    private static GroupItem mainGroup, completeGroup;
    private static GroupItem chosenGroup;
    
    private final static ArrayList<TaskItem> taskList = new ArrayList<TaskItem>();
    private final static ArrayList<TaskItem> completeList = new ArrayList<TaskItem>();

    private final static String standartStyle = "-fx-background-color: transparent;";
    private final static String chooseStyle = "-fx-background-color: rgba(235,235,235, 0.7);";

    private static ArrayList<Notification> Observable = new ArrayList<Notification>();

    private Container () { }

    public static GroupItem getMainGroup() {
        return mainGroup;
    }
    
    public static GroupItem getCompleteGroup() {
        return completeGroup;
    }

    public static GroupItem getChooseGroup() {
        return chosenGroup;
    }

    public static void initialize() {
        mainGroup = new GroupItem("Tasks", "none", false);
        completeGroup = new GroupItem("Complete", "none", false);
        completeGroup.getTaskList().addAll(FileHelper.ReadCompleteGroup());

        chooseItem(mainGroup);

        for (GroupItem group : FileHelper.ReadGroupList())
            groupList.add(group);

        for (TaskItem task : FileHelper.ReadTaskList()) {
            mainGroup.addTaskID(task.getID());
            task.setGroup(mainGroup);
            for (GroupItem group : groupList)
                if (group.getTaskList().contains(task.getID())) {
                    task.setGroup(group);
                    break;
                }
            taskList.add(task);
        }
    }

    public static ArrayList<GroupItem> getGroupList() {
        return groupList;
    }
    
    public static ArrayList<TaskItem> getTaskList() {
        return taskList;
    }

    public static void addGroup(GroupItem group) {
        groupList.add(group);

        FileHelper.UpdateGroupList(groupList);
        notifyObservers();
    }

    public static void addTask(TaskItem task) {
        task.setGroup(chosenGroup);
        
        mainGroup.addTaskID(task.getID());
        if (chosenGroup != mainGroup)
            chosenGroup.getTaskList().add(task.getID());
        taskList.add(task);
        
        FileHelper.UpdateTaskList(taskList);
        FileHelper.UpdateGroupList(groupList);
        notifyObservers();
    }

    public static void chooseItem(GroupItem group) {
        if (chosenGroup == group) return;
        chosenGroup = group;
           
        setStandardStyle();
        group.setStyle(chooseStyle);
        notifyObservers();
    }

    private static void setStandardStyle() {
        mainGroup.setStyle(standartStyle);
        completeGroup.setStyle(standartStyle);
        for (GroupItem group : groupList)
            group.setStyle(standartStyle);
    }

    public static void chooseItem(TaskItem task) {
        // TODO::Highlight task
    }

    public static void checkTask(TaskItem task) {
        mainGroup.deleteTaskID(task.getID());
        for (GroupItem group : groupList)
            group.deleteTaskID(task.getID());

        completeGroup.addTaskID(task.getID());
        FileHelper.updateCompleteGroup(completeGroup);
        notifyObservers();
    }

    public static void deleteItem(TaskItem task){
        int deleteIndex = -1;
        for (int i = 0; i < taskList.size(); i++) {    
            TaskItem tempTask = taskList.get(i);
            if (tempTask.getID() == task.getID()) {
                deleteIndex = i;
                break;
            }
        }
        if (deleteIndex == -1) return;
        taskList.remove(deleteIndex);

        mainGroup.deleteTaskID(task.getID());
        completeGroup.deleteTaskID(task.getID());
        for (GroupItem group : groupList)
            group.deleteTaskID(task.getID());
            
        FileHelper.updateCompleteGroup(completeGroup);
        FileHelper.UpdateGroupList(groupList);
        FileHelper.UpdateTaskList(taskList);
        notifyObservers();
    }

    public static void deleteItem(GroupItem group){
        groupList.remove(group);

        FileHelper.UpdateGroupList(groupList);
        notifyObservers();
    }

    private static void notifyObservers() {
        for (Notification obj : Observable) {
            obj.Notify();
        }
    }

    public static void addObservable(Notification observer) {
        Observable.add(observer);
    }

    public static void removeObservable(Notification observer) {
        Observable.remove(observer);
    }
}
