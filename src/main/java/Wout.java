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
    private static final Ui ui = new Ui();

    private static String doMarkTaskCommand(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            Task task = tasks.markTaskAt(index);
            return ui.markTaskMessage(task, tasks);
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
            return ui.unmarkTaskMessage(task, tasks);
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
            return ui.addTaskMessage(todo, tasks);
        }
    }

    private static String doAddTodoCommand(String input) throws WoutException {
        return doAddTodoCommand(input, false);
    }

    private static String doAddDeadlineCommand(String input, boolean isDone) throws WoutException {
        Matcher matcher = Pattern.compile(Parser.DEADLINE_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Deadline tasks\n");
        } else {
            try {
                LocalDateTime by = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                Task deadline = new Deadline(matcher.group(1), by, isDone);
                tasks.storeTask(deadline);
                return ui.addTaskMessage(deadline, tasks);
            } catch (DateTimeParseException e) {
                throw new WoutException(Ui.INVALID_DATE_TIME);
            }
        }
    }

    private static String doAddDeadlineCommand(String input) throws WoutException {
        return doAddDeadlineCommand(input, false);
    }

    private static String doAddEventCommand(String input, boolean isDone) throws WoutException {
        Matcher matcher = Pattern.compile(Parser.EVENT_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Event tasks\n");
        } else {
            try {
                LocalDateTime from = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                LocalDateTime to = LocalDateTime.parse(matcher.group(3), Ui.DATE_TIME_ENTRY);
                Task event = new Event(matcher.group(1), from, to, isDone);
                tasks.storeTask(event);
                return ui.addTaskMessage(event, tasks);
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
            return ui.deleteTaskMessage(task, tasks);
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
            ui.printWoutException(e);
            tasks = new TaskList();
        }
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (WoutException e) {
                ui.printMessage(e.toString());
            }
        }
        ui.printExit();
    }
}
