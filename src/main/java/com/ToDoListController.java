package com;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    @FXML
    private TableView<TaskList> taskTableView;

    @FXML
    private TableColumn<TaskList, String> titleTableColumn, descriptionTableColumn, dateTableColumn;
    
    @FXML
    private TableColumn<TaskList, CheckBox> checkBoxColumn;

    @FXML
    private TextField titleTextField, descriptionTextField, dateTextField;

    public ToDoListController(ScreenController screenController) {
    	this.screenController = screenController;
    }

    @FXML
    void addButtonClicked(ActionEvent event) {
        TaskList taskList = new TaskList(titleTextField.getText(), descriptionTextField.getText(), dateTextField.getText());
        taskTableView.getItems().add(taskList);
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
