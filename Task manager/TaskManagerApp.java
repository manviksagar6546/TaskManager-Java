import java.util.Scanner;

class Task {
    private String title;
    private String description;
    private String dueDate;
    private boolean isDone;

    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = false; // Newly added tasks are not done by default
    }

    // Getters and setters for task properties

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nDue Date: " + dueDate + "\nStatus: " +
                (isDone ? "Done" : "Pending") + "\n";
    }
}

class TaskManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS];
    private int numTasks = 0;

    public void addTask(String title, String description, String dueDate) {
        if (numTasks < MAX_TASKS) {
            tasks[numTasks++] = new Task(title, description, dueDate);
            System.out.println("Task added successfully!");
        } else {
            System.out.println("Task manager is full. Cannot add more tasks.");
        }
    }

    public void viewTasks() {
        if (numTasks == 0) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < numTasks; i++) {
                System.out.println(tasks[i]);
            }
        }
    }

    public void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < numTasks) {
            tasks[taskIndex].markAsDone();
            System.out.println("Task marked as done!");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < numTasks) {
            // Shift remaining tasks to fill the gap
            for (int i = taskIndex; i < numTasks - 1; i++) {
                tasks[i] = tasks[i + 1];
            }
            numTasks--;
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Invalid task index.");
        }
    }
}

public class TaskManagerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        int choice;
        do {
            System.out.println("Task Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date: ");
                    String dueDate = scanner.nextLine();
                    taskManager.addTask(title, description, dueDate);
                    break;
                case 2:
                    taskManager.viewTasks();
                    break;
                case 3:
                    System.out.print("Enter the index of the task to mark as done: ");
                    int doneIndex = scanner.nextInt();
                    taskManager.markTaskAsDone(doneIndex);
                    break;
                case 4:
                    System.out.print("Enter the index of the task to delete: ");
                    int deleteIndex = scanner.nextInt();
                    taskManager.deleteTask(deleteIndex);
                    break;
                case 5:
                    System.out.println("Exiting Task Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        } while (choice != 5);

        scanner.close();
    }
}

