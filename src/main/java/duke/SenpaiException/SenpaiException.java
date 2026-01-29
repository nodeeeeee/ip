package duke.SenpaiException;

import duke.ResponseBlock.ResponseBlock;

public class SenpaiException extends RuntimeException{
    /**
     * Create the exception.
     *
     * @param message Error message.
     */
    public SenpaiException(String message) {
        super(message);
        response = new ResponseBlock(message);
    }
    /**
     * Return the response for this exception.
     *
     * @return Response block.
     */
    public ResponseBlock getResponse() {
        return response;
    }
    private final ResponseBlock response;
}
