package plannerApp.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import plannerApp.javafxWidget.TaskGroup;
import plannerApp.javafxWidget.TaskItem;

import java.io.*;
import java.net.URL;

public class FileHelper {

    FileHelper() { }

    public static void SaveTask(TaskItem taskItem) {
        try(FileWriter writer = new FileWriter("./Resources/tasklist.txt", true)){
            writer.write(taskItem.toString() + "\n");
        } catch (IOException e) { e.getStackTrace(); }
    }

    public static ArrayList<TaskItem> ReadTaskList() {
        ArrayList<TaskItem> list = new ArrayList<TaskItem>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
        try (FileReader reader = new FileReader("./Resources/tasklist.txt")) {
            Scanner scan = new Scanner(reader);
            while(scan.hasNextLine()) {
                String[] words = scan.nextLine().split("§");
                list.add(new TaskItem(
                    words[0],
                    LocalDate.parse(words[2], format),
                    new TaskGroup(words[1], null)
                ));
            }
            scan.close();
        } catch (FileNotFoundException e) { e.getStackTrace(); }
          catch (IOException e) { e.getStackTrace(); }
        return list;
    }

    public static void loadAllViews(ScreenController controller) {
		Parent appView = FileHelper.loadView(new AppController(controller), "appView");
		
		controller.addScreen("appView", appView);
	}

    public static Parent loadView(Object controller, String screenName) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL xmlurl = FileHelper.class.getResource("/views/" + screenName + ".fxml");
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