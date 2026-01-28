import ResponseBlock.ResponseBlock;
import SenpaiException.SenpaiException;
import Task.DeadlineTask;
import Task.EventTask;
import Task.TodoTask;

public class Parser {
    static Command parse(String input) {
            String[] input_words = input.split(" ", 2);
            if (input.equals("list")) {
                return new Command("list", Command.Type.list);
            } else if (input_words[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(input_words[1]);
                    return new Command(Command.Type.mark, index);
                } catch (NumberFormatException e){
                    throw new SenpaiException("あのさぁ, should follow an integer");
                }
            } else if (input_words[0].equals("unmark")) {
                try {
                    int index = Integer.parseInt(input_words[1]);
                    return new Command(Command.Type.unmark, index);
                } catch (NumberFormatException e){
                    throw new SenpaiException("あのさぁ, should follow an integer");
                }
            } else if (input_words[0].equals("delete")) {
                try {
                    int index = Integer.parseInt(input_words[1]);
                    return new Command(Command.Type.delete, index);
                } catch (NumberFormatException e){
                    throw new SenpaiException("あのさぁ, should follow an integer");
                }
            }
            else if (input_words[0].equals("todo")) {
                if (input_words.length == 1) {
                    throw new SenpaiException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new Command(input_words[1], Command.Type.T);
            } else if (input_words[0].equals("deadline")) {
                if (input_words.length == 1) {
                    throw new SenpaiException("Yadamoyada!!! The description of a ddl cannot be empty.");
                }
                return new Command(input_words[1], Command.Type.D);
            } else if (input_words[0].equals("event")) {
                if (input_words.length == 1) {
                    throw new SenpaiException("Yadamoyada!!! The description of an event cannot be empty.");
                }
                return new Command(input_words[1], Command.Type.E);
            } else if (input_words[0].equals("bye")) {
                return new Command("bye", Command.Type.bye);
            }
            else {
                throw new SenpaiException("a- mou ikkai ittekure (What is this guy talking about?)");
            }
    }
}
