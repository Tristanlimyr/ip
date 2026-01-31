package wout.task;

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
     */
    public Task markTaskAt(int index) {
        Task task = listOfTasks.get(index - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks task as not done and returns marked task.
     *
     * @param index Index of task to be marked.
     * @return Marked task.
     */
    public Task unmarkTaskAt(int index) {
        Task task = listOfTasks.get(index - 1);
        task.unmarkAsDone();
        return task;
    }

    /**
     * Deletes task from list.
     *
     * @param index Index of task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTaskAt(int index) {
        return listOfTasks.remove(index - 1);
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
