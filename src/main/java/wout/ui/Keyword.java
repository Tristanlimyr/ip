package wout.ui;

/**
 * Represents all possible keywords that a user can use to create a command.
 */
public enum Keyword {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    UNDO("undo");

    private final String keyword;

    Keyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the keyword corresponding to the input string.
     *
     * @param keyword String corresponding to a keyword.
     * @return keyword representing the input string.
     * @throws WoutException If the keyword given is not a valid one.
     */
    public static Keyword fromString(String keyword) throws WoutException {
        for (Keyword k : Keyword.values()) {
            if (k.keyword.equals(keyword)) {
                return k;
            }
        }
        throw new WoutException(Ui.INVALID_COMMAND);
    }
}

