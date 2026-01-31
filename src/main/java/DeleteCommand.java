public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WoutException {
        Task task = tasks.deleteTaskAt(this.taskIndex);
        storage.store(tasks.getTasks());
        ui.printMessage(ui.deleteTaskMessage(task, tasks));
    }
}
