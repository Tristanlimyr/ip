package wout.command;

import wout.ui.Storage;
import wout.task.TaskList;
import wout.ui.Ui;

/**
 * Represents a user request to list the tasks in task list.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Lists tasks in task list.
     *
     * @param tasks task list to be listed.
     * @param ui Ui to display user message containing listed tasks.
     * @param storage Storage will not be used.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(tasks.listTasks());
    }
}
