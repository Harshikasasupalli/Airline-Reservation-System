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
import java.sql.ResultSet;

public class Users {
    private static final String DB_URL = "jdbc:mysql://localhost/airLineSystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sRi@695952";
    private Scanner scanner = new Scanner(System.in);

    public boolean signUp(UserDetails user) {
        // Implement sign-up logic
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
             String username=user.getUser();
             String password=user.getPassword();
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            int rowsAffected = statement.executeUpdate();
              return rowsAffected > 0;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean login(String username, String password) {
        // Implement login logic
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT username, password FROM users WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return password.equals(storedPassword);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void showOptions() {
        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String signUpUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String signUpPassword = scanner.nextLine();
                    if( signUp(new UserDetails(signUpUsername, signUpPassword))){
                    
                   
                      System.out.println("Sign-up successful!");
                      }
                    else {         
                        System.out.println("Sign-up failed. Username might already be taken.");
                         }
  
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                  
                    if (login(loginUsername, loginPassword)) {
                        UserDetails user=new UserDetails(loginUsername,loginPassword);
                        AuthenticationSystem.setLoggedInUser(user);
                        userMenu();
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    public void userMenu() {
        BookingManager bookingManager = new BookingManager();
        while (true) {
            System.out.println("1. Book a Flight");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View Flight Details");
            System.out.println("4. Log Out");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    bookingManager.bookFlight();
                    break;
                case 2:
                    bookingManager.cancelBooking();
                    break;
                case 3:
                    FlightDetailsViewer viewer = new FlightDetailsViewer();
                    viewer.displayAllFlights();
                    break;
                case 4:
                    BookedFlights b=new BookedFlights();
                    b.viewBookedDetails();
                    break;
                case 5:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

   /* public static void main(String[] args) {
        
        Users user = new Users();
        user.showOptions();
    }*/
}
