package duke;

import duke.ResponseBlock.ResponseBlock;

/**
 * Prints messages to the user.
 */
public class Ui {
    public Ui() {

    }

    public void showWelcome() {
        ResponseBlock greetings = new ResponseBlock("Hello! I'm Yaju Senpai.\nWhat can I do for you?");
        greetings.Print();
    }

    public void showLoadingError() {
        ResponseBlock loadingError = new ResponseBlock("Loading Error");
        loadingError.Print();
    }
}
