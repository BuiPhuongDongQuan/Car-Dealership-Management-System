package Roles;

import Menu.Menu;

import java.io.IOException;
import java.util.ArrayList;

public class Employee extends User {

    public Employee() {
        super();
    }
    public Employee(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership ,username, password, totalSpending);
    }

    ArrayList<Employee> employees = new ArrayList<>();


    public void login(String username, String password) throws IOException {
        readData();

        User user = new User();
        user = getUser(username);

        if(user == null){
            System.out.println("Employee not found");
            Menu.SystemMenu();
        } else {
            if(user.authenticate(username, password, "Salesperson")){
                System.out.println("Login success! Welcome to Salesperson!");
                setUsername(username);
                Menu.SalespersonMenu();
            }
            else if (user.authenticate(username, password, "Mechanic")){
                System.out.println("Login success! Welcome to Mechanic!");
                setUsername(username);
                Menu.MechanicMenu();
            } else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.EmployeeLoginMenu();
            }
        }
    }
}
