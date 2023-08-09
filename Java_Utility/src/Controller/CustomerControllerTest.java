package Controller;

import Model.Customer;

public class CustomerControllerTest {

    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();

        // Print initial customer data
        System.out.println("Initial Customer Data:");
        customerController.PrintCustomers();

        System.out.println("Trying to Add new Data");
        // Add new customers
        boolean added1 = customerController.registerNewUser("alice", "alice123", "alice@example.com");
        boolean added2 = customerController.registerNewUser("bob", "bob_password", "bob@example.com");

        if (added1) {
            System.out.println("Customer 'alice' added successfully.");
        }
        if (added2) {
            System.out.println("Customer 'bob' added successfully.");
        }

        // Print updated customer data
        System.out.println("\nUpdated Customer Data:");
        customerController.PrintCustomers();

        // Remove a customer
        customerController.removeCustomer("test_user");

        // Print customer data after removal
        System.out.println("\nCustomer Data after Removal:");
        customerController.PrintCustomers();

        // Get customer by username
        String usernameToSearch = "john_doe";
        Customer foundCustomer = customerController.getCustomerByUsername(usernameToSearch);
        if (foundCustomer != null) {
            System.out.println("\nFound Customer by Username: " + foundCustomer.getUsername());
        } else {
            System.out.println("\nCustomer not found with username: " + usernameToSearch);
        }

        // Check if username is taken
        String newUsername = "jane_smith";
        boolean isUsernameTaken = customerController.isUsernameTaken(newUsername);
        if (isUsernameTaken) {
            System.out.println("Username '" + newUsername + "' is already taken.");
        } else {
            System.out.println("Username '" + newUsername + "' is available.");
        }

        // Check if email is taken
        String newEmail = "new_email@example.com";
        boolean isEmailTaken = customerController.isEmailTaken(newEmail);
        if (isEmailTaken) {
            System.out.println("Email '" + newEmail + "' is already taken.");
        } else {
            System.out.println("Email '" + newEmail + "' is available.");
        }
        System.out.println("\nTesting loadCustomer function:");
        usernameToSearch = "john_doe";
        String passwordToSearch = "password123";

        Customer loadedCustomer = customerController.loadCustomer(usernameToSearch, passwordToSearch);
        if (loadedCustomer != null) {
            System.out.println("Loaded Customer: " + loadedCustomer.getUsername());
            System.out.println("Email: " + loadedCustomer.getEmail());
        } else {
            System.out.println("Customer not found with the given credentials.");
        }
    }
}
