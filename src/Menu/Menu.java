package Menu;

import Components.Service;
import Roles.Employee;
import Roles.Client;
import Roles.Manager;
import Roles.Mechanic;
import Components.Car;

import java.io.*;
import java.util.*;


public class Menu {
    static Manager manager = new Manager();
    static Employee employee = new Employee();
    static Mechanic mechanic = new Mechanic();
    static Service service = new Service();
    static Client client = new Client();
    static Car car = new Car();

    static Scanner sc = new Scanner(System.in);



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
            case 2:
                EmployeeLoginMenu();
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
        System.out.println("1. View information of user ");
        System.out.println("2. Remove user from database");
        System.out.println("3. ");
        System.out.println("4. ");
    }
    ////////////////////////////////////Employee//////////////////////////////////////

    // employee login menu
    public static void EmployeeLoginMenu() throws IOException {
        System.out.println("============================== Employee - Login ===============================");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        employee.login(username, password);
    }

    // salesperson menu
    public static void SalespersonMenu() throws IOException {
        System.out.println("============================== Salesperson - Menu ===============================");
    }

    // mechanic menu
    public static void MechanicMenu() throws IOException {
        System.out.println("============================== Mechanic - Menu ==============================");
        System.out.println("1. View information");
        System.out.println("2. Change information");
        System.out.println("3. Perform statistics operator");
        int choice = getValidatedInput(1,3);

        switch (choice) {
            case 1:
                System.out.println("============================== Mechanic Information ==============================");
                mechanic.viewMechanicInfo(employee.getUsername());
                break;
            case 2:
                break;
            case 3:
                MechanicStatisticsOperator();
                break;
        }
    }
    // mechanic statistics operator menu
    public static void MechanicStatisticsOperator() throws IOException {
        System.out.println("============================== Mechanic Statistics Operator - Menu =============================");
        System.out.println("1. Calculate total revenue");
        System.out.println("2. Calculate revenue in a specific date");
        System.out.println("3. Calculate revenue in a month");
        System.out.println("4. Calculate revenue in a year");
        System.out.println("5. List the number of service in a specific date");
        System.out.println("6. List the number of service in a month");
        System.out.println("7. List the number of service in a year");
        int choice = getValidatedInput(1,7);

        switch (choice) {
            case 1:
                System.out.println("============================== Mechanic - Service Total Revenue ==============================");
                service.calculateServiceCost();
                Menu.MechanicStatisticsOperator();
                break;
            case 2:
                System.out.println("============================== Mechanic - Service Total Revenue ==============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String serviceDate = sc.nextLine();
                if (service.checkServiceDate(serviceDate) == true) {
                    service.calculateServiceCostDate(serviceDate);
                }
                Menu.MechanicStatisticsOperator();
                break;
            case 3:
                System.out.println("============================== Mechanic - Service Total Revenue =============================");
                System.out.println("Enter a month MM: ");
                String serviceMonth = sc.nextLine();
                service.calculateServiceCostMonth(serviceMonth);
                Menu.MechanicStatisticsOperator();
                break;
            case 4:
                System.out.println("============================== Mechanic - Service Total Revenue =============================");
                System.out.println("Enter a year yyyy: ");
                String serviceYear = sc.nextLine();
                service.calculateServiceCostYear(serviceYear);
                Menu.MechanicStatisticsOperator();
        }
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
        String username = sc.nextLine();
        if (username.equalsIgnoreCase("esc")) {
            SystemMenu();
            return;
        }

        if(client.usernameValidation(username)) {
            System.out.println("Username existed. Moving you back to Client Welcome Menu");
            ClientWelcomeMenu();
        } else {
            System.out.print("Enter your password (or type 'esc' to go back): ");
            String password = sc.nextLine();
            if (password.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your full name (or type 'esc' to go back): ");
            String fullname = sc.nextLine();
            if (fullname.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your date of birth (or type 'esc' to go back): ");
            String dateOfBirth = sc.nextLine();
            if (dateOfBirth.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your email (or type 'esc' to go back): ");
            String email = sc.nextLine();
            if (email.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your address (or type 'esc' to go back): ");
            String address = sc.nextLine();
            if (address.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your phone number (or type 'esc' to go back): ");
            String phoneNumber = sc.nextLine();
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
                ViewCartMenu();
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
    public static void ViewCartMenu() throws IOException {
        System.out.println("============================== Customer - View Car Categories ==============================");
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
                car.viewAllCarSort("none");
                ViewCartMenu();
                break;

            case 2:
                car.viewAllCarSort("ascending");
                ViewCartMenu();
                break;

            case 3:
                car.viewAllCarSort("descending");
                ViewCartMenu();
                break;

//            case 4:
//                System.out.println("Here is the category list: " + car.getCategoryList());
//                System.out.print("Please enter a category for sorting: ");
//                String category = sc.nextLine();
//
//                System.out.println("How would you like to sort?");
//                System.out.println("1. Ascending price.");
//                System.out.println("2. Descending price.");
//                System.out.println("3. None.");
//                System.out.println("4. Return to View Product Detail Menu.");
//                System.out.print("Enter your option in NUMBER format (1-3): ");
//                option = getValidatedInput(1,4);
//                System.out.println();
//
//                switch (option){
//                    case 1:
//                        car.viewCategorySort(category,"ascending");
//                        ViewCartMenu();
//                        break;
//                    case 2:
//                        car.viewCategorySort(category,"descending");
//                        ViewCartMenu();
//                        break;
//                    case 3:
//                        car.viewCategorySort(category,"none");
//                        ViewCartMenu();
//                        break;
//                    case 4:
//                        ViewCartMenu();
//                        break;
//                }
//                break;
//
//            case 5:
//                car.viewPriceRangeSort();
//                ViewCartMenu();
//                break;
            case 6:
                ClientMenu();
                break;
            case 7:
                ThankYouMenu();
                System.exit(0);
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
