package controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logickModule.CalendarBox;
import logickModule.TaskItem;
import plannerApp.ScreenController;

public class AppController {
    ScreenController controller;
    CalendarBox calendarBox;
    private ObservableList<Node> list;
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

        list = FXCollections.observableArrayList();
        for (Node e : taskList.getChildren())
            list.add(e);
        for (int i = 0; i < 2; i++)
            list.add(new TaskItem("Content", LocalDate.now()));
		taskList.getChildren().clear();
        taskList.getChildren().addAll(list);
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
            calendarBox.getActiveDate());
        list.add(taskItem);
        taskList.getChildren().clear();
        taskList.getChildren().addAll(list);
    }
}
