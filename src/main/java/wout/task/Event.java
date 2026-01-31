package wout.task;

import wout.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a task that takes place during a period of time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * @param description Description of event.
     * @param from Start date and time of event.
     * @param to End date and time of event.
     * @param isDone Whether the event is completed.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an event which is not done.
     * @param description Description of event.
     * @param from Start date and time of event.
     * @param to End date and time of event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, from, to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(Ui.DATE_TIME_DISPLAY)
                + " to: " + to.format(Ui.DATE_TIME_DISPLAY) + ")";
    }

    @Override
    public String toEntry() {
        return super.toEntry() + " # event " + getDescription()
                + " /from " + from.format(Ui.DATE_TIME_ENTRY) + " /to "
                + to.format(Ui.DATE_TIME_ENTRY);
    }
}
