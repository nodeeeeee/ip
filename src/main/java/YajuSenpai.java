import java.util.Scanner;

public class YajuSenpai {
    public static void main(String[] args) {

//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);

        ResponseBlock greetings = new ResponseBlock("Hello! I'm Yaju Senpai.\nWhat can I do for you?");
        greetings.Print();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")){
                list();
            } else {
                add(input);
            }
        }
    }

    public static void add(String input) {
        chatHistory.addChat(input);
        ResponseBlock response = new ResponseBlock("added: " + input);
        response.Print();
    }

    public static void list() {
        ResponseBlock response = new ResponseBlock(chatHistory.getAllChats());
        response.Print();
    }

//    public static void echo(String input) {
//        ResponseBlock echo_response = new ResponseBlock(input);
//        echo_response.Print();
//    }

    public static void exit() {
        ResponseBlock exit = new ResponseBlock("Bye. Hope to see you again soon!");
        exit.Print();
    }


    private static final Chat chatHistory = new Chat();

}
