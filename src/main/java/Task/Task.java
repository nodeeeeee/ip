package Task;
public abstract class Task {

    public Task(String str) {
        taskName = str;
        isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

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

