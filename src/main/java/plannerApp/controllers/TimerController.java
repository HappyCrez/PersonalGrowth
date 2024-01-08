package plannerApp.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import plannerApp.javafxWidget.TaskItem;
import plannerApp.javafxWidget.timer.Timer;

public class TimerController implements Notification {
    private ScreenController controller;
    private Timer timer;
    private TaskItem chosenTask;

    @FXML
    private VBox leftListBox;
    
    @FXML
    private AnchorPane content;

    public TimerController(ScreenController controller) {
        this.controller = controller;
        timer = new Timer();

        chosenTask = null;
    }

    @FXML
    public void initialize() {
        content.getChildren().add(timer);
        AnchorPane.setRightAnchor(timer, 100.0);
        AnchorPane.setTopAnchor(timer, 50.0);
        AnchorPane.setBottomAnchor(timer, 100.0);

        Container.addObservable(this);
        Notify();
    }

    public void toSettingsClicked() {
        controller.activateScreen("settingsView", Animation.animationStyles.downToUp);
    }

    public void toToDoListClicked() {
        controller.activateScreen("toDoListView", Animation.animationStyles.instantShow);
    }
    
    @Override
    public void Notify() {
        leftListBox.getChildren().clear();
        for (TaskItem task : Container.getTaskList()) {
            TaskItem copy = new TaskItem(task);
            copy.setChecked();
            leftListBox.getChildren().add(copy);
        }
        
        if (Container.getChooseTask() == null) return;
        if (chosenTask != null) content.getChildren().remove(chosenTask);
        chosenTask = new TaskItem(Container.getChooseTask());
        chosenTask.setPrefWidth(200);
        chosenTask.setChecked();
        content.getChildren().add(chosenTask);
        AnchorPane.setTopAnchor(chosenTask, 50.0);
        AnchorPane.setLeftAnchor(chosenTask, 50.0);
    }
}
