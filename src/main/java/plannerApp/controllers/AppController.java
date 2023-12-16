package plannerApp.controllers;

import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import plannerApp.javafxWidget.GroupItem;
import plannerApp.javafxWidget.TaskItem;
import plannerApp.javafxWidget.calendar.CalendarBox;

public class AppController {
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
    private ArrayList<TaskItem> taskList;
    private ArrayList<GroupItem> groupList;
    @FXML
    private TextArea contentField, addGroupField;

    ChoiceBox<GroupItem> groupSelector;

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
        GroupItem mainGroup = new GroupItem("Tasks", "none"); 
        
        // TODO::Class TaskForm
        // It should include the same GroupSelector
        groupSelector = new ChoiceBox<GroupItem>();
        groupSelector.setValue(mainGroup);
        groupSelector.getStyleClass().add("groupSelector");
        taskForm.getChildren().add(groupSelector);
        AnchorPane.setBottomAnchor(groupSelector, 0.0);
        
        appendGroup(mainGroup);
        for (GroupItem group : FileHelper.ReadGroupList()) {
            appendGroup(group);
        }

        for (TaskItem item : FileHelper.ReadTaskList()) {
            taskList.add(item);
            taskBox.getChildren().add(item);
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
        
        taskList.add(task);
        taskBox.getChildren().add(task);
        
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

            GroupItem group = createGroup(groupName);
            appendGroup(group);

            addGroupField.setText("");
        }
    }

    private GroupItem createGroup(String groupName) {
        GroupItem newGroup = new GroupItem(groupName, "none");
        FileHelper.SaveGroup(newGroup);
        return newGroup; 
    }

    private TaskItem createTask() {
        TaskItem taskItem = new TaskItem(
            contentField.getText(),
            calendarBox.getActiveDate(),
            groupSelector.getValue()
            );
        FileHelper.SaveTask(taskItem);
        
        return taskItem;
    }

    private void appendGroup(GroupItem group) {
        groupList.add(group);
        groupSelector.getItems().add(group);
        groupBox.getChildren().add(groupBox.getChildren().size() - 1, group);
    }
}
