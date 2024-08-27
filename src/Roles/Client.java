package Roles;
import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.ArrayList;


public class Client extends User {
    private final String user_data = "src/Database/User.txt";

    public Client() {
        super();
    }
    public Client(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String username, String password) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, username, password);
    }


    public void login(String username, String password) throws IOException{
        readData();

        boolean loginValidation = false;
        User user = new User();
        user = getUser(username);

        if(user == null){
            System.out.println("Client not found. Please register to begin.");
            Menu.SystemMenu();
        }
        else{
            if(user.authenticate(username, password, "Client")){;
                loginValidation = true;
            }
            else {
                loginValidation = false;
            }

            if(loginValidation == true){
                System.out.println("Login success! Welcome back our customer!");
                Menu.ClientMenu();

            }
            else{
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.ClientLoginMenu();
            }
        }
    }

    public void register(String fullName, String dateOfBirth, String address, String phoneNumber, String email, String username, String password) throws IOException {
        int countLine = Features.countLine(user_data);
        String id = "U" + countLine;
        String user_type = "Client";
        String status = "Active";
        String newClient = "\n" + id+ "," + fullName+ "," + dateOfBirth+ "," + address+ "," + phoneNumber+ "," + email+ "," + user_type+ "," + status+ "," + username + "," + password;
        Features.writeToFile(user_data, newClient);
    }

    public ArrayList<String> getUsernameArraylist(){
        String[] usernameArray = Features.ReadCol(6, user_data,",");
        ArrayList<String> userNameArrayList = new ArrayList<>();

        for(int i = 0; i < usernameArray.length; i++){
            userNameArrayList.add(usernameArray[i]);
        }

        return userNameArrayList;
    }


    public boolean usernameValidation(String username){
        boolean usernameValidation = true;
        ArrayList<String> allUsernameList = getUsernameArraylist();

        if(allUsernameList.contains(username)) {
            usernameValidation = true;
        }
        else {
            usernameValidation = false;
        }

        return usernameValidation;
    }
}

