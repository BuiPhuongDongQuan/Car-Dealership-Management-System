package Roles;


import java.io.IOException;

public class Mechanic extends Employee {

    public Mechanic() {
        super();
    }
    public Mechanic(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }

    @Override
    public void login(String username, String password) throws IOException {
        super.login(username, password);
    }
}
