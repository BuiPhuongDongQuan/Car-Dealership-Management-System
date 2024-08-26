package Menu;

import Roles.Manager;

import java.io.IOException;
import java.util.Scanner;


public class Menu {
    static Manager manager = new Manager();
    static Scanner sc = new Scanner(System.in);

    // user input their choice
    public static int optionInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Choice: ");
        int optionInput = sc.nextInt();
        return optionInput;
    }

    public static void systemMenu() throws IOException {
        System.out.println("============================== System Menu - Welcome to Auto316 Management System ==============================");
        System.out.println("Please let us know who you are: ");
        System.out.println("1. Manager");
        System.out.println("2. Employee");
        System.out.println("3. Client");
        System.out.println("4. Exit");
        int choice = optionInput();

        switch (choice) {
            case 1:
                managerLoginMenu();
                break;

        }

    }
    // manager login
    public static void managerLoginMenu() throws IOException {
        System.out.println("============================== Manager - Login ==============================");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        manager.login(username, password);
    }
    // manager menu
    public static void adminMenu() throws IOException {
        System.out.println("============================== Manager - Menu ==============================");
        System.out.println("Welcome admin to Auto316 Management System");
    }

}
