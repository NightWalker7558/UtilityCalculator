package Model;

import java.util.ArrayList;

public class CustomerTest {

    public static void main(String[] args) {
        // Create a test customer
        Customer testCustomer = new Customer("john_doe", "password123", "john@example.com");

        // Add a new bill for the customer
        testCustomer.addBill("Electricity", 150, "2023-08-10");

        // Edit the added bill
        int billIdToEdit = 1; // Assuming bill with ID 1 exists
        testCustomer.editBill(billIdToEdit, 200);

        // Print updated bills for the customer
        System.out.println("Customer's Updated Bills:");
        ArrayList<UtilityBill> userBills = testCustomer.getBills();
        for (UtilityBill bill : userBills) {
            System.out.println("Bill ID: " + bill.getId() +
                    ", Username: " + bill.getUserName() +
                    ", Utility Type: " + bill.getUtilityType() +
                    ", Meter Measurement: " + bill.getMeterMeasurement() +
                    ", Price: " + bill.getPrice() +
                    ", Date: " + bill.getDate());
        }

        // Delete a bill
        int billIdToDelete = 0; // Assuming bill with ID 2 exists
        testCustomer.deleteBill(billIdToDelete);

        // Print updated bills after deletion
        System.out.println("\nCustomer's Bills after Deletion:");
        userBills = testCustomer.getBills();
        for (UtilityBill bill : userBills) {
            System.out.println("Bill ID: " + bill.getId() +
                    ", Username: " + bill.getUserName() +
                    ", Utility Type: " + bill.getUtilityType() +
                    ", Meter Measurement: " + bill.getMeterMeasurement() +
                    ", Price: " + bill.getPrice() +
                    ", Date: " + bill.getDate());
        }
    }
}
