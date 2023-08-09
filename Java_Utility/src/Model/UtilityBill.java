package Model;

import java.util.Date;

public class UtilityBill {
    private double reading;
    private ServiceType type;
    private Date date;
    private double amount;

    // Constructor

    public UtilityBill(double reading, ServiceType type, Date date) {
        setReading(reading);
        setType(type);
        setDate(date);
        setAmount();
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

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    public void setAmount() {
        this.amount = (this.reading * this.type.getUnitCharges()) + this.type.getServiceCharges();
    }

    public double getAmount() {
        return this.amount;
    }
}
