package wout.command;

import wout.task.Task;
import wout.task.TaskList;
import wout.ui.Storage;
import wout.ui.Ui;
import wout.ui.WoutException;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WoutException {
        Task task = tasks.markTaskAt(taskIndex);
        storage.store(tasks.getTasks());
        ui.printMessage(ui.markTaskMessage(task, tasks));
    }

    @Override
    public String toString() {
        return "Mark task at index " + taskIndex;
    }
}
