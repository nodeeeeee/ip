package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.senpaiexception.SenpaiException;

/**
 * Represents a DDLTask.
 */
public class DeadlineTask extends Task {
    private LocalDate due;

    /**
     * Initialization of DealineTask.
     *
     * @param taskDescription description string.
     * @throws SenpaiException If the input date is invalid.
     */
    public DeadlineTask(String taskDescription) throws SenpaiException {
        super(taskDescription.split(" /by ", 2)[0]);
        if (taskDescription.split(" /by ", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The due of a ddl cannot be empty.");
        }
        String dueStr = taskDescription.split(" /by ", 2)[1];

        try {
            if (!dueStr.contains("-")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                due = LocalDate.parse(dueStr, formatter);
            } else {
                due = LocalDate.parse(dueStr);

            }
        } catch (DateTimeParseException e) {
            throw new SenpaiException("date time format should be yyyy-mm-dd");
        }
        assert due != null : "due date must be parsed";
    }

    /**
     * Return the formatted representation.
     *
     * @return Formatted task string.
     */
    @Override
    public String getRep() {
        return "D | " + getStatus() + " | " + getTaskName()
                + " | /by " + due.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the due date.
     *
     * @return Due date.
     */
    public LocalDate getDue() {
        return due;
    }
}
