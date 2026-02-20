package duke;

import java.util.Random;

import duke.senpaiexception.SenpaiException;

/**
 * duke.Main class.
 */
public class YajuSenpai {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private String commandType = "";
    private final Random rng = new Random();
    /**
     * Initialization.
     *
     * @param filePath Path to save file.
     */
    public YajuSenpai(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SenpaiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Parses the input and returns the response string.
     *
     * @param input User input.
     * @return Response text or exit sentinel.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                return "--exit--";
            }
            String response = c.execute(tasks);
            commandType = c.getType().toString();
            return addPersonality(response, commandType, false);
        } catch (SenpaiException e) {
            return addPersonality(e.getResponse().print(), "", true);
        }
    }

    /**
     * Returns the last command type processed.
     *
     * @return Command type string.
     */
    public String getCommandType() {
        return commandType;
    }

    private String addPersonality(String response, String type, boolean isError) {
        String prefix = pickPrefix(type, isError);
        return prefix + response;
    }

    private String pickPrefix(String type, boolean isError) {
        String[] errorPrefixes = new String[] {
            "YajuSenpai: Oi oi, slow down. ",
            "YajuSenpai: Hmm, that doesn't look right. ",
            "YajuSenpai: Try that again, onegai. "
        };
        String[] addPrefixes = new String[] {
            "YajuSenpai: Noted. ",
            "YajuSenpai: Ryoukai. ",
            "YajuSenpai: Done and dusted. "
        };
        String[] markPrefixes = new String[] {
            "YajuSenpai: Nice work. ",
            "YajuSenpai: Good job. ",
            "YajuSenpai: Sasuga. "
        };
        String[] deletePrefixes = new String[] {
            "YajuSenpai: Clean slate. ",
            "YajuSenpai: Poof, gone. ",
            "YajuSenpai: Vanished. "
        };
        String[] findPrefixes = new String[] {
            "YajuSenpai: Look what I found. ",
            "YajuSenpai: Here you go. ",
            "YajuSenpai: I sniffed these out. "
        };
        String[] listPrefixes = new String[] {
            "YajuSenpai: Your lineup, boss. ",
            "YajuSenpai: Task parade incoming. ",
            "YajuSenpai: The list, as you wish. "
        };
        String[] freePrefixes = new String[] {
            "YajuSenpai: Scanning for gaps. ",
            "YajuSenpai: Let me check your schedule. ",
            "YajuSenpai: Free time hunt, go. "
        };
        String[] neutralPrefixes = new String[] {
            "YajuSenpai: ",
            "YajuSenpai: Roger. ",
            "YajuSenpai: Mm-hmm. "
        };

        if (isError) {
            return errorPrefixes[rng.nextInt(errorPrefixes.length)];
        }
        if ("E".equals(type) || "T".equals(type) || "D".equals(type)) {
            return addPrefixes[rng.nextInt(addPrefixes.length)];
        }
        if ("mark".equals(type)) {
            return markPrefixes[rng.nextInt(markPrefixes.length)];
        }
        if ("delete".equals(type)) {
            return deletePrefixes[rng.nextInt(deletePrefixes.length)];
        }
        if ("find".equals(type)) {
            return findPrefixes[rng.nextInt(findPrefixes.length)];
        }
        if ("list".equals(type)) {
            return listPrefixes[rng.nextInt(listPrefixes.length)];
        }
        if ("free".equals(type)) {
            return freePrefixes[rng.nextInt(freePrefixes.length)];
        }
        return neutralPrefixes[rng.nextInt(neutralPrefixes.length)];
    }
}
