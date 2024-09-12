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

import java.util.*;

public class ServiceType {
    private String id;
    private String servicetype;
    private String repalcedpart;
    private long price;

    private final String servicetype_data = "src/Database/ServiceType.txt";

    private ArrayList<ServiceType> servicetypes = new ArrayList<>();

    public ServiceType(String id, String servicetype, String repalcedpart, long price) {
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
            System.out.printf("%-8s%-35s%-30s %,d VND\n",
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
            long servicePrice = Long.parseLong(serviceCost[i].trim().replace(".", ""));

            // Add the Service object to the list
            servicetypes.add(new ServiceType(serviceID, serviceTypeName, replacedPartList, servicePrice));
        }
    }

    //validate service
    public boolean validateServiceTypeID(String serviceID){
        extractDatabase();
        boolean validateServiceTypeID = false;
        for(ServiceType service:servicetypes) {
            if (service.getId().equals(serviceID)) {
                validateServiceTypeID = true;
            }
        }
        return validateServiceTypeID;
    }

    //print service infor
    public void printServiceInfo(String serviceID){
        extractDatabase();
        for(ServiceType service:servicetypes) {
            if(service.id.equals(serviceID)){
                System.out.println("- Service ID: " + service.id);
                System.out.println("- Service Type: " + service.servicetype);
                System.out.println("- Replaced Part: " + service.repalcedpart);
                System.out.printf("- Price (in VND): %,d\n", service.price);
            }
        }
    }

    public ServiceType getService(String serviceID){
        extractDatabase();

        for(ServiceType service:servicetypes) {
            if(service.id.equals(serviceID)){
                return service;
            }
        }

        return null;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
