package plannerApp.controllers;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import plannerApp.javafxWidget.TaskGroup;
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
	private VBox taskList, groupList;
    @FXML
    private TextArea contentField, addGroupField;

    ChoiceBox<String> groupSelector;

    AppController(ScreenController controller) {
        this.controller = controller; 
    }
    
	@FXML
	private void initialize() {
		calendarBox = new CalendarBox();
        centerView.getChildren().add(calendarBox);

        addGroupField = new TextArea();
        Pattern pattern = Pattern.compile(".{0,15}");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        addGroupField.setTextFormatter(formatter);
        addGroupField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            {   if (!newPropertyValue)
                { addGroup(); }
            }
        });

        // TODO::CLASS TASK FORM
        // Now it's Load from view

        //TODO::CLASS GROUP SELECTOR
         ObservableList<String> groups = FXCollections.observableArrayList("Tasks", "Group#1", "Group#2");
        groupSelector = new ChoiceBox<String>(groups);
        groupSelector.setValue("Tasks");
        groupSelector.getStyleClass().add("groupSelector");
        taskForm.getChildren().add(groupSelector);
        AnchorPane.setBottomAnchor(groupSelector, 0.0);

        for (TaskItem item : FileHelper.ReadTaskList())
            taskList.getChildren().add(item);
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
        TaskItem taskItem = new TaskItem(
            contentField.getText(),
            calendarBox.getActiveDate(),
            new TaskGroup(groupSelector.getValue(), null)  //TODO::CORRECT GROUP
            );
        FileHelper.SaveTask(taskItem);
        taskList.getChildren().add(taskItem);
        
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

            TaskGroup group = new TaskGroup(groupName, "none");
            groupList.getChildren().add(groupList.getChildren().size() - 1, group);
            addGroupField.setText("");
        }
    }
}
