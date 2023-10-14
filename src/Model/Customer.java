package Model;

import java.util.ArrayList;

public class Customer {

  private String username;
  private String password;
  private String email;
  static WrittenBills writtenBills = new WrittenBills();

  // Add additional attributes as needed

  public Customer(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  /**
   * @return String
   */
  // Getters and setters for the attributes

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void addBill(
    String utilityType,
    double meterMeasurement,
    String date
  ) {
    writtenBills.addBill(username, utilityType, meterMeasurement, date);
  }

  public void editBill(int billId, double newMeterMeasurement) {
    writtenBills.editBill(billId, newMeterMeasurement);
  }

  public void deleteBill(int billId) {
    writtenBills.deleteBill(billId);
  }

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
