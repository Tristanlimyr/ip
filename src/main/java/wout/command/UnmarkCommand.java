package wout.command;

import wout.task.Task;
import wout.task.TaskList;
import wout.ui.Storage;
import wout.ui.Ui;
import wout.ui.WoutException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WoutException {
        Task task = tasks.unmarkTaskAt(taskIndex);
        storage.store(tasks.getTasks());
        ui.printMessage(ui.unmarkTaskMessage(task, tasks));
    }
}
