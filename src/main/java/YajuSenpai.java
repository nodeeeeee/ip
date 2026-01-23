public class YajuSenpai {
    public static void main(String[] args) {

//        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");

        ResponseBlock greetings = new ResponseBlock("Hello! I'm Yaju Senpai.\nWhat can I do for you?");
        greetings.Print();

        ResponseBlock exit = new ResponseBlock("Bye. Hope to see you again soon!");
        exit.Print();
    }
}
