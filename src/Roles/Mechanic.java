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

public class Mechanic extends User {

    public Mechanic() {
        super();
    }
    public Mechanic(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership ,username, password, totalSpending);
    }

    // allow mechanic login
    public void login(String username, String password) throws IOException{
        readData();

        User user = new User();
        user = getUser(username);

        if(user == null){
            System.out.println("Mechanic not found.");
            Menu.SystemMenu();
        } else{
            if(user.authenticate(username, password, "Mechanic")){;
                System.out.println("Login success! Welcome back Mechanic!");
                setUsername(username);
                Menu.MechanicMenu();
            }
            else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.MechanicLoginMenu();
            }

        }
    }

    // view mechanic's information by username
    public void viewMechanicInfo(String username) throws IOException {
        readData();
        System.out.println("Here is your information: ");
        for (User mechanic : users) {
            if (mechanic.getUsername().equals(username)) {
                System.out.println("Name: " + mechanic.getFullname() + "\n" +
                        "Date of Birth: " + mechanic.getDateOfBirth() + "\n" +
                        "Address: " + mechanic.getAddress() + "\n" +
                        "Phone number: " + mechanic.getPhoneNumber() +"\n" +
                        "Email: " + mechanic.getEmail());
            }
        }
    }

    public void mechanicUpdateInfo(int userInput, String username) throws IOException {
        Scanner sc = new Scanner(System.in);
        User mechanic = getUser(username);
        String oldContent = mechanic.getId() + "," + mechanic.getFullname() + "," + mechanic.getDateOfBirth() + "," + mechanic.getAddress() + "," + mechanic.getPhoneNumber() + "," + mechanic.getEmail() + "," + mechanic.getUserType() + "," + mechanic.getStatus() + "," + mechanic.getMembership() + "," + mechanic.getUsername() + "," + mechanic.getPassword() + "," + mechanic.getTotalSpending();

        String[] updateMechanicInfo = oldContent.split(",");
        String updatedContent = "";

        switch(userInput){
            // Update full name
            case 1:
                System.out.println("----- Full name update -----");
                System.out.print("Enter your new full name: ");
                String newFullname = sc.nextLine();

                updateMechanicInfo[1] = newFullname;
                updatedContent = Features.arrayToCSVString(updateMechanicInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update date of birth
            case 2:
                System.out.println("----- Date of birth update -----");
                System.out.print("Enter your new date of birth: ");
                String newDateOfBirth = sc.nextLine();

                updateMechanicInfo[2] = newDateOfBirth;
                updatedContent = Features.arrayToCSVString(updateMechanicInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update address
            case 3:
                System.out.println("----- Address update -----");
                System.out.print("Enter your new address: ");
                String newAddress = sc.nextLine();

                updateMechanicInfo[3] = newAddress;
                updatedContent = Features.arrayToCSVString(updateMechanicInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update phone number
            case 4:
                System.out.println("----- Phone number update -----");
                System.out.print("Enter your new phone number: ");
                String newPhoneNumber = sc.nextLine();

                updateMechanicInfo[4] = newPhoneNumber;
                updatedContent = Features.arrayToCSVString(updateMechanicInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update email
            case 5:
                System.out.println("----- Email update -----");
                System.out.print("Enter your new email: ");
                String newEmail = sc.nextLine();

                updateMechanicInfo[5] = newEmail;
                updatedContent = Features.arrayToCSVString(updateMechanicInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update password
            case 6:
                System.out.println("----- Password update -----");
                System.out.print("Enter your new password: ");
                String newPassword = sc.nextLine();

                updateMechanicInfo[10] = newPassword;
                updatedContent = Features.arrayToCSVString(updateMechanicInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;
            case 7:
                Menu.MechanicMenu();
                break;
        }
    }

    //validate mechanic
    public boolean validateMechanicID(String mechanicID){
        readData();
        boolean validatesalespersonID = false;
        for(User mechanic :users) {
            if (mechanic.getId().equals(mechanicID) && mechanic.getUserType().equals("Mechanic")) {
                validatesalespersonID = true;
            }
        }
        return validatesalespersonID;
    }

    public void viewMechanicIDandName(String mechanicID) throws IOException {
        readData();

        for(User mechanic :users) {
            if (mechanic.getId().equals(mechanicID)) {
                System.out.println("- Mechanic ID: " + mechanic.getId());
                System.out.println("- Name: " + mechanic.getFullname());
            }
        }
    }
}
