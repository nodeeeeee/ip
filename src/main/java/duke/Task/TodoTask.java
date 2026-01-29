package duke.Task;

public class TodoTask extends duke.Task.Task {
    /**
     * Initialization of TodoTask.
     *
     * @param taskDescription Task description.
     * @throws RuntimeException If the description is invalid.
     */
    public TodoTask(String taskDescription) throws RuntimeException {
        super(taskDescription);
    }
    /**
     * Return the formatted representation.
     *
     * @return Formatted task string.
     */
    @Override
    public String getRep() {
        return "T | " + getStatus() + " | " + getTaskName();
    }
}
