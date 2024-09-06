package Components;

import Features.Features;

import java.util.*;

public class ServiceType {
    private String id;
    private String servicetype;
    private String repalcedpart;
    private double price;

    private final String servicetype_data = "src/Database/ServiceType.txt";

    private ArrayList<ServiceType> servicetypes = new ArrayList<>();

    public ServiceType(String id, String servicetype, String repalcedpart, double price) {
        this.id = id;
        this.servicetype = servicetype;
        this.repalcedpart = repalcedpart;
        this.price = price;
    }

    public ServiceType() {

    }

    //view all part and sort by ascending or descending
    public void viewAllServiceSort(String sortOrder) {
        extractDatabase();
        // Print header
        System.out.printf("%-8s%-35s%-31s%-20s\n" ,
                "ID", "Name", "Associated Part", "Price");

        for (ServiceType service : servicetypes) {
            System.out.printf("%-8s%-35s%-30s $%,.2f\n",
                    service.getId(), service.getServicetype(), service.getRepalcedpart(), service.getPrice());
        }
    }

    public void extractDatabase() {
        // Clear the list to ensure no duplicate information
        servicetypes.clear();

        int countLine = Features.countLine(servicetype_data);
        String[] serviceId = Features.ReadCol(0, servicetype_data, ",");
        String[] serviceType = Features.ReadCol(1, servicetype_data, ",");
        String[] replacedParts = Features.ReadCol(2, servicetype_data, ",");
        String[] serviceCost = Features.ReadCol(3, servicetype_data, ",");

        for (int i = 0; i < countLine - 1; i++) {
            // Ensure correct parsing
            String serviceID = serviceId[i].trim();
            String serviceTypeName = serviceType[i].trim();
            String replacedPartList = replacedParts[i].trim();
            double servicePrice = Double.parseDouble(serviceCost[i].trim().replace("$", "").replace(",", "").trim());

            // Add the Service object to the list
            servicetypes.add(new ServiceType(serviceID, serviceTypeName, replacedPartList, servicePrice));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getRepalcedpart() {
        return repalcedpart;
    }

    public void setRepalcedpart(String repalcedpart) {
        this.repalcedpart = repalcedpart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
