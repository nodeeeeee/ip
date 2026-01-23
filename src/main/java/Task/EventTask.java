package Task;

public class EventTask extends Task {
    public EventTask(String taskDescription) {
        super(taskDescription.split("/from", 2)[0]);
        from = taskDescription.split("/from", 2)[1].split("/to", 2)[0];
        to = taskDescription.split("/from", 2)[1].split("/to", 2)[1];
//        if (from.isEmpty()) {
//            throw new RuntimeException("Mulimomuli!!! The starting time of a ddl cannot be empty.");
//        } else if (to.isEmpty()) {
//            throw new RuntimeException("Mulimomuli!!! The end time of a ddl cannot be empty.");
//        }
    }

    @Override
    public String getRep() {
        return "[E]" + "[" + getStatus() + "] " + getTaskName() + "(from:" + from + "to:" + to + ")";
    }

    final private String from;
    final private String to;
}
