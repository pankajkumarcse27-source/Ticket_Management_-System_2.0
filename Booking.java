package TicketBookingSystem;

public class Booking {

    int bookingId;
    Customer customer;
    String movieName;
    int roomNo;
    int ticketNo;
    double ticketPrice;

    public Booking(
            int bookingId,
            Customer customer,
            String movieName,
            int roomNo,
            int ticketNo,
            double ticketPrice) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.movieName = movieName;
        this.roomNo = roomNo;
        this.ticketNo = ticketNo;
        this.ticketPrice = ticketPrice;
    }

    public void displayBooking() {

        System.out.println("--------------------------------");
        System.out.println("Booking ID    : " + bookingId);
        System.out.println("Customer Name : " + customer.Name);
        System.out.println("Movie         : " + movieName);
        System.out.println("Screen Room   : " + roomNo);
        System.out.println("Ticket No     : " + ticketNo);
        System.out.println("Ticket Price  : Rs. " + ticketPrice);
        System.out.println("--------------------------------");
    }
}