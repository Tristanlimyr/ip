package wout.command;

import wout.ui.Storage;
import wout.task.TaskList;
import wout.ui.Ui;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(tasks.listTasks());
    }
}
