package wout.task;

import java.time.LocalDateTime;

import wout.ui.Ui;

/**
 * Represents a task that needs to be done by a certain date and time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * @param description Description of deadline.
     * @param by Date and time by which the task must be completed.
     * @param isDone Whether the deadline is completed.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructs deadline which is not done.
     *
     * @param description Description of deadline.
     * @param by Date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Ui.DATE_TIME_DISPLAY) + ")";
    }

    @Override
    public String toEntry() {
        return super.toEntry() + " # deadline "
                + getDescription() + " /by " + by.format(Ui.DATE_TIME_ENTRY);
    }
}
