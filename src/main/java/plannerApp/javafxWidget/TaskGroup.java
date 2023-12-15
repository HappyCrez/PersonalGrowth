package plannerApp.javafxWidget;

import java.util.ArrayList;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TaskGroup extends AnchorPane {

    String color;
    Label groupName;
    Button deleteGroup;

    ArrayList<Long> taskList;

    public TaskGroup (String name, String color) {
        groupName = new Label(name);
        deleteGroup = new Button("", new FontIcon("mdi-delete-forever"));
        deleteGroup.getStyleClass().add("deleteBtn");

        this.color = color;
        taskList = new ArrayList<Long>();

        this.getChildren().addAll(groupName, deleteGroup);
        this.getStyleClass().add("groupItem");

        AnchorPane.setLeftAnchor(groupName, 0.0);
        AnchorPane.setTopAnchor(groupName, 0.0);
        AnchorPane.setBottomAnchor(groupName, 0.0);

        AnchorPane.setRightAnchor(deleteGroup, 0.0);
        AnchorPane.setTopAnchor(deleteGroup, 0.0);
        AnchorPane.setBottomAnchor(deleteGroup, 0.0);
    }

    public void addTaskID(long taskID) {
        taskList.add(taskID);
    }

    public String getName() {
        return groupName.getText();
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Long> getTaskList() {
        return taskList;
    }
}