package TicketBookingSystem;

public class Customer {

    String Name;
    int customerId;

    public Customer(int customerId, String Name) {
        this.customerId = customerId;
        this.Name = Name;
    }

    public void displayCustomer() {
        System.out.println(
            "Customer ID: " + customerId +
            " | Name: " + Name
        );
    }
}