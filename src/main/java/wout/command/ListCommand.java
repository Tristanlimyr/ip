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
     * @param taskList task list to be listed.
     * @param ui Ui to display user message containing listed tasks.
     * @param storage Storage will not be used.
     * @return message generated from listing tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String message = ui.listTaskMessage(taskList.getTasks());
        ui.printMessage(message);
        return message;
    }
    @Override
    public String toString() {
        return "List";
    }
}
