package Roles;

import Features.Features;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Manager extends User {
    private final String managerData = "src/Database/ManagerData.txt";
    private ArrayList<Manager> managers = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Manager(String id, String fullname, Date dateOfBirth, String address, int phoneNumber, String email, String userType, boolean status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }

    // Method to read data from the file
    public void readData() {
        try {
            int countLine = Features.countLine(managerData);

            String[] id = Features.ReadCol(0, managerData, ",");
            String[] fullname = Features.ReadCol(1, managerData, ",");
            Date[] datesOfBirth = Features.ReadCol(2, managerData, ",", new SimpleDateFormat("dd-MM-yyyy"));
            String[] address = Features.ReadCol(3, managerData, ",");
            int[] phoneNumber = Features.ReadCol(4, managerData, ",");
            String[] email = Features.ReadCol(5, managerData, ",");
            String[] userType = Features.ReadCol(6, managerData, ",");
            boolean[] status = Features.ReadCol(7, managerData, ",");
            String[] username = Features.ReadCol(7, managerData, ",");
            String[] password = Features.ReadCol(8, managerData, ",");

            // Check if arrays have the same length
            if (id.length != countLine || fullname.length != countLine || datesOfBirth.length != countLine ||
                    address.length != countLine || phoneNumber.length != countLine || email.length != countLine ||
                    userType.length != countLine || status.length != countLine || username.length != countLine || password.length != countLine) {
                throw new IOException("Data mismatch in the file.");
            }

            for (int i = 0; i < countLine; i++) {
                managers.add(new Manager(
                        id[i], fullname[i], datesOfBirth[i], address[i], phoneNumber[i], email[i],
                        "Manager", true, username[i], password[i]
                ));
            }
        } catch (IOException | ParseException e) {
            System.err.println("An error occurred while reading data: " + e.getMessage());
        }

    }
}

