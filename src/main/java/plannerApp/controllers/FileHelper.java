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

    public static void updateCompleteGroup(GroupItem completeGroup) {
         try(FileWriter writer = new FileWriter("./Resources/completeTasks.txt", false)){
            for (Long id : completeGroup.getTaskList())
                writer.write(id + "\n");
        } catch (IOException e) { }
    }

    public static void updateTaskList(ArrayList<TaskItem> taskList) {
        try(FileWriter writer = new FileWriter("./Resources/taskList.txt", false)){
            for (TaskItem task : taskList) {
                writer.write(task.getID() + "\n");
                writer.write(task.getContent() + "\n");
                writer.write("§" + task.getDateField() + "\n");
            }
        } catch (IOException e) { }
    }

    public static void updateGroupList(ArrayList<GroupItem> groupList) {
        try(FileWriter writer = new FileWriter("./Resources/groupList.txt", false)){
            for (GroupItem group : groupList) {
                String taskList = group.getTaskList().toString();
                writer.write(String.format("%s§%s§%s\n", group.getName(), group.getColor(), taskList));
            }
        } catch (IOException e) { }
    }

    public static ArrayList<Long> readCompleteGroup() {
        ArrayList<Long> completeTaskIdList = new ArrayList<Long>();
        for (String line : readFile("completeTasks.txt")) {
            completeTaskIdList.add(Long.parseLong(line));
            System.out.println(line);
        }
        return completeTaskIdList;
    }

    public static ArrayList<TaskItem> readTaskList() {
        ArrayList<TaskItem> list = new ArrayList<TaskItem>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
        String content = "", id = "";
        for (String line : readFile("taskList.txt")) {
            if (line.length() > 0 && line.charAt(0) == '§') {
                line = line.substring(1);
                content = content.substring(0, content.length() - 1);
                list.add(new TaskItem(
                    content,
                    LocalDate.parse(line, format),
                    Long.parseLong(id)
                    ));
                id = "";
                content = "";
            }
            else {
                if (id == "") id = line;
                else content += line + "\n";
            }
        }
        return list;
    }

    public static ArrayList<GroupItem> readGroupList() {
        ArrayList<GroupItem> list = new ArrayList<GroupItem>();
        for (String line : readFile("groupList.txt")) {
            String[] words = line.split("§");
            GroupItem group = new GroupItem(
                words[0],
                words[1],
                true
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

    private static ArrayList<String> readFile(String filename) {
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
		Parent settingsView = FileHelper.loadView(new SettingsController(controller), "settingsView");

		controller.addScreen("toDoListView", toDoListView);
        controller.addScreen("timerView", timerView);
        controller.addScreen("settingsView", settingsView);
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