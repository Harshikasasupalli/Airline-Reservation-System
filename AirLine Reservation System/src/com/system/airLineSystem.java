package com.system;

/**
 *
 * @author HP
 */
import java.util.Scanner;

public class airLineSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isAdmin = false;
        
        while (true) {
            System.out.println("Welcome to Airline Reservation System!");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            
            switch (option) {
                case 1:
                    isAdmin = authenticateAdmin(scanner);
                    if (isAdmin) {
                        AdminMenu a=new AdminMenu();
                        a.adminMenu();
                    } else {
                        System.out.println("Admin login failed.");
                    }
                    break;
                case 2:
                    if (!isAdmin) {
                        //userMenu();
                        Users user = new Users();
                        user.showOptions();
                    } else {
                        System.out.println("Please log out from the admin account.");
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the Airline Reservation System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static boolean authenticateAdmin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.next();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.next();
        
        // You would perform actual admin authentication here
        // For simplicity, let's assume hardcoded admin credentials
        return adminUsername.equals("admin") && adminPassword.equals("admin123");
    }
    
    private static void adminMenu() {
        System.out.println("Welcome, Admin!");
        // Implement admin functionalities
    }
    
    /*private static void userMenu() {
        System.out.println("Welcome, User!");
        // Implement user functionalities
    }*/
}