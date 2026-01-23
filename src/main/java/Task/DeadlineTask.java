package Task;

import SenpaiException.SenpaiException;

public class DeadlineTask extends Task {
    public DeadlineTask(String taskDescription) throws SenpaiException{
        super(taskDescription.split("/by", 2)[0]);
        if (taskDescription.split("/by", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The due of a ddl cannot be empty.");
        }
        due = taskDescription.split("/by", 2)[1];
    }

    @Override
    public String getRep() {
        return "[D]" + "[" + getStatus() + "] " + getTaskName() + "(by:" + due + ")";
    }

    final private String due;
}
