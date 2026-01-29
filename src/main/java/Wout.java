import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
    public final static String DELETE_COMMAND = "delete";

    // default messages
    public static String greetingMessage = "Hello! I'm Wout!\n"
            + "What can I do for you?\n";
    public static String exitMessage = "Bye. Hope to see you again soon!\n";
    public static String invalidCommandMessage = "Please enter a valid command\n";

    // Regex
    public final static String DEADLINE_REGEX = "^(?!.*\\/by.*\\/by)(.+?)\\s+\\/by\\s+(.+)$";
    public final static String EVENT_REGEX = "^(?!.*\\/from.*\\/from)(?!.*\\/to.*\\/to)(.+?)\\s+\\/from\\s+(.+?)\\s+\\/to\\s+(.+)$";


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

    private static void doMarkTaskCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = userTaskStore.markTaskAt(index);
            printMessage("Nice! I've marked this task as done:\n"
                    + "  " + task + "\n"
                    + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n");
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for marking\n");
        }
    }

    private static void doUnmarkTaskCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = userTaskStore.unmarkTaskAt(index);
            printMessage("Ok, I've marked this task as not done yet:\n"
                    + "  " + task + "\n"
                    + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n");
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for unmarking\n");
        }
    }

    private static void doAddTodoCommand(String input) throws WoutException {
        if (input.isEmpty()) {
            throw new WoutException("Please provide a description for Todo tasks\n");
        } else {
            Task todo = new Todo(input);
            userTaskStore.storeTask(todo);
            printAddedTaskMessage(todo);
        }
    }

    private static void doAddDeadlineCommand(String input) throws WoutException {
        Matcher matcher = Pattern.compile(DEADLINE_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Deadline tasks\n");
        } else {
            Task deadline = new Deadline(matcher.group(1), matcher.group(2));
            userTaskStore.storeTask(deadline);
            printAddedTaskMessage(deadline);
        }
    }

    private static void doAddEventCommand(String input) throws WoutException {
        Matcher matcher = Pattern.compile(EVENT_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Event tasks\n");
        } else {
            Task event = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
            userTaskStore.storeTask(event);
            printAddedTaskMessage(event);
        }
    }

    private static void doDeleteCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = userTaskStore.deleteTaskAt(index);
            printMessage("Noted. I've remove this task:\n"
                    + "  " + task + "\n"
                    + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n");
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for deleting\n");
        }
    }

    public static void main(String[] args) {
        Wout.printMessage(greetingMessage);

        boolean exit = false;
        while (!exit) {
            // Read user input
            String input = Wout.userInputScanner.nextLine();
            String[] inputArr = input.split("\\s+", 2);
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
                case DELETE_COMMAND:
                    doDeleteCommand(inputArr[1]);
                    break;
                default:
                    throw new WoutException(invalidCommandMessage);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                printMessage("Please provide input for " + command + " command!\n");
            } catch (WoutException e) {
                printMessage(e.toString());
            }
        }

        Wout.printMessage(exitMessage);
    }
}
