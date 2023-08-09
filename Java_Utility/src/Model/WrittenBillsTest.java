package Model;

public class WrittenBillsTest {

    public static void main(String[] args) {
        // Create a test WrittenBills instance
        WrittenBills writtenBills = new WrittenBills();

        // Load bills from file
        writtenBills.loadBillsFromFile();

        // Print initial bills data
        System.out.println("Initial Bills Data:");
        if (writtenBills.getBills().isEmpty() == false) {
            for (UtilityBill bill : writtenBills.getBills()) {
                System.out.println("Bill ID: " + bill.getId() +
                        ", Username: " + bill.getUserName() +
                        ", Utility Type: " + bill.getUtilityType() +
                        ", Meter Measurement: " + bill.getMeterMeasurement() +
                        ", Price: " + bill.getPrice() +
                        ", Date: " + bill.getDate());
            }
        }

        // Add a new bill
        writtenBills.addBill("hakeem_doe", "Electricity", 150, "2023-08-10");

        // Print updated bills data
        System.out.println("\nUpdated Bills Data:");
        for (UtilityBill bill : writtenBills.getBills()) {
            System.out.println("Bill ID: " + bill.getId() +
                    ", Username: " + bill.getUserName() +
                    ", Utility Type: " + bill.getUtilityType() +
                    ", Meter Measurement: " + bill.getMeterMeasurement() +
                    ", Price: " + bill.getPrice() +
                    ", Date: " + bill.getDate());
        }

        // Edit a bill (Change meter measurement)
        int billIdToEdit = 1; // Assuming bill with ID 1 exists
        writtenBills.editBill(billIdToEdit, "200000");

        // Print updated bills data
        System.out.println("\nUpdated Bills Data after Edit:");
        for (UtilityBill bill : writtenBills.getBills()) {
            System.out.println("Bill ID: " + bill.getId() +
                    ", Username: " + bill.getUserName() +
                    ", Utility Type: " + bill.getUtilityType() +
                    ", Meter Measurement: " + bill.getMeterMeasurement() +
                    ", Price: " + bill.getPrice() +
                    ", Date: " + bill.getDate());
        }

        // Delete a bill
        int billIdToDelete = 3; // Assuming bill with ID 2 exists
        writtenBills.deleteBill(billIdToDelete);

        // Print bills data after deletion
        System.out.println("\nBills Data after Deletion:");
        for (UtilityBill bill : writtenBills.getBills()) {
            System.out.println("Bill ID: " + bill.getId() +
                    ", Username: " + bill.getUserName() +
                    ", Utility Type: " + bill.getUtilityType() +
                    ", Meter Measurement: " + bill.getMeterMeasurement() +
                    ", Price: " + bill.getPrice() +
                    ", Date: " + bill.getDate());
        }
    }
}
