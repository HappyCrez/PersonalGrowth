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
		screenController.activateScreen("toDoListView", ScreenController.animationStyles.leftToRight);
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
