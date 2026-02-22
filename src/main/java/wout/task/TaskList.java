package wout.task;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Locale;

import wout.ui.WoutException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private Deque<List<Task>> taskLists = new ArrayDeque<>();
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<Task>());
    }

    private List<Task> copy(List<Task> tasks) {
        List<Task> copiedTasks = new ArrayList<>();
        for (Task t : tasks) {
            copiedTasks.add(t.copy());
        }
        return copiedTasks;
    }

    private void commit() {
        taskLists.push(copy(tasks));
    }

    /**
     * Adds task to task list.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        commit();
        tasks.add(task);
    }

    /**
     * Marks task as done and returns marked task.
     *
     * @param index Index of task to be marked.
     * @return Marked task.
     * @throws WoutException If not a valid index.
     */
    public Task markTaskAt(int index) throws WoutException {
        commit();
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
        commit();
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
        commit();
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new WoutException(index + " is not a valid index to be deleted");
        }
    }

    /**
     * Returns a list of tasks that match description.
     *
     * @param description Description to be matched against.
     * @return list of tasks that matches.
     */
    public List<Task> findTasks(String description) {
        List<Task> tasksFound = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescriptionLowerCase = task.getDescription().toLowerCase(Locale.ROOT);
            String descriptionLowerCase = description.toLowerCase(Locale.ROOT);
            if (taskDescriptionLowerCase.contains(descriptionLowerCase)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    /**
     * Undoes latest modification made to the task list.
     * If no modifications made previously, nothing is done.
     */
    public void undo() {
        if (!taskLists.isEmpty()) {
            tasks = taskLists.pop();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

}
