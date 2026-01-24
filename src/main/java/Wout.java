import java.util.Scanner;

public class Wout {
    public static Scanner userCommandsScanner = new Scanner(System.in);
    public static String exitCommand = "bye";
    public static String greetingMessage =
            "________________________________\n"
            + "Hello! I'm Wout!\n"
            + "What can I do for you?\n"
            + "________________________________\n";

    public static String exitMessage =
            "________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "________________________________";

    public static void main(String[] args) {
        System.out.println(Wout.greetingMessage);

        boolean exit = false;
        while (!exit) {
            // Read user command
            String command = Wout.userCommandsScanner.nextLine();
            if (command.equals(Wout.exitCommand)) {
                exit = true;
            } else {
                // Echo user command
                System.out.println("________________________________\n"
                        + command
                        + "\n________________________________");
            }
        }

        System.out.println(Wout.exitMessage);
    }
}
