package wout.task;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description) {
        this(description, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toEntry() {
        return super.toEntry() + " # todo " + description;
    }

}
