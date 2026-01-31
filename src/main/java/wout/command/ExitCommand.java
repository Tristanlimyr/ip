package wout.command;

import wout.ui.Storage;
import wout.task.TaskList;
import wout.ui.Ui;

/**
 * Represents a user request to exit from Wout.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Does nothing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }
}
