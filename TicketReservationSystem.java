public class TicketReservationSystem {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        system.addTicket(101, "Harman", "Avengers: Endgame", 8, "11:00 AM");
        system.addTicket(102, "Anish", "The Dark Knight", 18, "3:45 PM");
        system.addTicket(103, "Nitish", "Inception", 25, "7:15 PM");

        System.out.println("All Tickets:");
        system.displayTickets();

        System.out.println("Total Tickets: " + system.countTickets());

        system.removeTicket(102);
        System.out.println("\nAfter Removing Ticket 102:");
        system.displayTickets();
        System.out.println("Total Tickets: " + system.countTickets());
    }

    TicketNode head = null;

    static class TicketNode {
        int ticketId;
        String customerName;
        String movieName;
        int seatNumber;
        String bookingTime;
        TicketNode next;

        TicketNode(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
            this.next = null;
        }
    }

    public void addTicket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        TicketNode newTicket = new TicketNode(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            head.next = head;
        } else {
            TicketNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    public void removeTicket(int ticketId) {
        if (head == null) return;
        TicketNode temp = head, prev = null;
        while (temp.ticketId != ticketId) {
            if (temp.next == head) return;
            prev = temp;
            temp = temp.next;
        }
        if (temp == head) {
            TicketNode last = head;
            while (last.next != head) {
                last = last.next;
            }
            if (head == head.next) {
                head = null;
            } else {
                head = head.next;
                last.next = head;
            }
        } else {
            prev.next = temp.next;
        }
    }

    public void displayTickets() {
        if (head == null) return;
        TicketNode temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Booking Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public int countTickets() {
        if (head == null) return 0;
        int count = 0;
        TicketNode temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}
//output
//All Tickets:
//Ticket ID: 101, Customer: Harman, Movie: Avengers: Endgame, Seat: 8, Booking Time: 11:00 AM
//Ticket ID: 102, Customer: Anish, Movie: The Dark Knight, Seat: 18, Booking Time: 3:45 PM
//Ticket ID: 103, Customer: Nitish, Movie: Inception, Seat: 25, Booking Time: 7:15 PM
//Total Tickets: 3
//
//After Removing Ticket 102:
//Ticket ID: 101, Customer: Harman, Movie: Avengers: Endgame, Seat: 8, Booking Time: 11:00 AM
//Ticket ID: 103, Customer: Nitish, Movie: Inception, Seat: 25, Booking Time: 7:15 PM
//Total Tickets: 2