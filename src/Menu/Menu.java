package Menu;

import Roles.Client;
import Roles.Manager;

import java.io.*;
import java.util.*;


public class Menu {
    static Manager manager = new Manager();
    static Client client = new Client();
    static Car car = new Car();

    static Scanner sc = new Scanner(System.in);
    static Scanner keyboard = new Scanner(System.in);

    // user input their choice
    public static int getValidatedInput(int min, int max){
        int choice;
        while (true) {
            try {
                System.out.print("Enter Your Choice (" + min + "-" + max + "): ");
                choice = sc.nextInt();

                if (choice >= min && choice <= max) {
                    break; // Valid input, exit the loop
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Clear the invalid input from the scanner buffer
            }
        }
        return choice;
    }

    public static void SystemMenu() throws IOException {
        System.out.println("Returning to System Menu...");
        System.out.println("============================== System Menu - Welcome to Auto316 Management System ==============================");
        System.out.println("Please let us know who you are: ");
        System.out.println("1. Manager");
        System.out.println("2. Employee");
        System.out.println("3. Client");
        System.out.println("4. Exit");
        int choice = getValidatedInput(1, 4);

        switch (choice) {
            case 1:
                ManagerLoginMenu();
                break;
            case 3:
                ClientWelcomeMenu();
                break;
            case 4:
                ThankYouMenu();
                System.exit(0);
                break;
        }
    }
    ////////////////////////////////////Manager//////////////////////////////////////
    // manager login
    public static void ManagerLoginMenu() throws IOException {
        System.out.println("============================== Manager - Login ==============================");
        sc.nextLine();

        System.out.print("Enter your username (or type 'esc' to go back): ");
        String username = sc.nextLine();
        if (username.equalsIgnoreCase("esc")) {
            SystemMenu();
            return; // Exit the current method to prevent further execution
        }

        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        if (password.equalsIgnoreCase("esc")) {
            SystemMenu();
            return;
        }

        manager.login(username, password);
    }

    // manager menu
    public static void ManagerMenu() throws IOException {
        System.out.println("============================== Manager - Menu ===============================");
        System.out.println("Welcome admin to Auto316 Management System");
    }

    ////////////////////////////////////Client//////////////////////////////////////
    public static void ClientWelcomeMenu() throws IOException {
        System.out.println("Returning to Client Welcome Menu...");
        System.out.println("============================== Client Welcome Menu ==========================");
        System.out.println("1. Register");
        System.out.println("2. Login.");
        System.out.println("3. Return to Store System Screen.");
        System.out.println("4. Exit.");
        int choice = getValidatedInput(1, 4);

        switch (choice){
            case 1:
                ClientRegisterMenu();
                break;
            case 2:
                ClientLoginMenu();
                break;
            case 3:
                SystemMenu();
                break;
            case 4:
                ThankYouMenu();
                System.exit(0);
                break;
        }
    }

    public static void ClientLoginMenu() throws IOException {
        System.out.println("============================== Client - Login ==============================");
        sc.nextLine();

        System.out.print("Enter your username (or type 'esc' to go back): ");
        String username = sc.nextLine();
        if (username.equalsIgnoreCase("esc")) {
            ClientWelcomeMenu();
            return; // Exit the current method to prevent further execution
        }

        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        if (password.equalsIgnoreCase("esc")) {
            ClientWelcomeMenu();
            return;
        }

        client.login(username, password);

    }

    //Allow client to register
    public static void ClientRegisterMenu() throws IOException {
        System.out.println("============================== Client - Register ==============================");

        System.out.print("Enter your username (or type 'esc' to go back): ");
        String username = keyboard.nextLine();
        if (username.equalsIgnoreCase("esc")) {
            SystemMenu();
            return;
        }

        if(client.usernameValidation(username)) {
            System.out.println("Username existed. Moving you back to Client Welcome Menu");
            ClientWelcomeMenu();
        } else {
            System.out.print("Enter your password (or type 'esc' to go back): ");
            String password = keyboard.nextLine();
            if (password.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your full name (or type 'esc' to go back): ");
            String fullname = keyboard.nextLine();
            if (fullname.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your date of birth (or type 'esc' to go back): ");
            String dateOfBirth = keyboard.nextLine();
            if (dateOfBirth.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your email (or type 'esc' to go back): ");
            String email = keyboard.nextLine();
            if (email.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your address (or type 'esc' to go back): ");
            String address = keyboard.nextLine();
            if (address.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your phone number (or type 'esc' to go back): ");
            String phoneNumber = keyboard.nextLine();
            if (phoneNumber.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            client.register(fullname, dateOfBirth, address, phoneNumber, email, username, password);
            System.out.println("Registration completed!");
            ClientWelcomeMenu();
        }
    }

    // Client menu
    public static void ClientMenu() throws IOException {
        System.out.println("============================== Client - Menu ==================================");
        System.out.println("1. View and Update personal information.");
        System.out.println("2. List all cars and view product details.");

        int choice = getValidatedInput(1, 4);

        switch (choice){
            case 1:
                ClientUpdateMenu();
                break;
            case 2:
                ViewProductMenu();
                break;
            case 3:
                SystemMenu();
                break;
            case 4:
                ThankYouMenu();
                System.exit(0);
                break;
        }
    }

    //Client update data information
    public static void ClientUpdateMenu() throws IOException {
        System.out.println("============================== Client - View and Update Personal Information ==============================");
        client.viewClientInfo(client.getUsername());

        //Ask user for which update option
        System.out.println("----- What do you want to update? -----");
        System.out.println("1. Full name.");
        System.out.println("2. Date of birth.");
        System.out.println("3. Email.");
        System.out.println("4. Address.");
        System.out.println("5. Phone number.");
        System.out.println("6. Password.");
        System.out.println("7. No update, return to Client Menu.");
        System.out.println("8. Exit.");
        int choice = getValidatedInput(1, 8);

        System.out.println();
        client.clientUpdateInfo(choice, client.getUsername());
        ClientMenu();
    }

    //allow customer to view product with sorting options
    public static void ViewProductMenu() throws IOException {
        System.out.println("============================== Customer - View Product Detail ==============================");
        System.out.println("Would you like to sort your product?");
        System.out.println("1. View all product.");
        System.out.println("2. Ascending price.");
        System.out.println("3. Descending price.");
        System.out.println("4. By product category.");
        System.out.println("5. By price range.");
        System.out.println("6. Return to Customer Action Menu.");
        System.out.println("7. Exit.");
        System.out.print("Enter your option in NUMBER format (1-7): ");
        int option = getValidatedInput(1,7);

        switch (option){
            case 1:
                product.viewAllProductSort("none");
                ViewProductMenu();
                break;

            case 2:
                product.viewAllProductSort("ascending");
                ViewProductMenu();
                break;

            case 3:
                product.viewAllProductSort("descending");
                ViewProductMenu();
                break;

            case 4:
                System.out.println("Here is the category list: " + product.getCategoryList());
                System.out.print("Please enter a category for sorting: ");
                String category = keyboard.nextLine();

                System.out.println("How would you like to sort?");
                System.out.println("1. Ascending price.");
                System.out.println("2. Descending price.");
                System.out.println("3. None.");
                System.out.println("4. Return to View Product Detail Menu.");
                System.out.print("Enter your option in NUMBER format (1-3): ");
                option = keyboard.nextInt();
                System.out.println();

                switch (option){
                    case 1:
                        product.viewCategorySort(category,"ascending");
                        ViewProductMenu();
                        break;
                    case 2:
                        product.viewCategorySort(category,"descending");
                        ViewProductMenu();
                        break;
                    case 3:
                        product.viewCategorySort(category,"none");
                        ViewProductMenu();
                        break;
                    case 4:
                        ViewProductMenu();
                        break;
                }
                break;

            case 5:
                product.viewPriceRangeSort();
                ViewProductMenu();
                break;
            case 6:
                CustomerActionMenu();
                break;
            case 7:
                ThankYouMenu();
                break;
        }
    }

    public static void ThankYouMenu(){
        System.out.println("============================== Thank you for using our system! Goodbye ==============================");
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("AUTO168 CAR DEALERSHIP MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Mr. Dung Nguyen");
        System.out.println("Group: Javalorant :)");
        System.out.println("s3978862, Nguyen Minh Khoi");
        System.out.println("s3975939, Bui Phuong Dong Quan");
        System.out.println("s3980424, Lam Quang Nhat");
   }
}
