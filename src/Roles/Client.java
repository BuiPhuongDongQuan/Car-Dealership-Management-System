package Roles;
import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.*;

public class Client extends User {
    private final String user_data = "src/Database/User.txt";
    private Client[] clients;

    public Client() {
        super();
        readData();
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
            Menu.ClientLoginMenu();
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

    public void viewCustomerInfo(String username){
        readData();

        System.out.println("Here is your information: ");
        for(Client client : clients){
            if(client.getUsername().equals(username)){
                System.out.println("- Name: " + client.getFullname() + "\n" +
                        "- Date of Birth: " + client.getDateOfBirth() + "\n" +
                        "- Email: " + client.getEmail() + "\n" +
                        "- Address: " + client.getAddress() + "\n" +
                        "- Phone number: " + client.getPhoneNumber() + "\n" +
                        "- Password: " + client.getPassword());
            }
        }
    }

    public Client getClient(String username){
        for(Client customer : clients){
            if(customer.getUsername().equals(username)){
                return customer;
            }
        }
        return null;
    }

    //allow customer to update certain personal information
    public void customerUpdateInfo(int userInput, String username) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        Client customer = getClient(username);
        String oldContent = customer.getId() + "," + customer.getFullname() + "," + customer.getDateOfBirth() + "," + customer.getAddress() + "," + customer.getPhoneNumber() + "," + customer.getEmail() + "," + customer.getUsername() + "," + customer.getPassword();

        String[] updateCustomerInfo = oldContent.split(",");
        String updatedContent = "";

        switch(userInput){
            //update full name
            case 1:
                System.out.println("----- Full name update -----");
                System.out.print("Enter your new full name: ");
                String newFullname = keyboard.nextLine();

                updateCustomerInfo[1] = newFullname;
                updatedContent = Features.arrayToCSVString(updateCustomerInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update date of birth
            case 2:
                System.out.println("----- Date of birth update -----");
                System.out.print("Enter your new date of birth: ");
                String newDateOfBirth = keyboard.nextLine();

                updateCustomerInfo[2] = newDateOfBirth;
                updatedContent = Features.arrayToCSVString(updateCustomerInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update email
            case 3:
                System.out.println("----- Email update -----");
                System.out.print("Enter your new email: ");
                String newEmail = keyboard.nextLine();

                updateCustomerInfo[3] = newEmail;
                updatedContent = Features.arrayToCSVString(updateCustomerInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update address
            case 4:
                System.out.println("----- Address update -----");
                System.out.print("Enter your new address: ");
                String newAddress = keyboard.nextLine();

                updateCustomerInfo[4] = newAddress;
                updatedContent = Features.arrayToCSVString(updateCustomerInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update phone number
            case 5:
                System.out.println("----- Phone number update -----");
                System.out.print("Enter your new phone number: ");
                String newPhoneNumber = keyboard.nextLine();

                updateCustomerInfo[5] = newPhoneNumber;
                updatedContent = Features.arrayToCSVString(updateCustomerInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;


            //update password
            case 6:
                System.out.println("----- Password update -----");
                System.out.print("Enter your new password: ");
                String newPassword = keyboard.nextLine();

                updateCustomerInfo[6] = newPassword;
                updatedContent = Features.arrayToCSVString(updateCustomerInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            case 7:
                Menu.ClientMenu();
                break;
            case 8:
                Menu.ThankYouMenu();
                break;
        }
    }
}

