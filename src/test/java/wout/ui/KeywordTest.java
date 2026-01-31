package wout.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class KeywordTest {

    @Test
    public void fromString_invalidKeyword() {
        try {
            Keyword.fromString("Mark");
            fail();
        } catch (WoutException e) {
            assertEquals(Ui.INVALID_COMMAND, e.toString());
        }
        try {
            Keyword.fromString("Unmark");
            fail();
        } catch (WoutException e) {
            assertEquals(Ui.INVALID_COMMAND, e.toString());
        }
        try {
            Keyword.fromString("Todo");
            fail();
        } catch (WoutException e) {
            assertEquals(Ui.INVALID_COMMAND, e.toString());
        }
        try {
            Keyword.fromString("Deadline");
            fail();
        } catch (WoutException e) {
            assertEquals(Ui.INVALID_COMMAND, e.toString());
        }
        try {
            Keyword.fromString("Event");
            fail();
        } catch (WoutException e) {
            assertEquals(Ui.INVALID_COMMAND, e.toString());
        }
    }
}
