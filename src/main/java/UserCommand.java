public enum UserCommand {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String command;

    UserCommand(String command) {
        this.command = command;
    }

    public static UserCommand fromString(String command) throws WoutException {
        for (UserCommand userCommand : UserCommand.values()) {
            if (userCommand.command.equals(command)) {
                return userCommand;
            }
        }
        throw new WoutException(Ui.INVALID_COMMAND);
    }
}

