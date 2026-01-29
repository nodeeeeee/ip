package duke;

import duke.ResponseBlock.ResponseBlock;
import duke.Task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.io.FileWriter;


public class TaskList {
    /**
     * Loads tasks from saved strings.
     *
     * @param load_str Array of saved strings.
     */
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

    /**
     * Saves the current list to file.
     */
    public void saveList() {
        try {
            writeToFile("savedTasks.txt", formatSave());
        } catch (IOException e) {
            System.out.println("cannot find file.");
        }
    }






    /**
     * Adds a task and prints the response.
     *
     * @param task Task to add.
     */
    public void addWithResponse(Task task) {
        addTask(task);
        ResponseBlock response = new ResponseBlock("Got it. I've added this task:\n" + task.getRep() + "\nNow you have " + getChatSize() + " tasks in the list.");
        response.Print();
    }

    /**
     * Adds a task without printing the response.
     *
     * @param task Task to add.
     */
    public void addWithoutResponse(Task task) {
        addTask(task);
    }

    //Reused from https://nus-cs2103-ay2526-s2.github.io/website/schedule/week3/topics.html
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in 1-based manner.
     *
     * @param idx 1-based index of task to delete.
     */
    public void deleteTask(int idx) {
        String task_rep = tasks.get(idx - 1).getRep();
        tasks.remove(idx - 1);
        ResponseBlock response = new ResponseBlock("Deleted task successfully:\n" + task_rep + "\nNow you have " + getChatSize() +  "tasks in the list.");
        response.Print();
    }

    /**
     * Get the task string in 1-based index.
     *
     * @param index 1-based index of task to get.
     * @return Task to string.
     */
    public String getTask(int index) {
        return tasks.get(index - 1).getRep();
    }

    /**
     * Mark a task as done.
     *
     * @param index 1-based index of task to mark.
     */
    public void mark(int index) {
        tasks.get(index - 1).mark();
    }

    /**
     * Mark a task as not done.
     *
     * @param index 1-based index of task to unmark.
     */
    public void unmark(int index) {
        tasks.get(index - 1).unmark();
    }

    /**
     * Return how many tasks are in the list.
     *
     * @return Number of tasks.
     */
    public int getChatSize() {
        return tasks.size();
    }

    /**
     * Print the list of tasks.
     */
    public void list() {
        ResponseBlock response = new ResponseBlock("Here are the tasks in your list:\n" + getAllTasks());
        response.Print();
    }

    /**
     * Get the string of all tasks.
     *
     * @return String of tasks.
     */
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

    /**
     * Format tasks to save to file.
     *
     * @return formatted string.
     */
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
