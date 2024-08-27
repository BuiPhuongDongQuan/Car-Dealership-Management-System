package Menu;

import Roles.Client;
import Roles.Manager;

import java.io.*;
import java.util.*;


public class Menu {
    static Manager manager = new Manager();
    static Client client = new Client();
    static Scanner sc = new Scanner(System.in);
    static Scanner keyboard = new Scanner(System.in);


    // user input their choice
    public static int optionInput(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Choice: ");
        int optionInput = sc.nextInt();
        return optionInput;
    }

    public static void SystemMenu() throws IOException {
        System.out.println("============================== System Menu - Welcome to Auto316 Management System ==============================");
        System.out.println("Please let us know who you are: ");
        System.out.println("1. Manager");
        System.out.println("2. Employee");
        System.out.println("3. Client");
        System.out.println("4. Exit");
        int choice = optionInput();

        switch (choice) {
            case 1:
                ManagerLoginMenu();
                break;
            case 3:
                ClientWelcomeMenu();
                break;
        }

    }
    // manager login
    public static void ManagerLoginMenu() throws IOException {
        System.out.println("============================== Manager - Login ==============================");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        manager.login(username, password);
    }

    // manager menu
    public static void ManagerMenu() throws IOException {
        System.out.println("============================== Manager - Menu ==============================");
        System.out.println("Welcome admin to Auto316 Management System");
    }

    public static void ClientWelcomeMenu() throws IOException {
        System.out.println("============================== Client Welcome Menu ==============================");
        System.out.println("1. Register");
        System.out.println("2. Login.");
        System.out.println("3. Return to Store System Screen.");
        System.out.println("4. Exit.");
        int option = optionInput();

        switch (option){
            case 1:
                CustomerRegisterMenu();
                break;
            case 2:
                ClientLoginMenu();
                break;
            case 3:
                SystemMenu();
                break;
            case 4:
                ThankYouMenu();
                break;
        }
    }

    public static void ClientLoginMenu() throws IOException {
        System.out.println("============================== Client - Login ==============================");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        client.login(username, password);
    }

    // manager menu
    public static void ClientMenu() throws IOException {
        System.out.println("============================== Client - Menu ==============================");
        System.out.println("Welcome client to Auto316 Management System");
    }

    //Allow customer to register
    public static void CustomerRegisterMenu() throws IOException {
        System.out.println("============================== Client - Register ==============================");
        System.out.print("Enter your username: ");
        String username = keyboard.nextLine();

        if(client.usernameValidation(username) == true){
            System.out.println("Username existed. Moving you back to Customer Welcome Menu");
            ClientWelcomeMenu();
        }
        else{
            System.out.print("Enter your password: ");
            String password = keyboard.nextLine();

            System.out.print("Enter your full name: ");
            String fullname = keyboard.nextLine();

            System.out.print("Enter your date of birth: ");
            String dateOfBirth = keyboard.nextLine();

            System.out.print("Enter your email: ");
            String email = keyboard.nextLine();

            System.out.print("Enter your address: ");
            String address = keyboard.nextLine();

            System.out.print("Enter your phone number: ");
            String phoneNumber = keyboard.nextLine();

            client.register(fullname,dateOfBirth,address,phoneNumber,email,username,password);

            System.out.println("Registration completed!");
            ClientWelcomeMenu();
        }
    }

    public static void ThankYouMenu(){
        System.out.println("============================== Thank you for using our system! Goodbye ==============================");
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("AUTO168 CAR DEALERSHIP MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Mr. Dung Nguyen");
        System.out.println("Group: Javalorant :)");
        System.out.println("s3978862, Nguyen Minh Khoi");
        System.out.println("s3975939, Bui Phuong Dong Quan");
        System.out.println("s3980424, Lam Quang Nhat");
    }
}
