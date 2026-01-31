package wout.command;

import wout.task.Task;
import wout.ui.Storage;
import wout.task.TaskList;
import wout.ui.Ui;

import java.util.List;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(ui.listTaskMessage(tasks.getTasks()));
    }
}
