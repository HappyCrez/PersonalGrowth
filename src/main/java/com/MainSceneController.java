package com;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainSceneController implements com.MainSceneInterface {
	
	@FXML
	private Button mainButton;
	
	@FXML
	private void buttonClicked() {
		System.out.println("hello world");
		mainButton.setText("click me again");
	}
	
	@Override
	public void showSmthng() {
		System.out.println("smthng");
		
	}
	
	public boolean pushTask() {
		return true;
	}
}
