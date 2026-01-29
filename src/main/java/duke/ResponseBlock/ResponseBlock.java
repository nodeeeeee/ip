package duke.ResponseBlock;

/**
 * A block Ui format.
 */
public class ResponseBlock {
    /**
     * Create a response block from a message.
     *
     * @param str Message to show.
     */
    public ResponseBlock(String str) {
        response = str;
    }
    public void Print() {
        System.out.print("____________________________________________________________\n" + response + "\n____________________________________________________________\n");
    }
    final private String response;
}
