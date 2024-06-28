package com.system;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.SQLException;


public class FlightDataBase {
    private static final String DB_URL = "jdbc:mysql://localhost/airLineSystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sRi@695952";

    public void displayFlights(String departureCity, String destinationCity) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM flights WHERE departure = ? AND destination = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, departureCity);
            statement.setString(2, destinationCity);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String flightNumber = resultSet.getString("flightNumber");
                String flightName=resultSet.getString("flightName");
                Time departureTime=resultSet.getTime("departureTime");
                Time arrivalTime=resultSet.getTime("arrivalTime");
                double  cost=resultSet.getDouble("cost");
                
                //int seatsAvailable = resultSet.getInt("seats_available");
                System.out.println("Flight Number: " + flightNumber);
                System.out.println("Flight Name: " + flightName);
                System.out.println("Departure: " + departureCity);
                System.out.println("Destination: " + destinationCity);
                System.out.println("Departure Time: "+ departureTime);
                System.out.println("Arrival Time: "+ arrivalTime);
                System.out.println("Cost: "+cost);
                
                //System.out.println("Seats Available: " + seatsAvailable);
                System.out.println("---------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    


    public Flight getFlightByNumber(String flightNumber) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT flightNumber,flightName departure, destination, cost,departureTime,arrivalTime FROM flights WHERE flightNumber = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, flightNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String flightName=resultSet.getString("flightName");
                String departure = resultSet.getString("departure");
                String destination = resultSet.getString("destination");
              // int seatsAvailable = resultSet.getInt("seats_available");
                Time departureTime=resultSet.getTime("departureTime");
                Time arrivalTime=resultSet.getTime("arrivalTime");
                Double cost=resultSet.getDouble("cost");
                
                
                
                

                return new Flight(flightNumber,flightName, departure, destination, cost, departureTime, arrivalTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
