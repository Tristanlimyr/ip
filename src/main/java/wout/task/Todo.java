package wout.task;

/**
 * Represents a task that needs to be done.
 */
public class Todo extends Task {

    /**
     * @param description Description of todo item.
     * @param isDone Whether the todo item is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @param description Description of todo item.
     */
    public Todo(String description) {
        this(description, false);
    }

    @Override
    public Task copy() {
        return new Todo(super.getDescription(), getIsDone());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toEntry() {
        return super.toEntry() + " # todo " + getDescription();
    }

}
