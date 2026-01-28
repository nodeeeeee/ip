import Task.DeadlineTask;
import Task.EventTask;
import Task.TodoTask;

import java.time.LocalDate;

public class Command {
    public enum Type {
        E,
        T,
        D,
        list,
        bye,
        mark,
        unmark,
        delete
    }
    public Command(String description, Type type) {
        this.description = description;
        this.type = type;
    }
    public Command(Type type, int idx) {
        this.type = type;
        this.idx = idx;
    }

    public void execute(TaskList tasks) {
        switch (type) {
            case E -> {tasks.addWithResponse(new EventTask(description));}
            case T -> {tasks.addWithResponse(new TodoTask(description));}
            case D -> {tasks.addWithResponse(new DeadlineTask(description));}
            case list -> {tasks.list();}
            case mark -> {tasks.mark(idx);}
            case unmark -> {tasks.unmark(idx);}
            case delete -> {tasks.deleteTask(idx);}
        }
        tasks.saveList();
    }

    public boolean isExit() {
        return type == Type.bye;
    }

    private String description;
    private Type type;
    private int idx;
}
