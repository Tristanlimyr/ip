package wout.ui;

import javafx.application.Platform;
import wout.command.Command;
import wout.task.TaskList;

public class Wout {
    private static final String FILE_PATH = "./data/wout.txt";
    private final Storage storage = new Storage(FILE_PATH);
    private final Ui ui = new Ui();
    private TaskList tasks;

    public Wout() {
        try {
            tasks = new TaskList(storage.load());
        } catch (WoutException e) {
            ui.printWoutException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.isExit()) {
                Platform.exit();
            }
            return command.execute(tasks, ui, storage);
        } catch (WoutException e) {
            return e.toString();
        }
    }

    public String getGreeting() {
        return ui.getGreetingMessage();
    }
}
