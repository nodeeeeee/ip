package duke;

import duke.responseblock.ResponseBlock;

/**
 * Prints messages to the user.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        ResponseBlock greetings = new ResponseBlock("Hello! I'm Yaju Senpai.\nWhat can I do for you?");
        greetings.print();
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        ResponseBlock loadingError = new ResponseBlock("Loading Error");
        loadingError.print();
    }
}
