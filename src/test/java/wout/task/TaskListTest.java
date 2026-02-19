package wout.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wout.ui.WoutException;

public class TaskListTest {
    // Used GitHub Copilot to generate test cases for TaskList class
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void constructor_emptyList() {
        assertEquals(0, taskList.getNumOfTasks());
    }

    @Test
    public void constructor_withInitialTasks() {
        List<Task> initialTasks = new ArrayList<>();
        initialTasks.add(new Todo("read book"));
        initialTasks.add(new Todo("write essay"));
        TaskList list = new TaskList(initialTasks);
        assertEquals(2, list.getNumOfTasks());
    }

    @Test
    public void addTask_success() {
        taskList.addTask(new Todo("read book", false));
        taskList.addTask(new Todo("write essay", false));
        assertEquals(2, taskList.getNumOfTasks());
    }

    @Test
    public void markTaskAt_validIndex() throws WoutException {
        taskList.addTask(new Todo("read book", false));
        Task task = taskList.markTaskAt(1);
        assertTrue(task.getIsDone());
    }

    @Test
    public void markTaskAt_invalidIndexZero() {
        taskList.addTask(new Todo("read book", false));
        assertThrows(WoutException.class, () -> taskList.markTaskAt(0));
    }

    @Test
    public void markTaskAt_invalidIndexNegative() {
        taskList.addTask(new Todo("read book", false));
        assertThrows(WoutException.class, () -> taskList.markTaskAt(-1));
    }

    @Test
    public void markTaskAt_invalidIndexTooLarge() {
        taskList.addTask(new Todo("read book", false));
        assertThrows(WoutException.class, () -> taskList.markTaskAt(5));
    }

    @Test
    public void markTaskAt_emptyList() {
        assertThrows(WoutException.class, () -> taskList.markTaskAt(1));
    }

    @Test
    public void unmarkTaskAt_validIndex() throws WoutException {
        taskList.addTask(new Todo("read book", true));
        Task task = taskList.unmarkTaskAt(1);
        assertEquals(false, task.getIsDone());
    }

    @Test
    public void unmarkTaskAt_invalidIndexZero() {
        taskList.addTask(new Todo("read book", true));
        assertThrows(WoutException.class, () -> taskList.unmarkTaskAt(0));
    }

    @Test
    public void unmarkTaskAt_invalidIndexNegative() {
        taskList.addTask(new Todo("read book", true));
        assertThrows(WoutException.class, () -> taskList.unmarkTaskAt(-1));
    }

    @Test
    public void unmarkTaskAt_invalidIndexTooLarge() {
        taskList.addTask(new Todo("read book", true));
        assertThrows(WoutException.class, () -> taskList.unmarkTaskAt(5));
    }

    @Test
    public void unmarkTaskAt_emptyList() {
        assertThrows(WoutException.class, () -> taskList.unmarkTaskAt(1));
    }

    @Test
    public void deleteTaskAt_validIndex() throws WoutException {
        taskList.addTask(new Todo("read book", false));
        Task task = taskList.deleteTaskAt(1);
        assertEquals("read book", task.getDescription());
        assertEquals(0, taskList.getNumOfTasks());
    }

    @Test
    public void deleteTaskAt_multipleTasksDeleteFirst() throws WoutException {
        taskList.addTask(new Todo("task 1", false));
        taskList.addTask(new Todo("task 2", false));
        taskList.addTask(new Todo("task 3", false));
        taskList.deleteTaskAt(1);
        assertEquals(2, taskList.getNumOfTasks());
        assertEquals("task 2", taskList.getTasks().get(0).getDescription());
        assertEquals("task 3", taskList.getTasks().get(1).getDescription());
    }

    @Test
    public void deleteTaskAt_multipleTasksDeleteLast() throws WoutException {
        taskList.addTask(new Todo("task 1", false));
        taskList.addTask(new Todo("task 2", false));
        taskList.addTask(new Todo("task 3", false));
        taskList.deleteTaskAt(3);
        assertEquals(2, taskList.getNumOfTasks());
        assertEquals("task 1", taskList.getTasks().get(0).getDescription());
        assertEquals("task 2", taskList.getTasks().get(1).getDescription());
    }

    @Test
    public void deleteTaskAt_multipleTasksDeleteMiddle() throws WoutException {
        taskList.addTask(new Todo("task 1", false));
        taskList.addTask(new Todo("task 2", false));
        taskList.addTask(new Todo("task 3", false));
        taskList.deleteTaskAt(2);
        assertEquals(2, taskList.getNumOfTasks());
        assertEquals("task 1", taskList.getTasks().get(0).getDescription());
        assertEquals("task 3", taskList.getTasks().get(1).getDescription());
    }

    @Test
    public void deleteTaskAt_invalidIndexZero() {
        taskList.addTask(new Todo("read book", false));
        assertThrows(WoutException.class, () -> taskList.deleteTaskAt(0));
    }

    @Test
    public void deleteTaskAt_invalidIndexNegative() {
        taskList.addTask(new Todo("read book", false));
        assertThrows(WoutException.class, () -> taskList.deleteTaskAt(-1));
    }

    @Test
    public void deleteTaskAt_invalidIndexTooLarge() {
        taskList.addTask(new Todo("read book", false));
        assertThrows(WoutException.class, () -> taskList.deleteTaskAt(5));
    }

    @Test
    public void deleteTaskAt_emptyList() {
        assertThrows(WoutException.class, () -> taskList.deleteTaskAt(1));
    }

    @Test
    public void findTasks_multipleMatches() {
        taskList.addTask(new Todo("read book", false));
        taskList.addTask(new Todo("read notes", false));
        taskList.addTask(new Todo("write essay", false));
        List<Task> found = taskList.findTasks("read");
        assertEquals(2, found.size());
    }

    @Test
    public void findTasks_noMatches() {
        taskList.addTask(new Todo("read book", false));
        List<Task> found = taskList.findTasks("xyz");
        assertEquals(0, found.size());
    }

    @Test
    public void findTasks_emptyList() {
        List<Task> found = taskList.findTasks("read");
        assertEquals(0, found.size());
    }

    @Test
    public void findTasks_caseSensitiveContains() {
        taskList.addTask(new Todo("read BOOK", false));
        List<Task> found = taskList.findTasks("book");
        assertEquals(0, found.size());
    }

    @Test
    public void findTasks_partialMatch() {
        taskList.addTask(new Todo("reading book", false));
        List<Task> found = taskList.findTasks("read");
        assertEquals(1, found.size());
    }

    @Test
    public void undo_noModifications() {
        assertEquals(0, taskList.getNumOfTasks());
        taskList.undo();
        assertEquals(0, taskList.getNumOfTasks());
    }

    @Test
    public void undo_afterAddTask() throws WoutException {
        taskList.addTask(new Todo("read book", false));
        assertEquals(1, taskList.getNumOfTasks());
        taskList.undo();
        assertEquals(0, taskList.getNumOfTasks());
    }

    @Test
    public void undo_afterMark() throws WoutException {
        taskList.addTask(new Todo("read book", false));
        taskList.markTaskAt(1);
        assertTrue(taskList.getTasks().get(0).getIsDone());
        taskList.undo();
        assertFalse(taskList.getTasks().get(0).getIsDone());
    }
}
