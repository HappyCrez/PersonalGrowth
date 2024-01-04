package plannerApp.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import plannerApp.javafxWidget.GroupItem;
import plannerApp.javafxWidget.TaskItem;

import java.io.*;
import java.net.URL;

public class FileHelper {

    FileHelper() { }

    public static void SaveTask(TaskItem taskItem) {
        try(FileWriter writer = new FileWriter("./Resources/taskList.txt", true)){
            writer.write(taskItem.toString() + "\n");
        } catch (IOException e) { }
    }

    public static void UpdateTaskList(ArrayList<TaskItem> taskList) {
        try(FileWriter writer = new FileWriter("./Resources/taskList.txt", false)){
            for (TaskItem task : taskList) {
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) { }
    }

    public static void UpdateGroupList(ArrayList<GroupItem> groupList) {
        try(FileWriter writer = new FileWriter("./Resources/groupList.txt", false)){
            for (GroupItem group : groupList) {
                String taskList = group.getTaskList().toString();
                writer.write(String.format("%s§%s§%s\n", group.getName(), group.getColor(), taskList));
            }
        } catch (IOException e) { }
    }

    public static ArrayList<TaskItem> ReadTaskList() {
        ArrayList<TaskItem> list = new ArrayList<TaskItem>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
        for (String line : ReadFile("taskList.txt")) {
            String[] words = line.split("§");
            list.add(new TaskItem(
                words[0],
                LocalDate.parse(words[1], format),
                Long.parseLong(words[2])
            ));
        }
        return list;
    }

    public static ArrayList<GroupItem> ReadGroupList() {
        ArrayList<GroupItem> list = new ArrayList<GroupItem>();
        for (String line : ReadFile("groupList.txt")) {
            String[] words = line.split("§");
            GroupItem group = new GroupItem(
                words[0],
                words[1]
            ); 
            words[2] = words[2].substring(1, words[2].length() - 1);
            for (String ID : Arrays.asList(words[2].split(", "))) {
                try {
                    group.addTaskID(Long.parseLong(ID));
                } catch(NumberFormatException e) { }
            }
            list.add(group);
        }
        return list;
    }

    private static ArrayList<String> ReadFile(String filename) {
        ArrayList<String> list = new ArrayList<String>();
        try (FileReader reader = new FileReader("./Resources/" + filename)) {
            Scanner scan = new Scanner(reader);
            while(scan.hasNextLine()) {
                list.add(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) { e.getStackTrace(); }
          catch (IOException e) { e.getStackTrace(); }
        return list;
    }

    public static void loadAllViews(ScreenController controller) {
		Parent toDoListView = FileHelper.loadView(new ToDoListController(controller), "toDoListView");
		Parent timerView = FileHelper.loadView(new TimerController(controller), "timerView");
		
		controller.addScreen("toDoListView", toDoListView);
        controller.addScreen("timerView", timerView);
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