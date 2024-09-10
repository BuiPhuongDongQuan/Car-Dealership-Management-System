package Components;

import Features.Features;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class Car {
    private String id;
    private String make;
    private String model;
    private int year;
    private long price;
    private String color;
    private float mileage;
    private String status;


    private static String tableFormat = "%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n";
    private static final String car_data = "src/Database/Car.txt";

    ArrayList<Car> cars = new ArrayList<>();

    public Car(String id, String make, String model, int year, long price, String color, float mileage, String status) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.mileage = mileage;
        this.status = status;
    }

    public Car() {

    }

    //view all car and sort by ascending or descending
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

    //view all car within specific category
    public void viewCarBrandSort(String category, String sortOrder) {
        extractDatabase();

        ArrayList<Car> brandCar = new ArrayList<>();

        for(Car car: cars){
            if(car.getMake().equalsIgnoreCase(category)){
                brandCar.add(car);
            }
        }

        printCarDetailHeader();

        //Source: https://www.youtube.com/watch?v=wzWFQTLn8hI
        if(sortOrder.equalsIgnoreCase("ascending")){
            Collections.sort(brandCar, new Comparator<Car>()
            {
                public int compare(Car p1, Car p2){
                    return Long.valueOf(p1.price).compareTo(p2.price);
                }
            });
            for(int i = 0; i < brandCar.size(); i++){
                String priceFormat = String.format("%,d", brandCar.get(i).getPrice());
                System.out.printf(tableFormat, brandCar.get(i).getId(), brandCar.get(i).getMake(), brandCar.get(i).getModel(), brandCar.get(i).getYear(), brandCar.get(i).getMileage(), brandCar.get(i).getColor(), brandCar.get(i).getStatus(), priceFormat + " VND");
            }
        }
        else if(sortOrder.equalsIgnoreCase("descending")){
            Collections.sort(brandCar, new Comparator<Car>()
            {
                public int compare(Car p1, Car p2){
                    return Long.valueOf(p2.price).compareTo(p1.price);
                }
            });
            for(int i = 0; i < brandCar.size(); i++){
                String priceFormat = String.format("%,d", brandCar.get(i).getPrice());
                System.out.printf(tableFormat, brandCar.get(i).getId(), brandCar.get(i).getMake(), brandCar.get(i).getModel(), brandCar.get(i).getYear(), brandCar.get(i).getMileage(), brandCar.get(i).getColor(), brandCar.get(i).getStatus(), priceFormat + " VND");
            }
        }
        else{
            for(Car car: brandCar){
                String priceFormat = String.format("%,d", car.getPrice());
                System.out.printf(tableFormat, car.getId(), car.getMake(), car.getModel(), car.getYear(), car.getMileage(), car.getColor(), car.getStatus(), priceFormat + " VND");
            }
        }

        printTableBottomBorder();
    }


    //get all available category
    public String getCarBrandList(){
        String[] category = Features.ReadCol(1,car_data, ",");
        ArrayList<String> categoryList = new ArrayList<String>();


        for(int i = 1; i < category.length; i++){
            if(categoryList.contains(category[i]) == false){
                categoryList.add(category[i]);
            }
        }
        String list = Features.arrayListToCSVString(categoryList);
        return list;
    }

    public void viewCarStatusSort(String status, String sortOrder) {
        extractDatabase();

        ArrayList<Car> statusCar = new ArrayList<>();

        for(Car car: cars){
            if(car.getStatus().equalsIgnoreCase(status)){
                statusCar.add(car);
            }
        }

        printCarDetailHeader();

        //Source: https://www.youtube.com/watch?v=wzWFQTLn8hI
        if(sortOrder.equalsIgnoreCase("ascending")){
            Collections.sort(statusCar, new Comparator<Car>()
            {
                public int compare(Car p1, Car p2){
                    return Long.valueOf(p1.price).compareTo(p2.price);
                }
            });
            for(int i = 0; i < statusCar.size(); i++){
                String priceFormat = String.format("%,d", statusCar.get(i).getPrice());
                System.out.printf(tableFormat, statusCar.get(i).getId(), statusCar.get(i).getMake(), statusCar.get(i).getModel(), statusCar.get(i).getYear(), statusCar.get(i).getMileage(), statusCar.get(i).getColor(), statusCar.get(i).getStatus(), priceFormat + " VND");
            }
        }
        else if(sortOrder.equalsIgnoreCase("descending")){
            Collections.sort(statusCar, new Comparator<Car>()
            {
                public int compare(Car p1, Car p2){
                    return Long.valueOf(p2.price).compareTo(p1.price);
                }
            });
            for(int i = 0; i < statusCar.size(); i++){
                String priceFormat = String.format("%,d", statusCar.get(i).getPrice());
                System.out.printf(tableFormat, statusCar.get(i).getId(), statusCar.get(i).getMake(), statusCar.get(i).getModel(), statusCar.get(i).getYear(), statusCar.get(i).getMileage(), statusCar.get(i).getColor(), statusCar.get(i).getStatus(), priceFormat + " VND");
            }
        }
        else{
            for(Car car: statusCar){
                String priceFormat = String.format("%,d", car.getPrice());
                System.out.printf(tableFormat, car.getId(), car.getMake(), car.getModel(), car.getYear(), car.getMileage(), car.getColor(), car.getStatus(), priceFormat + " VND");
            }
        }

        printTableBottomBorder();
    }

    //view car within a price range
    public void viewPriceRangeSort() {
        extractDatabase();

        ArrayList<Car> priceRangeSort = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        String regex = "\\d{1,3}(\\.\\d{3})*"; // Regex to match prices with dots (e.g., 1.000.000)

        System.out.print("Enter the MINIMUM price (Ex: 500000000 or 500.000.000): ");
        String minInput = keyboard.nextLine().trim();
        while (!Pattern.matches(regex, minInput)) {
            System.out.println("Invalid format. Please enter the price in the correct format (e.g., 500000000 or 500.000.000).");
            System.out.print("Enter the MINIMUM price (Ex: 500000000 or 500.000.000): ");
            minInput = keyboard.nextLine().trim();
        }

        System.out.print("Enter the MAXIMUM price (Ex: 47256040000 or 47.256.040.000): ");
        String maxInput = keyboard.nextLine().trim();
        while (!Pattern.matches(regex, maxInput)) {
            System.out.println("Invalid format. Please enter the price in the correct format (e.g., 47256040000 or 47.256.040.000).");
            System.out.print("Enter the MAXIMUM price (Ex: 47256040000 or 47.256.040.000): ");
            maxInput = keyboard.nextLine().trim();
        }

        long min = Long.parseLong(minInput.replace(".", ""));
        long max = Long.parseLong(maxInput.replace(".", ""));

        if (min <= max) {
            printCarDetailHeader();
            for (Car car : cars) {
                if (car.getPrice() >= min && car.getPrice() <= max) {
                    priceRangeSort.add(car);
                }
            }

            for (Car car : priceRangeSort) {
                String priceFormat = String.format("%,d", car.getPrice());
                System.out.printf(tableFormat, car.getId(), car.getMake(), car.getModel(), car.getYear(), car.getMileage(), car.getColor(), car.getStatus(), priceFormat + " VND");
            }

            printTableBottomBorder();
            System.out.println();
        } else {
            System.out.println("Minimum price cannot be greater than the maximum price.");
            viewPriceRangeSort();
        }
    }


    //extract data from database when there are updates and add object to arraylist
    public void extractDatabase() {
        // Empty the ArrayList to ensure no duplicate information
        cars.clear();

        int countLine = Features.countLine(car_data);
        String[] id = Features.ReadCol(0, car_data, ",");
        String[] make = Features.ReadCol(1, car_data, ",");
        String[] model = Features.ReadCol(2, car_data, ",");
        String[] year = Features.ReadCol(3, car_data, ",");
        String[] price = Features.ReadCol(4, car_data, ",");
        String[] color = Features.ReadCol(5, car_data, ",");
        String[] mileage = Features.ReadCol(6, car_data, ",");
        String[] status = Features.ReadCol(7, car_data, ",");

        for (int i = 0; i < countLine - 1; i++) {
            // Ensure correct parsing
            String carId = id[i].trim();
            String carMake = make[i].trim();
            String carModel = model[i].trim();
            int carYear = Integer.parseInt(year[i].trim());
            long carPrice = Long.parseLong(price[i].trim().replace(".", ""));
            String carColor = color[i].trim();

            // Handle mileage properly
            float carMileage = 0f;
            if (!mileage[i].trim().equalsIgnoreCase("Available")) {
                carMileage = Float.parseFloat(mileage[i].trim().replace("km", "").replace(",", "").trim());
            }

            String carStatus = status[i].trim();
            cars.add(new Car(carId, carMake, carModel, carYear, carPrice, carColor, carMileage, carStatus));
        }
    }

    //print table header and top border
    public static void printCarDetailHeader(){
        System.out.println();
        for(int i = 0; i <=190;i++){
            if(i == 80){
                System.out.print(" Car Detail ");
            }
            if(i < 80 || i > 100){
                System.out.print("-");
            }
        }
        System.out.println();
        System.out.printf(tableFormat,"Car ID", "Brand", "Model", "Year", "Mileage", "Color", "Status", "Price");
        printTableBottomBorder();
    }

    public static void printTableBottomBorder(){
        for(int i = 0; i <=180;i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //validate carID
    public boolean validateCarID(String carID){
        extractDatabase();
        boolean validateCarID = false;
        for(Car car:cars) {
            if (car.getId().equals(carID)) {
                validateCarID = true;
            }
        }
        return validateCarID;
    }

    //display car info
    public void printCarInfo(String carID){
        extractDatabase();
        for(Car car:cars){
            if(car.id.equals(carID)){
                System.out.println("- CarID: " + car.id);
                System.out.println("- Brand: " + car.make);
                System.out.println("- Model: " + car.model);
                System.out.println("- Year: " + car.year);
                System.out.println("- Mileage: " + car.mileage);
                System.out.println("- Color: " + car.color);
                System.out.printf("- Price (in VND): %,d\n", car.price);
            }
        }
    }

    public Car getCar(String carID){
        extractDatabase();

        for(Car car:cars){
            if(car.id.equals(carID)){
                return car;
            }
        }
        return null;
    }

    public boolean isAvailable() {
        return "Available".equalsIgnoreCase(status);
    }

    public static void updateCarStatusInDatabase(Car car) throws IOException {
        //
        List<String> lines = Files.readAllLines(Paths.get(car_data));
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            if (line.contains(car.getId())) {
                // Replace the line with the updated status
                String updatedLine = line.replace(",Available", ",Sold");
                updatedLines.add(updatedLine);
            } else {
                updatedLines.add(line);
            }
        }

        Files.write(Paths.get(car_data), updatedLines);
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

}
