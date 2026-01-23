public class ResponseBlock {
    public ResponseBlock(String str) {
        response = str;
    }
    public void Print() {
        System.out.print(response + "\n____________________________________________________________\n");
    }
    final private String response;
}
