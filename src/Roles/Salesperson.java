/*
  RMIT University Vietnam
  Course: COSC2081 Programming 1
  Semester: 2024B
  Group Assignment
  Author: Javalorant
  ID: s3978862, s3975939, s3980424
*/

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
        String oldContent = salesperson.getId() + "," + salesperson.getFullname() + "," + salesperson.getDateOfBirth() + "," + salesperson.getAddress() + "," + salesperson.getPhoneNumber() + "," + salesperson.getEmail() + "," + salesperson.getUserType() + "," + salesperson.getStatus() + "," + salesperson.getMembership() + "," + salesperson.getUsername() + "," + salesperson.getPassword() + "," + salesperson.getTotalSpending();

        String[] updateSalespersonInfo = oldContent.split(",");
        String updatedContent = "";

        switch(userInput){
            // Update full name
            case 1:
                System.out.println("----- Full name update -----");
                System.out.print("Enter your new full name: ");
                String newFullname = sc.nextLine();

                updateSalespersonInfo[1] = newFullname;
                updatedContent = Features.arrayToCSVString(updateSalespersonInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update date of birth
            case 2:
                System.out.println("----- Date of birth update -----");
                System.out.print("Enter your new date of birth: ");
                String newDateOfBirth = sc.nextLine();

                updateSalespersonInfo[2] = newDateOfBirth;
                updatedContent = Features.arrayToCSVString(updateSalespersonInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update address
            case 3:
                System.out.println("----- Address update -----");
                System.out.print("Enter your new address: ");
                String newAddress = sc.nextLine();

                updateSalespersonInfo[3] = newAddress;
                updatedContent = Features.arrayToCSVString(updateSalespersonInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update phone number
            case 4:
                System.out.println("----- Phone number update -----");
                System.out.print("Enter your new phone number: ");
                String newPhoneNumber = sc.nextLine();

                updateSalespersonInfo[4] = newPhoneNumber;
                updatedContent = Features.arrayToCSVString(updateSalespersonInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update email
            case 5:
                System.out.println("----- Email update -----");
                System.out.print("Enter your new email: ");
                String newEmail = sc.nextLine();

                updateSalespersonInfo[5] = newEmail;
                updatedContent = Features.arrayToCSVString(updateSalespersonInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update password
            case 6:
                System.out.println("----- Password update -----");
                System.out.print("Enter your new password: ");
                String newPassword = sc.nextLine();

                updateSalespersonInfo[10] = newPassword;
                updatedContent = Features.arrayToCSVString(updateSalespersonInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;
            case 7:
                Menu.SalespersonMenu();
                break;
        }
    }
}
