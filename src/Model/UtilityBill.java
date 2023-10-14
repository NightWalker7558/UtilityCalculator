package Model;

public class UtilityBill {

  private int id;
  private String userName;
  private String utilityType;
  private double meterMeasurement;
  private double price;
  private ServiceType type;
  private String date;

  // Constructor
  public UtilityBill(
    int id,
    String userName,
    String utilityType,
    double meterMeasurement,
    double price,
    String date
  ) {
    this.id = id;
    this.userName = userName;
    this.utilityType = utilityType;
    this.meterMeasurement = meterMeasurement;
    this.price = price;
    this.date = date;

    setprice();
  }

  /**
   * @param type
   */
  // Getters & Setters

  public void setType(ServiceType type) {
    this.type = type;
  }

  /**
   * @return ServiceType
   */
  public ServiceType getType() {
    return this.type;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDate() {
    return this.date;
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

  public double getMeterMeasurement() {
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
      this.price =
        (this.meterMeasurement * this.type.getUnitCharges()) +
        this.type.getServiceCharges();
    }
  }

  public void setMeterMeasurement(double parseInt) {
    this.meterMeasurement = parseInt;
  }
}
