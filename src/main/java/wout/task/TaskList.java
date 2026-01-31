package wout.task;

import java.util.ArrayList;
import java.util.List;

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

    public Task markTaskAt(int index) {
        Task task = listOfTasks.get(index - 1);
        task.markAsDone();
        return task;
    }

    public Task unmarkTaskAt(int index) {
        Task task = listOfTasks.get(index - 1);
        task.unmarkAsDone();
        return task;
    }

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
