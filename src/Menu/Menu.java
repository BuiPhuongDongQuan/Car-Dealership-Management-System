package Menu;

import Components.Service;
import Components.saleTransaction;
import Roles.*;

import java.io.*;
import java.util.*;


public class Menu {
    static Manager manager = new Manager();
    static Employee employee = new Employee();
    static Mechanic mechanic = new Mechanic();
    static Salesperson salesperson = new Salesperson();
    static Service service = new Service();
    static saleTransaction transaction = new saleTransaction();

    private static final String user_data = "src/Database/User.txt";

    static Scanner sc = new Scanner(System.in);




    // user input their choice
    public static int optionInput(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Choice: ");
        int optionInput = sc.nextInt();
        return optionInput;
    }

    public static void systemMenu() throws IOException {
        System.out.println("============================== System Menu - Welcome to Auto316 Management System ==============================");
        System.out.println("Please let us know who you are: ");
        System.out.println("1. Manager");
        System.out.println("2. Employee");
        System.out.println("3. Client");
        System.out.println("4. Exit");
        int choice = optionInput();

        switch (choice) {
            case 1:
                ManagerLoginMenu();
                break;
            case 2:
                EmployeeLoginMenu();
                break;

        }

    }


/////////////////////////////////////MANAGER////////////////////////////////////////////////
    // manager login menu
    public static void ManagerLoginMenu() throws IOException {
        System.out.println("============================== Manager - Login ==============================");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        manager.login(username, password);
    }

    // manager menu
    public static void ManagerMenu() throws IOException {
        System.out.println("============================== Manager - Menu ==============================");
        System.out.println("Welcome admin to Auto316 Management System");
        System.out.println("1. View information of user ");
        System.out.println("2. Remove user from database");
        System.out.println("3. Calculate total revenue");
        System.out.println("4. Calculate the revenue in a day/month/year");
        System.out.println("5. Calculate the revenue of the services done of a mechanic");
        System.out.println("6. Calculate the revenue of cars sold of a salesperson");
        int choice = optionInput();

        switch (choice) {
            case 1:
                viewInfoMenu();
                break;
            case 2:
                break;
            case 3:
                System.out.println("============================== Manager - Service Total Revenue ==============================");
                service.calculateServiceCost();
                Menu.MechanicStatisticsOperator();
                break;
            case 4:
                managerCalculateMenu();
                break;
            case 5:
                System.out.println("============================== Manager - Revenue of a Mechanic ==============================");
                System.out.print("Enter Mechanic ID: ");
                String mechanicID = sc.nextLine();
                if (service.getMechanicId() != null && service.getMechanicId().equals(mechanicID)) {
                    service.calculateServiceCost();
                }

        }
    }

    // view information menu
    public static void viewInfoMenu() throws IOException {
        System.out.println("============================== View Information Menu ==============================");
        System.out.println("1. View User Information");
        System.out.println("2. View Car Information");
        System.out.println("3. View Auto Part Information");
        System.out.println("4. View Service Information");
        System.out.println("5. View Sale Transaction Information");
        int choice = optionInput();

        switch (choice) {
            case 1:
                manager.viewInformation(user_data);
                ManagerMenu();
                break;
        }


    }


    // manager calculate menu
    public static void managerCalculateMenu() throws IOException {
        System.out.println("============================== Manager - Menu Calculate ============================");
        System.out.println("1. Calculate the revenue in a day");
        System.out.println("2. Calculate the revenue in a month");
        System.out.println("3. Calculate the revenue in a year");
        int choice = optionInput();

        switch (choice) {
            case 1:
                System.out.println("============================== Manager - Service Total Revenue ==============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String serviceDate = sc.nextLine();
                service.calculateServiceCostDate(serviceDate);
                System.out.print("============================== Manager - Transaction Total Revenue ==============================");
        }
    }

////////////////////////EMPLOYEE////////////////////
    // employee login menu
    public static void EmployeeLoginMenu() throws IOException {
        System.out.println("============================== Employee - Login ===============================");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        employee.login(username, password);
    }

//////////////////////Salesperson////////////////////////
    // salesperson menu
    public static void SalespersonMenu() throws IOException {
        System.out.println("============================== Salesperson - Menu ===============================");
        System.out.println("1. View and update information");
        System.out.println("2. Perform statistics operator");
        System.out.println("3. Exit");
        int choice = optionInput();

        switch (choice) {
            case 1:
                viewAndUpdateSalespersonInfo();
                break;
            case 2:
                SalespersonStatisticsOperatorMenu();
                break;
            case 3:
                systemMenu();
                break;
        }
    }

    //view and update salesperson information
    public static void viewAndUpdateSalespersonInfo() throws IOException {
        System.out.println("============================== Salesperson Information ==============================");
        salesperson.viewSalespersonInfo(employee.getUsername());

        System.out.println("============================== What do you want to update? =============================");
        System.out.println("1. Update Full name");
        System.out.println("2. Update Date of Birth");
        System.out.println("3. Update Address");
        System.out.println("4. Update Phone Number");
        System.out.println("5. Update Email");
        System.out.println("6. Update Password");
        System.out.println("7. Exit");
        int choice = optionInput();

        System.out.println();
        salesperson.salespersonUpdateInfo(choice, employee.getUsername());
        SalespersonMenu();
    }

    // salesperson statistics operator menu
    public static void SalespersonStatisticsOperatorMenu() throws IOException {
        System.out.println("============================== Salesperson Statistics Operator - Menu =============================");
        System.out.println("1. Calculate total amount");
        System.out.println("2. Calculate amount in a specific date");
        System.out.println("3. Calculate amount in a month");
        System.out.println("4. Calculate amount in a year");
        System.out.println("5. List the number of transaction in a specific date");
        System.out.println("6. List the number of transaction in a month");
        System.out.println("7. List the number of transaction in a year");
        System.out.println("8. Exit");
        int choice = optionInput();

        switch (choice) {
            case 1:
                System.out.println("============================== Salesperson - Transaction Total Amount ==============================");
                transaction.calculateTotalAmount();
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 2:
                System.out.println("============================== Salesperson - Transaction Total Amount ==============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String transactionDate = sc.nextLine();
                transaction.calculateTotalAmountDate(transactionDate);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 3:
                System.out.println("============================== Salesperson - Transaction Total Amount ==============================");
                System.out.print("Enter a month MM: ");
                String transactionMonth = sc.nextLine();
                transaction.calculateTotalAmountMonth(transactionMonth);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 4:
                System.out.println("============================== Salesperson - Transaction Total Amount ==============================");
                System.out.print("Enter a year yyyy: ");
                String transactionYear = sc.nextLine();
                transaction.calculateTotalAmountYear(transactionYear);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 5:
                System.out.println("============================== Salesperson - Transaction Detail ==============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String transactionDetailDate = sc.nextLine();
                transaction.listAllTransactionsDate(transactionDetailDate);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 6:
                System.out.println("============================== Salesperson - Transaction Detail ==============================");
                System.out.print("Enter a month MM: ");
                String transactionDetailMonth = sc.nextLine();
                transaction.listAllTransactionsMonth(transactionDetailMonth);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 7:
                System.out.println("============================== Salesperson - Transaction Detail ==============================");
                System.out.print("Enter a year yyyy: ");
                String transactionDetailYear = sc.nextLine();
                transaction.listAllTransactionsYear(transactionDetailYear);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 8:
                Menu.SalespersonMenu();
                break;

        }
    }

///////////////////////////////////MECHANIC/////////////////////////////////////////////

    // mechanic menu
    public static void MechanicMenu() throws IOException {
        System.out.println("============================== Mechanic - Menu ==============================");
        System.out.println("1. View and update information");
        System.out.println("2. Perform statistics operator");
        int choice = optionInput();

        switch (choice) {
            case 1:
                viewAndUpdateMechanicInfo();
                break;
            case 2:
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
        System.out.println("8. Exit");
        int choice = optionInput();

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
                service.calculateServiceCostDate(serviceDate);
                Menu.MechanicStatisticsOperator();
                break;
            case 3:
                System.out.println("============================== Mechanic - Service Total Revenue =============================");
                System.out.print("Enter a month MM: ");
                String serviceMonth = sc.nextLine();
                service.calculateServiceCostMonth(serviceMonth);
                Menu.MechanicStatisticsOperator();
                break;
            case 4:
                System.out.println("============================== Mechanic - Service Total Revenue =============================");
                System.out.print("Enter a year yyyy: ");
                String serviceYear = sc.nextLine();
                service.calculateServiceCostYear(serviceYear);
                Menu.MechanicStatisticsOperator();
                break;
            case 5:
                System.out.println("============================== Mechanic - Service Detail =============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String serviceDetailDate = sc.nextLine();
                service.listAllServicesDate(serviceDetailDate);
                Menu.MechanicStatisticsOperator();
                break;
            case 6:
                System.out.println("============================== Mechanic - Service Detail =============================");
                System.out.print("Enter a month MM: ");
                String serviceDetailMonth = sc.nextLine();
                service.listAllServicesMonth(serviceDetailMonth);
                Menu.MechanicStatisticsOperator();
                break;
            case 7:
                System.out.println("============================== Mechanic - Service Detail =============================");
                System.out.print("Enter a year yyyy: ");
                String serviceDetailYear = sc.nextLine();
                service.listAllServicesYear(serviceDetailYear);
                Menu.MechanicStatisticsOperator();
                break;
            case 8:
                Menu.MechanicMenu();
                break;
        }
    }

    // view and update mechanic information
    public static void viewAndUpdateMechanicInfo() throws IOException {
        System.out.println("============================== Mechanic Information ==============================");
        mechanic.viewMechanicInfo(employee.getUsername());

        System.out.println("============================== What do you want to update? =============================");
        System.out.println("1. Update Full name");
        System.out.println("2. Update Date of Birth");
        System.out.println("3. Update Address");
        System.out.println("4. Update Phone Number");
        System.out.println("5. Update Email");
        System.out.println("6. Update Password");
        System.out.println("7. Exit");
        int choice = optionInput();

        System.out.println();
        mechanic.mechanicUpdateInfo(choice, employee.getUsername());
        MechanicMenu();
    }
}
