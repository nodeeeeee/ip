package duke.senpaiexception;

import duke.responseblock.ResponseBlock;

/**
 * Represents a customized exception.
 */
public class SenpaiException extends RuntimeException {
    private final ResponseBlock response;

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
}
