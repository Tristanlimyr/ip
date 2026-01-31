package wout.command;

import wout.ui.Storage;
import wout.task.TaskList;
import wout.ui.Ui;
import wout.ui.WoutException;

/**
 * Represents a user request to perform certain operations.
 * For example, adding tasks, listing tasks, deleting tasks and marking/unmarking tasks.
 */
public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws WoutException;

}
