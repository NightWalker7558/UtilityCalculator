package Controller;

import Model.UtilityBill;
import Model.WrittenBills;
import java.util.ArrayList;

/**
 * <h1>StaffController Class</h1>
 *
 * The StaffController class provides methods for managing and retrieving
 * utility bills.
 *
 * <p>
 * This class contains methods for viewing all bills, viewing bills for a
 * specific user, and calculating
 * the total price of all bills.
 * </p>
 *
 * <p>
 * <strong>Usage Example:</strong>
 * </p>
 * 
 * <pre>
 * <code>
 * // Test viewAllBills
 * ArrayList<UtilityBill> allBills = viewAllBills();
 * printBills(allBills, "All Bills:");
 *
 * // Test viewUserBills
 * String usernameToView = "hakeem_doe"; // Replace with actual username
 * ArrayList<UtilityBill> userBills = viewUserBills(usernameToView);
 * printBills(userBills, "Bills for User " + usernameToView + ":");
 *
 * // Test calculateTotalPrice
 * double totalPrice = calculateTotalPrice();
 * System.out.println("Total Price for All Bills: " + totalPrice);
 * </code>
 * </pre>
 *
 * @see UtilityBill
 * @see WrittenBills
 */
public class StaffController {

  /**
   * <h1>Static WrittenBills Object</h1>
   *
   * The static WrittenBills object is used to manage and store utility bills in
   * the application.
   *
   * <p>
   * This object is instantiated as a static member of the {@link StaffController}
   * class and serves as a
   * central repository for utility bills. It provides methods to add, retrieve,
   * and manage utility bills.
   * </p>
   *
   * <p>
   * <strong>Usage Example:</strong>
   * </p>
   * 
   * <pre>
   * <code>
   * private static WrittenBills writtenBills = new WrittenBills();
   * </code>
   * </pre>
   *
   * @see StaffController
   * @see WrittenBills
   */
  private static WrittenBills writtenBills = new WrittenBills();

  /**
   * <h1>Main Method for Testing</h1>
   *
   * The main method of the StaffController class is used for testing various
   * functionalities of the class.
   *
   * <p>
   * It demonstrates how to use the following methods for testing purposes:
   * </p>
   *
   * <ul>
   * <li>View all utility bills using {@link #viewAllBills()}</li>
   * <li>View utility bills for a specific user using
   * {@link #viewUserBills(String)}</li>
   * <li>Calculate the total price of all utility bills using
   * {@link #calculateTotalPrice()}</li>
   * </ul>
   *
   * <p>
   * The testing examples show how to retrieve and display utility bills for all
   * users and a specific user.
   * The calculated total price is also displayed.
   * </p>
   *
   * <p>
   * <strong>Usage Example:</strong>
   * </p>
   * 
   * <pre>
   * <code>
   * public static void main(String[] args) {
   *     // Test viewAllBills
   *     ArrayList<UtilityBill> allBills = viewAllBills();
   *     printBills(allBills, "All Bills:");
   *
   *     // Test viewUserBills
   *     String usernameToView = "hakeem_doe"; // Replace with actual username
   *     ArrayList<UtilityBill> userBills = viewUserBills(usernameToView);
   *     printBills(userBills, "Bills for User " + usernameToView + ":");
   *
   *     // Test calculateTotalPrice
   *     double totalPrice = calculateTotalPrice();
   *     System.out.println("Total Price for All Bills: " + totalPrice);
   * }
   * </code>
   * </pre>
   *
   * @param args The command-line arguments (not used in this method).
   *
   * @see #viewAllBills()
   * @see #viewUserBills(String)
   * @see #calculateTotalPrice()
   * @see UtilityBill
   * @see WrittenBills
   * @see #printBills(ArrayList, String)
   */
  /**
   * <h1>Main Method for Testing</h1>
   *
   * The main method of the StaffController class is used for testing various
   * functionalities of the class.
   *
   * <p>
   * It demonstrates how to use the following methods for testing purposes:
   * </p>
   *
   * <ul>
   * <li>View all utility bills using {@link #viewAllBills()}</li>
   * <li>View utility bills for a specific user using
   * {@link #viewUserBills(String)}</li>
   * <li>Calculate the total price of all utility bills using
   * {@link #calculateTotalPrice()}</li>
   * </ul>
   *
   * <p>
   * The testing examples show how to retrieve and display utility bills for all
   * users and a specific user.
   * The calculated total price is also displayed.
   * </p>
   *
   * <p>
   * <strong>Usage Example:</strong>
   * </p>
   * 
   * <pre>
   * <code>
   * public static void main(String[] args) {
   *     // Test viewAllBills
   *     ArrayList<UtilityBill> allBills = viewAllBills();
   *     printBills(allBills, "All Bills:");
   *
   *     // Test viewUserBills
   *     String usernameToView = "hakeem_doe"; // Replace with actual username
   *     ArrayList<UtilityBill> userBills = viewUserBills(usernameToView);
   *     printBills(userBills, "Bills for User " + usernameToView + ":");
   *
   *     // Test calculateTotalPrice
   *     double totalPrice = calculateTotalPrice();
   *     System.out.println("Total Price for All Bills: " + totalPrice);
   * }
   * </code>
   * </pre>
   *
   * @param args The command-line arguments (not used in this method).
   *
   * @see #viewAllBills()
   * @see #viewUserBills(String)
   * @see #calculateTotalPrice()
   * @see UtilityBill
   * @see WrittenBills
   * @see #printBills(ArrayList, String)
   */
  public static void main(String[] args) {
    // Test viewAllBills
    ArrayList<UtilityBill> allBills = viewAllBills();
    printBills(allBills, "All Bills:");

    // Test viewUserBills
    String usernameToView = "hakeem_doe"; // Replace with actual username
    ArrayList<UtilityBill> userBills = viewUserBills(usernameToView);
    printBills(userBills, "Bills for User " + usernameToView + ":");

    // Test calculateTotalPrice
    double totalPrice = calculateTotalPrice();
    System.out.println("Total Price for All Bills: " + totalPrice);
  }

  /**
   * <h1>View All Utility Bills</h1>
   *
   * Retrieves a list of all utility bills stored in the application.
   *
   * <p>
   * This method accesses the {@link WrittenBills} object to retrieve a list of
   * all utility bills. It provides a
   * convenient way to view and access all bills stored in the application.
   * </p>
   *
   * <p>
   * <strong>Usage Example:</strong>
   * </p>
   * 
   * <pre>
   * <code>
   * ArrayList<UtilityBill> allBills = viewAllBills();
   * </code>
   * </pre>
   *
   * @return An ArrayList of {@link UtilityBill} objects representing all the
   *         utility bills.
   *
   * @see UtilityBill
   * @see WrittenBills
   * @see StaffController
   */
  public static ArrayList<UtilityBill> viewAllBills() {
    return writtenBills.getBills();
  }

  /**
   * <h1>View Utility Bills for a User</h1>
   *
   * Retrieves a list of utility bills for a specific user.
   *
   * <p>
   * This method takes a username as a parameter and retrieves a list of utility
   * bills associated with that user
   * from the {@link WrittenBills} object. It creates a new ArrayList to store the
   * user's bills and populates it
   * with the matching utility bills.
   * </p>
   *
   * <p>
   * <strong>Usage Example:</strong>
   * </p>
   * 
   * <pre>
   * <code>
   * String usernameToView = "hakeem_doe"; // Replace with the actual username
   * ArrayList<UtilityBill> userBills = viewUserBills(usernameToView);
   * </code>
   * </pre>
   *
   * @param username The username of the user for whom the bills are to be
   *                 retrieved.
   * @return An ArrayList of {@link UtilityBill} objects representing the utility
   *         bills for the specified user.
   *
   * @see UtilityBill
   * @see WrittenBills
   * @see StaffController
   */
  public static ArrayList<UtilityBill> viewUserBills(String username) {
    ArrayList<UtilityBill> userBills = new ArrayList<>();
    for (UtilityBill bill : writtenBills.getBills()) {
      if (bill.getUserName().contains(username)) {
        userBills.add(bill);
      }
    }
    return userBills;
  }

  /**
   * <h1>Calculate Total Price of Utility Bills</h1>
   *
   * Calculates the total price of all utility bills stored in the application.
   *
   * <p>
   * This method iterates through the list of utility bills stored in the
   * {@link WrittenBills} object and calculates
   * the total price by summing the prices of all the bills. It provides a way to
   * determine the overall cost of all
   * utility bills in the system.
   * </p>
   *
   * <p>
   * <strong>Usage Example:</strong>
   * </p>
   * 
   * <pre>
   * <code>
   * double totalPrice = calculateTotalPrice();
   * System.out.println("Total Price for All Bills: " + totalPrice);
   * </code>
   * </pre>
   *
   * @return The total price of all utility bills as a double value.
   *
   * @see UtilityBill
   * @see WrittenBills
   * @see StaffController
   */
  public static double calculateTotalPrice() {
    double total = 0.0;
    for (UtilityBill bill : writtenBills.getBills()) {
      total += bill.getPrice();
    }
    return total;
  }

  /**
   * <h1>Print Utility Bills</h1>
   *
   * Prints a list of utility bills to the console with a specified title.
   *
   * <p>
   * This method takes a list of utility bills and a title as parameters, and it
   * prints the bills to the console in
   * a formatted manner. Each bill's details, such as ID, username, utility type,
   * meter measurement, price, and date,
   * are displayed.
   * </p>
   *
   * <p>
   * <strong>Usage Example:</strong>
   * </p>
   * 
   * <pre>
   * <code>
   * ArrayList<UtilityBill> allBills = viewAllBills(); // Retrieve the list of bills
   * printBills(allBills, "All Bills:"); // Print the list with a title
   * </code>
   * </pre>
   *
   * @param bills The ArrayList of {@link UtilityBill} objects to be printed.
   * @param title The title to be displayed before the list of bills.
   *
   * @see UtilityBill
   * @see StaffController
   */
  public static void printBills(ArrayList<UtilityBill> bills, String title) {
    System.out.println(title);
    for (UtilityBill bill : bills) {
      System.out.println(
          "Bill ID: " + bill.getId() +
              ", Username: " + bill.getUserName() +
              ", Utility Type: " + bill.getUtilityType() +
              ", Meter Measurement: " + bill.getMeterMeasurement() +
              ", Price: " + bill.getPrice() +
              ", Date: " + bill.getDate());
    }
    System.out.println();
  }
}