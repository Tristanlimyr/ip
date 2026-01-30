import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Wout {
    private static final Scanner userInputScanner = new Scanner(System.in);
    private static final UserTaskStore userTaskStore = new UserTaskStore();
    private static final String FILE_PATH = "./data/wout.txt";

    // Regex
    public final static String DEADLINE_REGEX =
            "^(?!.*\\/by.*\\/by)(.+?)\\s+\\/by\\s+(.+)$";
    public final static String EVENT_REGEX =
            "^(?!.*\\/from.*\\/from)(?!.*\\/to.*\\/to)(.+?)\\s+\\/from\\s+(.+?)\\s+\\/to\\s+(.+)$";

    private static String doMarkTaskCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = userTaskStore.markTaskAt(index);
            return Ui.markTaskMessage(task, userTaskStore);
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
            return Ui.unmarkTaskMessage(task, userTaskStore);
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
            return Ui.addTaskMessage(todo, userTaskStore);
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
            try {
                LocalDateTime by = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                Task deadline = new Deadline(matcher.group(1), by, isDone);
                userTaskStore.storeTask(deadline);
                return Ui.addTaskMessage(deadline, userTaskStore);
            } catch (DateTimeParseException e) {
                throw new WoutException(Ui.INVALID_DATE_TIME);
            }
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
            try {
                LocalDateTime from = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                LocalDateTime to = LocalDateTime.parse(matcher.group(3), Ui.DATE_TIME_ENTRY);
                Task event = new Event(matcher.group(1), from, to, isDone);
                userTaskStore.storeTask(event);
                return Ui.addTaskMessage(event, userTaskStore);
            } catch (DateTimeParseException e) {
                throw new WoutException(Ui.INVALID_DATE_TIME);
            }
        }
    }

    private static String doAddEventCommand(String input) throws WoutException {
        return doAddEventCommand(input, false);
    }

    private static String doDeleteCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = userTaskStore.deleteTaskAt(index);
            return Ui.deleteTaskMessage(task, userTaskStore);
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
                UserCommand command = UserCommand.fromString(inputArr[0]);
                switch (command) {
                    case TODO -> doAddTodoCommand(inputArr[1], isDone);
                    case DEADLINE ->  doAddDeadlineCommand(inputArr[1], isDone);
                    case EVENT -> doAddEventCommand(inputArr[1], isDone);
                    default -> throw new WoutException("\"" + input + "\" is not a valid entry in your file!\n");
                }
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (WoutException e) {
            Ui.printWoutException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        loadTaskList();
        Ui.printGreeting();

        boolean exit = false;
        String msg;
        UserCommand command;
        while (!exit) {
            String input = Wout.userInputScanner.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            try {
                command = UserCommand.fromString(inputArr[0]);
                msg = switch (command) {
                    case EXIT -> {
                        exit = true;
                        yield Ui.EXIT;
                    }
                    case LIST -> userTaskStore.listTasks();
                    case MARK -> doMarkTaskCommand(inputArr[1]);
                    case UNMARK -> doUnmarkTaskCommand(inputArr[1]);
                    case TODO -> doAddTodoCommand(inputArr[1]);
                    case DEADLINE -> doAddDeadlineCommand(inputArr[1]);
                    case EVENT -> doAddEventCommand(inputArr[1]);
                    case DELETE -> doDeleteCommand(inputArr[1]);
                };
                Ui.printMessage(msg);
                userTaskStore.storeTaskList(FILE_PATH);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printMessage("Please provide input for " + inputArr[0] + " command!\n");
            } catch (WoutException e) {
                Ui.printMessage(e.toString());
            }
        }
    }
}
