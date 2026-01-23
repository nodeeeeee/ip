import java.util.Scanner;

public class YajuSenpai {
    public static void main(String[] args) {

//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);

        ResponseBlock greetings = new ResponseBlock("Hello! I'm Yaju Senpai.\nWhat can I do for you?");
        greetings.Print();

        while (true) {
            String input = scanner.nextLine();
            String[] input_words = input.split(" ");
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")){
                list();
            } else if (input_words[0].equals("mark")) {
                int index = Integer.parseInt(input_words[1]);
                mark(index);
            } else if (input_words[0].equals("unmark")) {
                int index = Integer.parseInt(input_words[1]);
                unmark(index);
            }
            else {
                add(input);
            }
        }
    }

    public static void add(String input) {
        chatHistory.addTask(input);
        ResponseBlock response = new ResponseBlock("added: " + input);
        response.Print();
    }

    public static void list() {
        ResponseBlock response = new ResponseBlock("Here are the tasks in your list: \n" + chatHistory.getAllTasks());
        response.Print();
    }

//    public static void echo(String input) {
//        ResponseBlock echo_response = new ResponseBlock(input);
//        echo_response.Print();
//    }
    public static void mark(int index) {
        chatHistory.mark(index);
        ResponseBlock response = new ResponseBlock("Nice! I've marked this task as done: \n" + chatHistory.getTask(index));
        response.Print();
    }

    public static void unmark(int index) {
        chatHistory.unmark(index);
        ResponseBlock response = new ResponseBlock("OK, I've marked this task as not done yet: \n" + chatHistory.getTask(index));
        response.Print();
    }

    public static void exit() {
        ResponseBlock exit = new ResponseBlock("Bye. Hope to see you again soon!");
        exit.Print();
    }


    private static final Chat chatHistory = new Chat();

}
