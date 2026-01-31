package wout.task;

import wout.ui.Wout;
import wout.ui.WoutException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks
 */
public class TaskList {

    private final List<Task> listOfTasks;

    public TaskList(List<Task> tasks) {
        this.listOfTasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public void storeTask(Task task) {
        listOfTasks.add(task);
    }

    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    /**
     * Marks task as done and returns marked task.
     *
     * @param index Index of task to be marked.
     * @return Marked task.
     * @throws WoutException if not a valid index.
     */
    public Task markTaskAt(int index) throws WoutException {
        try {
            Task task = listOfTasks.get(index - 1);
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
     * @throws WoutException if not a valid index.
     */
    public Task unmarkTaskAt(int index) throws WoutException {
        try {
            Task task = listOfTasks.get(index - 1);
            task.unmarkAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException(index + " is not a valid index to be marked as not done");
        }
    }

    /**
     * @param index Index of task to be deleted.
     * @return Deleted task.
     * @throws WoutException if not a valid index.
     */
    public Task deleteTaskAt(int index) throws WoutException {
        try {
            return listOfTasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException(index + " is not a valid index to be deleted");
        }
    }

    public String listTasks() {
        String tasks = "Here are the tasks in your list:\n";
        int index = 1;
        if (listOfTasks.isEmpty()) {
            return "list is currently empty!\n";
        } else {
            for (Task task : listOfTasks) {
                tasks += index + ". " + task + "\n";
                index++;
            }
            return tasks;
        }
    }

    public List<Task> getTasks() {
        return listOfTasks;
    }

}
