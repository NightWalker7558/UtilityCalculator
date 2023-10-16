package Model;

/**
 * The UtilityBill class represents a utility bill associated with a specific
 * customer.
 * It encompasses crucial details like the unique identifier (id), customer's
 * username,
 * type of utility (e.g., electricity, gas, water), meter measurement,
 * calculated price,
 * service type (GAS, ELECTRICITY, WATER), and the date of the bill.
 */
public class UtilityBill {

  /**
   * The unique identifier of the bill.
   */
  private int id;
  /**
   * The username of the customer.
   */
  private String userName;
  /**
   * The type of utility.
   */
  private String utilityType;
  /**
   * The meter measurement.
   */
  private double meterMeasurement;
  /**
   * The calculated price.
   */
  private double price;
  /**
   * The service type.
   */
  private ServiceType type;
  /**
   * The date of the bill.
   */
  private String date;

  /**
   * Constructs a UtilityBill object with the provided details.
   *
   * <h1>Constructor Details</h1>
   * This constructor initializes a new utility bill object with the specified
   * attributes,
   * including the unique identifier (id), customer's username, utility type,
   * meter measurement,
   * price, and date.
   *
   * @param id               The unique identifier for the utility bill.
   * @param userName         The username of the customer associated with the
   *                         bill.
   * @param utilityType      The type of utility (e.g., electricity, gas, water).
   * @param meterMeasurement The meter measurement for the utility.
   * @param price            The total price of the utility bill.
   * @param date             The date of the utility bill.
   *
   * @implSpec
   *           The method internally calls the setPrice() method to calculate the
   *           price of the utility bill
   *           based on the meter measurement and the service type.
   *
   * @see #setPrice()
   */
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
   * Sets the service type for the utility bill.
   *
   * <h1>Method Details</h1>
   * This method sets the service type for the utility bill, allowing for
   * categorization
   * based on the type of utility service (e.g., GAS, ELECTRICITY, WATER).
   *
   * @param type The service type to be set for the utility bill.
   *
   * @implSpec
   *           The method sets the provided service type to the utility bill's
   *           {@code type} attribute.
   *
   * @see ServiceType
   */

  public void setType(ServiceType type) {
    this.type = type;
  }

  /**
   * Returns the service type associated with the utility bill.
   *
   * <h1>Method Details</h1>
   * This method retrieves the service type assigned to the utility bill, which
   * indicates the
   * category of the utility service (e.g., GAS, ELECTRICITY, WATER).
   *
   * @return The service type associated with the utility bill.
   *
   * @implSpec
   *           The method returns the service type assigned to the utility bill's
   *           {@code type} attribute.
   *
   * @see ServiceType
   */
  public ServiceType getType() {
    return this.type;
  }

  /**
   * Sets the date for the utility bill.
   *
   * <h1>Method Details</h1>
   * This method sets the date for the utility bill, indicating the specific date
   * of the bill.
   *
   * @param date The date to be set for the utility bill.
   *
   * @implSpec
   *           The method sets the provided date to the utility bill's
   *           {@code date} attribute.
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Retrieves the date associated with the utility bill.
   *
   * <h1>Method Details</h1>
   * This method retrieves the date assigned to the utility bill, representing the
   * specific date
   * of the bill.
   *
   * @return The date associated with the utility bill.
   */
  public String getDate() {
    return this.date;
  }

  /**
   * gets the ID of the utility bill.
   * <h1>Method Details</h1>
   * This method retrieves the ID of the utility bill.
   *
   * @return the ID of the utility bill.
   */

  public int getId() {
    return id;
  }

  /**
   * gets the username of the customer.
   * <h1>Method Details</h1>
   * This method retrieves the username of the customer.
   *
   * @return the username of the customer.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * gets the type of utility.
   * <h1>Method Details</h1>
   * This method retrieves the type of utility.
   *
   * @return the type of utility.
   */
  public String getUtilityType() {
    return utilityType;
  }

  /**
   * gets the meter measurement.
   * <h1>Method Details</h1>
   * This method retrieves the meter measurement.
   *
   * @return the meter measurement.
   */
  public double getMeterMeasurement() {
    return meterMeasurement;
  }

  /**
   * gets the price.
   * <h1>Method Details</h1>
   * This method retrieves the price.
   *
   * @return the price.
   */
  public double getPrice() {
    return price;
  }

  /**
   * sets the price.
   * <h1>Method Details</h1>
   * This method sets the price.
   *
   * @param price the price to be set.
   */
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

  /**
   * sets the ID.
   * <h1>Method Details</h1>
   * This method sets the ID.
   *
   * @param parseInt the ID to be set.
   */
  public void setMeterMeasurement(double parseInt) {
    this.meterMeasurement = parseInt;
  }
}
