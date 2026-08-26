package TicketBookingSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    static Scanner s = new Scanner(System.in);

    // Java Collection Framework
    static ArrayList<Customer> customers = new ArrayList<>();

    static ArrayList<Booking> bookings = new ArrayList<>();


    public static void main(String[] args) {

        try {
            jdb.createTables();
        } catch (Exception e) {
            System.out.println("Database not connected. File system will continue.");
        }

        loadCustomers();
        loadBookings();
        


        while (true) {

            try {

                System.out.println();
                System.out.println("-----------------------------------------------------------------");
                System.out.println("                   TICKET BOOKING SYSTEM");
                System.out.println("-----------------------------------------------------------------");

                System.out.println("1. Add Customer");
                System.out.println("2. Movie List & Book Ticket");
                System.out.println("3. View All Booked Tickets");
                System.out.println("4. Delete Customer");
                System.out.println("5. Exit");

                System.out.print("Select Option: ");

                int option = s.nextInt();
                s.nextLine();

                System.out.println(
                    "Your Selected Option: " + option
                );


                switch (option) {

                    case 1:

                        addCustomer();

                        break;

                    case 2:

                        bookTicket();

                        break;



                    case 3:

                        viewBookings();

                        break;



                    case 4:

                        deleteCustomer();

                        break;


            

                    case 5:

                        System.out.println(
                            "Thank You for using Ticket Booking System!"
                        );

                        s.close();

                        return;


                    default:

                        System.out.println(
                            "Invalid Option!"
                        );
                }

            }

            catch (Exception e) {

                System.out.println(
                    "Invalid Input! Please enter correct value."
                );

                s.nextLine();
            }
        }
    }


   

    static void addCustomer() {

        try {

            System.out.print(
                "Enter Customer ID: "
            );

            int id = s.nextInt();
            s.nextLine();


            System.out.print(
                "Enter Customer Name: "
            );

            String name = s.nextLine();


            Customer n =
                new Customer(id, name);


            // ArrayList me customer save
            customers.add(n);


            // File me customer save
            FileWriter fw =
                new FileWriter(
                    "customer.txt",
                    true
                );

            fw.write(
                "Customer ID: " + id +
                " | Name: " + name + "\n"
            );

            fw.close();

            jdb.addCustomer(n);


            System.out.println();
            System.out.println(
                "Customer Added Successfully!"
            );

        }

        catch (Exception e) {

            System.out.println(
                "Error while adding customer!"
            );
        }
    }


   

    static void bookTicket() {

        try {

            // Customer check

            if (customers.size() == 0) {

                System.out.println(
                    "First Add Customer!"
                );

                return;
            }


            // Customer ID

            System.out.print(
                "Enter Customer ID: "
            );

            int customerId = s.nextInt();

            Customer selectedCustomer = null;


            // Customer search

            for (Customer c : customers) {

                if (c.customerId == customerId) {

                    selectedCustomer = c;

                    break;
                }
            }


            if (selectedCustomer == null) {

                System.out.println(
                    "Customer Not Found!"
                );

                return;
            }


            // MOVIE LIST

            System.out.println();
            System.out.println(
                "------------- MOVIE LIST -------------"
            );

            System.out.println(
                "1. Avengers       Rs. 250"
            );

            System.out.println(
                "2. Avatar         Rs. 300"
            );

            System.out.println(
                "3. KGF            Rs. 200"
            );

            System.out.println(
                "4. Bahubali       Rs. 220"
            );


            System.out.print(
                "Select Movie: "
            );

            int movie = s.nextInt();


            String movieName = "";
            double price = 0;


            // Movie selection

            switch (movie) {

                case 1:

                    movieName = "Avengers";
                    price = 250;

                    break;


                case 2:

                    movieName = "Avatar";
                    price = 300;

                    break;


                case 3:

                    movieName = "KGF";
                    price = 200;

                    break;


                case 4:

                    movieName = "Bahubali";
                    price = 220;

                    break;


                default:

                    System.out.println(
                        "Invalid Movie Selection!"
                    );

                    return;
            }


            System.out.println();
            System.out.println(
                "Your Movie Selected Successfully!"
            );

            System.out.println(
                "Movie: " + movieName
            );

            System.out.println(
                "Ticket Price: Rs. " + price
            );


            // Screening Room

            System.out.print(
                "Enter Screening Room No: "
            );

            int roomNo = s.nextInt();


            // Ticket Number

            System.out.print(
                "Enter Ticket No: "
            );

            int ticketNo = s.nextInt();


            // Booking ID automatically

            int bookingId =
                bookings.size() + 1001;


            // Booking object

            Booking b =
                new Booking(
                    bookingId,
                    selectedCustomer,
                    movieName,
                    roomNo,
                    ticketNo,
                    price
                );


            // ArrayList me booking save

            bookings.add(b);


            // File me booking save

            FileWriter fw =
                new FileWriter(
                    "booking.txt",
                    true
                );


            fw.write(
                "Booking ID: " + bookingId +
                " | Customer: " + selectedCustomer.Name +
                " | Movie: " + movieName +
                " | Room: " + roomNo +
                " | Ticket No: " + ticketNo +
                " | Price: Rs. " + price +
                "\n"
            );


            fw.close();

            jdb.addBooking(b);


            // Final booking details

            System.out.println();

            System.out.println(
                "================================="
            );

            System.out.println(
                "     TICKET BOOKED SUCCESSFULLY"
            );

            System.out.println(
                "================================="
            );

            b.displayBooking();
        }


        catch (Exception e) {

            System.out.println(
                "Invalid Input!"
            );

            s.nextLine();
        }
    }


    

    static void viewBookings() {

        System.out.println();
        System.out.println(
            "------------- ALL BOOKINGS -------------"
        );


        if (bookings.size() == 0) {

            System.out.println(
                "No Ticket Booked!"
            );

            return;
        }


        for (Booking b : bookings) {

            b.displayBooking();
        }
    }
static void updateCustomer() {

    try {

        System.out.print("Enter Customer ID: ");
        int id = s.nextInt();
        s.nextLine();

        boolean found = false;

        for (Customer c : customers) {

            if (c.customerId == id) {

                System.out.print("Enter New Customer Name: ");
                String newName = s.nextLine();

                c.Name = newName;

                found = true;

                System.out.println(
                    "Customer Updated Successfully!"
                );

                break;
            }
        }

        if (found) {

            saveCustomers();
        }
        else {

            System.out.println(
                "Customer Not Found!"
            );
        }

    }
    catch (Exception e) {

        System.out.println(
            "Invalid Input!"
        );

        s.nextLine();
    }
}

  static void deleteCustomer() {

    try {

        System.out.print("Enter Customer ID to Delete: ");

        int id = s.nextInt();

        boolean found = false;

        for (int i = 0; i < customers.size(); i++) {

            if (customers.get(i).customerId == id) {

                customers.remove(i);

                found = true;

                System.out.println(
                    "Customer Deleted Successfully!"
                );

                break;
            }
        }

        if (found) {

            saveCustomers();
        }
        else {

            System.out.println(
                "Customer Not Found!"
            );
        }

    }
    catch (Exception e) {

        System.out.println(
            "Invalid Customer ID!"
        );

        s.nextLine();
    }
}
    static void saveCustomers() {

    try {

        FileWriter fw =
            new FileWriter("C:\\Users\\panka\\OneDrive\\Desktop\\DSA java\\TicketBookingSystem\\Customer.txt");

        for (Customer c : customers) {

            fw.write(
                "Customer ID: " + c.customerId +
                " | Name: " + c.Name +
                "\n"
            );
        }

        fw.close();

        System.out.println(
            "Customer Data Saved!"
        );

    }
    catch (IOException e) {

        System.out.println(
            "Error while saving customer data!"
        );
    }
}
    static void cancelTicket() {

    try {

        System.out.print("Enter Booking ID: ");

        int id = s.nextInt();

        boolean found = false;

        for (int i = 0; i < bookings.size(); i++) {

            if (bookings.get(i).bookingId == id) {

                bookings.remove(i);

                found = true;

                System.out.println(
                    "Ticket Cancelled Successfully!"
                );

                break;
            }
        }

        if (found) {

            saveBookings();
        }
        else {

            System.out.println(
                "Booking Not Found!"
            );
        }

    }
    catch (Exception e) {

        System.out.println(
            "Invalid Booking ID!"
        );

        s.nextLine();
    }
}
static void saveBookings() {

    try {

        FileWriter fw =
            new FileWriter("C:\\Users\\panka\\OneDrive\\Desktop\\DSA java\\TicketBookingSystem\\Booking.txt");

        for (Booking b : bookings) {

            fw.write(
                "Booking ID: " + b.bookingId +
                " | Customer: " + b.customer.Name +
                " | Movie: " + b.movieName +
                " | Room: " + b.roomNo +
                " | Ticket No: " + b.ticketNo +
                " | Price: Rs. " + b.ticketPrice +
                "\n"
            );
        }

        fw.close();

        System.out.println(
            "Booking Data Saved!"
        );

    }
    catch (IOException e) {

        System.out.println(
            "Error while saving booking data!"
        );
    }
}
    
    
    static void loadCustomers() {

    try {

        FileReader fr = new FileReader("C:\\Users\\panka\\OneDrive\\Desktop\\DSA java\\TicketBookingSystem\\Customer.txt");
        BufferedReader br = new BufferedReader(fr);

        String line;

        while ((line = br.readLine()) != null) {

            System.out.println("Loading: " + line);

            // Example:
            // Customer ID: 101 | Name: Pankaj

            String[] data = line.split("\\|");

            int id = Integer.parseInt(
                data[0].split(":")[1].trim()
            );

            String name =
                data[1].split(":")[1].trim();

            Customer c =
                new Customer(id, name);

            customers.add(c);
        }

        br.close();

    }
    catch (IOException e) {

        System.out.println(
            "Customer file not found. New file will be created."
        );
    }
}
static void loadBookings() {

    try {

        FileReader fr =
            new FileReader("C:\\Users\\panka\\OneDrive\\Desktop\\DSA java\\TicketBookingSystem\\Booking.txt");

        BufferedReader br =
            new BufferedReader(fr);

        String line;

        while ((line = br.readLine()) != null) {

            System.out.println("Loading: " + line);

            String[] data =
                line.split("\\|");

            int bookingId =
                Integer.parseInt(
                    data[0]
                    .split(":")[1]
                    .trim()
                );

            String customerName =
                data[1]
                .split(":")[1]
                .trim();

            String movieName =
                data[2]
                .split(":")[1]
                .trim();

            int roomNo =
                Integer.parseInt(
                    data[3]
                    .split(":")[1]
                    .trim()
                );

            int ticketNo =
                Integer.parseInt(
                    data[4]
                    .split(":")[1]
                    .trim()
                );

            double price =
                Double.parseDouble(
                    data[5]
                    .split(":")[1]
                    .trim()
                );

            Customer customer =
                new Customer(0, customerName);

            Booking booking =
                new Booking(
                    bookingId,
                    customer,
                    movieName,
                    roomNo,
                    ticketNo,
                    price
                );

            bookings.add(booking);
        }

        br.close();

    }
    catch (IOException e) {

        System.out.println(
            "Booking file not found. New file will be created."
        );
    }
}
}
