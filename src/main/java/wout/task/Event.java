package wout.task;

import java.time.LocalDateTime;

import wout.ui.Ui;

/**
 * Represents a task that takes place during a period of time.
 */
public class Event extends Task {
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    /**
     * @param description Description of event.
     * @param fromDateTime Start date and time of event.
     * @param toDateTime End date and time of event.
     * @param isDone Whether the event is completed.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime, boolean isDone) {
        super(description, isDone);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Constructs an event which is not done.
     *
     * @param description Description of event.
     * @param fromDateTime Start date and time of event.
     * @param toDateTime End date and time of event.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this(description, fromDateTime, toDateTime, false);
    }

    @Override
    public Task copy() {
        return new Event(super.getDescription(), fromDateTime, toDateTime, super.getIsDone());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDateTime.format(Ui.DATE_TIME_DISPLAY)
                + " to: " + toDateTime.format(Ui.DATE_TIME_DISPLAY) + ")";
    }

    @Override
    public String toEntry() {
        return super.toEntry() + " # event " + getDescription()
                + " /from " + fromDateTime.format(Ui.DATE_TIME_ENTRY) + " /to "
                + toDateTime.format(Ui.DATE_TIME_ENTRY);
    }
}
