package wout.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class KeywordTest {
    @Test
    public void fromString_caseSensitive() {
        assertThrows(WoutException.class, () -> Keyword.fromString("Bye"));
        assertThrows(WoutException.class, () -> Keyword.fromString("Mark"));
        assertThrows(WoutException.class, () -> Keyword.fromString("Unmark"));
        assertThrows(WoutException.class, () -> Keyword.fromString("Todo"));
        assertThrows(WoutException.class, () -> Keyword.fromString("Deadline"));
        assertThrows(WoutException.class, () -> Keyword.fromString("Event"));
        assertThrows(WoutException.class, () -> Keyword.fromString("Delete"));
        assertThrows(WoutException.class, () -> Keyword.fromString("Find"));
        assertThrows(WoutException.class, () -> Keyword.fromString("Undo"));
    }

    // Used GitHub Copilot to generate this test method.
    @Test
    public void fromString_validKeywords() throws WoutException {
        assertEquals(Keyword.EXIT, Keyword.fromString("bye"));
        assertEquals(Keyword.LIST, Keyword.fromString("list"));
        assertEquals(Keyword.MARK, Keyword.fromString("mark"));
        assertEquals(Keyword.UNMARK, Keyword.fromString("unmark"));
        assertEquals(Keyword.TODO, Keyword.fromString("todo"));
        assertEquals(Keyword.DEADLINE, Keyword.fromString("deadline"));
        assertEquals(Keyword.EVENT, Keyword.fromString("event"));
        assertEquals(Keyword.DELETE, Keyword.fromString("delete"));
        assertEquals(Keyword.FIND, Keyword.fromString("find"));
        assertEquals(Keyword.UNDO, Keyword.fromString("undo"));
    }

    @Test
    public void fromString_emptyString() {
        assertThrows(WoutException.class, () -> Keyword.fromString(""));
    }

    @Test
    public void fromString_nullString() {
        assertThrows(WoutException.class, () -> Keyword.fromString(null));
    }

}
