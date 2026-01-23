import java.util.Scanner;

public class YajuSenpai {
    public static void main(String[] args) {

//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        ResponseBlock greetings = new ResponseBlock("Hello! I'm Yaju Senpai.\nWhat can I do for you?");
        greetings.Print();

        while (true) {
            String input = scanner.nextLine();
            if (!input.equals("bye")) {
                echo(input);
            } else {
                exit();
                break;
            }
        }
    }

    public static void echo(String input) {
        ResponseBlock echo_response = new ResponseBlock(input);
        echo_response.Print();
    }

    public static void exit() {
        ResponseBlock exit = new ResponseBlock("Bye. Hope to see you again soon!");
        exit.Print();
    }
}
