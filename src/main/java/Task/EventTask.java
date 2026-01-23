package Task;

import SenpaiException.SenpaiException;

public class EventTask extends Task {
    public EventTask(String taskDescription) throws SenpaiException {
        super(taskDescription.split("/from", 2)[0]);
        if (taskDescription.split("/from", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The starting time of a ddl cannot be empty.");
        } else if (taskDescription.split("/from", 2)[1].split("/to", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The end time of a ddl cannot be empty.");
        }
        from = taskDescription.split("/from", 2)[1].split("/to", 2)[0];
        to = taskDescription.split("/from", 2)[1].split("/to", 2)[1];

    }

    @Override
    public String getRep() {
        return "[E]" + "[" + getStatus() + "] " + getTaskName() + "(from:" + from + "to:" + to + ")";
    }

    final private String from;
    final private String to;
}
