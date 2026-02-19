package wout.task;

/**
 * Represents a task created by user.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * @param description Description of task.
     * @param isDone Whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns a deep copy of the task.
     */
    public abstract Task copy();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string, representing the task, that is ready to be written in file.
     *
     * @return a string representation of task
     */
    public String toEntry() {
        return (isDone ? "1" : "0");
    }
}
