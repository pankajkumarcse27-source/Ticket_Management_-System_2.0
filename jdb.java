package TicketBookingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class jdb {

    static String url = "jdbc:mysql://localhost:3306/Hospital";
    static String username = "root";
    static String password = "843128";

    public static void main(String[] args) {
        try {
            createTables();
            System.out.println("Database ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    static void createTables() throws Exception {
        try (Connection c = getConnection(); Statement st = c.createStatement()) {
            st.executeUpdate("create table if not exists Customer(customerId int primary key, name varchar(50))");
            st.executeUpdate("create table if not exists Ticket(bookingId int primary key, customerId int, customer varchar(50), movieName varchar(50), roomNo int, ticketNo int, ticketPrice double)");
        }
    }

    static void addCustomer(Customer customer) {
        String q = "insert into Customer(customerId, name) values(?, ?)";
        try (Connection c = getConnection(); PreparedStatement pr = c.prepareStatement(q)) {
            pr.setInt(1, customer.customerId);
            pr.setString(2, customer.Name);
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("Customer database save failed: " + e.getMessage());
        }
    }

    static void addBooking(Booking booking) {
        String q = "insert into Ticket(bookingId, customerId, customer, movieName, roomNo, ticketNo, ticketPrice) values(?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = getConnection(); PreparedStatement pr = c.prepareStatement(q)) {
            pr.setInt(1, booking.bookingId);
            pr.setInt(2, booking.customer.customerId);
            pr.setString(3, booking.customer.Name);
            pr.setString(4, booking.movieName);
            pr.setInt(5, booking.roomNo);
            pr.setInt(6, booking.ticketNo);
            pr.setDouble(7, booking.ticketPrice);
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("Booking database save failed: " + e.getMessage());
        }
    }
}
