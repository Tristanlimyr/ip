public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WoutException {
        tasks.storeTask(task);
        storage.store(tasks.getTasks());
        ui.printMessage(ui.addTaskMessage(task, tasks));
    }
}
