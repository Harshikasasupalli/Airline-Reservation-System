package com.system;

/**
 *
 * @author HP
 */

import java.sql.*;
import java.util.Scanner;
public class AdminMenu {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airline_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sRi@695952";

    // ... previous code ...

    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Flight");
            System.out.println("2. Delete Flight");
            System.out.println("3. Modify Flight Details");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");
            int adminOption = scanner.nextInt();

            switch (adminOption) {
                case 1:
                    addFlight(scanner);
                    break;
                case 2:
                    deleteFlight(scanner);
                    break;
                case 3:
                    modifyFlightDetails(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public static void addFlight(Scanner scanner) {
        // ... input flight details ...
        
    System.out.print("Enter Flight Number: ");
    String flightNumber = scanner.next();
    System.out.print("Enter Departure: ");
    String departure = scanner.next();
    System.out.print("Enter Destination: ");
    String destination = scanner.next();
    System.out.print("Enter Cost: ");
    double cost = scanner.nextDouble();
    System.out.print("Enter Start Time: ");
    String startTime = scanner.next();
    System.out.print("Enter End Time: ");
    String endTime = scanner.next();

 


        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO flights (flight_number, departure, destination, cost, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, flightNumber);
            statement.setString(2, departure);
            statement.setString(3, destination);
            statement.setDouble(4, cost);
            statement.setString(5, startTime);
            statement.setString(6, endTime);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Flight added successfully!");
            } else {
                System.out.println("Failed to add flight.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFlight(Scanner scanner) {
        // ... input flight number ...
       System.out.print("Enter Flight Number to delete: ");
        String flightNumberToDelete = scanner.next();
        

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM flights WHERE flight_number = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, flightNumberToDelete);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Flight deleted successfully!");
            } else {
                System.out.println("Flight not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyFlightDetails(Scanner scanner) {
        // ... input flight number ...
        System.out.print("Enter Flight Number to modify: ");
        String flightNumberToModify = scanner.next();
        System.out.println("Enter new cost price of the flight: ");
        Double newCost=scanner.nextDouble();
        
        /*System.out.println("Enter new start time: ");
         System.out.print("Enter Start Time (HH:mm): ");
        String startTimeInput = scanner.next();
        System.out.print("Enter End Time (HH:mm): ");
        String endTimeInput = scanner.next();
        
        java.sql.Time newStartTime = java.sql.Time.valueOf(startTimeInput + ":00");
        java.sql.Time newEndTime = java.sql.Time.valueOf(endTimeInput + ":00");
        Time newStartTime = Time.valueOf(startTimeInput+":00");
        Time newEndTime=Time.valueOf(endTimeInput+":00");
        */
        
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE flights SET cost = ?, start_time = ?, end_time = ? WHERE flight_number = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            
            //java.sql.Time newStartTime = java.sql.Time.valueOf(startTimeInput + ":00");
            //java.sql.Time newEndTime = java.sql.Time.valueOf(endTimeInput + ":00");
            
            statement.setDouble(1, newCost);
           // statement.setString(2, newStartTime);
            //statement.setString(3, newEndTime);
            statement.setString(4, flightNumberToModify);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Flight details modified successfully!");
            } else {
                System.out.println("Flight not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ... other methods ...

   /* public static void main(String[] args) {
        // ... previous code ...

        while (true) {
            // ... previous code ...

            switch (option) {
                case 1:
                    isAdmin = authenticateAdmin(scanner);
                    if (isAdmin) {
                        adminMenu();
                    } else {
                        System.out.println("Admin login failed.");
                    }
                    break;
                case 2:
                    // User menu
                    break;
                case 3:
                    System.out.println("Thank you for using the Airline Reservation System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }*/
}
