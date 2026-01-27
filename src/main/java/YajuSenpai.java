import ResponseBlock.ResponseBlock;
import SenpaiException.SenpaiException;
import Task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


import java.util.Scanner;

public class YajuSenpai {

    //Reused from https://nus-cs2103-ay2526-s2.github.io/website/schedule/week3/topics.html
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {

//        System.out.println("Hello from\n" + logo);
        loadSavedList();
        Scanner scanner = new Scanner(System.in);

        ResponseBlock greetings = new ResponseBlock("Hello! I'm Yaju Senpai.\nWhat can I do for you?");
        greetings.Print();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                return ;
            }
            processCommand(input);
        }
    }

    private static void processCommand(String input) {
        try {
            String[] input_words = input.split(" ", 2);
            if (input.equals("list")) {
                list();
            } else if (input_words[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(input_words[1]);
                    mark(index);
                    saveList();
                } catch (NumberFormatException e){
                    throw new SenpaiException("あのさぁ, should follow an integer");
                }
            } else if (input_words[0].equals("unmark")) {
                try {
                    int index = Integer.parseInt(input_words[1]);
                    unmark(index);
                    saveList();
                } catch (NumberFormatException e){
                    throw new SenpaiException("あのさぁ, should follow an integer");
                }
            } else if (input_words[0].equals("delete")) {
                int index = Integer.parseInt(input_words[1]);
                delete(index);
                saveList();
            }
            else if (input_words[0].equals("todo")) {
                if (input_words.length == 1) {
                    throw new SenpaiException("OOPS!!! The description of a todo cannot be empty.");
                }addWithResponse(new TodoTask(input_words[1]));
                saveList();
            } else if (input_words[0].equals("deadline")) {
                if (input_words.length == 1) {
                    throw new SenpaiException("Yadamoyada!!! The description of a ddl cannot be empty.");
                }
                addWithResponse(new DeadlineTask(input_words[1]));
                saveList();
            } else if (input_words[0].equals("event")) {
                if (input_words.length == 1) {
                    throw new SenpaiException("Yadamoyada!!! The description of an event cannot be empty.");
                }
                addWithResponse(new EventTask(input_words[1]));
                saveList();
            } else {
                ResponseBlock response = new ResponseBlock("a- mou ikkai ittekure (What is this guy talking about?)");
                response.Print();
            }
        } catch (SenpaiException e){
            e.getResponse().Print();
        }
    }

    private static void saveList() {
        try {
            writeToFile("savedTasks.txt", chatHistory.formatSave());
        } catch (IOException e) {
            System.out.println("cannot find file.");
        }
    }

    private static void loadSavedList() {
        try {
            File f = new File("savedTasks.txt"); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            int idx = 1;
            while (s.hasNext()) {
                String currTask = s.nextLine();
                String[] taskComponents = currTask.split(" \\| ", 4);

                if (taskComponents[0].equals("T")) {
                    addWithoutResponse(new TodoTask(taskComponents[2]));
                } else if (taskComponents[0].equals("D")) {
//                    System.out.println(taskComponents[0]);
//                    System.out.println(taskComponents[1]);
//                    System.out.println(taskComponents[2]);
//                    System.out.println(taskComponents[3]);
                    System.out.println(taskComponents[2] + " " + taskComponents[3]);
                    addWithoutResponse(new DeadlineTask(taskComponents[2] + " " + taskComponents[3]));
                } else if (taskComponents[0].equals("E")) {
                    addWithoutResponse(new EventTask(taskComponents[2] + " " + taskComponents[3]));
                }
                if (taskComponents[1].equals("1")) {
                    mark(idx);
                }
                idx++;
            }
        } catch (FileNotFoundException e) {
            return ;
        }
    }

    public static void addWithResponse(Task task) {
        chatHistory.addTask(task);
        ResponseBlock response = new ResponseBlock("Got it. I've added this task:\n" + task.getRep() + "\nNow you have " + chatHistory.getChatSize() + " tasks in the list.");
        response.Print();
    }

    public static void addWithoutResponse(Task task) {
        chatHistory.addTask(task);
    }

    public static void delete(int idx) {
        String removed_task = chatHistory.getTask(idx);
        chatHistory.deleteTask(idx);
        ResponseBlock response = new ResponseBlock("Noted. I've removed this task:\n" + removed_task + "\nNow you have " + chatHistory.getChatSize() + " tasks in the list.");
        response.Print();
    }

    public static void list() {
        ResponseBlock response = new ResponseBlock("Here are the tasks in your list:\n" + chatHistory.getAllTasks());
        response.Print();
    }

//    public static void echo(String input) {
//        ResponseBlock.ResponseBlock echo_response = new ResponseBlock.ResponseBlock(input);
//        echo_response.Print();
//    }
    public static void mark(int index) {
        chatHistory.mark(index);
        ResponseBlock response = new ResponseBlock("Nice! I've marked this task as done:\n" + chatHistory.getTask(index));
        response.Print();
    }

    public static void unmark(int index) {
        chatHistory.unmark(index);
        ResponseBlock response = new ResponseBlock("OK, I've marked this task as not done yet:\n" + chatHistory.getTask(index));
        response.Print();
    }

    public static void exit() {
        ResponseBlock exit = new ResponseBlock("Bye. Hope to see you again soon!");
        exit.Print();
    }


    private static final Chat chatHistory = new Chat();

}
