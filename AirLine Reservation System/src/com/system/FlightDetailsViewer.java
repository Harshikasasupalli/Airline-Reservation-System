package com.system;

/**
 *
 * @author HP
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class FlightDetailsViewer {
    
    private static final String DB_URL = "jdbc:mysql://localhost/airLineSystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sRi@695952";

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT flightNumber, flightName,departure, destination ,departureTime, arrivalTime, cost FROM flights";
            PreparedStatement statement = conn.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String flightNumber = resultSet.getString("flightNumber");
                String flightName = resultSet.getString("flightName");
                String departure = resultSet.getString("departure");
                String destination = resultSet.getString("destination");
                //int seatsAvailable = resultSet.getInt("seats_available");
                Time departureTime = resultSet.getTime("departureTime");
                Time arrivalTime = resultSet.getTime("arrivalTime");
                Double cost=resultSet.getDouble("cost");
                
                

                Flight flight = new Flight(flightNumber,flightName, departure, destination, cost ,departureTime, arrivalTime);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void displayAllFlights() {
        List<Flight> flights = getAllFlights();

        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Flight Name: " + flight.getFlightName());
            System.out.println("Departure: " + flight.getDeparture());
            System.out.println("Destination: " + flight.getDestination());
            //System.out.println("Seats Available: " + flight.getSeatsAvailable());
            System.out.println("Departure time: "+flight.getdepartureTime());
            System.out.println("Arrival time: "+flight.getarrivalTime());
            System.out.println("Cost: "+flight.getCost());
            
            System.out.println();
        }
    }

   /* public static void main(String[] args) {
        FlightDetailsViewer viewer = new FlightDetailsViewer();
        viewer.displayAllFlights();
    }
    */
}