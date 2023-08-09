package Model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WrittenBills {
    private ArrayList<UtilityBill> bills;
    private String billsFilePath; // File path for storing written bills
    private int id;

    public WrittenBills() {
        this.bills = new ArrayList<>();
        this.billsFilePath = "src/Model/bills.txt";
        this.id = 1;

    }

    public ArrayList<UtilityBill> getBills() {
        return bills;
    }

    public void loadBillsFromFile() {
        try {
            File file = new File(billsFilePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String UtilityType = parts[2].trim();
                int metermeasurment = Integer.parseInt(parts[3].trim());
                double price = Double.parseDouble(parts[4].trim());
                String Date = parts[5].trim();
                bills.add(new UtilityBill(id, name, UtilityType, metermeasurment, price, Date));
                increaseID();

            }
            this.id = bills.get(bills.size() - 1).getId() + 1;
            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading bills from file");
        }
    }

    public void addBill(String username, String utilityType, int meterMeasurement, String date) {
        bills.add(new UtilityBill(id, username, utilityType, meterMeasurement, 0.0, date));
        increaseID();
        saveBillsToFile();
    }

    public void increaseID() {
        this.id = this.id + 1;
    }

    public void deleteBill(int billId) {
        UtilityBill bill = findbill(billId);
        if (bill != null) {
            bills.remove(bill);
            saveBillsToFile();
        }
    }

    public void editBill(int billId, String newMeterMeasurement) {
        UtilityBill bill = findbill(billId);
        if (bill != null) {
            bill.setMeterMeasurement(Integer.parseInt(newMeterMeasurement));
            bill.setprice();
            saveBillsToFile();

        }
    }

    public UtilityBill findbill(int billId) {
        for (UtilityBill bill : bills) {
            if (bill.getId() == billId) {
                return bill;
            }
        }
        return null;
    }

    private void saveBillsToFile() {
        try {
            File file = new File(billsFilePath);
            PrintWriter writer = new PrintWriter(file);

            for (UtilityBill bill : bills) {
                writer.println(bill.getId() + "," + bill.getUserName() + "," + bill.getUtilityType() + ","
                        + bill.getMeterMeasurement() + "," + bill.getPrice() + "," + bill.getDate());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to customers file: " + e.getMessage());
        }
    }
}
