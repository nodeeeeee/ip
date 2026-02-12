package duke;

import duke.responseblock.ResponseBlock;

/**
 * Prints messages to the user.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Prints the loading error message.
     */
    public String showLoadingError() {
        ResponseBlock loadingError = new ResponseBlock("Loading Error");
        return loadingError.print();
    }
}
