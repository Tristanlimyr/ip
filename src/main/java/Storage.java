import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static boolean parseTaskDoneStatus(String isDone) throws WoutException {
        return switch (isDone) {
            case "1" -> true;
            case "0" -> false;
            default -> throw new WoutException(isDone + " is not a valid status\n");
        };
    }

    private Task doAddTodoCommand(String input, boolean isDone) throws WoutException {
        if (input.isEmpty()) {
            throw new WoutException("Please provide a description for Todo tasks\n");
        } else {
            return new Todo(input, isDone);
        }
    }

    private Task doAddDeadlineCommand(String input, boolean isDone) throws WoutException {
        Matcher matcher = Pattern.compile(Parser.DEADLINE_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Deadline tasks\n");
        } else {
            try {
                LocalDateTime by = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                return new Deadline(matcher.group(1), by, isDone);
            } catch (DateTimeParseException e) {
                throw new WoutException(Ui.INVALID_DATE_TIME);
            }
        }
    }

    private Task doAddEventCommand(String input, boolean isDone) throws WoutException {
        Matcher matcher = Pattern.compile(Parser.EVENT_REGEX).matcher(input);
        if (!matcher.matches()) {
            throw new WoutException("Please provide a valid input for Event tasks\n");
        } else {
            try {
                LocalDateTime from = LocalDateTime.parse(matcher.group(2), Ui.DATE_TIME_ENTRY);
                LocalDateTime to = LocalDateTime.parse(matcher.group(3), Ui.DATE_TIME_ENTRY);
                return new Event(matcher.group(1), from, to, isDone);
            } catch (DateTimeParseException e) {
                throw new WoutException(Ui.INVALID_DATE_TIME);
            }
        }
    }

    /**
     * Read tasks from file and return tasks read.
     * If file does not exist, return empty list.
     *
     * @return list of Task read from file
     */
    public List<Task> load() throws WoutException {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            List<Task> tasks = new ArrayList<>();
            Task task;
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    String[] inputArr = input.split("\\s+#\\s+");
                    boolean isDone = parseTaskDoneStatus(inputArr[0]);
                    inputArr = inputArr[1].split("\\s+", 2);
                    Keyword keyword = Keyword.fromString(inputArr[0]);
                    task = switch (keyword) {
                        case TODO -> doAddTodoCommand(inputArr[1], isDone);
                        case DEADLINE -> doAddDeadlineCommand(inputArr[1], isDone);
                        case EVENT -> doAddEventCommand(inputArr[1], isDone);
                        default -> throw new WoutException("\"" + input + "\" is not a valid entry in your file!\n");
                    };
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Write tasks in file by overwriting the file.
     * If file does not exist, create file.
     *
     * @param listOfTasks tasks to be written in file
     */
    public void store(List<Task> listOfTasks) throws WoutException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : listOfTasks) {
                fileWriter.write(task.toEntry() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new WoutException(e.toString());
        }
    }
}
