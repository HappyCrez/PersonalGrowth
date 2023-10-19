package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage toDoListStage) throws Exception {		
		Scene scene = new Scene(new Pane(), 600, 400);
		ScreenController screenController = new ScreenController(scene);

		FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("/toDoListView.fxml"));
		ToDoListController toDoContrl = new ToDoListController(screenController);
		fxmlLoader1.setController(toDoContrl);
		Parent toDoListRoot = fxmlLoader1.load();
		
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/mainView.fxml"));
		Parent MainRoot = fxmlLoader.load();

		screenController.addScreen("ToDoListView", toDoListRoot);
		screenController.addScreen("MainView", MainRoot);
		screenController.activateScreen("ToDoListView");
		
		toDoListStage.setTitle("Personal Growth");
		toDoListStage.setScene(scene);
		toDoListStage.show();
	}
}
