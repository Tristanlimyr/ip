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
     * Find tasks in task list.
     *
     * @param tasks task list from which tasks are found.
     * @param ui Ui to display user message containing tasks found.
     * @param storage Storage will not be used.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(ui.findTaskMessage(tasks.findTasks(keyword)));
    }

    @Override
    public String toString() {
        return "Find " + this.keyword;
    }
}
