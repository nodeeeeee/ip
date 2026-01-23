package Task;

public class TodoTask extends Task{
    public TodoTask(String taskDescription) throws RuntimeException {
        super(taskDescription);
    }
    @Override
    public String getRep() {
        return "[T]" + "[" + getStatus() + "] " + getTaskName();
    }
}
