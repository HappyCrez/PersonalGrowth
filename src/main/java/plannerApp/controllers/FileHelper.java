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
        } catch (IOException e) { e.getStackTrace(); }
    }

    public static ArrayList<TaskItem> ReadTaskList(TaskAction action) {
        ArrayList<TaskItem> list = new ArrayList<TaskItem>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
        for (String line : ReadFile("taskList.txt")) {
            String[] words = line.split("§");
            list.add(new TaskItem(
                words[0],
                LocalDate.parse(words[1], format),
                action,
                Long.parseLong(words[3])
            ));
        }
        return list;
    }

    public static void DeleteTask(long ID){
        File file = new File("./Resources/taskList.txt");
        File newFile = new File("./Resources/newtaskList.txt");
        for(String line : ReadFile("taskList.txt")){
            String[] words = line.split("§");
            if(ID != Long.parseLong(words[3])){
                try(FileWriter writer = new FileWriter("./Resources/newtaskList.txt", true)){
                    writer.write(line + "\n");
                } catch (IOException e) { e.getStackTrace(); }
            }
        }
        file.delete();
        newFile.renameTo(file);
    }

    public static void UpdateGroupList(ArrayList<GroupItem> groupList) {
        try(FileWriter writer = new FileWriter("./Resources/groupList.txt", false)){
            for (GroupItem item : groupList) {
                String taskList = item.getTaskList().toString();
                writer.write(String.format("%s§%s§%s\n", item.getName(), item.getColor(), taskList));
            }
        } catch (IOException e) { e.getStackTrace(); }
    }

    public static ArrayList<GroupItem> ReadGroupList(GroupAction action) {
        ArrayList<GroupItem> list = new ArrayList<GroupItem>();
        for (String line : ReadFile("groupList.txt")) {
            String[] words = line.split("§");
            GroupItem group = new GroupItem(
                words[0],
                words[1],
                action
            ); 
            words[2] = words[2].substring(1, words[2].length() - 1);
            for (String ID : Arrays.asList(words[2].split(", "))) {
                System.out.println(ID);
                try {
                    group.addTaskID(Long.parseLong(ID));
                } catch(NumberFormatException e) {
                    e.getStackTrace();
                }
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