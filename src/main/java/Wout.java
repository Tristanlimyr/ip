import java.util.Scanner;

public class Wout {
    public static Scanner userCommandsScanner = new Scanner(System.in);
    public static String exitCommand = "bye";
    public static String listCommand = "list";
    public static String greetingMessage = "Hello! I'm Wout!\n"
            + "What can I do for you?\n";
    public static String exitMessage = "Bye. Hope to see you again soon!\n";

    public static void printMessage(String message) {
        System.out.println("________________________________\n"
                + message
                + "________________________________\n"
        );
    }

    public static void main(String[] args) {
        Wout.printMessage(Wout.greetingMessage);

        UserCommandsStore userCommandsStore = new UserCommandsStore();
        boolean exit = false;
        while (!exit) {
            // Read user command
            String command = Wout.userCommandsScanner.nextLine();
            if (command.equals(Wout.exitCommand)) {
                exit = true;
            } else if (command.equals(Wout.listCommand)) {
                // List all user commands
                Wout.printMessage(userCommandsStore.listCommands());
            } else {
                // Store user command
                userCommandsStore.storeCommand(command);
                // Echo user command
                Wout.printMessage("added: " + command + "\n");
            }
        }

        Wout.printMessage(Wout.exitMessage);
    }
}
