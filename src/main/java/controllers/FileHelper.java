package controllers;

import logickModule.TaskGroup;
import logickModule.TaskItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FileHelper {

    public static void WriteFile(TaskItem taskItem) {
        try(FileWriter writer = new FileWriter("./Resources/tasklist.txt", true)){
            writer.write(taskItem.toString() + "\n");
        } catch (IOException e) { e.getStackTrace(); }
    }

    public static ArrayList<TaskItem> ReadFile() {
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
}