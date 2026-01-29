package duke;

import duke.Task.DeadlineTask;
import duke.Task.EventTask;
import duke.Task.TodoTask;

/**
 * Represent one user action.
 */
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
    /**
     * Create a command with text content.
     *
     * @param description Text for the command.
     * @param type Command type.
     */
    public Command(String description, Type type) {
        this.description = description;
        this.type = type;
    }
    /**
     * Create a command that uses 1-based index.
     *
     * @param type Command type.
     * @param idx 1-based index.
     */
    public Command(Type type, int idx) {
        this.type = type;
        this.idx = idx;
    }

    /**
     * Execute the command on the task list.
     *
     * @param tasks Task list to operate on.
     */
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

    /**
     * Check if command type is bye.
     *
     * @return True if command type is bye.
     */
    public boolean isExit() {
        return type == Type.bye;
    }

    private String description;
    private Type type;
    private int idx;
}
