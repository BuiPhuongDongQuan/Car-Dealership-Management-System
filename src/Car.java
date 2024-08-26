public class Car {
    private int cID;
    private String make;
    private String model;
    private int year;
    private float price;
    private String color;
    private float mileage;
    private boolean status;
    private String additionalNotes;
    private Service serviceHistory;

    public Car(int cID, String make, String model, int year, float price, String color, float mileage, boolean status, String additionalNotes, String serviceHistory) {
        this.cID = cID;
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
}
