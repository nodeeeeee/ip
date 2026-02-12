package duke;

import duke.senpaiexception.SenpaiException;

/**
 * Turns string input into a Command.
 */
public class Parser {
    /**
     * Parses the input string.
     *
     * @param input user input string.
     * @return Parsed command.
     * @throws SenpaiException If the input is not valid.
     */
    private static final String REQUIRE_INTEGER = "あのさぁ, should follow an integer";
    static Command parse(String input) {
        assert input != null : "input must not be null";
        String[] inputWords = input.split(" ", 2);

        if (input.equals("list")) {
            return new Command("list", Command.Type.list);
        } else if (inputWords[0].equals("mark")) {
            try {
                int index = Integer.parseInt(inputWords[1]);
                return new Command(Command.Type.mark, index);
            } catch (NumberFormatException e) {
                throw new SenpaiException(REQUIRE_INTEGER);
            }
        } else if (inputWords[0].equals("unmark")) {
            try {
                int index = Integer.parseInt(inputWords[1]);
                return new Command(Command.Type.unmark, index);
            } catch (NumberFormatException e) {
                throw new SenpaiException(REQUIRE_INTEGER);
            }
        } else if (inputWords[0].equals("delete")) {
            try {
                int index = Integer.parseInt(inputWords[1]);
                return new Command(Command.Type.delete, index);
            } catch (NumberFormatException e) {
                throw new SenpaiException(REQUIRE_INTEGER);
            }
        } else if (inputWords[0].equals("todo")) {
            if (inputWords.length == 1) {
                throw new SenpaiException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new Command(inputWords[1], Command.Type.T);
        } else if (inputWords[0].equals("deadline")) {
            if (inputWords.length == 1) {
                throw new SenpaiException("Yadamoyada!!! The description of a ddl cannot be empty.");
            }
            return new Command(inputWords[1], Command.Type.D);
        } else if (inputWords[0].equals("event")) {
            if (inputWords.length == 1) {
                throw new SenpaiException("Yadamoyada!!! The description of an event cannot be empty.");
            }
            return new Command(inputWords[1], Command.Type.E);
        } else if (inputWords[0].equals("find")) {
            if (inputWords.length == 1) {
                throw new SenpaiException("OOPS!!! The keyword for find cannot be empty.");
            }
            return new Command(inputWords[1], Command.Type.find);
        } else if (inputWords[0].equals("bye")) {
            return new Command("bye", Command.Type.bye);
        } else {
            throw new SenpaiException("a- mou ikkai ittekure (What is this guy talking about?)");
        }
    }
}
