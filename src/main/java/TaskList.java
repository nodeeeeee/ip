import ResponseBlock.ResponseBlock;
import SenpaiException.SenpaiException;
import Task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.io.FileWriter;
import Task.*;


public class TaskList {
    public TaskList(String[] load_str) {
        tasks = new ArrayList<>();
        int idx = 1;
        for (int i = 0; i < load_str.length; i++) {
            String[] taskComponents = load_str[i].split(" \\| ", 4);
            try {
                if (taskComponents[0].equals("T")) {
                    addWithoutResponse(new TodoTask(taskComponents[2]));
                } else if (taskComponents[0].equals("D")) {
                    //                    System.out.println(taskComponents[0]);
                    //                    System.out.println(taskComponents[1]);
                    //                    System.out.println(taskComponents[2]);
                    //                    System.out.println(taskComponents[3]);
//                    System.out.println(taskComponents[2] + " " + taskComponents[3]);
                    addWithoutResponse(new DeadlineTask(taskComponents[2] + " " + taskComponents[3]));
                } else if (taskComponents[0].equals("E")) {
                    addWithoutResponse(new EventTask(taskComponents[2] + " " + taskComponents[3]));
                }
                if (taskComponents[1].equals("1")) {
                    mark(idx);
                }
                idx++;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }


    public void saveList() {
        try {
            writeToFile("savedTasks.txt", formatSave());
        } catch (IOException e) {
            System.out.println("cannot find file.");
        }
    }






    public void addWithResponse(Task task) {
        addTask(task);
        ResponseBlock response = new ResponseBlock("Got it. I've added this task:\n" + task.getRep() + "\nNow you have " + getChatSize() + " tasks in the list.");
        response.Print();
    }

    public void addWithoutResponse(Task task) {
        addTask(task);
    }

    //Reused from https://nus-cs2103-ay2526-s2.github.io/website/schedule/week3/topics.html
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int idx) {
        tasks.remove(idx - 1);
    }

    public String getTask(int index) {
        return tasks.get(index - 1).getRep();
    }

    public void mark(int index) {
        tasks.get(index - 1).mark();
    }

    public void unmark(int index) {
        tasks.get(index - 1).unmark();
    }

    public int getChatSize() {
        return tasks.size();
    }

    public void list() {
        ResponseBlock response = new ResponseBlock("Here are the tasks in your list:\n" + getAllTasks());
        response.Print();
    }

    public String getAllTasks() {
        StringBuilder ret = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            ret.append(i).append(". ").append(tasks.get(i - 1).getRep());
            if (i != tasks.size()){
                ret.append("\n");
            }
        }
        return ret.toString();
    }

    public String formatSave() {
        StringBuilder ret = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            ret.append(tasks.get(i - 1).getRep());
            if (i != tasks.size()){
                ret.append("\n");
            }
        }
        return ret.toString();
    }


    private static ArrayList<Task> tasks ;
}
