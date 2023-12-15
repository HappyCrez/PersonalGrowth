package plannerApp.javafxWidget;

import java.util.ArrayList;

public class TaskGroup {

    String name;
    String color;

    ArrayList<Long> taskList;

    public TaskGroup (String name, String color) {
        this.name = name;
        this.color = color;

        taskList = new ArrayList<Long>();
    }

    public void addTaskID(long taskID) {
        taskList.add(taskID);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Long> getTaskList() {
        return taskList;
    }
}