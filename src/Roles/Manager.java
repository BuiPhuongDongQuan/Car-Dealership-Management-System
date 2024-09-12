package Roles;

import Components.AutoPart;
import Components.Car;
import Components.Service;
import Components.saleTransaction;
import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The Manager class extends the User class and provides additional functionalities
 * for a manager such as viewing client, mechanic, and salesperson information,
 * adding/removing cars and auto parts, and managing users.
 */
public class Manager extends User {
    private final String car_data = "src/Database/Car.txt";
    private final String part_data = "src/Database/AutoPart.txt";

    public Manager() {
        super();
    }
    public Manager(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership ,username, password, totalSpending);
    }

    /**
     * Allow the manager to log in by verifying the username and password.
     * @param username The manager's username.
     * @param password The manager's password.
     * @throws IOException If an input or output error occurs.
     */    public void login(String username, String password) throws IOException{
        readData();

        User user = new User();
        user = getUser(username);

        if(user == null){
            System.out.println("Manager not found.");
            Menu.ManagerLoginMenu();
        }
        else{
            if(user.authenticate(username, password, "Manager")){;
                System.out.println("Login success! Welcome back manager!");
                Menu.ManagerMenu();
            }
            else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.ManagerLoginMenu();
            }

        }
    }

    /**
     * Manager views the information of all clients.
     * @throws IOException If an input or output error occurs.
     */
    public void viewClientInformation() throws IOException{
        readData();
        List<User> clients = getClients();

       if(clients.isEmpty()){
           System.out.println("No client found.");
       }else {
           System.out.println("============================== View Clients Information ==============================");
           System.out.println("Here is the list of client details:");
           System.out.println("=========================================================================");
           System.out.println("#ID, Fullname, Date of birth, Address, Phone Number, Email, Status, Username, Password");
           for(User client : clients){
               System.out.println(client.getId() + "," +client.getFullname() + "," +client.getDateOfBirth() + "," +client.getAddress() + "," +client.getPhoneNumber() + "," +client.getEmail() + "," +client.getStatus()+ "," +client.getUsername() + "," +client.getPassword());
           }
       }
    }

    /**
     * Manager views the information of all mechanics.
     * @throws IOException If an input or output error occurs.
     */
    public void viewMechanicInformation() throws IOException {
        readData();
        List<User> mechanics = getMechanics();

        if (mechanics.isEmpty()) {
            System.out.println("No mechanic found.");
        } else {
            System.out.println("============================== View Mechanics Information ==============================");
            System.out.println("Here is the list of mechanic details:");
            System.out.println("#ID, Fullname, Date of birth, Address, Phone Number, Email, Status, Username, Password");
            for (User mechanic : mechanics) {
                System.out.println(mechanic.getId() + "," +mechanic.getFullname() + "," +mechanic.getDateOfBirth() + "," +mechanic.getAddress() + "," +mechanic.getPhoneNumber() + "," +mechanic.getEmail() + "," +mechanic.getStatus()+ "," +mechanic.getUsername() + "," +mechanic.getPassword());
            }
        }
    }


    /**
     * Manager views the information of all salespersons.
     * @throws IOException If an input or output error occurs.
     */
    public void viewSalespersonInformation() throws IOException{
        readData();
        List<User> salespersons = getSalesperson();

        if (salespersons.isEmpty()) {
            System.out.println("No salesperson found.");
        } else {
            System.out.println("============================== View Salesperson Information =============================");
            System.out.println("Here is the list of salesperson details:");
            System.out.println("==========================================================================");
            System.out.println("#ID, Fullname, Date of birth, Address, Phone Number, Email, Status, Username, Password");
            for (User salesperson : salespersons) {
                System.out.println(salesperson.getId() + "," +salesperson.getFullname() + "," +salesperson.getDateOfBirth() + "," +salesperson.getAddress() + "," +salesperson.getPhoneNumber() + "," +salesperson.getEmail() + "," +salesperson.getStatus()+ "," +salesperson.getUsername() + "," +salesperson.getPassword());
            }
        }
    }

    /**
     * Manager views all component information from the provided file path.
     * @param filepath The path of the file to view the information.
     */
    public void viewAllInformation(String filepath){
        Features.readAllLines(filepath);
    }


    /**
     * Manager adds a new car to the system by entering car details.
     * @throws IOException If an input or output error occurs.
     */
    public void addCar() throws IOException{
        Scanner sc = new Scanner(System.in);
        int carCount = Features.countLine(car_data);
        String newCarId = "C" + carCount;

        //enter car make
        System.out.println("Enter Car Make: ");
        String newCarMake  = sc.nextLine();

        //enter car model
        System.out.println("Enter Car Model: ");
        String newCarModel = sc.nextLine();

        //enter car year
        System.out.println("Enter Car Year: ");
        String newCarYear = sc.nextLine();

        //enter car price
        System.out.println("Enter Car Price(xxx.xxx.xxx): ");
        String newCarPrice = sc.nextLine();

        //enter car color
        System.out.println("Enter Car Color: ");
        String newCarColor = sc.nextLine();

        //enter car mileage
        System.out.println("Enter Car Mileage(ex: 0km): ");
        String newCarMileage = sc.nextLine();

        //enter car status
        System.out.println("Enter Car Status(Available/Sold): ");
        String newCarStatus = sc.nextLine();

        String newCar = "\n" + newCarId + "," + newCarMake + "," + newCarModel + "," + newCarYear + "," + newCarPrice + "," + newCarColor + "," + newCarMileage + "," + newCarStatus;
        Features.writeToFile(car_data, newCar);
        System.out.println("New car added!");
    }

    /**
     * Manager adds a new auto part to the system by entering part details.
     */
    public void addPart(){
        Scanner sc = new Scanner(System.in);
        int partCount = Features.countLine(part_data);
        String newPartId = "P" + partCount;

        //enter Part name
        System.out.println("Enter Part Name: ");
        String newPartName  = sc.nextLine();

        //enter Part brand
        System.out.println("Enter Part Brand: ");
        String newPartBrand = sc.nextLine();

        //enter Part model
        System.out.println("Enter Part Model: ");
        String newPartModel = sc.nextLine();

        //enter Part condition
        System.out.println("Enter Part Condition: ");
        String newPartCondition = sc.nextLine();

        //enter Part warranty
        System.out.println("Enter Part Warranty: ");
        String newPartWarranty = sc.nextLine();

        //enter Part price
        System.out.println("Enter Part Price($xx): ");
        String newPartPrice = sc.nextLine();

        String newPart = "\n" + newPartId + "," + newPartName + "," + newPartBrand + "," + newPartModel + "," + newPartCondition + "," + newPartWarranty + "," + newPartPrice;
        Features.writeToFile(part_data, newPart);
        System.out.println("New part added!");
    }

    /**
     * Manager removes a user from the system.
     * @param user The user to be removed.
     */
    public void removeUser(User user){
        String deleteUser = user.getId() + "," +user.getFullname() + "," +user.getDateOfBirth() + "," +user.getAddress() + "," +user.getPhoneNumber() + "," +user.getEmail() + "," + user.getUserType()+ "," +user.getStatus()+ "," + user.getMembership() +"," +user.getUsername() + "," +user.getPassword() + "," +user.getTotalSpending();
        Features.removeLine(user_data, deleteUser);
        System.out.println("User removed successfully.");
    }

    /**
     * Manager removes a car from the system.
     * @param car The car to be removed.
     */
    public void removeCar(Car car){

        String formattedPrice = String.format("%,d", car.getPrice()).replace(",", ".");
        String formattedMileage = String.format("%.0f", car.getMileage()) + "km";
        String deleteCar =  car.getId() + "," + car.getMake() + "," + car.getModel() + "," + car.getYear() + "," +  formattedPrice + "," + car.getColor() + "," + formattedMileage + "," + car.getStatus();
        Features.removeLine(car_data, deleteCar);
        System.out.println("Car removed successfully.");
    }

    /**
     * Manager removes a user from the system.
     * @param part The autoPart to be removed.
     */
    public void removePart(AutoPart part){
        String formattedPrice = String.format("$%.0f", part.getPrice());
        String deletePart = part.getId() + "," + part.getName() + "," + part.getBrand() + "," + part.getModel() + "," + part.getCondition() + "," + part.getWarranty() + "," + formattedPrice;
        Features.removeLine(part_data, deletePart);
        System.out.println("Part removed successfully.");
    }
}
