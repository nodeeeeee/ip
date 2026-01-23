package ResponseBlock;

public class ResponseBlock {
    public ResponseBlock(String str) {
        response = str;
    }
    public void Print() {
        System.out.print("____________________________________________________________\n" + response + "\n____________________________________________________________\n");
    }
    final private String response;
}
