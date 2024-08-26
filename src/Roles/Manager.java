package Roles;
import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.*;


public class Manager extends User {
    private final String user_data = "src/Database/Manager.txt";
    ArrayList<Manager> managers = new ArrayList<>();

    public Manager() {
        super();
    }
    public Manager(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }


    public void login(String username, String password) throws IOException{
        readData();

        boolean loginValidation = false;
        Manager manager = new Manager();
        manager = getManager(username);

        if(manager == null){
            System.out.println("Manager not found. Please register to begin.");
            Menu.systemMenu();
        }
        else{
            if(manager.getUsername()!= null && manager.getUsername().equals(username)&& manager.getPassword().equals(password)){;
                loginValidation = true;
            }
            else {
                loginValidation = false;
            }

            if(loginValidation == true){
                System.out.println("Login success! Welcome back manager!");
                setUsername(username);
                Menu.adminMenu();
            }
            else{
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.managerLoginMenu();
            }
        }
    }

    // Method to read data from the file
    public void readData() {
        managers.clear();

        int countLine = Features.countLine(user_data);
        String[] id = Features.ReadCol(0, user_data, ",");
        String[] fullname = Features.ReadCol(1, user_data, ",");
        String[] datesOfBirth = Features.ReadCol(2, user_data, ",");
        String[] address = Features.ReadCol(3, user_data, ",");
        String[] phoneNumber = Features.ReadCol(4, user_data, ",");
        String[] email = Features.ReadCol(5, user_data, ",");
        String[] userType = Features.ReadCol(6, user_data, ",");
        String[] status = Features.ReadCol(7, user_data, ",");
        String[] username = Features.ReadCol(8, user_data, ",");
        String[] password = Features.ReadCol(9, user_data, ",");

        // Check if arrays have the same length
        for (int i = 0; i < countLine - 1; i++) {
            managers.add(new Manager(
                    id[i], fullname[i], datesOfBirth[i], address[i], phoneNumber[i], email[i],
                    "Manager", "Active", username[i], password[i]
            ));
        }
    }

    public Manager getManager(String username) {
        for (Manager manager : managers) {
            if (manager.getUsername() != null && manager.getUsername().equals(username)) {
                return manager;
            }
        }
        return null;
    }

    public ArrayList<String> getUsernameArraylist(){
        String[] usernameArray = Features.ReadCol(6, user_data,",");
        ArrayList<String> userNameArrayList = new ArrayList<>();

        for(int i = 0; i < usernameArray.length; i++){
            userNameArrayList.add(usernameArray[i]);
        }

        return userNameArrayList;
    }
}

