import java.util.ArrayList;
import java.util.List;

public class UserCommandsStore {

    private List<String> listOfCommands;

    public UserCommandsStore() {
        this.listOfCommands = new ArrayList<String>();
    }

    public void storeCommand(String command) {
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
