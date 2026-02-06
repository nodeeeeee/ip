package duke; // same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.senpaiexception.SenpaiException;
import duke.task.DeadlineTask;
import duke.task.TodoTask;
/**
 * Tests for core task behavior.
 */
public class SenpaiTest {
    /**
     * Verifies that mark command updates task status.
     *
     * @throws SenpaiException If command execution fails.
     */
    @Test
    public void markCommand() throws SenpaiException {
        TaskList tasks = new TaskList();
        tasks.addWithoutResponse(new TodoTask("read book"));

        Command cmd = Parser.parse("mark 1");
        cmd.execute(tasks);

        assertEquals("T | 1 | read book", tasks.getTask(1));
    }

    /**
     * Verifies deadline formatting for date input.
     */
    @Test
    public void deadlineCommand() {
        DeadlineTask task = new DeadlineTask("submit report /by 1919-08-10");
        assertEquals("D | 0 | submit report | /by Aug 10 1919", task.getRep());
    }

    /**
     * Verifies invalid date parsing throws an exception.
     */
    @Test
    public void invalidDatetime() {
        SenpaiException ex = assertThrows(SenpaiException.class,
                () -> new DeadlineTask("submit report /by 1145/01/04"));
        assertEquals("date time format should be yyyy-mm-dd", ex.getMessage());
    }

    /**
     * Verifies find matches only tasks containing the keyword.
     */
    @Test
    public void findMatchingTasks() {
        TaskList tasks = new TaskList();
        tasks.addWithoutResponse(new TodoTask("read book"));
        tasks.addWithoutResponse(new TodoTask("buy milk"));
        tasks.addWithoutResponse(new TodoTask("return book"));

        String matches = tasks.getMatchingTasks("book");

        assertEquals("1. T | 0 | read book\n3. T | 0 | return book", matches);
    }
}
