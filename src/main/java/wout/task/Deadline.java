package wout.task;

import java.time.LocalDateTime;

import wout.ui.Ui;

/**
 * Represents a task that needs to be done by a certain date and time.
 */
public class Deadline extends Task {
    private final LocalDateTime byDateTime;

    /**
     * @param description Description of deadline.
     * @param byDateTime Date and time by which the task must be completed.
     * @param isDone Whether the deadline is completed.
     */
    public Deadline(String description, LocalDateTime byDateTime, boolean isDone) {
        super(description, isDone);
        this.byDateTime = byDateTime;
    }

    /**
     * Constructs deadline which is not done.
     *
     * @param description Description of deadline.
     * @param byDateTime Date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        this(description, byDateTime, false);
    }

    @Override
    public Task copy() {
        return new Deadline(super.getDescription(), byDateTime, super.getIsDone());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateTime.format(Ui.DATE_TIME_DISPLAY) + ")";
    }

    @Override
    public String toEntry() {
        return super.toEntry() + " # deadline "
                + getDescription() + " /by " + byDateTime.format(Ui.DATE_TIME_ENTRY);
    }
}
