package Model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The WrittenBills class represents a collection of utility bills stored in a
 * file.
 */
public class WrittenBills {

  /**
   * The list of utility bills.
   */
  private ArrayList<UtilityBill> bills;
  /**
   * The path of the file containing the utility bills.
   */
  private String billsFilePath;
  /**
   * The ID of the utility bill.
   */
  private int id;

  /**
   * Constructs a WrittenBills instance, initializing the list of bills and
   * loading existing bills from a file.
   */
  public WrittenBills() {
    this.bills = new ArrayList<>();
    this.billsFilePath = "src/Model/bills.txt";

    this.id = 1;
    this.loadBillsFromFile();
  }

  /**
   * Gets the list of utility bills.
   *
   * @return ArrayList of UtilityBill representing the bills.
   */
  public ArrayList<UtilityBill> getBills() {
    return bills;
  }

  /**
   * Loads utility bills from a file.
   */
  public void loadBillsFromFile() {
    try {
      File file = new File(billsFilePath);
      Scanner scanner = new Scanner(file);
      bills = new ArrayList<>();

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        String UtilityType = parts[2].trim();
        double metermeasurment = Double.parseDouble(parts[3].trim());
        double price = Double.parseDouble(parts[4].trim());
        String Date = parts[5].trim();
        if (findiD(id) == false) {
          bills.add(
              new UtilityBill(id, name, UtilityType, metermeasurment, price, Date));
          increaseID();
        }
      }
      this.id = bills.get(bills.size() - 1).getId() + 1;
      scanner.close();
    } catch (Exception e) {
      System.out.println("Error loading bills from file");
    }
  }

  /**
   * Finds a utility bill by its ID.
   * 
   * @param id The ID of the bill to be found.
   */
  public boolean findiD(int id) {
    for (UtilityBill bill : bills) {
      if (bill.getId() == id) {
        return true;
      }
    }
    return false;
  }

  /**
   * Adds a utility bill for the Customer.
   *
   * @param username         The username of the Customer.
   * @param utilityType      The type of utility.
   * @param meterMeasurement The meter measurement.
   * @param date             The date of the bill.
   */
  public void addBill(
      String username,
      String utilityType,
      double meterMeasurement,
      String date) {
    bills.add(
        new UtilityBill(id, username, utilityType, meterMeasurement, 0.0, date));
    increaseID();
    saveBillsToFile();
  }

  /**
   * Increases the ID of the utility bill.
   */
  public void increaseID() {
    this.id = this.id + 1;
  }

  /**
   * Deletes a utility bill for the Customer.
   *
   * @param billId The ID of the bill to be deleted.
   */
  public void deleteBill(int billId) {
    UtilityBill bill = findbill(billId);
    if (bill != null) {
      bills.remove(bill);
      saveBillsToFile();
    }
  }

  /**
   * Edits a utility bill for the Customer.
   *
   * @param billId              The ID of the bill to be edited.
   * @param newMeterMeasurement The new meter measurement.
   */
  public void editBill(int billId, Double newMeterMeasurement) {
    UtilityBill bill = findbill(billId);
    if (bill != null) {
      bill.setMeterMeasurement(newMeterMeasurement);
      bill.setprice();
      saveBillsToFile();
    }
  }

  /**
   * Finds a utility bill by its ID.
   * 
   * @param billId The ID of the bill to be found.
   */
  public UtilityBill findbill(int billId) {
    for (UtilityBill bill : bills) {
      if (bill.getId() == billId) {
        return bill;
      }
    }
    return null;
  }

  /**
   * Saves the utility bills to a file.
   */
  private void saveBillsToFile() {
    try {
      File file = new File(billsFilePath);
      PrintWriter writer = new PrintWriter(file);

      for (UtilityBill bill : bills) {
        writer.println(
            bill.getId() +
                "," +
                bill.getUserName() +
                "," +
                bill.getUtilityType() +
                "," +
                bill.getMeterMeasurement() +
                "," +
                bill.getPrice() +
                "," +
                bill.getDate());
      }

      writer.close();
    } catch (IOException e) {
      System.out.println("Error writing to customers file: " + e.getMessage());
    }
  }
}
