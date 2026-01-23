package Task;

public class DeadlineTask extends Task {
    public DeadlineTask(String taskDescription) throws RuntimeException{
        super(taskDescription.split("/by", 2)[0]);
        due = taskDescription.split("/by", 2)[1];
//        if (due.isEmpty()) {
//            throw new RuntimeException("Mulimomuli!!! The due of a ddl cannot be empty.");
//        }
    }

    @Override
    public String getRep() {
        return "[D]" + "[" + getStatus() + "] " + getTaskName() + "(by:" + due + ")";
    }

    final private String due;
}
