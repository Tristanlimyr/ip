package wout.command;

import wout.task.TaskList;
import wout.ui.Storage;
import wout.ui.Ui;

/**
 * Represents a user request to find tasks in task list that contains certain keywords in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Find tasks in task list, displays user message.
     *
     * @param taskList task list from which tasks are found.
     * @param ui Ui to display user message containing tasks found.
     * @return message generated from finding the task.
     * @param storage Storage will not be used.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String message = ui.findTaskMessage(taskList.findTasks(keyword));
        ui.printMessage(message);
        return message;
    }

    @Override
    public String toString() {
        return "Find " + this.keyword;
    }
}
