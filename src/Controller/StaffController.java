package Controller;

import Model.UtilityBill;
import Model.WrittenBills;
import java.util.ArrayList;

public class StaffController {

  private static WrittenBills writtenBills = new WrittenBills();

  /**
   * @param args
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
   * @return ArrayList<UtilityBill>
   */
  public static ArrayList<UtilityBill> viewAllBills() {
    return writtenBills.getBills();
  }

  public static ArrayList<UtilityBill> viewUserBills(String username) {
    ArrayList<UtilityBill> userBills = new ArrayList<>();
    for (UtilityBill bill : writtenBills.getBills()) {
      if (bill.getUserName().contains(username)) {
        userBills.add(bill);
      }
    }
    return userBills;
  }

  public static double calculateTotalPrice() {
    double total = 0.0;
    for (UtilityBill bill : writtenBills.getBills()) {
      total += bill.getPrice();
    }
    return total;
  }

  public static void printBills(ArrayList<UtilityBill> bills, String title) {
    System.out.println(title);
    for (UtilityBill bill : bills) {
      System.out.println(
        "Bill ID: " +
        bill.getId() +
        ", Username: " +
        bill.getUserName() +
        ", Utility Type: " +
        bill.getUtilityType() +
        ", Meter Measurement: " +
        bill.getMeterMeasurement() +
        ", Price: " +
        bill.getPrice() +
        ", Date: " +
        bill.getDate()
      );
    }
    System.out.println();
  }
}
