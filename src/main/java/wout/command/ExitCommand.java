package wout.command;

import wout.task.TaskList;
import wout.ui.Storage;
import wout.ui.Ui;

/**
 * Represents a user request to exit from Wout.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Displays exit message.
     *
     * @return exit message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String message = ui.getExitMessage();
        ui.printMessage(message);
        return message;
    }
}
