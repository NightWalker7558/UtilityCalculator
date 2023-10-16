package Controller;

import Model.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for managing customer data, including registration,
 * login,
 * and data persistence.
 *
 * <h1>Usage</h1>
 * To use this class, create an instance of `CustomerController` and call its
 * methods to
 * perform various operations on customer data.
 *
 * <h1>Example Usage</h1>
 * 
 * <pre>
 * <code>
 * CustomerController controller = new CustomerController();
 * controller.registerNewUser("username", "password", "email@example.com");
 * controller.validateLogin("username", "password");
 * </code>
 * </pre>
 *
 * <h1>File Management</h1>
 * Customer data is stored in a text file defined by the `customersFilePath`
 * attribute.
 * This class provides methods to load customers from the file and save
 * customers to the file.
 *
 * <h1>Thread Safety</h1>
 * This class is not thread-safe, so external synchronization may be required
 * when accessed
 * by multiple threads.
 *
 * @see Customer
 */

public class CustomerController {

  /**
   * A list that holds customer objects.
   *
   * <h1>Description</h1>
   * The `customers` list contains instances of the {@link Customer} class,
   * representing
   * registered customers in the system. Various operations related to customer
   * management
   * are performed on this list.
   */
  private List<Customer> customers;

  /**
   * The file path for storing customer data.
   *
   * <h1>Description</h1>
   * The `customersFilePath` field holds the file path to the location where
   * customer data
   * is stored and retrieved. The default file path is "src/Model/customers.txt,"
   * but you
   * can modify this path as needed to specify a different location for the
   * customer data file.
   */
  private String customersFilePath; // File path for storing customer data

  /**
   * Constructor for the CustomerController class that initializes the list of
   * customers
   * and loads customer data from a file.
   *
   * <h1>Initialization</h1>
   * Upon construction of a `CustomerController` instance, it initializes an empty
   * list
   * of customer objects and specifies the default file path for storing customer
   * data.
   * It then proceeds to load customer data from the file, if available.
   *
   * <h1>File Loading</h1>
   * This constructor automatically calls the `loadCustomersFromFile` method to
   * load
   * existing customer data from the file specified by the `customersFilePath`.
   *
   * <h1>Default File Path</h1>
   * By default, the `customersFilePath` is set to "src/Model/customers.txt". You
   * can
   * modify this path as needed.
   */
  public CustomerController() {
    this.customers = new ArrayList<>();
    this.customersFilePath = "src/Model/customers.txt";
    this.loadCustomersFromFile();
  }

  /**
   * Retrieves the list of customer objects.
   *
   * <h1>Usage</h1>
   * Use this method to obtain a reference to the list of customer objects
   * maintained
   * by the {@link CustomerController} instance.
   *
   * @return A list containing {@link Customer} objects.
   */
  public List<Customer> getCustomers() {
    return customers;
  }

  /**
   * Load customer data from a file, populating the customer list.
   * If the file is not found, it creates a new file.
   *
   * <h1>File Loading</h1>
   * This method reads customer data from the specified file, assuming that the
   * data is
   * stored in CSV (Comma-Separated Values) format, with each line containing
   * customer
   * information in the order: username, password, email.
   *
   * <h1>Error Handling</h1>
   * If the file is not found, it will catch a `FileNotFoundException` and create
   * a new
   * customer file using the `saveCustomersToFile` method.
   *
   * @throws FileNotFoundException If the customer data file is not found.
   */
  public void loadCustomersFromFile() {
    try {
      File file = new File(customersFilePath);
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        String username = parts[0].trim();
        String password = parts[1].trim();
        String email = parts[2].trim();
        this.registerNewUser(username, password, email);
      }

      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Customers file not found. Creating a new file...");
      saveCustomersToFile();
    }
  }

  /**
   * Registers a new user with the provided username, password, and email.
   *
   * <h1>Registration Process</h1>
   * This method attempts to register a new customer in the system by checking if
   * the
   * provided username and email address are available. If the username or email
   * is
   * already taken, it displays an error message and returns `false`. If both the
   * username
   * and email are available, it creates a new customer object and adds it to the
   * list of
   * customers. The customer data is then saved to a file using the
   * `saveCustomersToFile`
   * method.
   *
   * <h1>Username and Email Validation</h1>
   * It uses the `isUsernameTaken` and `isEmailTaken` methods to validate whether
   * the
   * provided username and email are already in use.
   *
   * @param username The username for the new user.
   * @param password The password for the new user.
   * @param email    The email address for the new user.
   * @return `true` if the registration is successful, `false` if the username or
   *         email is
   *         already taken.
   */
  public boolean registerNewUser(
      String username,
      String password,
      String email) {
    if (isUsernameTaken(username)) {
      System.out.println(
          "Username already exists. Please choose a different username.");
      return false;
    }

    if (isEmailTaken(email)) {
      System.out.println(
          "Email address already exists. Please provide a different email address.");
      return false;
    }
    Customer customer = new Customer(username, password, email);
    customers.add(customer);
    saveCustomersToFile();
    return true;
  }

  /**
   * Print information about all registered customers.
   *
   * <h1>Printing Customer Information</h1>
   * This method iterates through the list of registered customers and prints
   * information
   * about each customer, including their username, password, and email, to the
   * console.
   *
   * <h1>Output Format</h1>
   * The customer information is printed in the format:
   * 
   * <pre>
   * <code>
   * Username Password Email
   * </code>
   * </pre>
   *
   * <h1>Example Output</h1>
   * If there are two registered customers with usernames "user1" and "user2," the
   * output
   * might look like this:
   * 
   * <pre>
   * <code>
   * user1 password1 user1@example.com
   * user2 password2 user2@example.com
   * </code>
   * </pre>
   */
  public void PrintCustomers() {
    for (Customer customer : customers) {
      System.out.println(
          customer.getUsername() +
              " " +
              customer.getPassword() +
              " " +
              customer.getEmail());
    }
  }

  /**
   * Remove a registered customer with the specified username.
   *
   * <h1>Customer Removal</h1>
   * This method attempts to remove a customer with the given username from the
   * list
   * of registered customers. It checks if a customer with the provided username
   * exists.
   * If found, it removes the customer from the list and updates the customer data
   * file
   * using the `saveCustomersToFile` method. If the username is not found, it
   * prints an
   * error message indicating that the customer was not found.
   *
   * <h1>Parameter</h1>
   * 
   * @param username The username of the customer to be removed.
   *
   *                 <h1>Errors and Warnings</h1>
   *                 If the provided username is not found, a "Customer not found"
   *                 message is printed to
   *                 the console.
   */
  public void removeCustomer(String username) {
    if (isUsernameTaken(username)) {
      Customer customer = getCustomerByUsername(username);
      customers.remove(customer);
      saveCustomersToFile();
    } else {
      System.out.println("Customer not found.");
    }
  }

  /**
   * Retrieve a customer by their username.
   *
   * <h1>Customer Retrieval</h1>
   * This method searches the list of registered customers and attempts to
   * retrieve a
   * customer by their username. If a customer with the specified username is
   * found, the
   * customer object is returned. If the username is not found, the method returns
   * `null`
   * to indicate that the customer was not found.
   *
   * <h1>Parameter</h1>
   * 
   * @param username The username of the customer to retrieve.
   *
   * @return A {@link Customer} object if found, or `null` if the customer is not
   *         found.
   */
  public Customer getCustomerByUsername(String username) {
    for (Customer customer : customers) {
      if (customer.getUsername().equals(username)) {
        return customer;
      }
    }
    return null; // Customer not found
  }

  /**
   * Check if a username is already taken by a registered customer.
   *
   * <h1>Username Availability</h1>
   * This method checks if a provided username is already in use by any of the
   * registered
   * customers. If a customer with the specified username is found, it returns
   * `true` to
   * indicate that the username is taken. If the username is not found among
   * registered
   * customers, it returns `false` to indicate that the username is available for
   * registration.
   *
   * <h1>Parameter</h1>
   * 
   * @param username The username to be checked for availability.
   *
   * @return `true` if the username is already taken, `false` if it is available
   *         for
   *         registration.
   */
  public boolean isUsernameTaken(String username) {
    for (Customer customer : customers) {
      if (customer.getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check if an email address is already in use by a registered customer.
   *
   * <h1>Email Address Availability</h1>
   * This method checks if a provided email address is already associated with any
   * of the
   * registered customers. If a customer with the specified email address is
   * found, it
   * returns `true` to indicate that the email address is already taken. If the
   * email
   * address is not found among registered customers, it returns `false` to
   * indicate that
   * the email address is available for registration.
   *
   * <h1>Parameters</h1>
   * 
   * @param email The email address to be checked for availability.
   *
   * @return `true` if the email address is already taken, `false` if it is
   *         available for
   *         registration.
   */
  public boolean isEmailTaken(String email) {
    for (Customer customer : customers) {
      if (customer.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Validate customer login credentials.
   *
   * <h1>Login Validation</h1>
   * This method validates customer login credentials by comparing the provided
   * username and password with the credentials of registered customers. If a
   * matching
   * customer is found, it returns `true` to indicate a successful login. If no
   * customer
   * matches the provided credentials, it returns `false` to indicate an
   * unsuccessful login.
   *
   * <h1>Parameters</h1>
   * 
   * @param username The username provided during login.
   * @param password The password provided during login.
   *
   * @return `true` if the login credentials are valid, `false` if they are not.
   */
  public boolean validateLogin(String username, String password) {
    for (Customer customer : customers) {
      if (customer.getUsername().equals(username) &&
          customer.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Save customer data to a file.
   *
   * <h1>Data Saving</h1>
   * This method saves customer data, including usernames, passwords, and email
   * addresses,
   * to a file specified by the `customersFilePath` field. The data is stored in a
   * CSV
   * (Comma-Separated Values) format, where each line contains customer
   * information in the
   * order: username, password, email.
   *
   * <h1>Exception Handling</h1>
   * If an error occurs during the file writing process, an `IOException` is
   * caught and
   * an error message is printed to the console, indicating the issue encountered
   * while
   * saving the data.
   *
   * <h1>File Format</h1>
   * The customer data is saved in the following format:
   * 
   * <pre>
   * <code>
   * Username,Password,Email
   * </code>
   * </pre>
   *
   * <h1>File Location</h1>
   * The location and name of the file are determined by the `customersFilePath`
   * field.
   * Ensure that this field points to the desired location and file name for
   * saving
   * customer data.
   *
   * @throws IOException If an error occurs while writing the customer data to the
   *                     file.
   */
  public void saveCustomersToFile() {
    try {
      File file = new File(customersFilePath);
      PrintWriter writer = new PrintWriter(file);

      for (Customer customer : customers) {
        writer.println(
            customer.getUsername() +
                "," +
                customer.getPassword() +
                "," +
                customer.getEmail());
      }

      writer.close();
    } catch (IOException e) {
      System.out.println("Error writing to customers file: " + e.getMessage());
    }
  }

  /**
   * Load a customer by their username and password.
   *
   * <h1>Customer Retrieval</h1>
   * This method attempts to retrieve a registered customer by their provided
   * username
   * and password. If a matching customer is found with both the specified
   * username and
   * password, the customer object is returned. If no matching customer is found,
   * the
   * method returns `null` to indicate that the customer was not found.
   *
   * <h1>Parameters</h1>
   * 
   * @param username The username of the customer to retrieve.
   * @param password The password of the customer to retrieve.
   *
   * @return A {@link Customer} object if a match is found, or `null` if the
   *         customer is not
   *         found based on the provided username and password.
   */
  public Customer loadCustomer(String username, String password) {
    for (Customer customer : customers) {
      if (customer.getUsername().equals(username) &&
          customer.getPassword().equals(password)) {
        return customer;
      }
    }
    return null; // Customer not found
  }
}
