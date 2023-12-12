package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
	private VBox taskList;
    @FXML
    private TextArea contentField;

    AppController(ScreenController controller) {
        this.controller = controller; 
    }
    
	@FXML
	private void initialize() {
		calendarBox = new CalendarBox();
        centerView.getChildren().add(calendarBox);

        // TODO::CLASS TASK FORM
        // Now it's Load from view
        for (TaskItem item : FileHelper.ReadFile())
            taskList.getChildren().add(item);
    }

    @FXML
    private void toTimerClicked() {
        System.out.println("to Timer Clicked");
    }
    
    @FXML
    private void toSettingsClicked() {
        System.out.println("to Settings Clicked");
    }

    @FXML
    void addTask() {
        TaskItem taskItem = new TaskItem(
            contentField.getText(),
            calendarBox.getActiveDate(),
            new TaskGroup("Tasks", null)  //TODO::CORRECT GROUP
            );
        FileHelper.WriteFile(taskItem);
        taskList.getChildren().add(taskItem);
        
        contentField.setText("");
    }
}
