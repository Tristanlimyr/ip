package wout.command;

import wout.task.Task;
import wout.task.TaskList;
import wout.ui.Storage;
import wout.ui.Ui;
import wout.ui.WoutException;

/**
 * Represents a user request to add a task to task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * @param task Task to be added to task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds task to task list, displays user message and stores updated task list in file.
     *
     * @param taskList task list to be added to.
     * @param ui Ui to display user message.
     * @param storage Storage to store updated task list in file.
     * @return message generated from adding the task.
     * @throws WoutException If there is an issue with writing to file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WoutException {
        taskList.addTask(task);
        storage.writeTasksToFile(taskList);
        String message = ui.addTaskMessage(task, taskList);
        ui.printMessage(message);
        return message;
    }

    @Override
    public String toString() {
        return "Add " + task.toString();
    }
}
