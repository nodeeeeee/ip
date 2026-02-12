package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.senpaiexception.SenpaiException;

/**
 * Represents an EventTask.
 */
public class EventTask extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Initialization of EventTask.
     *
     * @param taskDescription description string.
     * @throws SenpaiException If the input dates are invalid.
     */
    public EventTask(String taskDescription) throws SenpaiException {
        super(taskDescription.split(" /from ", 2)[0]);
        if (taskDescription.split(" /from ", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The starting time of a ddl cannot be empty.");
        } else if (taskDescription.split(" /from ", 2)[1].split(" /to ", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The end time of a ddl cannot be empty.");
        }
        String fromStr = taskDescription.split(" /from ", 2)[1].split(" /to ", 2)[0];
        String toStr = taskDescription.split(" /from ", 2)[1].split(" /to ", 2)[1];
        try {
            if (!fromStr.contains("-")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                from = LocalDate.parse(fromStr, formatter);
            } else {
                from = LocalDate.parse(fromStr);
            }
            if (!fromStr.contains("-")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                to = LocalDate.parse(toStr, formatter);
            } else {
                to = LocalDate.parse(toStr);
            }
        } catch (DateTimeParseException e) {
            throw new SenpaiException("date time format should be yyyy-mm-dd");
        }
        assert !to.isBefore(from) : "event end must not be before start";

    }

    /**
     * Return the formatted representation.
     *
     * @return Formatted task string.
     */
    @Override
    public String getRep() {
        return "E | " + getStatus() + " | " + getTaskName()
                + " | /from " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " /to " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}
