package wout.ui;

import wout.command.*;
import wout.task.Deadline;
import wout.task.Event;
import wout.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final String DEADLINE_REGEX =
            "^(?!.*\\/by.*\\/by)(.+?)\\s+\\/by\\s+(.+)$";
    public static final String EVENT_REGEX =
            "^(?!.*\\/from.*\\/from)(?!.*\\/to.*\\/to)(.+?)\\s+\\/from\\s+(.+?)\\s+\\/to\\s+(.+)$";

    private static Command parseMark(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for marking\n");
        }
    }

    private static Command parseUnmark(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for unmarking\n");
        }
    }

    private static Command parseTodo(String input) throws WoutException {
        if (input.isEmpty()) {
            throw new WoutException("Please provide a description for wout.task.Todo tasks\n");
        } else {
            return new AddCommand(new Todo(input));
        }
    }

    private static Command parseDeadline(String input) throws WoutException {
        Matcher matcher = Pattern.compile(DEADLINE_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for wout.task.Deadline tasks\n");
        } else {
            try {
                LocalDateTime by = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                return new AddCommand(new Deadline(matcher.group(1), by));
            } catch (DateTimeParseException e) {
                throw new WoutException(Ui.INVALID_DATE_TIME);
            }
        }
    }

    private static Command parseEvent(String input) throws WoutException {
        Matcher matcher = Pattern.compile(EVENT_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for wout.task.Event tasks\n");
        } else {
            try {
                LocalDateTime from = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                LocalDateTime to = LocalDateTime.parse(matcher.group(3), Ui.DATE_TIME_ENTRY);
                return new AddCommand(new Event(matcher.group(1), from, to));
            } catch (DateTimeParseException e) {
                throw new WoutException(Ui.INVALID_DATE_TIME);
            }
        }
    }

    private static Command parseDelete(String input) throws WoutException {
        try {
            int index = Integer.parseInt(input);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new WoutException(input + " is not number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException("Index " + input + " is not a valid range for deleting\n");
        }
    }

    public static Command parse(String fullCommand) throws WoutException {
        String[] commandArr = fullCommand.split("\\s+", 2);
        Keyword keyword = Keyword.fromString(commandArr[0]);
        try {
            return switch (keyword) {
            case EXIT -> new ExitCommand();
            case LIST -> new ListCommand();
            case MARK -> parseMark(commandArr[1]);
            case UNMARK -> parseUnmark(commandArr[1]);
            case TODO -> parseTodo(commandArr[1]);
            case DEADLINE -> parseDeadline(commandArr[1]);
            case EVENT -> parseEvent(commandArr[1]);
            case DELETE -> parseDelete(commandArr[1]);
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WoutException("Please provide an input for " + keyword + "\n");
        }
    }
}