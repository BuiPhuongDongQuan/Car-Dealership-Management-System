package Roles;

import Menu.Menu;

import java.io.IOException;

public class Employee extends User {

    public Employee() {
        super();
    }
    public Employee(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }

    public void login(String username, String password) throws IOException {
        readData();

        User user = new User();
        user = getUser(username);

        if(user == null){
            System.out.println("Employee not found");
            Menu.systemMenu();
        } else {
            if(user.authenticate(username, password, "Salesperson")){
                System.out.println("Login success! Welcome to Salesperson!");
                Menu.SalespersonMenu();
            }
            else if (user.authenticate(username, password, "Mechanic")){
                System.out.println("Login success! Welcome to Mechanic!");
                Menu.MechanicMenu();
            } else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.EmployeeLoginMenu();
            }
        }
    }
}
