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
	
	private int width = 800, height = 600;
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage Window) throws Exception {	
		Scene mainScene = new Scene(new Pane());
		ScreenController screenController = new ScreenController(mainScene);
		
		setupWindow(Window, mainScene);

		setLoadView(mainScene, screenController);
		
		loadAllViews(screenController);
		//TODO::Notify that app was loaded
	}
	
	private void setupWindow(Stage Window, Scene mainScene) {
		Window.setWidth(width);
		Window.setHeight(height);
		Window.setTitle("Personal Growth");
		Window.setScene(mainScene);
		Window.show();
	}

	private void setLoadView(Scene mainScene, ScreenController controller) {
		Parent loadView = loadView(new LoadController(controller), "loadView");
		controller.addScreen("loadView", loadView);
		controller.activateScreen("loadView", ScreenController.animationStyles.instantShow);
	}
	
	private void loadAllViews(ScreenController controller) {
		Parent habitsView = loadView(new HabitsController(controller), "habitsView");
		Parent toDoListView = loadView(new ToDoListController(controller), "toDoListView");
		
		controller.addScreen("toDoListView", toDoListView);
		controller.addScreen("habbitsView", habitsView);
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
