import java.time.format.DateTimeFormatter;

public class Ui {
    public static final String GREET = "Hello! I'm Wout!\n"
            + "What can I do for you?\n";
    public static final String EXIT = "Bye. Hope to see you again soon!\n";
    public static final String INVALID_COMMAND = "Please enter a valid command\n";
    public static final String INVALID_DATE_TIME = "Please provide date and time in \"yyyy-mm-dd HH:mm\" format!\n";
    public static final DateTimeFormatter DATE_TIME_DISPLAY = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    public static final DateTimeFormatter DATE_TIME_ENTRY = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
