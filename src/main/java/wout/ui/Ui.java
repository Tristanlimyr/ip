package wout.ui;

import wout.task.Task;
import wout.task.TaskList;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Class to read input from user and display messages to user.
 */
public class Ui {
    public static final String GREET = "Hello! I'm Wout!\n"
            + "What can I do for you?";
    public static final String EXIT = "Bye. Hope to see you again soon!";
    public static final String INVALID_COMMAND = "Please enter a valid command";
    public static final String INVALID_DATE_TIME = "Please provide date and time in \"yyyy-mm-dd HH:mm\" format!";
    public static final DateTimeFormatter DATE_TIME_DISPLAY = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    public static final DateTimeFormatter DATE_TIME_ENTRY = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Scanner scanner = new Scanner(System.in);

    public void printMessage(String message) {
        System.out.println("________________________________\n"
                + message + "\n"
                + "________________________________\n"
        );
    }

    public void printGreeting() {
        printMessage(GREET);
    }

    public void printExit() {
        printMessage(EXIT);
    }

    public String addTaskMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.";
    }

    public String markTaskMessage(Task task, TaskList taskList) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.";
    }

    public String unmarkTaskMessage(Task task, TaskList taskList) {
        return "Ok, I've marked this task as not done yet:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.";
    }

    public String deleteTaskMessage(Task task, TaskList taskList) {
        return "Noted. I've remove this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.";
    }

    private String listTasks(List<Task> tasks) {
        String result = "";
        int index = 1;
        if (tasks.isEmpty()) {
            return "list is currently empty!\n";
        } else {
            for (Task task : tasks) {
                result += index + ". " + task + "\n";
                index++;
            }
            return result;
        }
    }

    public String listTaskMessage(List<Task> tasks) {
        return "Here are the tasks in your list:\n"
                + listTasks(tasks);
    }

    public String findTaskMessage(List<Task> tasks) {
        return "Here are the matching tasks in your list:\n"
                + listTasks(tasks);
    }

    public void printWoutException(WoutException e) {
        printMessage(e.toString());
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
