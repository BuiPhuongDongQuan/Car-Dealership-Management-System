package Components;

import Features.Features;


import java.util.ArrayList;

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

    public void getServiceDate(String serviceDate){

    }
}
