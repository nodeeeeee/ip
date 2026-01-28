package Task;

import SenpaiException.SenpaiException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    public DeadlineTask(String taskDescription) throws SenpaiException{
        super(taskDescription.split(" /by ", 2)[0]);
        if (taskDescription.split(" /by ", 2).length == 1) {
            throw new SenpaiException("Mulimomuli!!! The due of a ddl cannot be empty.");
        }
        String due_str = taskDescription.split(" /by ", 2)[1];

        try {
            if (!due_str.contains("-")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                due = LocalDate.parse(due_str, formatter);
            } else {
                due = LocalDate.parse(due_str);

            }
        } catch (DateTimeParseException e) {
            throw new SenpaiException("date time format should be yyyy-mm-dd");
        }
    }

    @Override
    public String getRep() {
        return "D | "  + getStatus() + " | " + getTaskName() + " | /by " + due.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public LocalDate getDue() {
        return due;
    }

    private LocalDate due;

}
