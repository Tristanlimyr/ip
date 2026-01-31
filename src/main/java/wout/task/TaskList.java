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

    public List<Task> findTasks(String description) {
        List<Task> tasksFound = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(description)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
