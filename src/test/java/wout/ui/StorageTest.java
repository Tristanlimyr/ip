package wout.ui;

import org.junit.jupiter.api.Test;
import wout.task.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void load_fileNotFound() {
        try {
            List< Task> tasks = new Storage("file.txt").load();
            assertEquals(true, tasks.isEmpty());
        } catch (WoutException e) {
            fail();
        }
    }
}
