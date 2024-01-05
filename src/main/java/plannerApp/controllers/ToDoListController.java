package plannerApp.controllers;

import java.time.LocalDate;
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

public class ToDoListController implements Notification {
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
    
    @FXML
    private TextArea contentField, addGroupField;

    ToDoListController(ScreenController controller) {
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

        Container.addObservable(this);
        Notify();
        
        // TODO::Fill complete group from file
        // TODO::Class TaskForm
    }

    @FXML
    private void toTimerClicked() {
        //TODO::Timer view
        controller.activateScreen("timerView", Animation.animationStyles.instantShow);
    }
    
    @FXML
    private void toSettingsClicked() {
        //TODO::Settings
        System.out.println("to Settings Clicked");
    }

    @FXML
    void addTask() {
        TaskItem task = createTask();
        Container.addTask(task);
        
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
            Container.addGroup(group);

            addGroupField.setText("");
        }
    }
    private GroupItem createGroup(String groupName, String style) {
        GroupItem newGroup = new GroupItem(groupName, style, true);
        return newGroup; 
    }

    private TaskItem createTask() {
        TaskItem task = new TaskItem(
            contentField.getText(),
            calendarBox.getActiveDate()
            );
        return task;
    }

    @Override
    public void Notify() {
        groupBox.getChildren().clear();
        groupBox.getChildren().add(Container.getMainGroup());
        groupBox.getChildren().add(Container.getCompleteGroup());
        for (GroupItem group : Container.getGroupList())
            groupBox.getChildren().add(group);
        
        groupBox.getChildren().add(addGroupBox);

        taskBox.getChildren().clear();
        ArrayList<Long> idList = Container.getChooseGroup().getTaskList();
        Boolean isCompleteTasks = false;
        if (Container.getChooseGroup() == Container.getCompleteGroup())
            isCompleteTasks = true;
        for (TaskItem task : Container.getTaskList()) {
            if (idList.contains(task.getID())) {
                TaskItem copy = new TaskItem(task);
                if (isCompleteTasks)
                    copy.setChecked();
                taskBox.getChildren().add(copy);
            }
        }
    }
}
