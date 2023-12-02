package ToDoListModule;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.ScreenController;

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

    private ObservableList<TaskItem> list;

    public ToDoListController(ScreenController screenController) {
        this.screenController = screenController;
        list = FXCollections.observableArrayList();
    }

    @FXML
    void addButtonClicked(ActionEvent event) {
        TaskItem taskItem = new TaskItem(titleTextField.getText(), descriptionTextField.getText(), dateField.getValue());
        list.add(taskItem);
        taskTableView.getItems().clear();
        taskTableView.getItems().addAll(list);
    }

    @FXML
    void completedButtonClicked(ActionEvent event) {
        statusLabel.setText("Выполненые задачи");
    }

    @FXML
    void todayButtonClicked(ActionEvent event) {
        LocalDate nowDate = LocalDate.now();
        taskTableView.getItems().clear();
        for (TaskItem e :  list) {
            if (e.getDateTask().toString().equals(nowDate.toString())) {
                taskTableView.getItems().add(e);
            }
        }
    }

    @FXML
    void tomorrowButtonClicked(ActionEvent event) {
        LocalDate nowDate = LocalDate.now().plusDays(1);
        taskTableView.getItems().clear();
        for (TaskItem e :  list) {
            if (e.getDateTask().toString().equals(nowDate.toString())) {
                taskTableView.getItems().add(e);
            }
        }
    }

    @FXML
    void weekButtonClicked(ActionEvent event) {
        LocalDate nowDate = LocalDate.now().plusDays(7);
        taskTableView.getItems().clear();
        for (TaskItem e :  list) {
            if (e.getDateTask().isBefore(nowDate)) {
                taskTableView.getItems().add(e);
            }
        }
    }

    @FXML
    void toHabbitsClicked(ActionEvent event) {
        statusLabel.setText("Вы перешли к трекеру привычек");
        screenController.activateScreen("habbitsView", ScreenController.animationStyles.downToUp);
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
