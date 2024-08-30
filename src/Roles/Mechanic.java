package Roles;


import Features.Features;

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

    public void updateInfo(int userInput, String username) throws IOException {
        Scanner sc = new Scanner(System.in);
        User mechanic = getUser(username);
        if (mechanic == null) {
            System.out.println("User not found.");
            return;
        }

        String oldData = mechanic.getId() + "," + mechanic.getFullname() + "," + mechanic.getDateOfBirth() + "," + mechanic.getAddress() + "," + mechanic.getPhoneNumber() + "," + mechanic.getEmail() + "," + mechanic.getUserType() + "," + mechanic.getStatus() + "," + mechanic.getUser(username) + "," + mechanic.getPassword();
        System.out.println("Old data is: " + oldData);

        String[] updateInfo = oldData.split(",");
        String updatedData = "";

        switch(userInput) {
            case 1:
                System.out.println("----- Update Fullname -----");
                System.out.print("Enter new fullname: ");
                String newFullname = sc.nextLine();
                updateInfo[1] = newFullname;
                updatedData = Features.arrayToCSVString(updateInfo);
                Features.modifyFile(user_data, oldData, updatedData);
                break;

            case 2:
                System.out.println("----- Update Email -----");
                System.out.print("Enter new email: ");
                String newEmail = sc.nextLine();
                updateInfo[5] = newEmail;
                updatedData = Features.arrayToCSVString(updateInfo);
                Features.modifyFile(user_data, oldData, updatedData);
                break;

            case 3:
                System.out.println("----- Update Address -----");
                System.out.print("Enter new address: ");
                String newAddress = sc.nextLine();
                updateInfo[3] = newAddress;
                updatedData = Features.arrayToCSVString(updateInfo);
                Features.modifyFile(user_data, oldData, updatedData);
                break;

            case 4:
                System.out.println("----- Update Phone Number -----");
                System.out.print("Enter new phone number: ");
                String newPhoneNumber = sc.nextLine();
                updateInfo[4] = newPhoneNumber;
                updatedData = Features.arrayToCSVString(updateInfo);
                Features.modifyFile(user_data, oldData, updatedData);
                break;

            case 5:
                System.out.println("----- Update Password -----");
                System.out.print("Enter new password: ");
                String newPassword = sc.nextLine();
                updateInfo[9] = newPassword;
                updatedData = Features.arrayToCSVString(updateInfo);
                Features.modifyFile(user_data, oldData, updatedData);
                break;
        }

        System.out.println("New data is: " + updatedData);

    }

}
