package com;

import javafx.fxml.FXML;

public class LoadController {
	
	ScreenController screenController;
	
	public LoadController(ScreenController screenController) {
		this.screenController = screenController; 
	}
	
	@FXML
	private void mouseClicked(){
		screenController.activateScreen("toDoListView", ScreenController.animationStyles.downToUp);
	}
}
