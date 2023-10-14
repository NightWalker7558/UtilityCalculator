package Model;

import java.util.ArrayList;

/**
 * The Customer class represents a customer using the utility management system.
 */
public class Customer {

  /**
   * The username of the Customer.
   */
  private String username;
  /**
   * The password of the Customer.
   */
  private String password;
  /**
   * The email of the Customer.
   */
  private String email;
  /**
   * The WrittenBills instance for the Customer.
   * 
   * @see WrittenBills
   */
  static WrittenBills writtenBills = new WrittenBills();

  /**
   * Constructs a Customer instance with the specified username, password, and
   * email.
   *
   * @param username The username of the customer.
   * @param password The password of the customer.
   * @param email    The email of the customer.
   */
  public Customer(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  /**
   * Gets the username of the Customer.
   *
   * @return String representing the username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of the Customer.
   *
   * @param username The new username.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password of the Customer.
   *
   * @return String representing the password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the Customer.
   *
   * @param password The new password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the email of the Customer.
   *
   * @return String representing the email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the Customer.
   *
   * @param email The new email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Adds a utility bill for the Customer.
   *
   * @param utilityType      The type of utility.
   * @param meterMeasurement The meter measurement.
   * @param date             The date of the bill.
   */
  public void addBill(
      String utilityType,
      double meterMeasurement,
      String date) {
    writtenBills.addBill(username, utilityType, meterMeasurement, date);
  }

  /**
   * Edits a utility bill for the Customer.
   *
   * @param billId              The ID of the bill to be edited.
   * @param newMeterMeasurement The new meter measurement.
   */
  public void editBill(int billId, double newMeterMeasurement) {
    writtenBills.editBill(billId, newMeterMeasurement);
  }

  /**
   * Deletes a utility bill for the Customer.
   *
   * @param billId The ID of the bill to be deleted.
   */
  public void deleteBill(int billId) {
    writtenBills.deleteBill(billId);
  }

  /**
   * Gets all utility bills for the Customer.
   *
   * @return ArrayList of UtilityBill representing the bills.
   */
  public ArrayList<UtilityBill> getBills() {
    ArrayList<UtilityBill> userBills = new ArrayList<>();
    writtenBills.loadBillsFromFile();
    for (UtilityBill bill : writtenBills.getBills()) {
      if (bill.getUserName().equals(username)) {
        userBills.add(bill);
      }
    }
    return userBills;
  }
}
