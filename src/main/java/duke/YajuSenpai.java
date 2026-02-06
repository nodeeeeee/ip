package duke;

import java.util.Scanner;

import duke.senpaiexception.SenpaiException;

/**
 * Main class.
 */
public class YajuSenpai {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

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
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                Command c = Parser.parse(input);
                if (c.isExit()) {
                    break;
            }
            c.execute(tasks);

        } catch (SenpaiException e) {
            e.getResponse().print();
        }

    }
}


    public static void main(String[] args) {
        new YajuSenpai("data/tasks.txt").run();
    }
}
