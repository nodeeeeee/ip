package duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import duke.SenpaiException.SenpaiException;
import duke.Task.DeadlineTask;
import duke.Task.TodoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SenpaiTest {
    @Test
    public void markCommand() {
        TaskList tasks = new TaskList();
        tasks.addWithoutResponse(new TodoTask("read book"));

        Command cmd = Parser.parse("mark 1");
        cmd.execute(tasks);

        assertEquals("T | 1 | read book", tasks.getTask(1));
    }

    @Test
    public void deadlineCommand() {
        DeadlineTask task = new DeadlineTask("submit report /by 1919-08-10");
        assertEquals("D | 0 | submit report | /by Aug 10 1919", task.getRep());
    }

    @Test
    public void invalidDatetime() {
        SenpaiException ex = assertThrows(SenpaiException.class,
                () -> new DeadlineTask("submit report /by 1145/01/04"));
        assertEquals("date time format should be yyyy-mm-dd", ex.getMessage());
    }
}
