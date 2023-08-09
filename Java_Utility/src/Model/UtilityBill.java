package Model;

public class UtilityBill {
    private int id;
    private String userName;
    private String utilityType;
    private int meterMeasurement;
    private double price;
    private double reading;
    private ServiceType type;
    private String date;
    private double amount;

    // Constructor
    public UtilityBill(int id, String userName, String utilityType, int meterMeasurement, double price, String date) {
        this.id = id;
        this.userName = userName;
        this.utilityType = utilityType;
        this.meterMeasurement = meterMeasurement;
        this.price = price;
        this.date = date;
        setReading(0.0); // Default reading
        setprice();
    }

    // Getters & Setters

    public void setReading(double reading) {
        this.reading = reading;
    }

    public double getReading() {
        return this.reading;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public ServiceType getType() {
        return this.type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setAmount() {
        if (type != null) {
            this.amount = (this.reading * this.type.getUnitCharges()) + this.type.getServiceCharges();
        } else {
            this.amount = 0.0;
        }
    }

    public double getAmount() {
        return this.amount;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUtilityType() {
        return utilityType;
    }

    public int getMeterMeasurement() {
        return meterMeasurement;
    }

    public double getPrice() {

        return price;
    }

    public void setprice() {
        if (utilityType == null) {
            System.out.println("Service Type is Null");
            this.price = 0;
        } else {
            if (this.utilityType.toLowerCase().equals("gas")) {

                this.setType(ServiceType.GAS);
            }
            if (this.utilityType.toLowerCase().equals("electricity")) {

                this.setType(ServiceType.ELECTRICITY);
            }
            if (this.utilityType.toLowerCase().equals("water")) {

                this.setType(ServiceType.WATER);
            }
            this.amount = (this.reading * this.type.getUnitCharges()) + this.type.getServiceCharges();

        }

    }

    public void setMeterMeasurement(int parseInt) {
        this.meterMeasurement = parseInt;
    }
}
