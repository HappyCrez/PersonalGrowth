package com;

import java.io.IOException;
import java.net.URL;

import HabitsModule.HabitsController;
import ToDoListModule.ToDoListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class LoadHelper {

    private LoadHelper() { }
    
	public static void loadAllViews(ScreenController controller) {
		Parent habitsView = LoadHelper.loadView(new HabitsController(controller), "habitsView");
		Parent toDoListView = LoadHelper.loadView(new ToDoListController(controller), "toDoListView");
		
		controller.addScreen("toDoListView", toDoListView);
		controller.addScreen("habbitsView", habitsView);
	}

    public static Parent loadView(Object controller, String screenName) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL xmlurl = LoadHelper.class.getResource("/views/" + screenName + ".fxml");
		fxmlLoader.setLocation(xmlurl);
		fxmlLoader.setController(controller);
		Parent root = new Pane();
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(screenName + " onload error");
		}
		return root;
	}
}
