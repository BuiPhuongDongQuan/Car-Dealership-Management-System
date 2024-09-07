package Components;

import Features.Features;

import java.util.*;

public class AutoPart {
    private String id;
    private String name;
    private String brand;
    private String model;
    private String condition;
    private String warranty;
    private long price;

    private final String part_data = "src/Database/AutoPart.txt";

    private ArrayList<AutoPart> autoParts = new ArrayList<>();

    public AutoPart(String id, String name, String brand, String model, String condition, String warranty, long price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.warranty = warranty;
        this.price = price;
    }

    public AutoPart() {

    }

    //view all part and sort by ascending or descending
    public void viewAllAutoPartSort(String sortOrder) {
        extractDatabase();
        // Print header
        System.out.printf("%-8s %-20s %-10s %-10s %-15s %-15s %-10s\n",
                "ID", "Name", "Brand", "Model", "Condition", "Warranty", "Price");

        for (AutoPart autoPart : autoParts) {
            System.out.printf("%-8s %-20s %-10s %-10s %-15s %-15s $%,.2f\n",
                    autoPart.getId(), autoPart.getName(), autoPart.getBrand(), autoPart.getModel(),
                    autoPart.getCondition(), autoPart.getWarranty(), autoPart.getPrice());
        }

    }

    public void extractDatabase() {
        // Empty the ArrayList to ensure no duplicate information
        autoParts.clear();

        int countLine = Features.countLine(part_data);
        String[] partId = Features.ReadCol(0, part_data, ",");
        String[] partName = Features.ReadCol(1, part_data, ",");
        String[] manufacturer = Features.ReadCol(2, part_data, ",");
        String[] partNumber = Features.ReadCol(3, part_data, ",");
        String[] condition = Features.ReadCol(4, part_data, ",");
        String[] warranty = Features.ReadCol(5, part_data, ",");
        String[] cost = Features.ReadCol(6, part_data, ",");

        for (int i = 0; i < countLine - 1; i++) {
            // Ensure correct parsing
            String autoPartId = partId[i].trim();
            String autoPartName = partName[i].trim();
            String autoPartManufacturer = manufacturer[i].trim();
            String autoPartNumber = partNumber[i].trim();
            String autoPartCondition = condition[i].trim();
            String autoPartWarranty = warranty[i].trim();
            long autoPartCost = Long.parseLong(cost[i].trim().replace(".", "").trim());

            // Add the AutoPart object to the list
            autoParts.add(new AutoPart(autoPartId, autoPartName, autoPartManufacturer, autoPartNumber, autoPartCondition, autoPartWarranty, autoPartCost));
        }
    }

    public boolean validateAutoPartID(String autoPartID){
        extractDatabase();
        boolean validateautoPartID = false;
        for(AutoPart autoPart :autoParts) {
            if (autoPart.getId().equals(autoPartID)) {
                validateautoPartID = true;
            }
        }
        return validateautoPartID;
    }

    public void printAutoPartInfo(String autopartID){
        extractDatabase();
        for(AutoPart autoPart :autoParts) {
            if(autoPart.id.equals(autopartID)){
                System.out.println("- AutoPart: " + autoPart.id);
                System.out.println("- Name: " + autoPart.name);
                System.out.println("- Brand: " + autoPart.brand);
                System.out.println("- Model: " + autoPart.model);
                System.out.println("- Condition: " + autoPart.condition);
                System.out.println("- Warranty: " + autoPart.warranty);
                System.out.printf("- Price (in VND): %,d\n", autoPart.price);
            }
        }
    }

    public AutoPart getautoPart(String autoPartId){
        extractDatabase();

        for(AutoPart autoPart:autoParts){
            if(autoPart.id.equals(autoPartId)){
                return autoPart;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}

