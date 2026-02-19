package wout.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    // Used GitHub Copilot to generate test cases for Todo class
    @Test
    public void copy_createsDeepCopy() {
        Todo original = new Todo("read book", false);
        Task copy = original.copy();

        assertEquals(original.getDescription(), copy.getDescription());
        assertEquals(original.getIsDone(), copy.getIsDone());

        assertNotSame(original, copy);
        // Modify the copy and verify original is unchanged
        copy.markAsDone();
        assertFalse(original.getIsDone());
        assertTrue(copy.getIsDone());
    }
}
