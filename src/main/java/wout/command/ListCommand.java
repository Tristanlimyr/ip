package wout.command;

import wout.task.TaskList;
import wout.ui.Storage;
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
     * Lists tasks in task list, displays user message.
     *
     * @param tasks task list to be listed.
     * @param ui Ui to display user message containing listed tasks.
     * @return message generated from listing tasks.
     * @param storage Storage will not be used.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = ui.listTaskMessage(tasks.getTasks());
        ui.printMessage(message);
        return message;
    }
}
