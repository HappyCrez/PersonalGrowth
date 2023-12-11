package controllers;

import javafx.collections.ObservableList;
import logickModule.TaskGroup;
import logickModule.TaskItem;
import org.w3c.dom.Node;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileHelper {

    private static ObservableList<Node> list;

    public static void WriteFile(TaskItem taskItem) {
        try(FileWriter writer = new FileWriter("tasklist.txt", true)){
            writer.write(taskItem.toString() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<TaskItem> ReadFile() {
        ArrayList<TaskItem> list = new ArrayList<TaskItem>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
        try
        {
            FileReader reader = new FileReader("tasklist.txt");
            BufferedReader buf = new BufferedReader(reader);

            String line = null;
            do
            {
                try {
                    line = buf.readLine();
                    String[] words = line.split("§");
                    list.add(new TaskItem(
                            words[0],
                            LocalDate.parse(words[2], format),
                            new TaskGroup(words[1], null)
                    ));

                } catch (Exception e) { }

            } while(line != null);

        } catch (FileNotFoundException e) { }

        return list;
    }
}