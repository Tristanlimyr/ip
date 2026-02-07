package wout.command;

import wout.task.Task;
import wout.task.TaskList;
import wout.ui.Storage;
import wout.ui.Ui;
import wout.ui.WoutException;

/**
 * Represents a user request to mark a task in task list as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * @param taskIndex Index of task in task list to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks task in tasks list as done, displays user message and stores updated task list in file.
     *
     * @param tasks task list containing task to be marked.
     * @param ui Ui to display user message.
     * @param storage Storage to store updated task list in file.
     * @return message generated from marking the task.
     * @throws WoutException If there is an issue with writing to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WoutException {
        Task task = tasks.markTaskAt(taskIndex);
        storage.writeTasksToFile(tasks.getTasks());
        String message = ui.markTaskMessage(task, tasks);
        ui.printMessage(message);
        return message;
    }

    @Override
    public String toString() {
        return "Mark task at index " + taskIndex;
    }
}
