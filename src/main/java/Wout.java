import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Wout {
    private static Scanner userInputScanner = new Scanner(System.in);
    private static UserTaskStore userTaskStore = new UserTaskStore();
    private final static String FILE_PATH = "./data/wout.txt";

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
    public final static String DEADLINE_REGEX =
            "^(?!.*\\/by.*\\/by)(.+?)\\s+\\/by\\s+(.+)$";
    public final static String EVENT_REGEX =
            "^(?!.*\\/from.*\\/from)(?!.*\\/to.*\\/to)(.+?)\\s+\\/from\\s+(.+?)\\s+\\/to\\s+(.+)$";


    public static void printMessage(String message) {
        System.out.println("________________________________\n"
                + message
                + "________________________________\n"
        );
    }

    public static String addTaskMessage(Task task) {
          return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
    }

    private static String doMarkTaskCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = userTaskStore.markTaskAt(index);
            return "Nice! I've marked this task as done:\n"
                    + "  " + task + "\n"
                    + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for marking\n");
        }
    }

    private static String doUnmarkTaskCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = userTaskStore.unmarkTaskAt(index);
            return "Ok, I've marked this task as not done yet:\n"
                    + "  " + task + "\n"
                    + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for unmarking\n");
        }
    }

    private static String doAddTodoCommand(String input, boolean isDone) throws WoutException {
        if (input.isEmpty()) {
            throw new WoutException("Please provide a description for Todo tasks\n");
        } else {
            Task todo = new Todo(input, isDone);
            userTaskStore.storeTask(todo);
            return addTaskMessage(todo);
        }
    }

    private static String doAddTodoCommand(String input) throws WoutException {
        return doAddTodoCommand(input, false);
    }

    private static String doAddDeadlineCommand(String input, boolean isDone) throws WoutException {
        Matcher matcher = Pattern.compile(DEADLINE_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Deadline tasks\n");
        } else {
            Task deadline = new Deadline(matcher.group(1), matcher.group(2), isDone);
            userTaskStore.storeTask(deadline);
            return addTaskMessage(deadline);
        }
    }

    private static String doAddDeadlineCommand(String input) throws WoutException {
        return doAddDeadlineCommand(input, false);
    }

    private static String doAddEventCommand(String input, boolean isDone) throws WoutException {
        Matcher matcher = Pattern.compile(EVENT_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Event tasks\n");
        } else {
            Task event = new Event(matcher.group(1), matcher.group(2), matcher.group(3), isDone);
            userTaskStore.storeTask(event);
            return addTaskMessage(event);
        }
    }

    private static String doAddEventCommand(String input) throws WoutException {
        return doAddEventCommand(input, false);
    }

    private static String doDeleteCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = userTaskStore.deleteTaskAt(index);
            return "Noted. I've remove this task:\n"
                    + "  " + task + "\n"
                    + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for deleting\n");
        }
    }

    private static boolean parseTaskDoneStatus(String isDone) throws WoutException {
        return switch (isDone) {
            case "1" -> true;
            case "0" -> false;
            default -> throw new WoutException(isDone + " is not a valid status\n");
        };
    }
    /**
     * Read tasks from FILE_PATH and add tasks into userTaskStore.
     * If file does not exist, do nothing
     */
    private static void loadTaskList() {
        File file = new File(FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] inputArr = input.split("\\s+#\\s+");
                boolean isDone = parseTaskDoneStatus(inputArr[0]);
                inputArr = inputArr[1].split("\\s+", 2);
                String command = inputArr[0];
                switch (command) {
                    case ADD_TODO_COMMAND -> doAddTodoCommand(inputArr[1], isDone);
                    case ADD_DEADLINE_COMMAND ->  doAddDeadlineCommand(inputArr[1], isDone);
                    case ADD_EVENT_COMMAND -> doAddEventCommand(inputArr[1], isDone);
                    default -> throw new WoutException("\"" + input + "\" is not a valid entry in your file!\n");
                }
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (WoutException e) {
            printMessage(e.toString());
        }
    }

    public static void main(String[] args) {
        Wout.loadTaskList();
        Wout.printMessage(greetingMessage);

        boolean exit = false;
        String msg;
        while (!exit) {
            // Read user input
            String input = Wout.userInputScanner.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            String command = inputArr[0];
            // Carry out user command
            try {
                msg = switch (command) {
                    case EXIT_COMMAND -> {
                        exit = true;
                        yield exitMessage;
                    }
                    case LIST_COMMAND -> userTaskStore.listTasks();
                    case MARK_TASK_COMMAND -> doMarkTaskCommand(inputArr[1]);
                    case UNMARK_TASK_COMMAND -> doUnmarkTaskCommand(inputArr[1]);
                    case ADD_TODO_COMMAND -> doAddTodoCommand(inputArr[1]);
                    case ADD_DEADLINE_COMMAND -> doAddDeadlineCommand(inputArr[1]);
                    case ADD_EVENT_COMMAND -> doAddEventCommand(inputArr[1]);
                    case DELETE_COMMAND -> doDeleteCommand(inputArr[1]);
                    default -> throw new WoutException(invalidCommandMessage);
                };
                printMessage(msg);
            } catch (ArrayIndexOutOfBoundsException e) {
                printMessage("Please provide input for " + command + " command!\n");
            } catch (WoutException e) {
                printMessage(e.toString());
            }
        }
    }
}
