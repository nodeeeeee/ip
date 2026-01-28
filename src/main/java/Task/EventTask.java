package Task;

import SenpaiException.SenpaiException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class EventTask extends Task {
    public EventTask(String taskDescription) throws SenpaiException {
        super(taskDescription.split(" /from ", 2)[0]);
        if (taskDescription.split(" /from ", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The starting time of a ddl cannot be empty.");
        } else if (taskDescription.split(" /from ", 2)[1].split(" /to ", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The end time of a ddl cannot be empty.");
        }
        String from_str = taskDescription.split(" /from ", 2)[1].split(" /to ", 2)[0];
        String to_str = taskDescription.split(" /from ", 2)[1].split(" /to ", 2)[1];
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

    }

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
