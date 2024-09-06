package Roles;
import Features.Features;
import Menu.Menu;

import java.io.IOException;


public class Manager extends User {

    public Manager() {
        super();
    }
    public Manager(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }

    // allow manager login
    public void login(String username, String password) throws IOException{
        readData();

        User user = new User();
        user = getUser(username);

        if(user == null){
            System.out.println("Manager not found.");
            Menu.ManagerLoginMenu();
        }
        else{
            if(user.authenticate(username, password, "Manager")){;
                System.out.println("Login success! Welcome back manager!");
                Menu.ManagerMenu();
            }
            else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.ManagerLoginMenu();
            }

        }
    }

    // manager view all information
    public void viewInformation(String filepath) throws IOException{
        Features.readAllLines(filepath);
    }
}

