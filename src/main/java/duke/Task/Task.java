package duke.Task;
/**
 * Base class for all tasks.
 */
public abstract class Task {

    /**
     * Create a task with a name.
     *
     * @param str Task name.
     */
    public Task(String str) {
        taskName = str;
        isDone = false;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Decide whether the task is done.
     *
     * @return Status string.
     */
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    public String getTaskName() {
        return taskName;
    }

    public abstract String getRep();



    final private String taskName;
    private boolean isDone;
}
