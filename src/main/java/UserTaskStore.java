import java.util.ArrayList;
import java.util.List;

public class UserTaskStore {

    private List<Task> listOfTasks;

    public UserTaskStore() {
        this.listOfTasks = new ArrayList<Task>();
    }

    public void storeTask(Task task) {
        listOfTasks.add(task);
    }

    public String markTaskAt(int index) {
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            return "Nice! I've marked this task as done:\n"
                    + "  " + task + "\n";
        } catch (IndexOutOfBoundsException e) {
            return "Index " + index + " is not valid!\n";
        }
    }

    public String unmarkTaskAt(int index) {
        try {
            Task task = listOfTasks.get(index - 1);
            task.unmarkAsDone();
            return "Ok, I've marked this task as not done yet:\n"
                    + "  " + task + "\n";
        } catch (IndexOutOfBoundsException e) {
            return "Index " + index + " is not valid!\n";
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
}
