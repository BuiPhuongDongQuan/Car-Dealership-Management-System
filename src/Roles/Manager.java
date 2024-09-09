package Roles;

import Components.AutoPart;
import Components.Car;
import Components.Service;
import Components.saleTransaction;
import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.List;


public class Manager extends User {
    private final String car_data = "src/Database/Car.txt";
    private final String part_data = "src/Database/AutoPart.txt";

    public Manager() {
        super();
    }
    public Manager(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership ,username, password, totalSpending);
    }

    // allow manager login
    public void login(String username, String password) throws IOException{
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

    // manager view client information
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

    //manager view mechanic information
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

    //manager view salesperson information
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

    //manager view all components
    public void viewAllInformation(String filepath){
        Features.readAllLines(filepath);
    }

    //manager remove users
    public void removeUser(User user){
        String deleteUser = user.getId() + "," +user.getFullname() + "," +user.getDateOfBirth() + "," +user.getAddress() + "," +user.getPhoneNumber() + "," +user.getEmail() + "," + user.getUserType()+ "," +user.getStatus()+ "," + user.getMembership() +"," +user.getUsername() + "," +user.getPassword() + "," +user.getTotalSpending();
        Features.removeLine(user_data, deleteUser);
        System.out.println("User removed successfully.");
    }

    //manager remove cars
    public void removeCar(Car car){

        String formattedPrice = String.format("%,d", car.getPrice()).replace(",", ".");
        String formattedMileage = String.format("%.0f", car.getMileage()) + "km";
        String deleteCar =  car.getId() + "," + car.getMake() + "," + car.getModel() + "," + car.getYear() + "," +  formattedPrice + "," + car.getColor() + "," + formattedMileage + "," + car.getStatus();
        Features.removeLine(car_data, deleteCar);
        System.out.println("Car removed successfully.");
    }

    public void removePart(AutoPart part){
        String formattedPrice = String.format("$%.0f", part.getPrice());
        String deletePart = part.getId() + "," + part.getName() + "," + part.getBrand() + "," + part.getModel() + "," + part.getCondition() + "," + part.getWarranty() + "," + formattedPrice;
        Features.removeLine(part_data, deletePart);
        System.out.println("Part removed successfully.");
    }
}

