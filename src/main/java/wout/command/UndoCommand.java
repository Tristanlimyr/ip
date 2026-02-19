package wout.command;

import java.util.List;

import wout.task.Task;
import wout.task.TaskList;
import wout.ui.Storage;
import wout.ui.Ui;
import wout.ui.WoutException;

/**
 * Represents a user request to undo command
 */
public class UndoCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Undoes the last command that modifies the task list, displays user message and stores updated task list in file.
     *
     * @param taskList task list to be modified.
     * @param ui Ui to display user message.
     * @param storage Storage to store updated task list in file.
     * @return message generated from undoing the last command.
     * @throws WoutException If there is an issue with writing to file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WoutException {
        taskList.undo();
        List<Task> tasks = taskList.getTasks();
        storage.writeTasksToFile(taskList);
        String message = ui.listTaskMessage(tasks);
        ui.printMessage(message);
        return message;
    }
    @Override
    public String toString() {
        return "Undo last command";
    }
}
