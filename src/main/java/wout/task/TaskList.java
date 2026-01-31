package wout.task;

import wout.ui.WoutException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks
 */
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

    /**
     * Marks task as done and returns marked task.
     *
     * @param index Index of task to be marked.
     * @return Marked task.
     * @throws WoutException If not a valid index.
     */
    public Task markTaskAt(int index) throws WoutException {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException(index + " is not a valid task index to be marked as done");
        }
    }

    /**
     * Marks task as not done and returns marked task.
     *
     * @param index Index of task to be marked.
     * @return Marked task.
     * @throws WoutException If not a valid index.
     */
    public Task unmarkTaskAt(int index) throws WoutException {
        try {
            Task task = tasks.get(index - 1);
            task.unmarkAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException(index + " is not a valid index to be marked as not done");
        }
    }

    /**
     * @param index Index of task to be deleted.
     * @return Deleted task.
     * @throws WoutException If not a valid index.
     */
    public Task deleteTaskAt(int index) throws WoutException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException(index + " is not a valid index to be deleted");
        }
    }

    public List<Task> findTasks(String description) {
        List<Task> tasksFound = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
