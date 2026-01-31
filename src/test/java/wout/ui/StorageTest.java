package wout.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Storage {
    @Test
    public void load_fileNotFound() {
        assertEquals(true, new Storage("file.txt").load());
    }
}
