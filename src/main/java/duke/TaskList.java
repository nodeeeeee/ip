package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.senpaiexception.SenpaiException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Manages the task list and related operations.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Loads tasks from saved strings.
     *
     * @param loadStr Array of saved strings.
     */
    public TaskList(String[] loadStr) {
        assert loadStr != null : "loadStr must not be null";
        tasks = new ArrayList<>();
        int idx = 1;
        for (int i = 0; i < loadStr.length; i++) {
            String[] taskComponents = loadStr[i].split(" \\| ", 4);
            try {
                if (taskComponents[0].equals("T")) {
                    addWithoutResponse(new TodoTask(taskComponents[2]));
                } else if (taskComponents[0].equals("D")) {
                    addWithoutResponse(new DeadlineTask(taskComponents[2] + " " + taskComponents[3]));
                } else if (taskComponents[0].equals("E")) {
                    addWithoutResponse(new EventTask(taskComponents[2] + " " + taskComponents[3]));
                } else {
                    throw new SenpaiException("loading error...");
                }
                if (taskComponents[1].equals("1")) {
                    mark(idx);
                }
                idx++;
            } catch (DateTimeParseException e) {
                System.out.println("Date time error");
                continue;
            } catch (SenpaiException e) {
                System.out.println(e.getResponse());
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
    public String addWithResponse(Task task) {
        addTask(task);
        //        ResponseBlock response = new ResponseBlock("Got it. I've added this task:\n"
        //                + task.getRep() + "\nNow you have " + getChatSize() + " tasks in the list.");
        //        response.print();
        return "Got it. I've added this task:\n"
                + task.getRep() + "\nNow you have " + getChatSize() + " tasks in the list.";
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
        assert task != null : "task must not be null";
        tasks.add(task);
    }

    /**
     * Deletes a task in 1-based manner.
     *
     * @param idx 1-based index of task to delete.
     */
    public String deleteTask(int idx) {
        assert idx >= 1 && idx <= tasks.size() : "index out of range";
        String taskRep = tasks.get(idx - 1).getRep();
        tasks.remove(idx - 1);
        //        ResponseBlock response = new ResponseBlock("Deleted task successfully:\n"
        //                + taskRep + "\nNow you have " + getChatSize() + " tasks in the list.");
        //        response.print();
        return "Deleted task successfully:\n"
                + taskRep + "\nNow you have " + getChatSize() + " tasks in the list.";
    }

    /**
     * Get the task string in 1-based index.
     *
     * @param index 1-based index of task to get.
     * @return Task to string.
     */
    public String getTask(int index) {
        assert index >= 1 && index <= tasks.size() : "index out of range";
        return tasks.get(index - 1).getRep();
    }

    /**
     * Mark a task as done.
     *
     * @param index 1-based index of task to mark.
     */
    public String mark(int index) {
        assert index >= 1 && index <= tasks.size() : "index out of range";
        tasks.get(index - 1).mark();
        return "marked";
    }

    /**
     * Mark a task as not done.
     *
     * @param index 1-based index of task to unmark.
     */
    public String unmark(int index) {
        assert index >= 1 && index <= tasks.size() : "index out of range";
        tasks.get(index - 1).unmark();
        return "unmarked";
    }

    /**
     * Returns how many tasks are in the list.
     *
     * @return Number of tasks.
     */
    public int getChatSize() {
        return tasks.size();
    }

    /**
     * Prints the list of tasks.
     */
    public String list() {
        //        ResponseBlock response = new ResponseBlock("Here are the tasks in your list:\n" + getAllTasks());
        //        response.print();
        return "Here are the tasks in your list:\n" + getAllTasks();
    }

    /**
     * Find tasks that match a keyword and print them.
     *
     * @param keyword Keyword to search for.
     */
    public String find(String ...keyword) {
        //        ResponseBlock response = new ResponseBlock("Here are the matching tasks in your list:\n"
        //                + getMatchingTasks(keyword));
        //        response.print();

        return "Here are the matching tasks in your list:\n"
                + getMatchingTasks(keyword);
    }

    /**
     * Gets the string of all tasks.
     *
     * @return String of tasks.
     */
    public String getAllTasks() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).getRep())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Get the string of matched tasks.
     *
     * @param keywords Keyword to search for.
     * @return String of matched tasks.
     */
    public String getMatchingTasks(String ...keywords) {
        if (keywords == null || keywords.length == 0) {
            return "";
        }
        return IntStream.range(0, tasks.size())
                .filter(i -> {
                    Task task = tasks.get(i);
                    for (String keyword : keywords) {
                        if (task.getTaskName().contains(keyword)) {
                            return true;
                        }
                    }
                    return false;
                })
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).getRep())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Formats tasks to save to file.
     *
     * @return formatted string.
     */
    public String formatSave() {
        return tasks.stream()
                .map(Task::getRep)
                .collect(Collectors.joining("\n"));
    }


}
