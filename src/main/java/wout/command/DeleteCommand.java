package wout.command;

import wout.task.Task;
import wout.task.TaskList;
import wout.ui.Storage;
import wout.ui.Ui;
import wout.ui.WoutException;

/**
 * Represents a user request to delete a task from task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * @param taskIndex Index of task in task list to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes task from task list, displays user message and stores updated task list in file.
     * @param tasks task list to be deleted from.
     * @param ui Ui to display user message.
     * @param storage Storage to store updated task list in file.
     * @throws WoutException If there is an issue with writing to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WoutException {
        Task task = tasks.deleteTaskAt(this.taskIndex);
        storage.store(tasks.getTasks());
        ui.printMessage(ui.deleteTaskMessage(task, tasks));
    }

    @Override
    public String toString() {
        return "Delete task at index " + taskIndex;
    }
}
