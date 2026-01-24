import java.util.ArrayList;
import java.util.List;

public class UserTaskStore {

    private List<Task> listOfCommands;

    public UserTaskStore() {
        this.listOfCommands = new ArrayList<Task>();
    }

    public void storeTask(Task command) {
        listOfCommands.add(command);
    }

    public String listCommands() {
        String commands = "";
        int index = 1;
        if (listOfCommands.isEmpty()) {
            return "list is currently empty!";
        } else {
            for (String command : listOfCommands) {
                commands += index + ". " + command + "\n";
                index++;
            }
            return commands;
        }
    }
}
