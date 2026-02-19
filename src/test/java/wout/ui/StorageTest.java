package wout.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wout.task.Deadline;
import wout.task.Event;
import wout.task.Task;
import wout.task.TaskList;
import wout.task.Todo;

public class StorageTest {
    private static final String TEST_FILE = "./data/test_storage.txt";
    private Storage storage;

    // Used GitHub Copilot to generate test cases for Storage class.
    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE);
        // Clean up test file if it exists
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    public void tearDown() {
        // Clean up test file after each test
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    private void createEmptyFile() throws WoutException {
        try {
            FileWriter fileWriter = new FileWriter(TEST_FILE);
            fileWriter.close();
        } catch (IOException e) {
            throw new WoutException(e.toString());
        }
    }

    private void writeToFile(String input) {
        try {
            FileWriter fileWriter = new FileWriter(TEST_FILE);
            fileWriter.write(input);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readTasksFromFile_fileNotFound() throws WoutException {
        List<Task> tasks = storage.readTasksFromFile();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void readTasksFromFile_emptyFile() throws WoutException {
        createEmptyFile();
        List<Task> tasks = storage.readTasksFromFile();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void readTasksFromFile_singleTodo() throws WoutException {
        writeToFile("0 # todo read book");
        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(1, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));
        assertEquals("read book", tasks.get(0).getDescription());
        assertFalse(tasks.get(0).getIsDone());
    }

    @Test
    public void readTasksFromFile_singleDeadline() throws WoutException {
        writeToFile("0 # deadline submit paper /by 2026-02-01 23:59");
        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.get(0));
        assertEquals("submit paper", tasks.get(0).getDescription());
        assertFalse(tasks.get(0).getIsDone());
    }

    @Test
    public void readTasksFromFile_singleEvent() throws WoutException {
        writeToFile("0 # event party /from 2026-01-31 14:00 /to 2026-01-31 22:00");
        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(1, tasks.size());
        assertInstanceOf(Event.class, tasks.get(0));
        assertEquals("party", tasks.get(0).getDescription());
        assertFalse(tasks.get(0).getIsDone());
    }

    @Test
    public void readTasksFromFile_multipleTasks() throws WoutException {
        writeToFile("0 # todo read book\n"
                + "1 # deadline submit /by 2026-02-01 23:59\n"
                + "0 # event party /from 2026-01-31 14:00 /to 2026-01-31 22:00");
        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(3, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));
        assertInstanceOf(Deadline.class, tasks.get(1));
        assertInstanceOf(Event.class, tasks.get(2));
    }

    @Test
    public void readTasksFromFile_taskMarkedAsNotDone() throws WoutException {
        writeToFile("0 # todo read book");
        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(1, tasks.size());
        assertFalse(tasks.get(0).getIsDone());
    }

    @Test
    public void readTasksFromFile_taskMarkedAsDone() throws WoutException {
        writeToFile("1 # todo read book");
        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0).getIsDone());
    }

    @Test
    public void readTasksFromFile_invalidStatusFormat() {
        writeToFile("2 # todo read book");
        assertThrows(WoutException.class, storage::readTasksFromFile);
    }

    @Test
    public void readTasksFromFile_invalidKeyword() {
        writeToFile("0 # invalid read book");
        assertThrows(WoutException.class, storage::readTasksFromFile);
    }

    @Test
    public void readTasksFromFile_invalidDeadlineFormat() {
        writeToFile("0 # deadline submit");
        assertThrows(WoutException.class, storage::readTasksFromFile);
    }

    @Test
    public void readTasksFromFile_invalidEventFormat() {
        writeToFile("0 # event party /from 2026-01-31 14:00");
        assertThrows(WoutException.class, storage::readTasksFromFile);
    }

    @Test
    public void writeTasksToFile_emptyTaskList() throws WoutException {
        TaskList taskList = new TaskList();
        storage.writeTasksToFile(taskList);
        List<Task> tasks = storage.readTasksFromFile();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void writeTasksToFile_singleTodo() throws WoutException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book", false));
        storage.writeTasksToFile(taskList);
        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(1, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));
    }

    @Test
    public void writeTasksToFile_multipleTasks() throws WoutException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book", false));
        taskList.addTask(new Deadline("submit", LocalDateTime.of(2026, 2, 1, 23, 59), false));
        taskList.addTask(new Event("party", LocalDateTime.of(2026, 1, 31, 14, 0),
                LocalDateTime.of(2026, 1, 31, 22, 0), false));
        storage.writeTasksToFile(taskList);
        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(3, tasks.size());
    }

    @Test
    public void writeTasksToFile_overwriteExistingFile() throws WoutException {
        TaskList taskList1 = new TaskList();
        taskList1.addTask(new Todo("old task", false));
        storage.writeTasksToFile(taskList1);

        TaskList taskList2 = new TaskList();
        taskList2.addTask(new Todo("new task", false));
        storage.writeTasksToFile(taskList2);

        List<Task> tasks = storage.readTasksFromFile();
        assertEquals(1, tasks.size());
        assertEquals("new task", tasks.get(0).getDescription());
    }

}
