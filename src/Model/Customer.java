package Model;

import java.util.ArrayList;

/**
 * The Customer class represents a customer in the utility management system.
 * It encapsulates essential customer information, including the username,
 * password, and email associated with the customer.
 *
 * <h1>Usage</h1>
 * To use this class, create an instance of {@code Customer} and populate it
 * with the necessary customer details.
 *
 * <h1>Example Usage</h1>
 *
 * <pre>
 * <code>
 * Customer customer = new Customer("username", "password", "email@example.com");
 * </code>
 * </pre>
 *
 * <h1>Attributes</h1>
 * <ul>
 * <li>{@code username}: The username of the customer.</li>
 * <li>{@code password}: The password of the customer.</li>
 * <li>{@code email}: The email address of the customer.</li>
 * </ul>
 *
 * <h1>File Management</h1>
 * Customer data is stored in a text file specified by the
 * {@code customersFilePath} attribute.
 * This class provides methods to load customers from the file and save
 * customers to the file.
 *
 * <h1>Thread Safety</h1>
 * This class is not thread-safe, so external synchronization may be required
 * when accessed by multiple threads.
 *
 * @see CustomerController
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
   * <h1>Method Details</h1>
   * This method retrieves the username of the Customer by calling the function.
   *
   *
   * @return String representing the username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of the Customer.
   * <h1>Method Details</h1>
   * This method sets the username of the Customer by setting the username to the
   * username attribute.
   *
   * @param username The new username.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password of the Customer.
   * <h1>Method Details</h1>
   * This method retrieves the password of the Customer by calling the function.
   *
   * @return String representing the password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the Customer.
   * <h1>Method Details</h1>
   * This method sets the password of the Customer by setting the password to the
   * password attribute.
   *
   * @param password The new password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the email of the Customer.
   * <h1>Method Details</h1>
   * This method retrieves the email of the Customer by calling the function.
   *
   * @return String representing the email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the Customer.
   * <h1>Method Details</h1>
   * This method sets the email of the Customer by setting the email to the email
   * attribute
   *
   * @param email The new email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Adds a utility bill for the Customer.
   * <h1>Method Details</h1>
   * This method adds a utility bill for the Customer by calling the function.
   * and passing the parameters to the addBill function in WrittenBills.
   *
   * @see WrittenBills
   *
   * @param utilityType      The type of utility.
   * @param meterMeasurement The meter measurement.
   * @param date             The date of the bill.
   */
  public void addBill(
    String utilityType,
    double meterMeasurement,
    String date
  ) {
    writtenBills.addBill(username, utilityType, meterMeasurement, date);
  }

  /**
   * Edits a utility bill for the Customer.
   * <h1>Method Details</h1>
   * This method edits a utility bill for the Customer by passing the parameters
   * to editBill function in WrittenBills.
   *
   * @see WrittenBills
   *
   * @param billId              The ID of the bill to be edited.
   * @param newMeterMeasurement The new meter measurement.
   */
  public void editBill(int billId, double newMeterMeasurement) {
    writtenBills.editBill(billId, newMeterMeasurement);
  }

  /**
   * Deletes a utility bill for the Customer.
   * <h1>Method Details</h1>
   * This method deletes a utility bill for the Customer by passing the bill id to
   * deleteBill function in WrittenBills.
   *
   * @see WrittenBills
   *
   * @param billId The ID of the bill to be deleted.
   */
  public void deleteBill(int billId) {
    writtenBills.deleteBill(billId);
  }

  /**
   * Gets all utility bills for the Customer.
   * <h1>Method Details</h1>
   * This method gets all utility bills for the Customer by calling the loadBills
   * function of written bills and adding them in an arraylist.
   *
   * @see WrittenBills
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
