import ResponseBlock.ResponseBlock;
import SenpaiException.SenpaiException;
import Task.*;

import java.util.Scanner;

public class YajuSenpai {
    public static void main(String[] args) {

//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);

        ResponseBlock greetings = new ResponseBlock("Hello! I'm Yaju Senpai.\nWhat can I do for you?");
        greetings.Print();

        while (true) {
            try {
                String input = scanner.nextLine();
                String[] input_words = input.split(" ", 2);
                if (input.equals("bye")) {
                    exit();
                    break;
                } else if (input.equals("list")) {
                    list();
                } else if (input_words[0].equals("mark")) {
                    try {
                        int index = Integer.parseInt(input_words[1]);
                        mark(index);
                    } catch (NumberFormatException e){
                        throw new SenpaiException("あのさぁ, should follow an integer");
                    }
                } else if (input_words[0].equals("unmark")) {
                    try {
                        int index = Integer.parseInt(input_words[1]);
                        unmark(index);
                    } catch (NumberFormatException e){
                        throw new SenpaiException("あのさぁ, should follow an integer");
                    }
                } else if (input_words[0].equals("todo")) {
                    if (input_words.length == 1) {
                        throw new SenpaiException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    add(new TodoTask(input_words[1]));
                } else if (input_words[0].equals("deadline")) {
                    if (input_words.length == 1) {
                        throw new SenpaiException("Yadamoyada!!! The description of a ddl cannot be empty.");
                    }
                    add(new DeadlineTask(input_words[1]));
                } else if (input_words[0].equals("event")) {
                    if (input_words.length == 1) {
                        throw new SenpaiException("Yadamoyada!!! The description of an event cannot be empty.");
                    }
                    add(new EventTask(input_words[1]));
                } else {
                    ResponseBlock response = new ResponseBlock("a- mou ikkai ittekure (What is this guy talking about?)");
                    response.Print();
                }
            } catch (SenpaiException e){
                e.getResponse().Print();
            }
        }
    }

    public static void add(Task task) {

        chatHistory.addTask(task);
        ResponseBlock response = new ResponseBlock("Got it. I've added this task: \n" + task.getRep() + "\nNow you have " + chatHistory.getChatSize() + " tasks in the list.");
        response.Print();
    }

    public static void list() {
        ResponseBlock response = new ResponseBlock("Here are the tasks in your list: \n" + chatHistory.getAllTasks());
        response.Print();
    }

//    public static void echo(String input) {
//        ResponseBlock.ResponseBlock echo_response = new ResponseBlock.ResponseBlock(input);
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
