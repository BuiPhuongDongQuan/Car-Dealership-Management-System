package Roles;

import Features.Features;
import Menu.Menu;

import java.io.IOException;
import java.util.*;

public class Client extends User {
    private final String user_data = "src/Database/User.txt"; // Path to the user data file

    // Constructor that initializes the Client and reads user data
    public Client() {
        super();
        readData();
    }

    // Constructor to create a Client object with specified details
    public Client(String id, String fullname, String dateOfBirth, String address, String phoneNumber, String email, String userType, String status, String membership, String username, String password, long totalSpending) {
        super(id, fullname, dateOfBirth, address, phoneNumber, email, userType, status, membership, username, password, totalSpending);
    }

    // List to store all clients
    ArrayList<Client> clients = new ArrayList<>();

    /**
     * Handles the login process for a client.
     * Checks if the username exists and verifies the password.
     * If successful, navigates to the client menu, otherwise prompts to login again.
     *
     * @param username the username of the client
     * @param password the password of the client
     * @throws IOException if an I/O error occurs
     */
    public void login(String username, String password) throws IOException {
        readData();

        User user = getUser(username); // Retrieve the user by username

        if (user == null) {
            System.out.println("Client not found. Please register to begin.");
            Menu.ClientLoginMenu();
        } else {
            if (user.authenticate(username, password, "Client")) {
                System.out.println("Login success! Welcome back our customer!");
                setUsername(username); // Set the username for the session
                Menu.ClientMenu();
            } else {
                System.out.println("Login failed! Username or password is incorrect.");
                Menu.ClientLoginMenu();
            }
        }
    }

    /**
     * Handles the registration process for a new client.
     * Collects user information, assigns a unique ID, and stores the data.
     *
     * @param fullName     the full name of the client
     * @param dateOfBirth  the date of birth of the client
     * @param address      the address of the client
     * @param phoneNumber  the phone number of the client
     * @param email        the email of the client
     * @param username     the username for the client
     * @param password     the password for the client
     * @throws IOException if an I/O error occurs
     */
    public void register(String fullName, String dateOfBirth, String address, String phoneNumber, String email, String username, String password) throws IOException {
        int countLine = Features.countLine(user_data);
        String id = "U" + countLine; // Generate a unique ID
        String user_type = "Customer";
        String status = "Active";
        String membership = "Regular";
        long totalSpending = 0;
        String newClient = "\n" + id + "," + fullName + "," + dateOfBirth + "," + address + "," + phoneNumber + "," + email + "," + user_type + "," + status + "," + membership + "," + username + "," + password + "," + totalSpending;
        Features.writeToFile(user_data, newClient); // Write the new client's data to the file
    }

    /**
     * Retrieves a list of all usernames in the system.
     *
     * @return an ArrayList containing all usernames
     */
    public ArrayList<String> getUsernameArraylist() {
        String[] usernameArray = Features.ReadCol(8, user_data, ",");
        ArrayList<String> userNameArrayList = new ArrayList<>();

        for (int i = 0; i < usernameArray.length; i++) {
            userNameArrayList.add(usernameArray[i]);
        }

        return userNameArrayList;
    }

    /**
     * Validates whether a username exists in the system.
     *
     * @param username the username to validate
     * @return true if the username exists, false otherwise
     */
    public boolean usernameValidation(String username) {
        boolean usernameValidation = true;
        ArrayList<String> allUsernameList = getUsernameArraylist();

        if (allUsernameList.contains(username)) {
            usernameValidation = true;
        } else {
            usernameValidation = false;
        }

        return usernameValidation;
    }

    /**
     * Displays the personal information of a client based on the provided username.
     *
     * @param username the username of the client
     */
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

    /**
     * Displays the membership type and corresponding discount reward for a client.
     *
     * @param username the username of the client
     */
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

    /**
     * Allows a client to update their personal information.
     * Based on the user's input, specific fields like name, address, etc., are updated.
     *
     * @param userInput the field the user wants to update
     * @param username  the username of the client
     * @throws IOException if an I/O error occurs
     */
    public void clientUpdateInfo(int userInput, String username) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        User client = getUser(username);
        String oldContent = client.getId() + "," + client.getFullname() + "," + client.getDateOfBirth() + "," + client.getAddress() + "," + client.getPhoneNumber() + "," + client.getEmail() + "," + client.getUserType() + "," + client.getStatus() + "," + client.getMembership() + "," + client.getUsername() + "," + client.getPassword() + "," + client.getTotalSpending();

        String[] updateClientInfo = oldContent.split(",");
        String updatedContent = "";

        switch (userInput) {
            // Update full name
            case 1:
                System.out.println("----- Full name update -----");
                System.out.print("Enter your new full name: ");
                String newFullname = keyboard.nextLine();

                updateClientInfo[1] = newFullname;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update date of birth
            case 2:
                System.out.println("----- Date of birth update -----");
                System.out.print("Enter your new date of birth: ");
                String newDateOfBirth = keyboard.nextLine();

                updateClientInfo[2] = newDateOfBirth;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update address
            case 3:
                System.out.println("----- Address update -----");
                System.out.print("Enter your new address: ");
                String newAddress = keyboard.nextLine();

                updateClientInfo[3] = newAddress;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update phone number
            case 4:
                System.out.println("----- Phone number update -----");
                System.out.print("Enter your new phone number: ");
                String newPhoneNumber = keyboard.nextLine();

                updateClientInfo[4] = newPhoneNumber;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update email
            case 5:
                System.out.println("----- Email update -----");
                System.out.print("Enter your new email: ");
                String newEmail = keyboard.nextLine();

                updateClientInfo[5] = newEmail;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Update password
            case 6:
                System.out.println("----- Password update -----");
                System.out.print("Enter your new password: ");
                String newPassword = keyboard.nextLine();

                updateClientInfo[10] = newPassword;
                updatedContent = Features.arrayToCSVString(updateClientInfo);
                Features.modifyFile(user_data, oldContent, updatedContent);
                break;

            // Return to client menu
            case 7:
                System.out.println("Returning to client menu...");
                Menu.ClientMenu();
                break;
            case 8:
                Menu.ThankYouMenu();
                System.exit(0);
                break;
        }

        System.out.println("Your information has been updated successfully.");
    }
}
