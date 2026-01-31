package wout.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public void storeTask(Task task) {
        tasks.add(task);
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public Task markTaskAt(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        return task;
    }

    public Task unmarkTaskAt(int index) {
        Task task = tasks.get(index - 1);
        task.unmarkAsDone();
        return task;
    }

    public Task deleteTaskAt(int index) {
        return tasks.remove(index - 1);
    }

    public String listTasks() {
        String tasks = "Here are the tasks in your list:\n";
        int index = 1;
        if (this.tasks.isEmpty()) {
            return "list is currently empty!\n";
        } else {
            for (Task task : this.tasks) {
                tasks += index + ". " + task + "\n";
                index++;
            }
            return tasks;
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
