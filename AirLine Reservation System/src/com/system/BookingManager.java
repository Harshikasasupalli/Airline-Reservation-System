package com.system;

/**
 *
 * @author HP
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BookingManager {
    private static final String DB_URL = "jdbc:mysql://localhost/airLineSystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sRi@695952";
    private Scanner scanner = new Scanner(System.in);
   //private  user loginUsername;
   
    public void bookFlight() {
        
          UserDetails loggedInUser = AuthenticationSystem.getLoggedInUser();
              String username;
        if (loggedInUser != null) {
            // Use the logged-in user's information for bookings
           // loggedInUser.getUser();
             username = loggedInUser.getUser();
             
     

        
        FlightDataBase flightDatabase = new FlightDataBase();
        // Assuming you have a FlightDatabase class to retrieve flight information
        System.out.print("Enter departure city: ");
        String departureCity = scanner.nextLine();

        System.out.print("Enter destination city: ");
        String destinationCity = scanner.nextLine();

        System.out.println("Flights from " + departureCity + " to " + destinationCity + ":");
        flightDatabase.displayFlights(departureCity, destinationCity);

        System.out.println("Enter flight number: ");
        String flightNumber=scanner.nextLine();
      // Flight selectedFlight = flightDatabase.getFlightByNumber(flightNumber);
        
        if (flightNumber != null) {
            boolean addAnotherPassenger = true;

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                while (addAnotherPassenger) {
                    System.out.println("Enter details for passenger:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    System.out.println("Enter your gender");
                    String gender=scanner.nextLine();
                    scanner.nextLine(); // Consume newline

                    String query = "INSERT INTO bookings (username,flightNumber, name, age, gender, departureCity, destinationCity) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1,username);
                    statement.setString(2, flightNumber);
                    statement.setString(3, name);
                    statement.setInt(4, age);
                    statement.setString(5,gender);
                    statement.setString(6,departureCity);
                    statement.setString(7,destinationCity);
                    statement.executeUpdate();
                   
                    System.out.println("Passenger added.");

                    System.out.print("Do you want to add another passenger? (yes/no): ");
                    String choice = scanner.nextLine();
                    addAnotherPassenger = choice.equalsIgnoreCase("yes");
                }

                System.out.println("Booking completed.");
                // Print ticket details
               // printTickets(conn, flightNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid flight number.");
        }
        }
        else {
            System.out.println("You need to log in first.");
        }
    }

    /*private void printTickets(Connection conn, String flightNumber) throws SQLException {
        String query = "SELECT * FROM bookings WHERE flightNumber = ? and username=username";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, flightNumber);
        java.sql.ResultSet resultSet = statement.executeQuery();
//int i=0;
        while (resultSet.next()) {
            
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String flightName=resultSet.getString("flightName");
            String departureCity=resultSet.getString("departureCity");
            String destinationCity=resultSet.getString("destinationCity");
           // String departureTime=resultSet.getTime("departureTime").toString();
            //String arrivalTime=resultSet.getTime("arrivalTime").toString();
           // double cost=resultSet.getDouble("cost");
            System.out.println("Ticket for " + name + " (Age: " + age + ")");
            System.out.println("Flight: " + flightNumber);
            System.out.println("Flight Name: " + flightName);
            System.out.println("Departure: " + departureCity);
            System.out.println("Destination: " + destinationCity);
            //System.out.println("Departure Time: "+departureTime);
            //System.out.println("Arrival Time: "+arrivalTime);
            //System.out.println("Cost of the flight: "+ cost);
            
            
        }
        
        System.out.println("Enjoy your flight!\n");
    }*/


     

   

    public void cancelBooking() {
        
        UserDetails loggedInUser = AuthenticationSystem.getLoggedInUser();
        
        String currentUser;
        if (loggedInUser != null) {
            // Use the logged-in user's information for bookings
           // loggedInUser.getUser();
            currentUser = loggedInUser.getUser();
        

        System.out.print("Enter the flight number for the booking you want to cancel: ");
        String flightNumber = scanner.nextLine();

        System.out.print("Enter the passenger's name: ");
        String passengerName = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM bookings WHERE   flightNumber = ? AND name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            //statement.setInt(1, currentUser.getId());
            statement.setString(1, flightNumber);
            statement.setString(2, passengerName);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Booking for passenger " + passengerName + " on flight " + flightNumber + " has been canceled.");
            } else {
                System.out.println("No bookings found for passenger " + passengerName + " on flight " + flightNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
        
        
        else {
            System.out.println("You need to log in first.");
        }
}
}
