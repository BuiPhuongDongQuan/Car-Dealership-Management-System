package Components;

import Features.Features;
import Menu.Menu;
import Roles.Client;
import Roles.Employee;
import Roles.User;
import Roles.Salesperson;

import java.io.IOException;
import java.util.*;

public class saleTransaction {
    private String transactionID;
    private String date;
    private String clientID;
    private String carID;
    private String salespersonID;
    private long totalPrice;
    private final String transaction_data = "src/Database/SaleTransaction.txt";

    public saleTransaction(String transactionID, String date, String clientID, String carID, String salespersonID,long totalPrice) {
        this.transactionID = transactionID;
        this.date = date;
        this.clientID = clientID;
        this.carID = carID;
        this.salespersonID = salespersonID;
        this.totalPrice = totalPrice;
    }

    public saleTransaction() {

    }

    public void createOrder(User client, Car car, String salespersonID) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Check if the car is available
        if (!car.isAvailable()) {
            System.out.println("Error: This car is already sold.");
            Menu.CreateCarTransactionMenu();
            return;
        }

        this.clientID = client.getId();
        this.carID = car.getId();
        String reward = "";

        String clientMembership = client.getMembership();

        //Discount
        reward = switch (clientMembership) {
            case "Silver" -> "5%";
            case "Gold" -> "10%";
            case "Platinum" -> "15%";
            default -> "0%";
        };

        // Calculating price based on membership reward
        if (clientMembership.equals("Platinum")) {
            totalPrice = car.getPrice() - (car.getPrice() * 15 / 100);
        } else if (clientMembership.equals("Gold")) {
            totalPrice = car.getPrice() - (car.getPrice() * 10 / 100);
        } else if (clientMembership.equals("Silver")) {
            totalPrice = car.getPrice() - (car.getPrice() * 5 / 100);
        } else {
            totalPrice = car.getPrice();
        }

        // Show new price
        System.out.println("Based on your membership: " + clientMembership);
        System.out.printf("The order total price will be (in VND): %,d\n", totalPrice);
        System.out.print("Pay and confirm order (Y/N): ");
        String orderConfirm = sc.next();

        if (orderConfirm.equalsIgnoreCase("y")) {
            // Generate order ID
            transactionID = "T" + Features.countLine(transaction_data);

            // Set order status and date
            date = Features.getDate();

            // Format order data and write to file
            String data = "\n" + transactionID + "," + date + "," + clientID + "," + salespersonID + "," + carID + "," + reward + "," + totalPrice;
            Features.writeToFile(transaction_data, data);

            // Mark the car as sold
            car.setStatus("Sold");

            // Update the car status in the database
            Car.updateCarStatusInDatabase(car);

            // Update customer spending and membership status
            client.addSpending(client.getUsername(), totalPrice);
            client.updateMembership(client.getUsername());
            System.out.println("Order confirmed! Your order ID is: " + transactionID);
        }

        // Return to the customer action menu
        Menu.ClientMenu();
    }


    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSalespersonID() {
        return salespersonID;
    }

    public void setSalespersonID(String salespersonID) {
        this.salespersonID = salespersonID;
    }
}
