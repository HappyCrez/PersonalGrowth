package com;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Builder extends Application{
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage toDoListStage) throws Exception {		
		Scene scene = new Scene(new Pane(), 600, 400);
		ScreenController screenController = new ScreenController(scene);
		
		toDoListStage.setTitle("Personal Growth");
		toDoListStage.setScene(scene);
		toDoListStage.show();
		
		Parent mainView = loadView(new Object(), "mainView");
		Parent toDoListView = loadView(new ToDoListController(screenController), "toDoListView");

		screenController.addScreen("ToDoListView", toDoListView);
		screenController.addScreen("MainView", mainView);
		screenController.activateScreen("ToDoListView");
	}
	
	private Parent loadView(Object controller, String screenName) {
		FXMLLoader fxmlLoader = new FXMLLoader(Builder.class.getResource("/" + screenName + ".fxml"));
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(screenName + " onload error");
		}
		return root;
	}
}
