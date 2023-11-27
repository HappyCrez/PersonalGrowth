package com;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ToDoListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dateField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button completedButton, addButton, todayButton, tomorrowButton, weekButton;

    private ScreenController screenController;

    @FXML
    private TableView<TaskItem> taskTableView;

    @FXML
    private TableColumn<TaskItem, String> titleTableColumn, descriptionTableColumn, dateTableColumn;

    @FXML
    private TableColumn<TaskItem, CheckBox> checkBoxColumn;

    @FXML
    private TextField titleTextField, descriptionTextField;

    public ToDoListController(ScreenController screenController) {
        this.screenController = screenController;
    }

    @FXML
    void addButtonClicked(ActionEvent event) {
        TaskItem taskItem = new TaskItem(titleTextField.getText(), descriptionTextField.getText(), dateField.getValue());
        taskTableView.getItems().add(taskItem);
    }

    @FXML
    void completedButtonClicked(ActionEvent event) {
        statusLabel.setText("Выполненые задачи");
    }

    @FXML
    void todayButtonClicked(ActionEvent event) {
        LocalDate nowDate = LocalDate.now();
        ObservableList<TaskItem> list = taskTableView.getItems();
        ObservableList<TaskItem> todayList = FXCollections.observableArrayList();
        for (TaskItem e :  list) {
            if (e.getDateTask().toString().equals(nowDate.toString())) {
                todayList.add(e);
            }
        }
        taskTableView.getItems().clear();
        taskTableView.getItems().addAll(todayList);
    }

    @FXML
    void tomorrowButtonClicked(ActionEvent event) {
        LocalDate nowDate = LocalDate.now().plusDays(1);
        ObservableList<TaskItem> list = taskTableView.getItems();
        ObservableList<TaskItem> todayList = FXCollections.observableArrayList();
        for (TaskItem e :  list) {
            if (e.getDateTask().toString().equals(nowDate.toString())) {
                todayList.add(e);
            }
        }
        taskTableView.getItems().clear();
        taskTableView.getItems().addAll(todayList);
    }

    @FXML
    void weekButtonClicked(ActionEvent event) {
        LocalDate nowDate = LocalDate.now().plusDays(7);
        ObservableList<TaskItem> list = taskTableView.getItems();
        ObservableList<TaskItem> todayList = FXCollections.observableArrayList();
        for (TaskItem e :  list) {
            if (e.getDateTask().isBefore(nowDate)) {
                todayList.add(e);
            }
        }
        taskTableView.getItems().clear();
        taskTableView.getItems().addAll(todayList);
    }

    @FXML
    void toHabbitsClicked(ActionEvent event) {
        statusLabel.setText("Вы перешли к трекеру привычек");
        screenController.activateScreen("habbitsView", ScreenController.animationStyles.rightToLeft);
    }

    @FXML
    void toMenuClicked(ActionEvent event) {
        statusLabel.setText("Вы перешли в меню");
    }

    @FXML
    void toTimerClicked(ActionEvent event) {
        statusLabel.setText("Вы перешли к таймеру фокусировки");
    }
}
