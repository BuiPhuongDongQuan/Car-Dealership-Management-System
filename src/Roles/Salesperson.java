package Roles;

import java.io.IOException;

public class Salesperson extends Employee {

    public Salesperson() {
        super();
    }
    public Salesperson(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }

    @Override
    public void login(String username, String password) throws IOException {
        super.login(username, password);
    }
}
