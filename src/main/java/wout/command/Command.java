package wout.command;

import wout.ui.Storage;
import wout.task.TaskList;
import wout.ui.Ui;
import wout.ui.WoutException;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws WoutException;

}
