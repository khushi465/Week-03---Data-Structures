
import java.util.Scanner;

class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime;
    int turnaroundTime;
    Process next;

    Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.next = null;
    }
}

class Scheduler {
    private Process head = null;

    // Add process at the end of the circular list
    public void addProcess(int pid, int burstTime, int priority) {
        Process newNode = new Process(pid, burstTime, priority);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Process temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
        System.out.println("Process " + pid + " added.");
    }

    // Display current processes in queue
    public void displayQueue() {
        if (head == null) {
            System.out.println("No processes in queue.");
            return;
        }
        System.out.println("Processes in queue:");
        Process temp = head;
        do {
            System.out.printf("PID: %d | Remaining: %d | Priority: %d\n", temp.pid, temp.remainingTime, temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    // Simulate round-robin scheduling
    public void roundRobin(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int time = 0;
        Process current = head;
        Process prev = null;

        System.out.println("\nStarting Round-Robin Scheduling with Time Quantum = " + timeQuantum);
        while (head != null) {
            boolean completed = false;

            if (current.remainingTime <= timeQuantum) {
                time += current.remainingTime;
                current.remainingTime = 0;
                current.turnaroundTime = time;
                current.waitingTime = current.turnaroundTime - current.burstTime;
                System.out.println("Process " + current.pid + " completed at time " + time);
                removeProcess(current.pid, prev);
                current = (prev != null) ? prev.next : head;
                completed = true;
            } else {
                current.remainingTime -= timeQuantum;
                time += timeQuantum;
                prev = current;
                current = current.next;
            }

            displayQueue();

            if (head == null) break;
            if (!completed && current == head) System.out.println("Completed one full cycle.\n");
        }

        System.out.println("All processes completed.\n");
    }

    // Remove a process by ID
    private void removeProcess(int pid, Process prev) {
        if (head == null) return;

        Process temp = head;
        Process last = head;

        // Find last node
        while (last.next != head) {
            last = last.next;
        }

        // If removing head
        if (temp.pid == pid) {
            if (head.next == head) {
                head = null;
            } else {
                head = head.next;
                last.next = head;
            }
        } else {
            do {
                if (temp.next != null && temp.next.pid == pid) {
                    temp.next = temp.next.next;
                    if (temp.next == head) head = temp.next;
                    break;
                }
                temp = temp.next;
            } while (temp != head);
        }
    }

    // Calculate average times
    public void calculateAvgTimes(int originalCount) {
        float totalWaiting = 0, totalTurnaround = 0;
        Process temp = head;
        if (temp == null) {
            System.out.println("No processes left for calculation.");
            return;
        }

        do {
            totalWaiting += temp.waitingTime;
            totalTurnaround += temp.turnaroundTime;
            temp = temp.next;
        } while (temp != head);

        System.out.printf("Average Waiting Time: %.2f\n", totalWaiting / originalCount);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaround / originalCount);
    }
}

public class RoundRobinScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter Burst Time for P" + i + ": ");
            int bt = sc.nextInt();
            System.out.print("Enter Priority for P" + i + ": ");
            int pr = sc.nextInt();
            scheduler.addProcess(i, bt, pr);
        }

        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();

        scheduler.displayQueue();
        scheduler.roundRobin(quantum);

        // Final output
        System.out.println("\nSimulation Complete!");
        // Not calculating average here because remaining list is empty after processing
    }
}



