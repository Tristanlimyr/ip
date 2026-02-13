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

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WoutException {
        taskList.undo();
        List<Task> tasks = taskList.getTasks();
        storage.writeTasksToFile(taskList);
        String message = ui.listTaskMessage(tasks);
        ui.printMessage(message);
        return message;
    }
}
