package Components;

import Features.Features;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service{
    private final String service_data = "src/Database/Service.txt";
    private String id;
    private String serviceDate;
    private String clientId;
    private String mechanicId;
    private String serviceType;
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
        this.serviceType = serviceType;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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
    public void calculateServiceCost(){
        readData();
        long totalCost = 0;
        for(Service service: services) {
            totalCost += service.getServiceCost();
        }
        String priceFormat = String.format("The service total revenue is: %,d", totalCost);
        System.out.println(priceFormat + " VND");
    }

    // calculate total service cost on specific date
    public void calculateServiceCostDate(String serviceDate){
        readData();
        long totalCost = 0;
        for(Service service: services) {
            if(service.getServiceDate().equals(serviceDate)) {
                totalCost += service.getServiceCost();
            }
        }
        String priceFormat = String.format("The service total revenue is: %,d", totalCost);
        System.out.println(priceFormat + " VND");
    }

    //calculate total service cost in a month
    public void calculateServiceCostMonth(String serviceMonth){
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
                System.out.println(service.getId() +", "+ service.getClientId() +", "+ service.getMechanicId() +", "+ service.getServiceType() +", "+ service.getListOfReplacedPart() + ", "+ costFormat +"VND" +", "+ service.getAdditionalNotes());
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
                    System.out.println(service.getId() +", "+ service.getClientId() +", "+ service.getMechanicId() +", "+ service.getServiceType() +", "+ service.getListOfReplacedPart() + ", "+ costFormat +"VND" +", "+ service.getAdditionalNotes());
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
                    System.out.println(service.getId() +", "+ service.getClientId() +", "+ service.getMechanicId() +", "+ service.getServiceType() +", "+ service.getListOfReplacedPart() + ", "+ costFormat +"VND" +", "+ service.getAdditionalNotes());
                }
            }
        }
    }
}