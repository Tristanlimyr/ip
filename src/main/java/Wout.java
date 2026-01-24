import java.util.Scanner;

public class Wout {
    public static Scanner userInputScanner = new Scanner(System.in);
    public static UserTaskStore userTaskStore = new UserTaskStore();

    // user commands
    public final static String EXIT_COMMAND = "bye";
    public final static String LIST_COMMAND = "list";
    public final static String MARK_TASK_COMMAND = "mark";
    public final static String UNMARK_TASK_COMMAND = "unmark";
    public final static String ADD_TODO_COMMAND = "todo";
    public final static String ADD_DEADLINE_COMMAND = "deadline";
    public final static String ADD_EVENT_COMMAND = "event";

    // default messages
    public static String greetingMessage = "Hello! I'm Wout!\n"
            + "What can I do for you?\n";
    public static String exitMessage = "Bye. Hope to see you again soon!\n";
    public static String invalidCommandMessage = "Please enter a valid command\n";

    public static void printMessage(String message) {
        System.out.println("________________________________\n"
                + message
                + "________________________________\n"
        );
    }

    public static void printAddedTaskMessage(Task task) {
        String addedTaskMessage = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
        printMessage(addedTaskMessage);
    }

    private static void doMarkTaskCommand(String input) {
        try {
            int index = Integer.parseInt(input);
            Wout.printMessage(userTaskStore.markTaskAt(index));
        } catch (NumberFormatException e) {
            Wout.printMessage(input + " is not a valid index!\n");
        }
    }

    private static void doUnmarkTaskCommand(String input) {
        try {
            int index = Integer.parseInt(input);
            Wout.printMessage(userTaskStore.unmarkTaskAt(index));
        } catch (NumberFormatException e) {
            Wout.printMessage(input + " is not a valid index!\n");
        }
    }

    private static void doAddTodoCommand(String input) {
        try {
            Task todo = new Todo(input);
            userTaskStore.storeTask(todo);
            printAddedTaskMessage(todo);
        } catch (ArrayIndexOutOfBoundsException e) {
            printMessage("Please provide a valid input for Todo tasks");
        }
    }

    private static void doAddDeadlineCommand(String input) {
        try {
            String[] inputArr = input.split("/by ");
            Task deadline = new Deadline(inputArr[0], inputArr[1]);
            userTaskStore.storeTask(deadline);
            printAddedTaskMessage(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            printMessage("Please provide a valid input for Deadline tasks");
        }
    }

    private static void doAddEventCommand(String input) {
        try {
            String[] inputArr = input.split("/from | /to ");
            Task event = new Event(inputArr[0], inputArr[1], inputArr[2]);
            userTaskStore.storeTask(event);
            printAddedTaskMessage(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            printMessage("Please provide a valid input for Event tasks");
        }
    }

    public static void main(String[] args) {
        Wout.printMessage(greetingMessage);

        boolean exit = false;
        while (!exit) {
            // Read user input
            String input = Wout.userInputScanner.nextLine();
            String[] inputArr = input.split(" ", 2);
            if (inputArr.length == 0) {
                Wout.printMessage(invalidCommandMessage);
            } else {
                String command = inputArr[0];

                // Carry out user command
                try {
                    switch (command) {
                        case EXIT_COMMAND:
                            exit = true;
                            break;
                        case LIST_COMMAND:
                            Wout.printMessage(userTaskStore.listTasks());
                            break;
                        case MARK_TASK_COMMAND:
                            doMarkTaskCommand(inputArr[1]);
                            break;
                        case UNMARK_TASK_COMMAND:
                            doUnmarkTaskCommand(inputArr[1]);
                            break;
                        case ADD_TODO_COMMAND:
                            doAddTodoCommand(inputArr[1]);
                            break;
                        case ADD_DEADLINE_COMMAND:
                            doAddDeadlineCommand(inputArr[1]);
                            break;
                        case ADD_EVENT_COMMAND:
                            doAddEventCommand(inputArr[1]);
                            break;
                        default:
                            Wout.printMessage(invalidCommandMessage);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    printMessage("Please provide a valid input for " + command + "command");
                }
            }
        }
        Wout.printMessage(exitMessage);
    }
}
