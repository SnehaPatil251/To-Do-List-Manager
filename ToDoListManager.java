import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;
    private String dueDate;
    private boolean completed;

    public Task(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markPending() {
        this.completed = false;
    }
}

public class ToDoListManager {
    private List<Task> tasks;

    public ToDoListManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markCompleted(int index) {
        if (index >= 1 && index <=tasks.size()) {
            tasks.get(index-1).markCompleted();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void deleteTask(int index) {
        if (index >= 1 && index <=tasks.size()) {
            tasks.remove(index-1);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    public List<Task> getPendingTasks() {
        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }
    
    public static void main(String[] args) {
        ToDoListManager manager = new ToDoListManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. View Completed Tasks");
            System.out.println("6. View Pending Tasks");
            System.out.println("7. Quit");
            
            System.out.println("Enter your Choice");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline1


            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date: ");
                    String dueDate = scanner.nextLine();
                    Task newTask = new Task(description, dueDate);
                    manager.addTask(newTask);
                    System.out.println("Task added successfully.");
                    break;

                case 2:
                    System.out.print("Enter task index to mark as completed: ");
                    int completeIndex = scanner.nextInt();
                    manager.markCompleted(completeIndex);
                    break;

                case 3:
                    System.out.print("Enter task index to delete: ");
                    int deleteIndex = scanner.nextInt();
                    manager.deleteTask(deleteIndex);
                    break;

                case 4:
                    List<Task> allTasks = manager.getAllTasks();
                    displayTasks(allTasks);
                    break;

                case 5:
                    List<Task> completedTasks = manager.getCompletedTasks();
                    displayTasks(completedTasks);
                    break;

                case 6:
                    List<Task> pendingTasks = manager.getPendingTasks();
                    displayTasks(pendingTasks);
                    break;
                
               
                 case 7:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayTasks(List<Task> tasks) {
        System.out.println("\nTasks:");
        for (int i = 1; i <=tasks.size(); i++) {
            Task task = tasks.get(i-1);
            String status = task.isCompleted() ? "Completed" : "Not Completed";
            System.out.println(i + ". " +  " " + task.getDescription()+" - "+ status + " Due: " + task.getDueDate());
        }
    }
}
