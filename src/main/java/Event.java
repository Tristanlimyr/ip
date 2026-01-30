import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, from, to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(UserMessages.DATE_TIME_DISPLAY)
                + " to: " + to.format(UserMessages.DATE_TIME_DISPLAY) + ")";
    }

    @Override
    public String toEntry() {
        return super.toEntry() + " # event " + description
                + " /from " + from.format(UserMessages.DATE_TIME_ENTRY) + " /to "
                + to.format(UserMessages.DATE_TIME_ENTRY);
    }
}
