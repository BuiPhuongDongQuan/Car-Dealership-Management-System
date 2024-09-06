package Roles;
import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.*;

public class Client extends User {
    private final String user_data = "src/Database/User.txt";

    public Client() {
        super();
        readData();
    }
    public Client(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership ,username, password, totalSpending);
    }

    ArrayList<Client> clients = new ArrayList<>();

    public void login(String username, String password) throws IOException{
        readData();

        User user = getUser(username);

        if(user == null){
            System.out.println("Client not found. Please register to begin.");
            Menu.ClientLoginMenu();
        }
        else{
            if(user.authenticate(username, password, "Client")){;
                System.out.println("Login success! Welcome back our customer!");
                setUsername(username);
                Menu.ClientMenu();
            }
            else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.ClientLoginMenu();
            }
        }
    }

    public void register(String fullName, String dateOfBirth, String address, String phoneNumber, String email, String username, String password) throws IOException {
        int countLine = Features.countLine(user_data);
        String id = "U" + countLine;
        String user_type = "Customer";
        String status = "Active";
        String membership = "Regular";
        long totalSpending = 0;
        String newClient = "\n" + id+ "," + fullName+ "," + dateOfBirth+ "," + address+ "," + phoneNumber+ "," + email+ "," + user_type+ "," + status+ "," + membership+ "," + username + "," + password + "," + totalSpending;
        Features.writeToFile(user_data, newClient);
    }

    public ArrayList<String> getUsernameArraylist(){
        String[] usernameArray = Features.ReadCol(8, user_data,",");
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

    public void viewClientInfo(String username) {
        readData();

        System.out.println("Here is your information: ");
        for (User client : users) {
            if (client.getUsername() != null && client.getUsername().equals(username)) {
                System.out.println("- Name: " + client.getFullname() + "\n" +
                        "- Date of Birth: " + client.getDateOfBirth() + "\n" +
                        "- Email: " + client.getEmail() + "\n" +
                        "- Address: " + client.getAddress() + "\n" +
                        "- Phone number: " + client.getPhoneNumber() + "\n" +
                        "- Membership: " + client.getMembership() + "\n" +
                        "- Password: " + client.getPassword());
            }
        }
    }

    //search for client membership by username
    public void viewMembership(String username) {
        readData();

        for (User client : users) {
            if (client.getUsername().equals(username)) {
                System.out.println("Your current membership is: " + client.getMembership());

                String reward = switch (client.getMembership()) {
                    case "Silver" -> "5%";
                    case "Gold" -> "10%";
                    case "Platinum" -> "15%";
                    default -> "0%";
                };

                System.out.println("Your current reward is: " + reward + " discount.");
                return; // Exit the method once the correct user is found and processed
            }
        }
    }


    public void clientUpdateInfo(int userInput, String username) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        User client = getUser(username);
        String oldContent = client.getId() + "," + client.getFullname() + "," + client.getDateOfBirth() + "," + client.getAddress() + "," + client.getPhoneNumber() + "," + client.getEmail() + "," + client.getUserType() + "," + client.getStatus() + "," + client.getMembership() + "," + client.getUsername() + "," + client.getPassword() + "," + client.getTotalSpending();

        String[] updateClientInfo = oldContent.split(",");
        String updatedContent = "";

        switch(userInput){
            //update full name
            case 1:
                System.out.println("----- Full name update -----");
                System.out.print("Enter your new full name: ");
                String newFullname = keyboard.nextLine();

                updateClientInfo[1] = newFullname;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update date of birth
            case 2:
                System.out.println("----- Date of birth update -----");
                System.out.print("Enter your new date of birth: ");
                String newDateOfBirth = keyboard.nextLine();

                updateClientInfo[2] = newDateOfBirth;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update address
            case 3:
                System.out.println("----- Address update -----");
                System.out.print("Enter your new address: ");
                String newAddress = keyboard.nextLine();

                updateClientInfo[3] = newAddress;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update phone number
            case 4:
                System.out.println("----- Phone number update -----");
                System.out.print("Enter your new phone number: ");
                String newPhoneNumber = keyboard.nextLine();

                updateClientInfo[4] = newPhoneNumber;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update email
            case 5:
                System.out.println("----- Email update -----");
                System.out.print("Enter your new email: ");
                String newEmail = keyboard.nextLine();

                updateClientInfo[5] = newEmail;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            //update password
            case 6:
                System.out.println("----- Password update -----");
                System.out.print("Enter your new password: ");
                String newPassword = keyboard.nextLine();

                updateClientInfo[10] = newPassword;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            case 7:
                Menu.ClientMenu();
                break;
            case 8:
                Menu.ThankYouMenu();
                System.exit(0);
                break;
        }
    }
}

