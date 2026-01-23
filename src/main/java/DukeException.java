public class DukeException extends RuntimeException{
    public DukeException(String message) {
        super(message);
        response = new ResponseBlock(message);
    }
    public ResponseBlock getResponse() {
        return response;
    }
    private final ResponseBlock response;
}
