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
    private static TaskList tasks;
    private static final String FILE_PATH = "./data/wout.txt";
    private static final Storage storage = new Storage(FILE_PATH);

    private static String doMarkTaskCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = tasks.markTaskAt(index);
            return Ui.markTaskMessage(task, tasks);
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for marking\n");
        }
    }

    private static String doUnmarkTaskCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = tasks.unmarkTaskAt(index);
            return Ui.unmarkTaskMessage(task, tasks);
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
            tasks.storeTask(todo);
            return Ui.addTaskMessage(todo, tasks);
        }
    }

    private static String doAddTodoCommand(String input) throws WoutException {
        return doAddTodoCommand(input, false);
    }

    private static String doAddDeadlineCommand(String input, boolean isDone) throws WoutException {
        Matcher matcher = Pattern.compile(Ui.DEADLINE_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Deadline tasks\n");
        } else {
            try {
                LocalDateTime by = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                Task deadline = new Deadline(matcher.group(1), by, isDone);
                tasks.storeTask(deadline);
                return Ui.addTaskMessage(deadline, tasks);
            } catch (DateTimeParseException e) {
                throw new WoutException(Ui.INVALID_DATE_TIME);
            }
        }
    }

    private static String doAddDeadlineCommand(String input) throws WoutException {
        return doAddDeadlineCommand(input, false);
    }

    private static String doAddEventCommand(String input, boolean isDone) throws WoutException {
        Matcher matcher = Pattern.compile(Ui.EVENT_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Event tasks\n");
        } else {
            try {
                LocalDateTime from = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                LocalDateTime to = LocalDateTime.parse(matcher.group(3), Ui.DATE_TIME_ENTRY);
                Task event = new Event(matcher.group(1), from, to, isDone);
                tasks.storeTask(event);
                return Ui.addTaskMessage(event, tasks);
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
            Task task = tasks.deleteTaskAt(index);
            return Ui.deleteTaskMessage(task, tasks);
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for deleting\n");
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            tasks = new TaskList(storage.load());
        } catch (WoutException e) {
            Ui.printWoutException(e);
            tasks = new TaskList();
        }
        Ui.printGreeting();

        boolean exit = false;
        String msg;
        Keyword keyword;
        while (!exit) {
            String input = Wout.userInputScanner.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            try {
                keyword = Keyword.fromString(inputArr[0]);
                msg = switch (keyword) {
                    case EXIT -> {
                        exit = true;
                        yield Ui.EXIT;
                    }
                    case LIST -> tasks.listTasks();
                    case MARK -> doMarkTaskCommand(inputArr[1]);
                    case UNMARK -> doUnmarkTaskCommand(inputArr[1]);
                    case TODO -> doAddTodoCommand(inputArr[1]);
                    case DEADLINE -> doAddDeadlineCommand(inputArr[1]);
                    case EVENT -> doAddEventCommand(inputArr[1]);
                    case DELETE -> doDeleteCommand(inputArr[1]);
                };
                Ui.printMessage(msg);
                storage.store(tasks.getTasks());
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printMessage("Please provide input for " + inputArr[0] + " command!\n");
            } catch (WoutException e) {
                Ui.printMessage(e.toString());
            }
        }
    }
}
