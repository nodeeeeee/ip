package duke.responseblock;

/**
 * Represents a formatted response block.
 */
public class ResponseBlock {
    private static final String BORDER = "---------------------------------------------------";

    private final String response;

    /**
     * Create a response block from a message.
     *
     * @param str Message to show.
     */
    public ResponseBlock(String str) {
        response = str;
    }

    /**
     * Prints the response block.
     */
    public String print() {
        return response;
        //        System.out.print(BORDER + "\n" + response + "\n" + BORDER + "\n");
    }
}
