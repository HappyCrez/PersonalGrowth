package com;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class LoadController {
	
	ScreenController screenController;
	
	public LoadController(ScreenController screenController) {
		this.screenController = screenController; 
	}
	
	@FXML
	private void mouseClicked(){
		screenController.activateScreen("toDoListView");
	}
}
