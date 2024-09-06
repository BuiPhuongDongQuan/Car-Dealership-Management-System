package Roles;

import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Salesperson extends User {

    public Salesperson() {
        super();
    }
    public Salesperson(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }

    //allow salesperson login
    public void login(String username, String password) throws IOException{
        readData();

        User user = new User();
        user = getUser(username);

        if(user == null){
            System.out.println("Manager not found. Please register to begin.");
            Menu.SystemMenu();
        } else{
            if(user.authenticate(username, password, "Mechanic")){;
                System.out.println("Login success! Welcome back Mechanic!");
                Menu.ManagerMenu();
            }
            else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.ManagerLoginMenu();
            }

        }
    }

    // view salesperson's information by username
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
        }
    }
}
