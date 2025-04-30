
import java.util.Scanner;

class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    private Item head = null;

    // Add item at beginning
    public void addAtBeginning(String name, int id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        newItem.next = head;
        head = newItem;
        System.out.println("Item added at beginning.");
    }

    // Add item at end
    public void addAtEnd(String name, int id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        if (head == null) {
            head = newItem;
        } else {
            Item temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        }
        System.out.println("Item added at end.");
    }

    // Add item at position
    public void addAtPosition(int pos, String name, int id, int qty, double price) {
        if (pos <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 1) {
            addAtBeginning(name, id, qty, price);
            return;
        }

        Item newItem = new Item(name, id, qty, price);
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds.");
            return;
        }

        newItem.next = temp.next;
        temp.next = newItem;
        System.out.println("Item added at position " + pos);
    }

    // Remove item by ID
    public void removeById(int id) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        if (head.itemId == id) {
            head = head.next;
            System.out.println("Item with ID " + id + " removed.");
            return;
        }

        Item temp = head;
        while (temp.next != null && temp.next.itemId != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Item ID not found.");
            return;
        }

        temp.next = temp.next.next;
        System.out.println("Item with ID " + id + " removed.");
    }

    // Update quantity
    public void updateQuantity(int id, int qty) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == id) {
                temp.quantity = qty;
                System.out.println("Quantity updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item ID not found.");
    }

    // Search by ID or name
    public void search(String keyword) {
        boolean found = false;
        Item temp = head;
        while (temp != null) {
            if (String.valueOf(temp.itemId).equals(keyword) || temp.itemName.equalsIgnoreCase(keyword)) {
                printItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item not found.");
    }

    // Calculate total inventory value
    public void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.printf("Total Inventory Value: ₹%.2f\n", total);
    }

    // Display all items
    public void display() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        Item temp = head;
        System.out.println("Inventory Items:");
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }

    private void printItem(Item item) {
        System.out.printf("ID: %d, Name: %s, Qty: %d, Price: ₹%.2f\n",
                item.itemId, item.itemName, item.quantity, item.price);
    }

    // Sort by name or price
    public void sortInventory(String field, boolean ascending) {
        head = mergeSort(head, field, ascending);
        System.out.println("Inventory sorted by " + field + " (" + (ascending ? "ASC" : "DESC") + ").");
    }

    private Item mergeSort(Item head, String field, boolean ascending) {
        if (head == null || head.next == null) return head;

        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(head, field, ascending);
        Item right = mergeSort(nextOfMiddle, field, ascending);

        return sortedMerge(left, right, field, ascending);
    }

    private Item sortedMerge(Item a, Item b, String field, boolean asc) {
        if (a == null) return b;
        if (b == null) return a;

        boolean condition;
        if (field.equals("name"))
            condition = asc ? a.itemName.compareToIgnoreCase(b.itemName) <= 0 : a.itemName.compareToIgnoreCase(b.itemName) > 0;
        else // price
            condition = asc ? a.price <= b.price : a.price > b.price;

        Item result;
        if (condition) {
            result = a;
            result.next = sortedMerge(a.next, b, field, asc);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, field, asc);
        }
        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

public class InventoryManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inv = new Inventory();
        int choice;

        do {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add Item (Beginning)");
            System.out.println("2. Add Item (End)");
            System.out.println("3. Add Item (Position)");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity");
            System.out.println("6. Search Item by ID/Name");
            System.out.println("7. Calculate Total Inventory Value");
            System.out.println("8. Display Inventory");
            System.out.println("9. Sort Inventory (Name)");
            System.out.println("10. Sort Inventory (Price)");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1, 2, 3 -> {
                    System.out.print("Item Name: ");
                    String name = sc.nextLine();
                    System.out.print("Item ID: ");
                    int id = sc.nextInt();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    if (choice == 1) inv.addAtBeginning(name, id, qty, price);
                    else if (choice == 2) inv.addAtEnd(name, id, qty, price);
                    else {
                        System.out.print("Position: ");
                        int pos = sc.nextInt();
                        inv.addAtPosition(pos, name, id, qty, price);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();
                    inv.removeById(id);
                }
                case 5 -> {
                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();
                    System.out.print("New Quantity: ");
                    int qty = sc.nextInt();
                    inv.updateQuantity(id, qty);
                }
                case 6 -> {
                    System.out.print("Enter Item ID or Name: ");
                    String keyword = sc.nextLine();
                    inv.search(keyword);
                }
                case 7 -> inv.calculateTotalValue();
                case 8 -> inv.display();
                case 9, 10 -> {
                    boolean asc;
                    System.out.print("Sort Ascending? (true/false): ");
                    asc = sc.nextBoolean();
                    if (choice == 9) inv.sortInventory("name", asc);
                    else inv.sortInventory("price", asc);
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 0);

        sc.close();
    }
}



