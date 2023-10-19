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
    private Button addButton;

    @FXML
    private Label addLabel;

    @FXML
    private Button completedButton;

    @FXML
    private ListView<?> taskList;

    @FXML
    private Button todayButton;

    @FXML
    private Button tomorrowButton;

    @FXML
    private Button weekButton;

    @FXML
    void addButtonClicked(ActionEvent event) {
        addLabel.setText("Задача добавлена!");
    }

    @FXML
    void completedButtonClicked(ActionEvent event) {
        addLabel.setText("Выполненые задачи");
    }

    @FXML
    void todayButtonClicked(ActionEvent event) {
        addLabel.setText("Задачи на сегодня");
    }

    @FXML
    void tomorrowButtonClicked(ActionEvent event) {
        addLabel.setText("Задачи на завтра!");
    }

    @FXML
    void weekButtonClicked(ActionEvent event) {
        addLabel.setText("Задачи на неделю");
    }

    @FXML
    void toHabbitsClicked(ActionEvent event) {
        addLabel.setText("Вы перешли к трекеру привычек");
    }

    @FXML
    void toMenuClicked(ActionEvent event) {
        addLabel.setText("Вы перешли в меню");
    }

    @FXML
    void toTimerClicked(ActionEvent event) {
        addLabel.setText("Вы перешли к таймеру фокусировки");
    }

    @FXML
    void initialize() {

    }

}
