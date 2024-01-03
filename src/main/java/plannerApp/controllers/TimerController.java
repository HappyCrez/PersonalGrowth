package plannerApp.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import plannerApp.javafxWidget.TaskItem;
import plannerApp.javafxWidget.timer.Timer;

public class TimerController {
    private ScreenController controller;
    private Timer timer;

    @FXML
    private VBox leftListBox;
    
    @FXML
    private AnchorPane content;

    public TimerController(ScreenController controller) {
        this.controller = controller;
        timer = new Timer();
    }

    @FXML
    public void initialize() {
        content.getChildren().add(timer);
        AnchorPane.setRightAnchor(timer, 100.0);
        AnchorPane.setBottomAnchor(timer, 100.0);

        updateTaskList();
    }

    public void toSettingsClicked() {
        // controller.activateScreen("settingsView", null);
        System.out.println("to Settings");
    }

    public void toToDoListClicked() {
        controller.activateScreen("toDoListView", Animation.animationStyles.instantShow);
    }
    
    private void updateTaskList() {
        for (TaskItem task : FileHelper.ReadTaskList(null))
            leftListBox.getChildren().add(task);
    }
}
