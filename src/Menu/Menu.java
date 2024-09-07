package Menu;

import Components.Service;
import Roles.Client;
import Roles.Manager;
import Roles.Mechanic;
import Roles.Salesperson;
import Components.Car;
import Components.AutoPart;
import Components.ServiceType;
import Components.saleTransaction;


import java.io.*;


public class Menu {
    static Manager manager = new Manager();
    static Mechanic mechanic = new Mechanic();
    static Salesperson salesperson = new Salesperson();
    static Service service = new Service();
    static saleTransaction transaction = new saleTransaction();
    private static final String user_data = "src/Database/User.txt";
    static Client client = new Client();
    static Car car = new Car();
    static AutoPart autoPart = new AutoPart();
    static ServiceType serviceType = new ServiceType();
    static saleTransaction saleTransaction = new saleTransaction();

    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    // user input their choice
    public static int getValidatedInput(int min, int max) throws IOException {
        int choice = -1;
        while (true) {
            try {
                System.out.print("Enter Your Choice (" + min + "-" + max + "): ");
                String input = sc.readLine();
                choice = Integer.parseInt(input);

                if (choice >= min && choice <= max) {
                    break; // Valid input, exit the loop
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
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
                EmployeeMenu();
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

        // Clear any leftover newlines in the buffer
        sc.readLine();

        System.out.print("Enter your username (or type 'esc' to go back): ");
        String username = sc.readLine();
        if (username.equalsIgnoreCase("esc")) {
            SystemMenu();
            return; // Exit the current method to prevent further execution
        }

        System.out.print("Enter your password: ");
        String password = sc.readLine();
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
        System.out.println("3. Calculate total revenue");
        System.out.println("4. Calculate the revenue in a day/month/year");
        System.out.println("5. Calculate the revenue of the services done of a mechanic");
        System.out.println("6. Calculate the revenue of cars sold of a salesperson");
        int choice = getValidatedInput(1, 6);

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
                String mechanicID = sc.readLine();
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
        int choice = getValidatedInput(1, 5);

        switch (choice) {
            case 1:
                manager.viewInformation(user_data);
                ManagerMenu();
                break;
        }


    }
    ////////////////////////////////////Employee//////////////////////////////////////


    // manager calculate menu
    public static void managerCalculateMenu() throws IOException {
        System.out.println("============================== Manager - Menu Calculate ============================");
        System.out.println("1. Calculate the revenue in a day");
        System.out.println("2. Calculate the revenue in a month");
        System.out.println("3. Calculate the revenue in a year");
        int choice = getValidatedInput(1, 3);

        switch (choice) {
            case 1:
                System.out.println("============================== Manager - Service Total Revenue ==============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String serviceDate = sc.readLine();
                service.calculateServiceCostDate(serviceDate);
                System.out.print("============================== Manager - Transaction Total Revenue ==============================");
        }
    }
    // employee menu
    public static void EmployeeMenu() throws IOException {
        System.out.println("============================== Employee - Menu ===============================");
        System.out.println("1. Mechanic");
        System.out.println("2. Salesperson");
        System.out.println("3. Return to Store System Screen ");
        int choice = getValidatedInput(1, 3);

        switch (choice) {
            case 1:
                MechanicLoginMenu();
                break;
            case 2:
                SalespersonLoginMenu();
                break;
            case 3:
                SystemMenu();
                break;
        }
    }

//////////////////////////Salesperson/////////////////////////
    public static void SalespersonLoginMenu() throws IOException {
        System.out.println("============================== Employee - Login ===============================");
        System.out.print("Enter your username: ");
        String username = sc.readLine();
        System.out.print("Enter your password: ");
        String password = sc.readLine();

        salesperson.login(username, password);
    }

    // salesperson menu
    public static void SalespersonMenu() throws IOException {
        System.out.println("============================== Salesperson - Menu ===============================");
        System.out.println("1. View and update information");
        System.out.println("2. Perform statistics operator");
        System.out.println("3. Exit");
        int choice = getValidatedInput(1, 3);

        switch (choice) {
            case 1:
                viewAndUpdateSalespersonInfo();
                break;
            case 2:
                SalespersonStatisticsOperatorMenu();
                break;
            case 3:
                SystemMenu();
                break;
        }
    }

    //view and update salesperson information
    public static void viewAndUpdateSalespersonInfo() throws IOException {
        System.out.println("============================== Salesperson Information ==============================");
        salesperson.viewSalespersonInfo(salesperson.getUsername());

        System.out.println("============================== What do you want to update? =============================");
        System.out.println("1. Update Full name");
        System.out.println("2. Update Date of Birth");
        System.out.println("3. Update Address");
        System.out.println("4. Update Phone Number");
        System.out.println("5. Update Email");
        System.out.println("6. Update Password");
        System.out.println("7. Exit");
        int choice = getValidatedInput(1, 7);

        System.out.println();
        salesperson.salespersonUpdateInfo(choice, salesperson.getUsername());
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
        int choice = getValidatedInput(1, 8);

        switch (choice) {
            case 1:
                System.out.println("============================== Salesperson - Transaction Total Amount ==============================");
                transaction.calculateTotalAmount();
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 2:
                System.out.println("============================== Salesperson - Transaction Total Amount ==============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String transactionDate = sc.readLine();
                transaction.calculateTotalAmountDate(transactionDate);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 3:
                System.out.println("============================== Salesperson - Transaction Total Amount ==============================");
                System.out.print("Enter a month MM: ");
                String transactionMonth = sc.readLine();
                transaction.calculateTotalAmountMonth(transactionMonth);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 4:
                System.out.println("============================== Salesperson - Transaction Total Amount ==============================");
                System.out.print("Enter a year yyyy: ");
                String transactionYear = sc.readLine();
                transaction.calculateTotalAmountYear(transactionYear);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 5:
                System.out.println("============================== Salesperson - Transaction Detail ==============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String transactionDetailDate = sc.readLine();
                transaction.listAllTransactionsDate(transactionDetailDate);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 6:
                System.out.println("============================== Salesperson - Transaction Detail ==============================");
                System.out.print("Enter a month MM: ");
                String transactionDetailMonth = sc.readLine();
                transaction.listAllTransactionsMonth(transactionDetailMonth);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 7:
                System.out.println("============================== Salesperson - Transaction Detail ==============================");
                System.out.print("Enter a year yyyy: ");
                String transactionDetailYear = sc.readLine();
                transaction.listAllTransactionsYear(transactionDetailYear);
                Menu.SalespersonStatisticsOperatorMenu();
                break;
            case 8:
                Menu.SalespersonMenu();
                break;

        }
    }

///////////////////////////////////MECHANIC/////////////////////////////////////////////

    // mechanic login menu
    public static void MechanicLoginMenu() throws IOException {
        System.out.println("============================== Employee - Login ===============================");
        System.out.print("Enter your username: ");
        String username = sc.readLine();
        System.out.print("Enter your password: ");
        String password = sc.readLine();

        mechanic.login(username, password);
    }

    // mechanic menu
    public static void MechanicMenu() throws IOException {
        System.out.println("============================== Mechanic - Menu ==============================");
        System.out.println("1. View and update information");
        System.out.println("2. Perform statistics operator");
        System.out.println("3. Exit");
        int choice = getValidatedInput(1,3);

        switch (choice) {
            case 1:
                viewAndUpdateMechanicInfo();
                break;
            case 2:
                MechanicStatisticsOperator();
                break;
            case 3:
                Menu.SalespersonMenu();
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
        int choice = getValidatedInput(1, 8);

        switch (choice) {
            case 1:
                System.out.println("============================== Mechanic - Service Total Revenue ==============================");
                service.calculateServiceCost();
                Menu.MechanicStatisticsOperator();
                break;
            case 2:
                System.out.println("============================== Mechanic - Service Total Revenue ==============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String serviceDate = sc.readLine();
                service.calculateServiceCostDate(serviceDate);
                Menu.MechanicStatisticsOperator();
                break;
            case 3:
                System.out.println("============================== Mechanic - Service Total Revenue =============================");
                System.out.print("Enter a month MM: ");
                String serviceMonth = sc.readLine();
                service.calculateServiceCostMonth(serviceMonth);
                Menu.MechanicStatisticsOperator();
                break;
            case 4:
                System.out.println("============================== Mechanic - Service Total Revenue =============================");
                System.out.print("Enter a year yyyy: ");
                String serviceYear = sc.readLine();
                service.calculateServiceCostYear(serviceYear);
                Menu.MechanicStatisticsOperator();
                break;
            case 5:
                System.out.println("============================== Mechanic - Service Detail =============================");
                System.out.print("Enter a date dd-MM-yyyy: ");
                String serviceDetailDate = sc.readLine();
                service.listAllServicesDate(serviceDetailDate);
                Menu.MechanicStatisticsOperator();
                break;
            case 6:
                System.out.println("============================== Mechanic - Service Detail =============================");
                System.out.print("Enter a month MM: ");
                String serviceDetailMonth = sc.readLine();
                service.listAllServicesMonth(serviceDetailMonth);
                Menu.MechanicStatisticsOperator();
                break;
            case 7:
                System.out.println("============================== Mechanic - Service Detail =============================");
                System.out.print("Enter a year yyyy: ");
                String serviceDetailYear = sc.readLine();
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
        mechanic.viewMechanicInfo(mechanic.getUsername());

        System.out.println("============================== What do you want to update? =============================");
        System.out.println("1. Update Full name");
        System.out.println("2. Update Date of Birth");
        System.out.println("3. Update Address");
        System.out.println("4. Update Phone Number");
        System.out.println("5. Update Email");
        System.out.println("6. Update Password");
        System.out.println("7. Exit");
        int choice = getValidatedInput(1, 7);

        System.out.println();
        mechanic.mechanicUpdateInfo(choice, mechanic.getUsername());
        MechanicMenu();
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
        System.out.print("Enter your username (or type 'esc' to go back): ");
        String username = sc.readLine();
        if (username.equalsIgnoreCase("esc")) {
            ClientWelcomeMenu();
            return; // Exit the current method to prevent further execution
        }

        System.out.print("Enter your password: ");
        String password = sc.readLine();
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
        String username = sc.readLine();
        if (username.equalsIgnoreCase("esc")) {
            SystemMenu();
            return;
        }

        if(client.usernameValidation(username)) {
            System.out.println("Username existed. Moving you back to Client Welcome Menu");
            ClientWelcomeMenu();
        } else {
            System.out.print("Enter your password (or type 'esc' to go back): ");
            String password = sc.readLine();
            if (password.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your full name (or type 'esc' to go back): ");
            String fullname = sc.readLine();
            if (fullname.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your date of birth (or type 'esc' to go back): ");
            String dateOfBirth = sc.readLine();
            if (dateOfBirth.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your email (or type 'esc' to go back): ");
            String email = sc.readLine();
            if (email.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your address (or type 'esc' to go back): ");
            String address = sc.readLine();
            if (address.equalsIgnoreCase("esc")) {
                SystemMenu();
                return;
            }

            System.out.print("Enter your phone number (or type 'esc' to go back): ");
            String phoneNumber = sc.readLine();
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
        System.out.println("2. View membership and reward.");
        System.out.println("3. List all cars and view car details.");
        System.out.println("4. List all services.");
        System.out.println("5. List all auto parts.");
        System.out.println("6. Create car orders.");
        System.out.println("7. Create service order.");
        System.out.println("8. Create auto parts order.");
        System.out.println("9. Look for specific order with order ID.");
        System.out.println("10. Get information of all orders made.");
        System.out.println("11. Exit.");
        int choice = getValidatedInput(1,11);

        switch (choice){
            case 1:
                ClientUpdateMenu();
                break;
            case 2:
                System.out.println("============================== Membership Status ==============================");
                client.viewMembership(client.getUsername());
                ClientMenu();
                break;
            case 3:
                ViewCarMenu();
                break;
            case 4:
                System.out.println("============================== Services Categories ==============================");
                serviceType.viewAllServiceSort("none");
                ClientMenu();
                break;
            case 5:
                System.out.println("============================== Auto Part Categories ==============================");
                autoPart.viewAllAutoPartSort("none");
                ClientMenu();
                break;
            case 6:
                System.out.println("============================== Create Car Order =============================");
                CreateCarTransactionMenu();
                break;
            case 7:
                System.out.println("============================== Create Service Order =============================");
                CreateServiceTransactionMenu();
                break;
            case 8:
                System.out.println("============================== Create Auto part Order =============================");
                CreateAutoPartTransactionMenu();
                break;
            case 9:
                break;
            case 11:
                ThankYouMenu();
                System.exit(0);
                break;
        }
    }

    //Client update data information
    public static void ClientUpdateMenu() throws IOException {
        System.out.println("============================== View and Update Personal Information ==============================");
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
    public static void ViewCarMenu() throws IOException {
        System.out.println("============================== View Car Categories ==============================");
        System.out.println("Would you like to sort car?");
        System.out.println("1. View all cars.");
        System.out.println("2. Ascending price.");
        System.out.println("3. Descending price.");
        System.out.println("4. By car brand.");
        System.out.println("5. By car status.");
        System.out.println("6. By price range.");
        System.out.println("7. Return to Customer Action Menu.");
        System.out.println("8. Exit.");
        int option = getValidatedInput(1, 8);

        switch (option) {
            case 1:
                car.viewAllCarSort("none");
                break;

            case 2:
                car.viewAllCarSort("ascending");
                break;

            case 3:
                car.viewAllCarSort("descending");
                break;

            case 4:
                System.out.println("Here is the category list: " + car.getCarBrandList());
                System.out.print("Please enter a category for sorting: ");
                String category = sc.readLine();

                System.out.println("How would you like to sort?");
                System.out.println("1. View all cars.");
                System.out.println("1. Ascending price.");
                System.out.println("2. Descending price.");
                System.out.println("4. Return to View Car Detail Menu.");
                option = getValidatedInput(1, 4);
                System.out.println();

                switch (option) {
                    case 1:
                        car.viewCarBrandSort(category, "none");
                        break;
                    case 2:
                        car.viewCarBrandSort(category, "ascending");
                        break;
                    case 3:
                        car.viewCarBrandSort(category, "descending");
                        break;
                    case 4:
                        ViewCarMenu();
                        return;
                }
                break;

            case 5:
                System.out.println("Please enter 1 for available cars and 2 for sold cars:");
                int statusOption = getValidatedInput(1, 2);
                String status = (statusOption == 1) ? "Available" : "Sold";

                System.out.println("How would you like to sort?");
                System.out.println("1. View all cars.");
                System.out.println("1. Ascending price.");
                System.out.println("2. Descending price.");
                System.out.println("4. Return to View Car Detail Menu.");
                int statusSortOption = getValidatedInput(1, 4);

                switch (statusSortOption) {
                    case 1:
                        car.viewCarStatusSort(status, "none");
                        break;
                    case 2:
                        car.viewCarStatusSort(status, "ascending");
                        break;
                    case 3:
                        car.viewCarStatusSort(status, "descending");
                        break;
                    case 4:
                        ViewCarMenu();
                        return;
                }
                break;

            case 6:
                car.viewPriceRangeSort();
                ViewCarMenu();
                return;

            case 7:
                ClientMenu();
                return;

            case 8:
                ThankYouMenu();
                System.exit(0);
                return;
        }

        // Return to the menu after handling sorting or navigating
        ViewCarMenu();
    }

    //allow client to create car order
    public static void CreateCarTransactionMenu() throws IOException {
        System.out.print("Enter the car ID you wanted to order (or type 'esc' to go back): ");
        String carID = sc.readLine();
        if (carID.equalsIgnoreCase("esc")) {
            ClientMenu();
            return; // Exit the current method to prevent further execution
        }

        System.out.print("Enter the salesperson ID that serve you (or type 'esc' to go back): ");
        String salespersonID = sc.readLine();
        if (salespersonID.equalsIgnoreCase("esc")) {
            ClientMenu();
            return; // Exit the current method to prevent further execution
        }

        if(car.validateCarID(carID) == true && salesperson.validateSalespersonID(salespersonID) == true){
            System.out.println("Here is the salesperson name: ");
            salesperson.viewSalespersonIDandName(salespersonID);

            System.out.println("Here is the car info: ");
            car.printCarInfo(carID);

            saleTransaction.createOrder(client.getUser(client.getUsername()), car.getCar(carID), null, salespersonID);
        } else if (salesperson.validateSalespersonID(salespersonID) == false) {
            System.out.println(salespersonID);
            System.out.println("Salesperson not found.");
            CreateCarTransactionMenu();
        } else{
            System.out.println("Car not found.");
            CreateCarTransactionMenu();
        }
    }

    public static void CreateAutoPartTransactionMenu() throws IOException {
        System.out.print("Enter the auto part ID you want to order (or type 'esc' to go back): ");
        String autoPartID = sc.readLine();
        if (autoPartID.equalsIgnoreCase("esc")) {
            ClientMenu();
            return; // Exit the current method to prevent further execution
        }

        System.out.print("Enter the salesperson ID that served you (or type 'esc' to go back): ");
        String salespersonID = sc.readLine();
        if (salespersonID.equalsIgnoreCase("esc")) {
            ClientMenu();
            return; // Exit the current method to prevent further execution
        }

        if (autoPart.validateAutoPartID(autoPartID) && salesperson.validateSalespersonID(salespersonID)) {
            System.out.println("Here is the salesperson name: ");
            salesperson.viewSalespersonIDandName(salespersonID);

            System.out.println("Here is the auto part info: ");
            autoPart.printAutoPartInfo(autoPartID);

            saleTransaction.createOrder(client.getUser(client.getUsername()), null, autoPart.getautoPart(autoPartID), salespersonID);
        } else if (!salesperson.validateSalespersonID(salespersonID)) {
            System.out.println("Salesperson not found.");
            CreateAutoPartTransactionMenu();
        } else {
            System.out.println("Auto part not found.");
            CreateAutoPartTransactionMenu();
        }
    }

    public static void CreateServiceTransactionMenu() throws IOException {
        System.out.print("Enter the service ID you wanted to order (or type 'esc' to go back): ");
        String serviceTypeID = sc.readLine();
        if (serviceTypeID.equalsIgnoreCase("esc")) {
            ClientMenu();
            return; // Exit the current method to prevent further execution
        }

        System.out.print("Enter the mechanic ID that serve you (or type 'esc' to go back): ");
        String mechanicID = sc.readLine();
        if (mechanicID.equalsIgnoreCase("esc")) {
            ClientMenu();
            return; // Exit the current method to prevent further execution
        }

        if(serviceType.validateServiceID(serviceTypeID) == true && mechanic.validateMechanicID(mechanicID) == true){
            System.out.println("Here is the mechanic name: ");
            mechanic.viewMechanicIDandName(mechanicID);

            System.out.println("Here is the service info: ");
            serviceType.printServiceInfo(serviceTypeID);

            service.createOrder(client.getUser(client.getUsername()), serviceType.getService(serviceTypeID), mechanicID);
        } else if (salesperson.validateSalespersonID(mechanicID) == false) {
            System.out.println(mechanicID);
            System.out.println("Salesperson not found.");
            CreateCarTransactionMenu();
        } else{
            System.out.println("Car not found.");
            CreateCarTransactionMenu();
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
