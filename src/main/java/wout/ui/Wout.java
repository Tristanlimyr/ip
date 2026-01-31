package wout.ui;

import wout.command.Command;
import wout.task.TaskList;

import java.io.IOException;

public class Wout {
    private static final String FILE_PATH = "./data/wout.txt";
    private static final Storage storage = new Storage(FILE_PATH);
    private static final Ui ui = new Ui();

    public static void main(String[] args) throws IOException {
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (WoutException e) {
            ui.printWoutException(e);
            tasks = new TaskList();
        }
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (WoutException e) {
                ui.printMessage(e.toString());
            }
        }
        ui.printExit();
    }
}
