package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage toDoListStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/toDoListView.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 400);
		toDoListStage.setTitle("ToDoList");
		toDoListStage.setScene(scene);
		toDoListStage.show();
	}
}
