import java.util.Scanner;

public class Wout {
    public static Scanner userInputScanner = new Scanner(System.in);
    public static String exitCommand = "bye";
    public static String listCommand = "list";
    public static String markTaskCommand = "mark";
    public static String unmarkTaskCommand = "unmark";
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

        UserTaskStore userTaskStore = new UserTaskStore();
        boolean exit = false;
        while (!exit) {
            // Read user input
            String input = Wout.userInputScanner.nextLine();
            String[] inputArr = input.split(" ");
            if (inputArr[0].equals(Wout.exitCommand)) {
                exit = true;
            } else if (inputArr[0].equals(Wout.listCommand)) {
                // List all user tasks
                Wout.printMessage(userTaskStore.listTasks());
            } else if (inputArr[0].equals(Wout.markTaskCommand)) {
                try {
                    int index = Integer.parseInt(inputArr[1]);
                    Wout.printMessage(userTaskStore.markTaskAt(index));
                } catch (NumberFormatException e) {
                    Wout.printMessage(inputArr[1] + " is not a valid index!\n");
                } catch (IndexOutOfBoundsException e) {
                    Wout.printMessage("Please provide a valid index to mark task as done\n");
                }
            } else if (inputArr[0].equals(Wout.unmarkTaskCommand)) {
                try {
                    int index = Integer.parseInt(inputArr[1]);
                    Wout.printMessage(userTaskStore.unmarkTaskAt(index));
                } catch (NumberFormatException e) {
                    Wout.printMessage(inputArr[1] + " is not a valid index!\n");
                } catch (IndexOutOfBoundsException e) {
                    Wout.printMessage("Please provide a valid index to unmark task as done\n");
                }
            } else {
                    // Store user task
                    Task task = new Task(input);
                    userTaskStore.storeTask(task);
                    // Echo user command
                    Wout.printMessage("added: " + task + "\n");
                }
            }

        Wout.printMessage(Wout.exitMessage);
    }
}
