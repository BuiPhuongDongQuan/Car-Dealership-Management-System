package Roles;

import Menu.Menu;

import java.io.IOException;

public class Admin {
    private final String Username = "admin";
    private final String Password = "password";


    public Admin() {

    }

    public void login(String username, String password) throws IOException {
        if (username.equals(Username) && password.equals(Password)) {
            System.out.println("Login Success!");
            Menu.adminMenu();
        } else {
            System.out.println("Login Failed!");
            Menu.systemMenu();
        }
    }
}
