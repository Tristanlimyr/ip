package wout.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    // Used GitHub Copilot to generate test cases for Parser class
    @Test
    public void parseEvent_success() throws WoutException {
        assertEquals("Add [E][ ] party (from: Jan 31 2026, 2:00 pm to: Jan 31 2026, 10:00 pm)",
                Parser.parse("event party /from 2026-01-31 14:00 /to 2026-01-31 22:00").toString());
    }

    @Test
    public void parseEvent_missingTo() {
        assertThrows(WoutException.class, () -> Parser.parse("event party /from 2026-01-31 14:00"));
    }

    @Test
    public void parseEvent_invalidDateTime() {
        assertThrows(WoutException.class, () -> Parser.parse(
                "event party /from 2026-01-31 2100 /to 2026-01-31 22:00"));
    }

    @Test
    public void parseEvent_duplicateFrom() {
        assertThrows(WoutException.class, () -> Parser.parse(
                "event party /from 2026-01-31 14:00 /from 2026-01-31 15:00 /to 2026-01-31 22:00"));
    }

    @Test
    public void parseTodo_success() throws WoutException {
        assertEquals("Add [T][ ] read book", Parser.parse("todo read book").toString());
    }

    @Test
    public void parseTodo_noDescription() {
        assertThrows(WoutException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parseDeadline_success() throws WoutException {
        assertEquals("Add [D][ ] submit assignment (by: Feb 1 2026, 11:59 pm)",
                Parser.parse("deadline submit assignment /by 2026-02-01 23:59").toString());
    }

    @Test
    public void parseDeadline_noBy() {
        assertThrows(WoutException.class, () -> Parser.parse("deadline submit assignment"));
    }

    @Test
    public void parseDeadline_invalidDateTime() {
        assertThrows(WoutException.class, () -> Parser.parse("deadline submit assignment /by 2026-02-01 2300"));
    }

    @Test
    public void parseDeadline_duplicateBy() {
        assertThrows(WoutException.class, () -> Parser.parse(
                "deadline submit /by 2026-02-01 23:59 /by 2026-02-02 23:59"));
    }

    @Test
    public void parseMark_success() throws WoutException {
        assertEquals("Mark task at index 1", Parser.parse("mark 1").toString());
    }

    @Test
    public void parseMark_nonInteger() {
        assertThrows(WoutException.class, () -> Parser.parse("mark a"));
    }

    @Test
    public void parseMark_noIndex() {
        assertThrows(WoutException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void parseUnmark_success() throws WoutException {
        assertEquals("Unmark task at index 2", Parser.parse("unmark 2").toString());
    }

    @Test
    public void parseUnmark_nonInteger() {
        assertThrows(WoutException.class, () -> Parser.parse("unmark x"));
    }

    @Test
    public void parseUnmark_noIndex() {
        assertThrows(WoutException.class, () -> Parser.parse("unmark"));
    }

    @Test
    public void parseDelete_success() throws WoutException {
        assertEquals("Delete task at index 1", Parser.parse("delete 1").toString());
    }

    @Test
    public void parseDelete_nonInteger() {
        assertThrows(WoutException.class, () -> Parser.parse("delete a"));
    }

    @Test
    public void parseDelete_noIndex() {
        assertThrows(WoutException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void parseFind_success() throws WoutException {
        assertEquals("Find book", Parser.parse("find book").toString());
    }

    @Test
    public void parseExit_success() throws WoutException {
        assertEquals("Exit", Parser.parse("bye").toString());
    }

    @Test
    public void parseList_success() throws WoutException {
        assertEquals("List", Parser.parse("list").toString());
    }

    @Test
    public void parseUndo_success() throws WoutException {
        assertEquals("Undo last command", Parser.parse("undo").toString());
    }

    @Test
    public void parse_emptyInput() {
        assertThrows(WoutException.class, () -> Parser.parse(""));
    }
}

