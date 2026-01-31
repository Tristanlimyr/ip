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
}
