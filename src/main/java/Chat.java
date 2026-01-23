public class Chat {
    public Chat() {
        tasks = new Task[105];
        taskCount = 1;

    }

    public void addTask(String taskName) {
        tasks[taskCount] = new Task(taskName);
        taskCount++;
    }

    public String getTask(int index) {
        return "[" + (tasks[index].getStatus()) + "]" + tasks[index].getTaskName();
    }

    public void mark(int index) {
        tasks[index].mark();
    }

    public void unmark(int index) {
        tasks[index].unmark();
    }
//
//    public int getChatSize() {
//        return chatCount;
//    }

    public String getAllTasks() {
        StringBuilder ret = new StringBuilder();
        for (int i = 1; i < taskCount; i++) {
            ret.append(i).append(". [").append(tasks[i].getStatus()).append("]").append(tasks[i].getTaskName());
            if (i != taskCount - 1){
                ret.append("\n");
            }
        }
        return ret.toString();
    }

    private int taskCount;
    private static Task[] tasks ;
}
