package Roles;

import Features.Features;
import java.io.IOException;
import java.util.ArrayList;


public class Manager extends User {
    private final String user_data = "src/Database/User.txt";
    private ArrayList<Manager> managers = new ArrayList<>();


    public Manager(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }

    // Method to read data from the file
    public void readData() {
        try {
            int countLine = Features.countLine(user_data);

            String[] id = Features.ReadCol(0, user_data, ",");
            String[] fullname = Features.ReadCol(1, user_data, ",");
            String[] datesOfBirth = Features.ReadCol(2, user_data, ",");
            String[] address = Features.ReadCol(3, user_data, ",");
            String[] phoneNumber = Features.ReadCol(4, user_data, ",");
            String[] email = Features.ReadCol(5, user_data, ",");
            String[] userType = Features.ReadCol(6, user_data, ",");
            String[] status = Features.ReadCol(7, user_data, ",");
            String[] username = Features.ReadCol(7, user_data, ",");
            String[] password = Features.ReadCol(8, user_data, ",");

            // Check if arrays have the same length
            if (id.length != countLine || fullname.length != countLine || datesOfBirth.length != countLine ||
                    address.length != countLine || phoneNumber.length != countLine || email.length != countLine ||
                    userType.length != countLine || status.length != countLine || username.length != countLine || password.length != countLine) {
                throw new IOException("Data mismatch in the file.");
            }

            for (int i = 0; i < countLine; i++) {
                managers.add(new Manager(
                        id[i], fullname[i], datesOfBirth[i], address[i], phoneNumber[i], email[i],
                        "Manager", "Active", username[i], password[i]
                ));
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading data: " + e.getMessage());
        }

    }

    public void login(String username, String password){
        readData();

        if (username != User && password.equals("admin")) {}



    }
}

