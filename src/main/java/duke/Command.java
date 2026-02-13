package duke;

import java.time.LocalDateTime;

import duke.senpaiexception.SenpaiException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;



/**
 * Represents one user action.
 */
public class Command {
    /**
     * Supported command types.
     */
    public enum Type {
        E,
        T,
        D,
        find,
        list,
        bye,
        mark,
        unmark,
        delete,
        free,
        err
    }

    private String description;
    private Type type;
    private int idx;
    private LocalDateTime time;
    private int minutes;

    /**
     * Creates a command with text content.
     *
     * @param description Text for the command.
     * @param type        Command type.
     */
    public Command(String description, Type type) {
        this.description = description;
        this.type = type;
    }

    /**
     * Creates a command that uses 1-based index.
     *
     * @param type Command type.
     * @param idx  1-based index.
     */
    public Command(Type type, int idx) {
        this.type = type;
        this.idx = idx;
    }

    /**
     * Creates a command with a time argument.
     *
     * @param type Command type.
     * @param minutes Duration in minutes.
     * @param time Start time.
     */
    public Command(Type type, int minutes, LocalDateTime time) {
        this.type = type;
        this.minutes = minutes;
        this.time = time;
    }

    public Type getType() {
        return type;
    }

    /**
     * Executes the command on the task list.
     *
     * @param tasks Task list to operate on.
     * @throws SenpaiException If an invalid command is encountered.
     */
    public String execute(TaskList tasks) throws SenpaiException {
        assert tasks != null : "tasks must not be null";
        if (type == Type.E || type == Type.T || type == Type.D || type == Type.find) {
            assert description != null : "description must not be null for content commands";
        }

        String response;
        switch (type) {
        case E -> {
            response = tasks.addWithResponse(new EventTask(description));
        }
        case T -> {
            response = tasks.addWithResponse(new TodoTask(description));
        }
        case D -> {
            response = tasks.addWithResponse(new DeadlineTask(description));
        }
        case find -> {
            String[] keywords = description.split(" ");
            response = tasks.find(keywords);
        }
        case list -> {
            response = tasks.list();
        }
        case mark -> {
            response = tasks.mark(idx);
        }
        case unmark -> {
            response = tasks.unmark(idx);
        }
        case delete -> {
            response = tasks.deleteTask(idx);
        }
        case free -> {
            response = tasks.findNearestFreeDay(minutes, time);
        }
        default -> {
            throw new SenpaiException("Unknown command type.");
        }
        }
        tasks.saveList();
        return response;
    }

    /**
     * Checks if command type is bye.
     *
     * @return True if command type is bye.
     */
    public boolean isExit() {
        return type == Type.bye;
    }
}
