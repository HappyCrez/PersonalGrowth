package plannerApp.controllers;

public class TimerController {
    ScreenController controller;

    public TimerController(ScreenController controller) {
        this.controller = controller;
    }

    public void toSettingsClicked() {
        // controller.activateScreen("settingsView", null);
        System.out.println("to Settings");
    }

    public void toToDoListClicked() {
        controller.activateScreen("toDoListView", Animation.animationStyles.instantShow);
    }
}
