package duke.Task;

import duke.SenpaiException.SenpaiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class EventTask extends duke.Task.Task {
    /**
     * Initialization of EventTask.
     *
     * @param taskDescription description string.
     * @throws duke.SenpaiException.SenpaiException If the input dates are invalid.
     */
    public EventTask(String taskDescription) throws duke.SenpaiException.SenpaiException {
        super(taskDescription.split(" /from ", 2)[0]);
        if (taskDescription.split(" /from ", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The starting time of a ddl cannot be empty.");
        } else if (taskDescription.split(" /from ", 2)[1].split(" /to ", 2).length == 1) {
            throw new duke.SenpaiException.SenpaiException("Mulimomuli!!! The end time of a ddl cannot be empty.");
        }
        String from_str = taskDescription.split(" /from ", 2)[1].split(" /to ", 2)[0];
        String to_str = taskDescription.split(" /from ", 2)[1].split(" /to ", 2)[1];
        try {
            if (!from_str.contains("-")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                from = LocalDate.parse(from_str, formatter);
            } else {
                from = LocalDate.parse(from_str);
            }
            if (!from_str.contains("-")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                to = LocalDate.parse(to_str, formatter);
            } else {
                to = LocalDate.parse(to_str);
            }
        } catch (DateTimeParseException e) {
            throw new duke.SenpaiException.SenpaiException("date time format should be yyyy-mm-dd");
        }

    }

    /**
     * Return the formatted representation.
     *
     * @return Formatted task string.
     */
    @Override
    public String getRep() {
        return "E | " + getStatus() + " | " + getTaskName() + " | /from " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " /to " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    private LocalDate from;
    private LocalDate to;
}
