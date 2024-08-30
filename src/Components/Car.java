package Components;

import Features.Features;

import java.util.*;

public class Car {
    private String id;
    private String make;
    private String model;
    private int year;
    private long price;
    private String color;
    private float mileage;
    private String status;
    private String additionalNotes;
    private Service serviceHistory;

    private static String tableFormat = "%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n";
    private final String car_data = "src/Database/Car.txt";

    ArrayList<Car> cars = new ArrayList<>();

    public Car(String id, String make, String model, int year, long price, String color, float mileage, String status, String additionalNote, String string2) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.mileage = mileage;
        this.status = status;
        this.additionalNotes = null;
        this.serviceHistory = null;
    }

    public Car() {

    }

    //view all product, allow sorting by price range
    public void viewAllCarSort(String sortOrder){
        extractDatabase();

        printCarDetailHeader();

        //Source: https://www.youtube.com/watch?v=wzWFQTLn8hI
        if(sortOrder.equalsIgnoreCase("ascending")){
            Collections.sort(cars, new Comparator<Car>()
            {
                public int compare(Car p1, Car p2){
                    return Long.valueOf(p1.price).compareTo(p2.price);
                }
            });
            for(int i = 0; i < cars.size(); i++){
                String priceFormat = String.format("%,d", cars.get(i).getPrice());
                System.out.printf(tableFormat, cars.get(i).getId(), cars.get(i).getMake(), cars.get(i).getModel(), cars.get(i).getYear(), cars.get(i).getMileage(), cars.get(i).getColor(), cars.get(i).getStatus(), priceFormat + " VND");
            }
        }
        else if(sortOrder.equalsIgnoreCase("descending")){
            Collections.sort(cars, new Comparator<Car>()
            {
                public int compare(Car p1, Car p2){
                    return Long.valueOf(p2.price).compareTo(p1.price);
                }
            });
            for(int i = 0; i < cars.size(); i++){
                String priceFormat = String.format("%,d", cars.get(i).getPrice());
                System.out.printf(tableFormat, cars.get(i).getId(), cars.get(i).getMake(), cars.get(i).getModel(), cars.get(i).getYear(), cars.get(i).getMileage(), cars.get(i).getColor(), cars.get(i).getStatus(), priceFormat + " VND");
            }
        }
        else{
            for(Car car:cars){
                String priceFormat = String.format("%,d", car.getPrice());
                System.out.printf(tableFormat, car.getId(), car.getMake(), car.getModel(), car.getYear(), car.getMileage(), car.getColor(), car.getStatus(), priceFormat + " VND");
            }
        }

        printTableBottomBorder();
    }

    //extract data from database when there are updates and add object to arraylist
    public void extractDatabase(){
        //empty an ArrayList to make sure no info was repeated
        cars.clear();

        int countLine = Features.countLine(car_data);
        String[] id = Features.ReadCol(0, car_data,",");
        String[] make = Features.ReadCol(1, car_data,",");
        String[] model = Features.ReadCol(2, car_data,",");
        String[] year = Features.ReadCol(3, car_data,",");
        String[] price = Features.ReadCol(4, car_data,",");
        String[] color = Features.ReadCol(5, car_data,",");
        String[] mileague = Features.ReadCol(6, car_data,",");
        String[] status = Features.ReadCol(7, car_data,",");
        String[] additionalNotes = Features.ReadCol(8, car_data,",");
        String[] serviceHistory = Features.ReadCol(9, car_data,",");


        for(int i = 0; i < countLine - 1; i++){
            cars.add(new Car(id[i], make[i], model[i], Integer.parseInt(year[i]), Long.parseLong(price[i]),color[i], Float.parseFloat(mileague[i]), status[i], additionalNotes[i],serviceHistory[i]));
        }
    }

    //print table header and top border
    public static void printCarDetailHeader(){
        System.out.println();
        for(int i = 0; i <=90;i++){
            if(i == 35){
                System.out.print(" Product Detail ");
            }
            if(i < 35 || i > 50){
                System.out.print("-");
            }
        }
        System.out.println();
        System.out.printf(tableFormat,"Product ID", "Title", "Price", "Catetory");
        printTableBottomBorder();
    }

    public static void printTableBottomBorder(){
        for(int i = 0; i <=90;i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Service getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(Service serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
