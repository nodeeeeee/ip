import ResponseBlock.ResponseBlock;
import SenpaiException.SenpaiException;
import Task.*;

import java.io.IOException;
import java.util.Scanner;

public class YajuSenpai {
    public YajuSenpai(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SenpaiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                Command c = Parser.parse(input);
                if (c.isExit()) {
                    break;
                }
                c.execute(tasks);

            } catch (SenpaiException e) {
                e.getResponse().Print();
            }

        }
    }



    public static void main(String[] args) {
        new YajuSenpai("data/tasks.txt").run();
    }









//    public static void delete(int idx) {
//        String removed_task = tasks.getTask(idx);
//        tasks.deleteTask(idx);
//        ResponseBlock response = new ResponseBlock("Noted. I've removed this task:\n" + removed_task + "\nNow you have " + tasks.getChatSize() + " tasks in the list.");
//        response.Print();
//    }
//
//    public static void list() {
//        ResponseBlock response = new ResponseBlock("Here are the tasks in your list:\n" + tasks.getAllTasks());
//        response.Print();
//    }
//
////    public static void echo(String input) {
////        ResponseBlock.ResponseBlock echo_response = new ResponseBlock.ResponseBlock(input);
////        echo_response.Print();
////    }
//    public static void mark(int index) {
//        tasks.mark(index);
//        ResponseBlock response = new ResponseBlock("Nice! I've marked this task as done:\n" + tasks.getTask(index));
//        response.Print();
//    }
//
//    public static void unmark(int index) {
//        tasks.unmark(index);
//        ResponseBlock response = new ResponseBlock("OK, I've marked this task as not done yet:\n" + tasks.getTask(index));
//        response.Print();
//    }
//
//    public static void exit() {
//        ResponseBlock exit = new ResponseBlock("Bye. Hope to see you again soon!");
//        exit.Print();
//    }

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
}
