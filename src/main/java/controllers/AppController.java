package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logickModule.CalendarBox;
import logickModule.TaskGroup;
import logickModule.TaskItem;
import plannerApp.ScreenController;

public class AppController {
    ScreenController controller;
    CalendarBox calendarBox;

    @FXML
    private HBox centerView;
	@FXML
	private Button toTimer, toSettings, addButton;
    @FXML
    private AnchorPane taskForm;
	@FXML
	private VBox taskList;
    @FXML
    private TextArea contentField;

    ChoiceBox<String> groupSelector;

    AppController(ScreenController controller) {
        this.controller = controller; 
    }
    
	@FXML
	private void initialize() {
		calendarBox = new CalendarBox();
        centerView.getChildren().add(calendarBox);

        // TODO::CLASS TASK FORM
        // Now it's Load from view

        //TODO::CLASS GROUP SELECTOR
         ObservableList<String> groups = FXCollections.observableArrayList("Tasks", "Group#1", "Group#2");
        groupSelector = new ChoiceBox<String>(groups);
        groupSelector.setValue("Tasks");
        groupSelector.getStyleClass().add("groupSelector");
        taskForm.getChildren().add(groupSelector);
        AnchorPane.setBottomAnchor(groupSelector, 0.0);

        for (TaskItem item : FileHelper.ReadFile())
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
        FileHelper.WriteFile(taskItem);
        taskList.getChildren().add(taskItem);
        
        contentField.setText("");
    }
}
