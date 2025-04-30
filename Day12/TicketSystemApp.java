
import java.util.*;

class Ticket {
    String ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(String ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head = null;

    // Add a new ticket at the end
    public void addTicket(String ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            newTicket.next = head;
        } else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
        System.out.println("Ticket added successfully.");
    }

    // Remove a ticket by ticketId
    public void removeTicket(String ticketId) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }

        Ticket current = head, prev = null;

        // Search for ticket to remove
        do {
            if (current.ticketId.equals(ticketId)) {
                if (prev == null) { // removing head
                    if (head.next == head) {
                        head = null; // only one node
                    } else {
                        Ticket last = head;
                        while (last.next != head) {
                            last = last.next;
                        }
                        head = head.next;
                        last.next = head;
                    }
                } else {
                    prev.next = current.next;
                }
                System.out.println("Ticket removed: " + ticketId);
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket not found: " + ticketId);
    }

    // Display all tickets
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        System.out.println("Booked Tickets:");
        do {
            System.out.println("Ticket ID: " + temp.ticketId);
            System.out.println("Customer Name: " + temp.customerName);
            System.out.println("Movie Name: " + temp.movieName);
            System.out.println("Seat Number: " + temp.seatNumber);
            System.out.println("Booking Time: " + temp.bookingTime);
            System.out.println("---------------------------");
            temp = temp.next;
        } while (temp != head);
    }

    // Search by customer or movie name
    public void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        boolean found = false;
        Ticket temp = head;
        do {
            if (temp.customerName.equalsIgnoreCase(keyword) || temp.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("Ticket ID: " + temp.ticketId);
                System.out.println("Customer Name: " + temp.customerName);
                System.out.println("Movie Name: " + temp.movieName);
                System.out.println("Seat Number: " + temp.seatNumber);
                System.out.println("Booking Time: " + temp.bookingTime);
                System.out.println("---------------------------");
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No ticket found for: " + keyword);
    }

    // Count total booked tickets
    public int countTickets() {
        if (head == null) return 0;

        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        return count;
    }
}

public class TicketSystemApp {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        system.addTicket("T001", "Alice", "Avengers", "A1", "10:00 AM");
        system.addTicket("T002", "Bob", "Batman", "B1", "12:00 PM");
        system.addTicket("T003", "Charlie", "Avengers", "A2", "10:00 AM");

        system.displayTickets();

        System.out.println("Total Booked Tickets: " + system.countTickets());

        system.searchTicket("Avengers");
        system.searchTicket("Alice");

        system.removeTicket("T002");
        system.displayTickets();
        System.out.println("Total Booked Tickets: " + system.countTickets());
    }
}

