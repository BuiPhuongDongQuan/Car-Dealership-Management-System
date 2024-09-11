package Roles;

import Features.Features;
import Menu.Menu;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Salesperson extends User {

    public Salesperson() {
        super();
    }
    public Salesperson(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership ,username, password, totalSpending);
    }
    ArrayList<Salesperson> salespersons = new ArrayList<>();

    /**
     * Validates if a given salesperson ID exists and is assigned to a salesperson.
     *
     * @param salespersonID The ID of the salesperson to validate.
     * @return True if the salesperson ID is valid; false otherwise.
     */
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

    /**
     * Displays the ID and name of a salesperson based on their ID.
     *
     * @param salespersonID The ID of the salesperson.
     * @throws IOException If an error occurs while reading data.
     */
    public void viewSalespersonIDandName(String salespersonID) throws IOException {
        readData();

        for (User salesperson : users) {
            if (salesperson.getId().equals(salespersonID)) {
                System.out.println("- Salesperon ID: " + salesperson.getId());
                System.out.println("- Name: " + salesperson.getFullname());
            }
        }
    }

    /**
     * Allows a salesperson to log in by verifying their username and password.
     * If successful, it redirects to the salesperson menu; otherwise, it retries login.
     *
     * @param username The username of the salesperson.
     * @param password The password of the salesperson.
     * @throws IOException If an error occurs while reading data.
     */
    public void login(String username, String password) throws IOException{
        readData();

        User user = new User();
        user = getUser(username);

        if(user == null){
            System.out.println("Salesperson not found.");
            Menu.SystemMenu();
        } else{
            if(user.authenticate(username, password, "Salesperson")){;
                System.out.println("Login success! Welcome back Salesperson!");
                setUsername(username);
                Menu.SalespersonMenu();
            }
            else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.SalespersonLoginMenu();
            }

        }
    }

    /**
     * Displays the current salesperson's information based on their username.
     *
     * @param username The username of the salesperson.
     * @throws IOException If an error occurs while reading data.
     */
    public void viewSalespersonInfo(String username) throws IOException {
        readData();
        System.out.println("Here is your information: ");
        for (User salesperson : users) {
            if (salesperson.getUsername().equals(username)) {
                System.out.println("Name: " + salesperson.getFullname() + "\n" +
                        "Date of Birth: " + salesperson.getDateOfBirth() + "\n" +
                        "Address: " + salesperson.getAddress() + "\n" +
                        "Phone number: " + salesperson.getPhoneNumber() +"\n" +
                        "Email: " + salesperson.getEmail());
            }
        }
    }

    /**
     * Allows a salesperson to update their information based on their input.
     *
     * @param userInput The type of information to update (1-6).
     * @param username  The username of the salesperson.
     * @throws IOException If an error occurs while reading or modifying data.
     */
    public void salespersonUpdateInfo(int userInput, String username) throws IOException {
        Scanner sc = new Scanner(System.in);
        User salesperson = getUser(username);
        String oldContent = salesperson.getId() + "," + salesperson.getFullname() + "," + salesperson.getDateOfBirth() + "," + salesperson.getAddress() + "," + salesperson.getPhoneNumber() + "," + salesperson.getEmail() + "," + salesperson.getUserType() + "," + salesperson.getStatus()+ "," + salesperson.getUsername()+ "," + salesperson.getPassword();

        String[] updateClientInfo = oldContent.split(",");
        String updatedContent = "";

        switch (userInput) {
            //update full name
            case 1:
                System.out.println("----- Full name update -----");
                System.out.print("Enter your new full name: ");
                String newFullname = sc.nextLine();

                updateClientInfo[1] = newFullname;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update date of birth
            case 2:
                System.out.println("----- Date of birth update -----");
                System.out.print("Enter your new date of birth: ");
                String newDateOfBirth = sc.nextLine();

                updateClientInfo[2] = newDateOfBirth;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update email
            case 3:
                System.out.println("----- Address update -----");
                System.out.print("Enter your new address: ");
                String newAddress = sc.nextLine();

                updateClientInfo[3] = newAddress;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update address
            case 4:
                System.out.println("----- Phone number update -----");
                System.out.print("Enter your new phone number: ");
                String newPhoneNumber = sc.nextLine();

                updateClientInfo[4] = newPhoneNumber;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update phone number
            case 5:
                System.out.println("----- Email update -----");
                System.out.print("Enter your new email: ");
                String newEmail = sc.nextLine();

                updateClientInfo[5] = newEmail;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update password
            case 6:
                System.out.println("----- Password update -----");
                System.out.print("Enter your new password: ");
                String newPassword = sc.nextLine();

                updateClientInfo[9] = newPassword;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;
            case 7:
                Menu.SalespersonMenu();
                break;
        }
    }
}
