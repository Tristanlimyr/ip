import java.util.ArrayList;
import java.util.List;

public class UserCommandsStore {

    private List<String> listOfCommands;
    
    public UserCommandsStore() {
        this.listOfCommands = new ArrayList<String>();
    }

    public void addCommand(String command) {
        listOfCommands.add(command);
    }

    public String listCommands() {
        String commands = "";
        for (String command : listOfCommands) {
            commands += command + "\n";
        }
        return commands;
    }

}
