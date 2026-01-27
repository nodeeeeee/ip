import Task.Task;

import java.util.ArrayList;

public class Chat {
    public Chat() {
        tasks = new ArrayList<>();
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
