package wout.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    // Used GitHub Copilot to generate test cases for Event class
    @Test
    public void copy_createsDeepCopy() {
        LocalDateTime from = LocalDateTime.of(2026, 1, 31, 14, 0);
        LocalDateTime to = LocalDateTime.of(2026, 1, 31, 22, 0);
        Event original = new Event("party", from, to, false);
        Event copy = (Event) original.copy();

        assertEquals(original.getDescription(), copy.getDescription());
        assertEquals(original.getIsDone(), copy.getIsDone());

        // Modify the copy and verify original is unchanged
        copy.markAsDone();
        assertFalse(original.getIsDone());
        assertTrue(copy.getIsDone());
    }
}
