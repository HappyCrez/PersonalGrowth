package com;

import java.time.LocalDate;

import HabitsModule.HabitsModule;
import ToDoListModule.TaskItem;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AppController {
    ScreenController controller;
    HabitsModule habitsModule;
    private ObservableList<Node> list;
    
	@FXML
	private Button toTimer, toSettings, addButton;
	@FXML
	private Label calendarLabel;
	@FXML
	private GridPane calendarGridPane;
	@FXML
	private VBox taskList;
    @FXML
    private TextArea contentField;

    AppController(ScreenController controller) {
        this.controller = controller; 
    }
    
	@FXML
	private void initialize() {
		habitsModule = new HabitsModule(calendarGridPane, calendarLabel);

        list = FXCollections.observableArrayList();
        for (Node e : taskList.getChildren())
            list.add(e);
        for (int i = 0; i < 2; i++)
            list.add(new TaskItem("Content", LocalDate.now().toString()));
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
            LocalDate.now().toString()); // TODO::CORRECT THE DATE
        list.add(taskItem);
        taskList.getChildren().clear();
        taskList.getChildren().addAll(list);
    }

    @FXML
    private void nextMounth() {
		habitsModule.setNextMonth();
    }

    @FXML
    private void prevMounth() {
		habitsModule.setPrevMonth();
    }
}
