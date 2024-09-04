package Roles;


import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Mechanic extends Employee {

    public Mechanic() {
        super();
    }
    public Mechanic(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
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
        Scanner keyboard = new Scanner(System.in);
        User client = getUser(username);
        String oldContent = client.getId() + "," + client.getFullname() + "," + client.getDateOfBirth() + "," + client.getAddress() + "," + client.getPhoneNumber() + "," + client.getEmail() + "," + client.getUserType() + "," + client.getStatus()+ "," + client.getUsername()+ "," + client.getPassword();

        String[] updateClientInfo = oldContent.split(",");
        String updatedContent = "";

        switch(userInput){
            //update full name
            case 1:
                System.out.println("----- Full name update -----");
                System.out.print("Enter your new full name: ");
                String newFullname = keyboard.nextLine();

                updateClientInfo[1] = newFullname;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update date of birth
            case 2:
                System.out.println("----- Date of birth update -----");
                System.out.print("Enter your new date of birth: ");
                String newDateOfBirth = keyboard.nextLine();

                updateClientInfo[2] = newDateOfBirth;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update email
            case 3:
                System.out.println("----- Address update -----");
                System.out.print("Enter your new address: ");
                String newAddress = keyboard.nextLine();

                updateClientInfo[3] = newAddress;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update address
            case 4:
                System.out.println("----- Phone number update -----");
                System.out.print("Enter your new phone number: ");
                String newPhoneNumber = keyboard.nextLine();

                updateClientInfo[4] = newPhoneNumber;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update phone number
            case 5:
                System.out.println("----- Email update -----");
                System.out.print("Enter your new email: ");
                String newEmail = keyboard.nextLine();

                updateClientInfo[5] = newEmail;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update password
            case 6:
                System.out.println("----- Password update -----");
                System.out.print("Enter your new password: ");
                String newPassword = keyboard.nextLine();

                updateClientInfo[9] = newPassword;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;
            case 7:
                Menu.MechanicMenu();
        }
    }

}
