package wout.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseEvent_success() {
        try {
            assertEquals("Add [E][ ] party (from: Jan 31 2026, 2:00 pm to: Jan 31 2026, 10:00 pm)",
                    Parser.parse("event party /from 2026-01-31 14:00 /to 2026-01-31 22:00").toString());
        } catch (WoutException e) {
            fail();
        }
    }

    @Test
    public void parseEvent_fail() {
        try {
            Parser.parse("event party /from 2026-01-31 14:00 to 2026-01-31 22:00");
            fail();
        } catch (WoutException e) {
            assertEquals("Please provide a valid input for Event tasks", e.toString());
        }
    }
}
