import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(UserMessages.DATE_TIME_DISPLAY) + ")";
    }

    @Override
    public String toEntry() {
        return super.toEntry() + " # deadline "
                + description + " /by " + by.format(UserMessages.DATE_TIME_ENTRY);
    }
}
