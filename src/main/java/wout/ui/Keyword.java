package wout.ui;

public enum Keyword {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find");

    private final String keyword;

    Keyword(String keyword) {
        this.keyword = keyword;
    }

    public static Keyword fromString(String keyword) throws WoutException {
        for (Keyword k : Keyword.values()) {
            if (k.keyword.equals(keyword)) {
                return k;
            }
        }
        throw new WoutException(Ui.INVALID_COMMAND);
    }
}

