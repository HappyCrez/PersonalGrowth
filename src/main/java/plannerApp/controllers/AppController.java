package plannerApp.controllers;

import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import plannerApp.javafxWidget.GroupItem;
import plannerApp.javafxWidget.TaskItem;
import plannerApp.javafxWidget.calendar.CalendarBox;

public class AppController implements TaskAction, GroupAction {
    ScreenController controller;
    CalendarBox calendarBox;

    @FXML
    private HBox centerView, addGroupBox;
	@FXML
	private Button toTimer, toSettings, addButton, addGroupButton;
    @FXML
    private AnchorPane taskForm;
	@FXML
	private VBox taskBox, groupBox;
    private ArrayList<TaskItem> taskList, completeList;
    private ArrayList<GroupItem> groupList;
    @FXML
    private TextArea contentField, addGroupField;

    private GroupItem chosenGroup;

    private GroupItem mainGroup, completeGroup;

    AppController(ScreenController controller) {
        this.controller = controller; 
    }
    
	@FXML
	private void initialize() {
		calendarBox = new CalendarBox();
        centerView.getChildren().add(calendarBox);

        addGroupField = new TextArea();
        Pattern pattern = Pattern.compile(".{0,15}");
        TextFormatter<Change> formatter = new TextFormatter<Change>((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        addGroupField.setTextFormatter(formatter);
        addGroupField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            {   if (!newPropertyValue) // true then field lose Focus 
                { addGroup(); }
            }
        });

        taskList = new ArrayList<>();
        groupList = new ArrayList<>();
        
        mainGroup = new GroupItem("Tasks", "none", this);
        groupBox.getChildren().add(groupBox.getChildren().size() - 1, mainGroup);
        
        completeGroup = new GroupItem("Complete", "none", this); 
        groupBox.getChildren().add(groupBox.getChildren().size() - 1, completeGroup);
       
        // TODO::Fill complete group from file

        // TODO::Class TaskForm

        chooseItem(mainGroup);
        for (GroupItem group : FileHelper.ReadGroupList(this)) {
            System.out.println(group.getTaskList());
            appendGroup(group);
        }

        for (TaskItem task : FileHelper.ReadTaskList(this)) {    
            for (GroupItem group : groupList)
                if (group.getTaskList().contains(task.getID())) {
                    task.setGroup(group);
                    break;
                }
            appendTask(task);
        }
    }

    @FXML
    private void toTimerClicked() {
        //TODO::Timer view
        System.out.println("to Timer Clicked");
    }
    
    @FXML
    private void toSettingsClicked() {
        //TODO::Settings
        System.out.println("to Settings Clicked");
    }

    @FXML
    void addTask() {
        TaskItem task = createTask();

        appendTask(task);
        chosenGroup.addTaskID(task.getID());

        FileHelper.UpdateGroupList(groupList);

        contentField.setText("");
    }

    @FXML
    void addGroup() {
        if (addGroupBox.getChildrenUnmodifiable().contains(addGroupButton)) {
            addGroupBox.getChildren().remove(addGroupButton);
            addGroupBox.getChildren().add(addGroupField);
            addGroupField.requestFocus();
        }
        else {
            addGroupBox.getChildren().remove(addGroupField);
            addGroupBox.getChildren().add(addGroupButton);
            
            String groupName = addGroupField.getText();
            if (groupName.length() <= 0) return;

            GroupItem group = createGroup(groupName, "none");
            appendGroup(group);
            FileHelper.UpdateGroupList(groupList);

            addGroupField.setText("");
        }
    }
    private GroupItem createGroup(String groupName, String style) {
        GroupItem newGroup = new GroupItem(groupName, style, this);
        return newGroup; 
    }

    private TaskItem createTask() {
        TaskItem taskItem = new TaskItem(
            contentField.getText(),
            calendarBox.getActiveDate(),
            this
            );
        taskItem.setGroup(chosenGroup);
        FileHelper.SaveTask(taskItem);
        
        return taskItem;
    }

    private void appendTask(TaskItem task) {
        taskList.add(task);
        taskBox.getChildren().add(task);
        mainGroup.addTaskID(task.getID());
    }

    private void appendGroup(GroupItem group) {
        groupList.add(group);
        groupBox.getChildren().add(groupBox.getChildren().size() - 1, group);
    }

    public void chooseItem(GroupItem currentGroup) {
        chosenGroup = currentGroup;
        
        mainGroup.setStyle("-fx-background-color: transparent;");
        completeGroup.setStyle("-fx-background-color: transparent;");
        for (GroupItem group : groupList)
            group.setStyle("-fx-background-color: transparent;");    

        currentGroup.setStyle("-fx-background-color: rgba(235,235,235, 0.7);");
        updateTaskList();
    }

    public void chooseItem(TaskItem item) {
        // TODO::Highlight task
    }

    public void deleteItem(TaskItem item){
        if (item.getCheckerField().isSelected()){
            taskBox.getChildren().remove(item);
            FileHelper.DeleteTask(item.getID());
        }
    }

    public void deleteItem(GroupItem item){
        groupBox.getChildren().remove(item);
        // FileHelper.DeleteTask(item.getID());
    }

    private void updateTaskList() {
        taskBox.getChildren().clear();
        for (TaskItem item : taskList)
            if (chosenGroup.getTaskList().contains(item.getID()))
                taskBox.getChildren().add(item);
    }
}
