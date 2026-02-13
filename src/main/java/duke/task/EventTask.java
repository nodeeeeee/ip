package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.senpaiexception.SenpaiException;

/**
 * Represents an EventTask.
 */
public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

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
            boolean fromHasTime = fromStr.contains(":");
            boolean toHasTime = toStr.contains(":");
            if (fromHasTime != toHasTime) {
                throw new SenpaiException("Event /from and /to must both include hour and minute or both not.");
            }
            if (fromHasTime) {
                from = parseDateTime(fromStr);
                to = parseDateTime(toStr);
            } else {
                LocalDate fromDate = parseDate(fromStr);
                LocalDate toDate = parseDate(toStr);
                from = fromDate.atStartOfDay();
                to = toDate.atTime(23, 59);
            }
        } catch (DateTimeParseException e) {
            throw new SenpaiException("date time format should be yyyy-mm-dd HH:mm");
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
                + " | /from " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " /to " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    private LocalDate parseDate(String dateStr) {
        if (!dateStr.contains("-")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return LocalDate.parse(dateStr, formatter);
        }
        return LocalDate.parse(dateStr);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr.contains("-")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}
