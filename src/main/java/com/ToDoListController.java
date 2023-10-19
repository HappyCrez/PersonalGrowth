package com;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ToDoListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DatePicker;

    @FXML
    private Label statusLabel;

    @FXML
    private Button completedButton, addButton, todayButton, tomorrowButton, weekButton;
    
    private ScreenController screenController;

    public ToDoListController(ScreenController screenController) {
    	this.screenController = screenController;
    }
    
    @FXML
    private ListView<?> taskList;

    @FXML
    void addButtonClicked(ActionEvent event) {
        statusLabel.setText("Задача добавлена!");
    }

    @FXML
    void completedButtonClicked(ActionEvent event) {
        statusLabel.setText("Выполненые задачи");
    }

    @FXML
    void todayButtonClicked(ActionEvent event) {
        statusLabel.setText("Задачи на сегодня");
    }

    @FXML
    void tomorrowButtonClicked(ActionEvent event) {
        statusLabel.setText("Задачи на завтра!");
    }

    @FXML
    void weekButtonClicked(ActionEvent event) {
        statusLabel.setText("Задачи на неделю");
    }

    @FXML
    void toHabbitsClicked(ActionEvent event) {
        statusLabel.setText("Вы перешли к трекеру привычек");
    }

    @FXML
    void toMenuClicked(ActionEvent event) {
        statusLabel.setText("Вы перешли в меню");
        screenController.activateScreen("MainView");
    }

    @FXML
    void toTimerClicked(ActionEvent event) {
        statusLabel.setText("Вы перешли к таймеру фокусировки");
    }

    @FXML
    void initialize() {

    }

}
