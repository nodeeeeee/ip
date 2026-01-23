package SenpaiException;

import ResponseBlock.ResponseBlock;

public class SenpaiException extends RuntimeException{
    public SenpaiException(String message) {
        super(message);
        response = new ResponseBlock(message);
    }
    public ResponseBlock getResponse() {
        return response;
    }
    private final ResponseBlock response;
}
