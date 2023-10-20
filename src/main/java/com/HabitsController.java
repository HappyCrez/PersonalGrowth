package com;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HabitsController {
	
	private ScreenController screenController;
	
	@FXML
	private Button toToDoList, toTimer, toSettings;
	
	public HabitsController (ScreenController screenController) {
		this.screenController = screenController;
	}
	
	@FXML
	private void toToDoListClicked() {
		System.out.println("to ToDoListBtn clicked");
		screenController.activateScreen("toDoListView");
	}
	
	@FXML
	private void toTimerClicked() {
		System.out.println("to ToTimerBtn clicked");
	}
	
	@FXML
	private void toSettingsClicked() {
		System.out.println("to ToSettingsBtn clicked");
	}
}
