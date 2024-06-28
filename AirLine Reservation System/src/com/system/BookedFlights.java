package com.system;




/**
 *
 * @author KARUNA
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BookedFlights {
        private static final String DB_URL = "jdbc:mysql://localhost/airLineSystem";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "sRi@695952";
     public void viewBookedDetails() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
              UserDetails loggedInUser = AuthenticationSystem.getLoggedInUser();
              String username;
        if (loggedInUser != null) {
            // Use the logged-in user's information for bookings
           // loggedInUser.getUser();
             username = loggedInUser.getUser();
            String query = "SELECT flights.flightNumber, flights.departure, flights.destination, flights.departureTime, flights.arrivalTime, "
                         + "bookings.name, bookings.age, bookings.gender "
                         + "FROM bookings "
                         + "INNER JOIN flights ON bookings.flightNumber = flights.flightNumber "
                         + "WHERE bookings.username = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            System.out.println("Your Booked Details:");
            while (resultSet.next()) {
                String flightNumber = resultSet.getString("flightNumber");
                String flightName=resultSet.getString("flightName");
                String departure = resultSet.getString("departure");
                String destination = resultSet.getString("destination");
                String startTime = resultSet.getTime("departureTime").toString();
                String endTime = resultSet.getTime("arrivalTime").toString();
                String passengerName = resultSet.getString("passenger_name");
                int passengerAge = resultSet.getInt("passenger_age");
                String passengerGender = resultSet.getString("passenger_gender");

                System.out.println("Flight Number: " + flightNumber);
                System.out.println("FLight Name: "+flightName );
                System.out.println("Departure: " + departure);
                System.out.println("Destination: " + destination);
                System.out.println("Start Time: " + startTime);
                System.out.println("End Time: " + endTime);
                System.out.println("Passenger Name: " + passengerName);
                System.out.println("Passenger Age: " + passengerAge);
                System.out.println("Passenger Gender: " + passengerGender);
                System.out.println();
            }
            
        }
        else{
            System.out.println("You need to log in first.");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}