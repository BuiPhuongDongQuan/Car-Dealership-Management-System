package Roles;


import java.io.IOException;
import java.util.Scanner;

public class Mechanic extends Employee {

    public Mechanic() {
        super();
    }
    public Mechanic(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership ,username, password, totalSpending);
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
        String oldData = mechanic.getId() + "," + mechanic.getFullname() + "," + mechanic.getDateOfBirth() + "," + mechanic.getAddress() + "," + mechanic.getPhoneNumber() + "," + mechanic.getEmail() + "," + mechanic.getUserType() + "," + mechanic.getStatus() + "," + mechanic.getUser(username) + "," + mechanic.getPassword();

        String[] updateInfo = oldData.split(",");
        String updatedInfo = "";

        switch(userInput) {
            case 1:
                System.out.println("----- Update Fullname -----");
                System.out.println("Enter new fullname: ");
                String newFullname = sc.nextLine();
                updateInfo[1] = newFullname;
                updatedInfo += mechanic.getFullname();

        }

    }
}
