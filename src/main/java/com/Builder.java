package com;

import java.io.IOException;
import java.net.URL;

import HabitsModule.HabitsController;
import ToDoListModule.ToDoListController;
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
		int width = 600, height = 400;
		Scene scene = new Scene(new Pane(), width, height);
		ScreenController screenController = new ScreenController(scene);
		
		toDoListStage.setTitle("Personal Growth");
		toDoListStage.setScene(scene);
		toDoListStage.show();
		
		Parent habitsView = loadView(new HabitsController(screenController), "habitsView");
		Parent toDoListView = loadView(new ToDoListController(screenController), "toDoListView");
		Parent loadView = loadView(new LoadController(screenController), "loadView");
		
		screenController.addScreen("toDoListView", toDoListView);
		screenController.addScreen("habbitsView", habitsView);
		screenController.addScreen("loadView", loadView);
		screenController.activateScreen("loadView", ScreenController.animationStyles.instantShow);
	}
	
	private Parent loadView(Object controller, String screenName) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL xmlurl = getClass().getResource("/views/" + screenName + ".fxml");
		fxmlLoader.setLocation(xmlurl);
		fxmlLoader.setController(controller);
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
