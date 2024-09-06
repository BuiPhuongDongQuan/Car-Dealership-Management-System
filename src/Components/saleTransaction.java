package Components;

import Features.Features;
import Menu.Menu;
import Roles.Client;
import Roles.User;
import Roles.Salesperson;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class saleTransaction {
    private final String transaction_data = "src/Database/Transaction.txt";
    private String id;
    private String transactionDate;
    private String clientId;
    private String carId;
    private String salespersonId;
    private String discount;
    private long totalAmount;
    private String additionalNotes;

    ArrayList<saleTransaction> transactions = new ArrayList<>();

    public saleTransaction(){}

    public saleTransaction(String id, String transactionDate, String clientId, String salespersonId, String carId, String discount, long totalAmount, String additionalNotes) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.clientId = clientId;
        this.salespersonId = salespersonId;
        this.carId = carId;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.additionalNotes = additionalNotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(String salespersonId) {
        this.salespersonId = salespersonId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public void readData() {
        transactions.clear();

        int countLine = Features.countLine(transaction_data);
        String[] id = Features.ReadCol(0, transaction_data, ";");
        String[] transactionDate = Features.ReadCol(1, transaction_data, ";");
        String[] clientId = Features.ReadCol(2, transaction_data, ";");
        String[] salespersonId = Features.ReadCol(3, transaction_data, ";");
        String[] carId = Features.ReadCol(4, transaction_data, ";");
        String[] discount = Features.ReadCol(5, transaction_data, ";");
        String[] totalAmount = Features.ReadCol(6, transaction_data, ";");
        String[] additionalNotes = Features.ReadCol(7, transaction_data, ";");

        // Check if arrays have the same length
        for (int i = 0; i < countLine - 1; i++){
            transactions.add(new saleTransaction(id[i], transactionDate[i], clientId[i], salespersonId[i], carId[i], discount[i], Long.parseLong(totalAmount[i]), additionalNotes[i]));
        }
    }

    //calculate total amount
    public void calculateTotalAmount() {
        readData();
        long totalAmount = 0;
        for (saleTransaction transaction : transactions) {
            totalAmount += transaction.getTotalAmount();
        }
        String priceFormat = String.format("The transaction total amount is: %,d", totalAmount);
        System.out.println(priceFormat + " VND");
    }

    //calculate total amount on a specific date
    public void calculateTotalAmountDate(String transactionDate) {
        readData();
        long totalAmount = 0;
        for (saleTransaction transaction : transactions) {
            if(transaction.getTransactionDate().equals(transactionDate)) {
                totalAmount += transaction.getTotalAmount();
            }
        }
        String priceFormat = String.format("The transaction total amount is: %,d", totalAmount);
        System.out.println(priceFormat + " VND");
    }

    //calculate total amount in a month
    public void calculateTotalAmountMonth(String transactionMonth) {
        readData();
        long totalAmount = 0;
        String regex = "(\\d{2})-(\\d{2})-(\\d{4})";
        Pattern pattern = Pattern.compile(regex);

        for (saleTransaction transaction : transactions) {
            Matcher matcher = pattern.matcher(transaction.getTransactionDate());
            if(matcher.find()){
                String month = matcher.group(2);
                if(transactionMonth.equals(month)) {
                    totalAmount += transaction.getTotalAmount();
                }
            }
        }
        String priceFormat = String.format("The transaction total amount is: %,d", totalAmount);
        System.out.println(priceFormat + " VND");
    }

    //calculate total amount in a year
    public void calculateTotalAmountYear(String transactionYear) {
        readData();
        long totalAmount = 0;
        String regex = "(\\d{2})-(\\d{2})-(\\d{4})";
        Pattern pattern = Pattern.compile(regex);

        for (saleTransaction transaction : transactions) {
            Matcher matcher = pattern.matcher(transaction.getTransactionDate());
            if(matcher.find()){
                String year = matcher.group(3);
                if(transactionYear.equals(year)) {
                    totalAmount += transaction.getTotalAmount();
                }
            }
        }
        String priceFormat = String.format("The transaction total amount is: %,d", totalAmount);
        System.out.println(priceFormat + " VND");
    }

    //list all transactions on a specific date
    public void listAllTransactionsDate(String transactionDate) {
        readData();
        System.out.println("Here is the list of transactions on: " + transactionDate);
        System.out.println("ID, Client ID, Salesperson ID, List of Purchased Items, Discount Amount, Total Amount, Additional Notes");
        for (saleTransaction transaction : transactions) {
            if(transaction.getTransactionDate().equals(transactionDate)) {
                String priceFormat = String.format("%,d", transaction.getTotalAmount());
                System.out.println(transaction.getId() +","+ transaction.getClientId() +","+ transaction.getSalespersonId() +","+ transaction.getCarId() +","+ transaction.getDiscount() +","+ priceFormat +"VND" +","+ transaction.getAdditionalNotes());
            }
        }
    }

    //list all transactions in a month
    public void listAllTransactionsMonth(String transactionMonth) {
        readData();
        System.out.println("Here is the list of transactions in: " + transactionMonth);
        System.out.println("ID, Client ID, Salesperson ID, List of Purchased Items, Discount Amount, Total Amount, Additional Notes");

        String regex = "(\\d{2})-(\\d{2})-(\\d{4})";
        Pattern pattern = Pattern.compile(regex);

        for (saleTransaction transaction : transactions) {
            Matcher matcher = pattern.matcher(transaction.getTransactionDate());
            if(matcher.find()){
                String month = matcher.group(2);
                if(transactionMonth.equals(month)) {
                    String priceFormat = String.format("%,d", transaction.getTotalAmount());
                    System.out.println(transaction.getId() +","+ transaction.getClientId() +","+ transaction.getSalespersonId() +","+ transaction.getCarId() +","+ transaction.getDiscount() +","+ priceFormat + "VND" +","+ transaction.getAdditionalNotes());
                }
            }
        }
    }

    //list all transactions in a year
    public void listAllTransactionsYear(String transactionYear) {
        readData();
        System.out.println("Here is the list of transactions in: " + transactionYear);
        System.out.println("ID, Client ID, Salesperson ID, List of Purchased Items, Discount Amount, Total Amount, Additional Notes");

        String regex = "(\\d{2})-(\\d{2})-(\\d{4})";
        Pattern pattern = Pattern.compile(regex);

        for (saleTransaction transaction : transactions) {
            Matcher matcher = pattern.matcher(transaction.getTransactionDate());
            if (matcher.find()) {
                String year = matcher.group(3);
                if (transactionYear.equals(year)) {
                    String priceFormat = String.format("%,d", transaction.getTotalAmount());
                    System.out.println(transaction.getId() +","+ transaction.getClientId() +","+ transaction.getSalespersonId() +","+ transaction.getCarId() +","+ transaction.getDiscount() +","+ priceFormat +"VND"+","+ transaction.getAdditionalNotes());
                }
            }
        }
    }

    public void createOrder(User client, Car car, String salespersonID) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Check if the car is available
        if (!car.isAvailable()) {
            System.out.println("Error: This car is already sold.");
            Menu.CreateCarTransactionMenu();
            return;
        }

        this.clientId = client.getId();
        this.carId = car.getId();
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
            totalAmount = car.getPrice() - (car.getPrice() * 15 / 100);
        } else if (clientMembership.equals("Gold")) {
            totalAmount = car.getPrice() - (car.getPrice() * 10 / 100);
        } else if (clientMembership.equals("Silver")) {
            totalAmount = car.getPrice() - (car.getPrice() * 5 / 100);
        } else {
            totalAmount = car.getPrice();
        }

        // Show new price
        System.out.println("Based on your membership: " + clientMembership);
        System.out.printf("The order total price will be (in VND): %,d\n", totalAmount);
        System.out.print("Pay and confirm order (Y/N): ");
        String orderConfirm = sc.next();

        if (orderConfirm.equalsIgnoreCase("y")) {
            // Generate order ID
            id = "T" + Features.countLine(transaction_data);

            // Set order status and date
            transactionDate = Features.getDate();

            // Format order data and write to file
            String data = "\n" + id + "," + transaction_data + "," + clientId + "," + salespersonID + "," + carId + "," + reward + "," + totalAmount;
            Features.writeToFile(transaction_data, data);

            // Mark the car as sold
            car.setStatus("Sold");

            // Update the car status in the database
            Car.updateCarStatusInDatabase(car);

            // Update customer spending and membership status
            client.addSpending(client.getUsername(), totalAmount);
            client.updateMembership(client.getUsername());
            System.out.println("Order confirmed! Your order ID is: " + id);
        }

        // Return to the customer action menu
        Menu.ClientMenu();
    }

}

