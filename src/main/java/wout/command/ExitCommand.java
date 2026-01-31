package wout.command;

import wout.ui.Storage;
import wout.task.TaskList;
import wout.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }
}
