import java.time.format.DateTimeFormatter;

public class Ui {
    public static final String GREET = "Hello! I'm Wout!\n"
            + "What can I do for you?\n";
    public static final String EXIT = "Bye. Hope to see you again soon!\n";
    public static final String INVALID_COMMAND = "Please enter a valid command\n";
    public static final String INVALID_DATE_TIME = "Please provide date and time in \"yyyy-mm-dd HH:mm\" format!\n";
    public static final DateTimeFormatter DATE_TIME_DISPLAY = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    public static final DateTimeFormatter DATE_TIME_ENTRY = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void printMessage(String message) {
        System.out.println("________________________________\n"
                + message
                + "________________________________\n"
        );
    }

    public static void printGreeting() {
        printMessage(GREET);
    }

    public static String addTaskMessage(Task task, TaskList userTaskStore) {
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
    }

    public static String markTaskMessage(Task task, TaskList userTaskStore) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task + "\n"
                + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
    }

    public static String unmarkTaskMessage(Task task, TaskList userTaskStore) {
        return "Ok, I've marked this task as not done yet:\n"
                + "  " + task + "\n"
                + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
    }

    public static String deleteTaskMessage(Task task, TaskList userTaskStore) {
        return "Noted. I've remove this task:\n"
                + "  " + task + "\n"
                + "Now you have " + userTaskStore.getNumOfTasks() + " tasks in the list.\n";
    }

    public static void printWoutException(WoutException e) {
        printMessage(e.toString());
    }
}
