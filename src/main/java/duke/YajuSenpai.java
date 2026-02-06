package duke;

import duke.senpaiexception.SenpaiException;

/**
 * duke.Main class.
 */
public class YajuSenpai {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private String commandType = "";
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
     * Run the main input loop.
     */
    //    public void run() {
    //        Scanner scanner = new Scanner(System.in);
    //        ui.showWelcome();
    //        while (scanner.hasNextLine()) {
    //            String input = scanner.nextLine();
    //            try {
    //                Command c = Parser.parse(input);
    //                if (c.isExit()) {
    //                    break;
    //            }
    //            c.execute(tasks);
    //
    //            } catch (SenpaiException e) {
    //                e.getResponse().print();
    //            }
    //
    //        }
    //    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                return "--exit--";
            }
            String response = c.execute(tasks);
            commandType = c.getType().toString();
            return response;
        } catch (SenpaiException e) {
            return e.getResponse().print();
        }
    }

    public String getCommandType() {
        return commandType;
    }


    //    public static void main(String[] args) {
    //        new YajuSenpai("data/tasks.txt").run();
    //    }
}
