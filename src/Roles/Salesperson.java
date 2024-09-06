package Roles;

import Menu.Menu;

import java.io.IOException;
import java.util.ArrayList;

public class Salesperson extends User {

    public Salesperson() {
        super();
    }
    public Salesperson(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership ,username, password, totalSpending);
    }
    ArrayList<Salesperson> salespersons = new ArrayList<>();

    public boolean validateSalespersonID(String salespersonID){
        readData();
        boolean validatesalespersonID = false;
        for(User salesperson:users) {
            if (salesperson.getId().equals(salespersonID) && salesperson.getUserType().equals("Salesperson")) {
                validatesalespersonID = true;
            }
        }
        return validatesalespersonID;
    }

    public void viewSalespersonInfo(String salespersonID){
        readData();

        for(User salesperson:users){
            if(salesperson.getId().equals(salespersonID)){
                System.out.println("- Salesperon ID: " + salesperson.getId());
                System.out.println("- Name: " + salesperson.getFullname());
            }
        }
    }
}
