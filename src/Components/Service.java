/*
  RMIT University Vietnam
  Course: COSC2081 Programming 1
  Semester: 2024B
  Group Assignment
  Author: Javalorant
  ID: s3978862, s3975939, s3980424
*/

package Components;

import Features.Features;
import Menu.Menu;
import Roles.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service{
    private final String service_data = "src/Database/Service.txt";
    private String id;
    private String serviceDate;
    private String clientId;
    private String mechanicId;
    private String serviceTypeId;
    private String listOfReplacedPart;
    private long serviceCost;
    private String additionalNotes;

    ArrayList<Service> services = new ArrayList<>();

    public Service(){

    }

    public Service(String id, String serviceDate, String clientId, String mechanicId, String serviceType, String listOfReplacedPart, long serviceCost, String additionalNotes){
        this.id = id;
        this.serviceDate = serviceDate;
        this.clientId = clientId;
        this.mechanicId = mechanicId;
        this.serviceTypeId = serviceType;
        this.listOfReplacedPart = listOfReplacedPart;
        this.serviceCost = serviceCost;
        this.additionalNotes = additionalNotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(String mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getListOfReplacedPart() {
        return listOfReplacedPart;
    }

    public void setListOfReplacedPart(String listOfReplacedPart) {
        this.listOfReplacedPart = listOfReplacedPart;
    }

    public long getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(long serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }


    public void readData() {
        services.clear();

        int countLine = Features.countLine(service_data);
        String[] id = Features.ReadCol(0, service_data, ";");
        String[] serviceDate = Features.ReadCol(1, service_data, ";");
        String[] clientId = Features.ReadCol(2, service_data, ";");
        String[] mechanicId = Features.ReadCol(3, service_data, ";");
        String[] serviceType = Features.ReadCol(4, service_data, ";");
        String[] listOfReplacedPart = Features.ReadCol(5, service_data, ";");
        String[] serviceCost = Features.ReadCol(6, service_data, ";");
        String[] additionalNotes = Features.ReadCol(7, service_data, ";");

        // Check if arrays have the same length
        for (int i = 0; i < countLine - 1; i++) {
            services.add(new Service(id[i], serviceDate[i], clientId[i], mechanicId[i], serviceType[i], listOfReplacedPart[i], Long.parseLong(serviceCost[i]), additionalNotes[i]));
        }
    }

    //calculate total service cost
    public long calculateServiceCost(){
        readData();
        long totalCost = 0;
        for(Service service: services) {
            totalCost += service.getServiceCost();
        }
        String priceFormat = String.format("The service total revenue is: %,d", totalCost);
        System.out.println(priceFormat + " VND");
        return totalCost;
    }

    // calculate total service cost on specific date
    public long calculateServiceCostDate(String serviceDate){
        readData();
        long totalCost = 0;
        for(Service service: services) {
            if(service.getServiceDate().equals(serviceDate)) {
                totalCost += service.getServiceCost();
            }
        }
        String priceFormat = String.format("The service total revenue is: %,d", totalCost);
        System.out.println(priceFormat + " VND");
        return totalCost;
    }

    //calculate total service cost in a month
    public long calculateServiceCostMonth(String serviceMonth){
        readData();
        long totalCost = 0;
        String regex = "(\\d{2})-(\\d{2})-(\\d{4})";
        Pattern pattern = Pattern.compile(regex);

        for(Service service: services) {
            Matcher matcher = pattern.matcher(service.getServiceDate());
            if(matcher.find()) {
                String month = matcher.group(2);
                if(serviceMonth.equals(month)) {
                    totalCost += service.getServiceCost();
                }
            }
        }
        String priceFormat = String.format("The service total revenue is: %,d", totalCost);
        System.out.println(priceFormat + " VND");
        return totalCost;
    }

    //calculate total service cost in one year
    public void calculateServiceCostYear(String serviceYear){
        readData();
        long totalCost = 0;
        String regex = "(\\d{2})-(\\d{2})-(\\d{4})";
        Pattern pattern = Pattern.compile(regex);

        for(Service service: services) {
            Matcher matcher = pattern.matcher(service.getServiceDate());
            if(matcher.find()) {
                String year = matcher.group(3);
                if(serviceYear.equals(year)) {
                    totalCost += service.getServiceCost();
                }
            }
        }
        String priceFormat = String.format("The service total revenue is: %,d", totalCost);
        System.out.println(priceFormat + " VND");
    }

    // list all services on a specific date
    public void listAllServicesDate(String serviceDate){
        readData();
        System.out.println("Here is the service in : " + serviceDate);
        System.out.println("ID, Client ID, Mechanic ID, Service Type, List of Replaced Part, Service Cost, Additional Notes ");
        for(Service service: services) {
            if (service.getServiceDate().equals(serviceDate)) {
                String costFormat = String .format("%,d", service.getServiceCost());
                System.out.println(service.getId() +", "+ service.getClientId() +", "+ service.getMechanicId() +", "+ service.getServiceTypeId() +", "+ service.getListOfReplacedPart() + ", "+ costFormat +"VND" +", "+ service.getAdditionalNotes());
            }
        }
    }

    // list all services in month
    public void listAllServicesMonth(String serviceMonth) {
        readData();
        System.out.println("Here is the service in month : " + serviceMonth);
        System.out.println("ID, Client ID, Mechanic ID, Service Type, List of Replaced Part, Service Cost, Additional Notes ");

        String regex = "(\\d{2})-(\\d{2})-(\\d{4})";
        Pattern pattern = Pattern.compile(regex);

        for (Service service : services) {
            Matcher matcher = pattern.matcher(service.getServiceDate());
            if (matcher.find()) {
                String month = matcher.group(2);
                if (serviceMonth.equals(month)) {
                    String costFormat = String .format("%,d", service.getServiceCost());
                    System.out.println(service.getId() +", "+ service.getClientId() +", "+ service.getMechanicId() +", "+ service.getServiceTypeId() +", "+ service.getListOfReplacedPart() + ", "+ costFormat +"VND" +", "+ service.getAdditionalNotes());
                }
            }
        }
    }

    // list all services in a year
    public void listAllServicesYear(String serviceYear) {
        readData();
        System.out.println("Here is the service in year : " + serviceYear);
        System.out.println("ID, Client ID, Mechanic ID, Service Type, List of Replaced Part, Service Cost, Additional Notes ");

        String regex = "(\\d{2})-(\\d{2})-(\\d{4})";
        Pattern pattern = Pattern.compile(regex);

        for (Service service : services) {
            Matcher matcher = pattern.matcher(service.getServiceDate());
            if (matcher.find()) {
                String year = matcher.group(3);
                if (serviceYear.equals(year)) {
                    String costFormat = String .format("%,d", service.getServiceCost());
                    System.out.println(service.getId() +", "+ service.getClientId() +", "+ service.getMechanicId() +", "+ service.getServiceTypeId() +", "+ service.getListOfReplacedPart() + ", "+ costFormat +"VND" +", "+ service.getAdditionalNotes());
                }
            }
        }
    }

    //calculate service revenue of a mechanic
    public void calculateRevenueOfMechanic(String mechanicId) {
        readData();
        long mechanicRevenue = 0;
        for(Service service: services) {
            if(service.getMechanicId().equals(mechanicId)) {
                mechanicRevenue += service.getServiceCost();
            }
        }
        String priceFormat = String.format("The total revenue of this mechanic is: %,d", mechanicRevenue);
        System.out.println(priceFormat + " VND");
    }


    //create service order
    public void createOrder(User client, ServiceType serviceType, String mechanicId) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Initialize variables
        long servicePrice = 0;

        // Set client and salesperson IDs
        this.clientId = client.getId();
        this.mechanicId = mechanicId;
        this.serviceTypeId = serviceType.getId();
        this.listOfReplacedPart = serviceType.getRepalcedpart();

        // Calculate total price based on membership discount
        String clientMembership = client.getMembership();
        String reward = switch (clientMembership) {
            case "Silver" -> "5%";
            case "Gold" -> "10%";
            case "Platinum" -> "15%";
            default -> "0%";
        };

        // Apply discount based on membership
        long discountPercentage = switch (clientMembership) {
            case "Platinum" -> 15;
            case "Gold" -> 10;
            case "Silver" -> 5;
            default -> 0;
        };

        serviceCost = (serviceType.getPrice()) - (serviceType.getPrice() * discountPercentage / 100);

        // Display total amount
        System.out.printf("Based on your membership: %s\n", clientMembership);
        System.out.printf("The order total price will be (in VND): %,d\n", serviceCost);
        System.out.print("Pay and confirm order (Y/N): ");
        String orderConfirm = sc.next();

        if (orderConfirm.equalsIgnoreCase("y")) {
            // Generate order ID
            id = "S" + Features.countLine(service_data);

            // Set order status and date
            serviceDate = Features.getDate();

            // Format order data and write to file
            String data = "\n" + id + "," + serviceDate + "," + clientId + "," + mechanicId + "," +
                    serviceTypeId + "," + listOfReplacedPart + "," + serviceCost + ",N/A"; // Additional notes can be set as needed
            Features.writeToFile(service_data, data);

            // Update customer spending and membership status
            client.addSpending(client.getUsername(), serviceCost);
            client.updateMembership(client.getUsername());
            System.out.println("Order confirmed! Your order ID is: " + id);
        }

        // Return to the customer action menu
        Menu.ClientMenu();
    }

    public Service getService(String id) {
        readData();

        for (Service service : services) {
            if (service.getId().equals(id)) {
                return service;
            }
        }
        return null;
    }

    public boolean validateServiceID(String serviceID){
        readData();
        boolean validateServiceID = false;
        for(Service service:services) {
            if (service.getId().equals(serviceID)) {
                validateServiceID = true;
            }
        }
        return validateServiceID;
    }

    public void searchOrder(String orderID, User client) throws IOException {
        Service service = getService(orderID);
        if(client.getId().equals(service.getClientId())){
            System.out.println("The service " + service.getId() + " contains: ");
            System.out.println("- Service Type: " + service.getServiceTypeId());
            System.out.println("- List of replaced part: " + service.getListOfReplacedPart());
            System.out.printf("- Total Price: %,d\n", service.getServiceCost());
            System.out.println("- Order date: " + service.getServiceDate());
        }
        else{
            System.out.println("Ineligible right.");
        }

        Menu.ClientMenu();
    }

    public void printAllService(User client) {
        readData();
        boolean hasPurchases = false;
        this.clientId = client.getId();

        for (Service service : services) {
            if(service.getClientId().equals(clientId)) {
                hasPurchases = true;
                System.out.println("Service ID: " + service.getId());
                System.out.println("Service Date: " + service.getServiceDate());
                System.out.println("Mechanic ID: " + service.getMechanicId());
                System.out.println("Service Type ID: " + service.getServiceTypeId());
                System.out.printf("Total Amount: %,d\n", service.getServiceCost());
                System.out.println("-------------------------");
            }
        }

        if (!hasPurchases) {
            System.out.println("No purchases found for client ID: " + clientId);
        }
    }
}
