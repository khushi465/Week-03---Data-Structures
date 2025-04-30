
import java.util.Scanner;

class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task current = null;

    // Add at beginning
    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
        if (current == null) current = head;
        System.out.println("Task added at beginning.");
    }

    // Add at end
    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (tail == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            newTask.next = head;
            tail = newTask;
        }
        if (current == null) current = head;
        System.out.println("Task added at end.");
    }

    // Add at specific position
    public void addAtPosition(int position, int id, String name, int priority, String dueDate) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        for (int i = 1; i < position - 1 && temp.next != head; i++) {
            temp = temp.next;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;
        }

        System.out.println("Task added at position " + position);
    }

    // Remove task by ID
    public void removeById(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Task temp = head, prev = tail;
        do {
            if (temp.id == id) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                    if (temp == tail)
                        tail = prev;
                }

                if (temp == current) {
                    current = current.next;
                }

                // If only one task was present
                if (temp == temp.next) {
                    head = tail = current = null;
                }

                System.out.println("Task with ID " + id + " removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task ID not found.");
    }

    // View current task
    public void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        printTask(current);
    }

    // Move to next task
    public void moveToNextTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        current = current.next;
        System.out.println("Moved to next task.");
        viewCurrentTask();
    }

    // Display all tasks
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        Task temp = head;
        System.out.println("All tasks:");
        do {
            printTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    // Search by priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }

        boolean found = false;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                printTask(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No tasks found with priority " + priority);
    }

    // Utility method
    private void printTask(Task task) {
        System.out.println("Task ID: " + task.id + ", Name: " + task.name + ", Priority: " + task.priority + ", Due: " + task.dueDate);
    }
}

public class TaskSchedulerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();
        int choice;

        do {
            System.out.println("\n--- Task Scheduler Menu ---");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task");
            System.out.println("6. Move to Next Task");
            System.out.println("7. Display All Tasks");
            System.out.println("8. Search Task by Priority");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1, 2, 3 -> {
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    String dueDate = sc.nextLine();

                    if (choice == 1) scheduler.addAtBeginning(id, name, priority, dueDate);
                    else if (choice == 2) scheduler.addAtEnd(id, name, priority, dueDate);
                    else {
                        System.out.print("Enter position: ");
                        int pos = sc.nextInt();
                        scheduler.addAtPosition(pos, id, name, priority, dueDate);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Task ID to remove: ");
                    int id = sc.nextInt();
                    scheduler.removeById(id);
                }
                case 5 -> scheduler.viewCurrentTask();
                case 6 -> scheduler.moveToNextTask();
                case 7 -> scheduler.displayAllTasks();
                case 8 -> {
                    System.out.print("Enter priority to search: ");
                    int prio = sc.nextInt();
                    scheduler.searchByPriority(prio);
                }
                case 0 -> System.out.println("Exiting Task Scheduler.");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}



