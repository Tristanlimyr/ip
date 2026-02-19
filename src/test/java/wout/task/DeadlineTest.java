package wout.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    // Used GitHub Copilot to generate test cases for Deadline class
    @Test
    public void copy_createsDeepCopy() {
        LocalDateTime deadline = LocalDateTime.of(2026, 2, 1, 23, 59);
        Deadline original = new Deadline("submit", deadline, false);
        Task copy = original.copy();

        assertEquals(original.getDescription(), copy.getDescription());
        assertEquals(original.getIsDone(), copy.getIsDone());

        // Modify the copy and verify original is unchanged
        copy.markAsDone();
        assertFalse(original.getIsDone());
        assertTrue(copy.getIsDone());
    }
}
