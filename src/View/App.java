package View;

import Controller.CustomerController;
import Controller.ServiceController;
import Model.Customer;
import Model.ServiceType;
import Model.UtilityBill;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.View;

/**
 * The main class representing the Utility App.
 *
 * <p>This class represents the main application window for the Utility App. It extends the {@link JFrame} class
 * and contains fields for various views and controllers used in the application. It also includes a reference to the
 * logged-in customer. The class provides a central point for managing the application's views and controllers.</p>
 *
 * <p><strong>Note:</strong> The actual implementation of the views and controllers is not shown in this class.
 * Please refer to the specific view and controller classes for more details.</p>
 *
 * @implSpec This class relies on the existence of the {@link JFrame} class, as well as other view and controller classes,
 *           to perform the necessary operations.
 * @see JFrame
 * @see LoginSelectionView
 * @see AdminLoginView
 * @see CustomerLoginView
 * @see CustomerRegistrationView
 * @see CustomerDashboardView
 * @see AdminDashboardView
 * @see NewBillView
 * @see EditBillView
 * @see EditServiceView
 */
public class App extends JFrame {

  /**
   * The view for selecting the login type.
   */
  private LoginSelectionView loginSelectionView;

  /**
   * The view for admin login.
   */
  private AdminLoginView adminLoginView;

  /**
   * The view for customer login.
   */
  private CustomerLoginView customerLoginView;

  /**
   * The view for customer registration.
   */
  private CustomerRegistrationView customerRegistrationView;

  /**
   * The view for the customer dashboard.
   */
  private CustomerDashboardView customerDashboardView;

  /**
   * The view for the admin dashboard.
   */
  private AdminDashboardView adminDashboardView;

  /**
   * The view for creating a new bill.
   */
  private NewBillView newBillView;

  /**
   * The view for editing a bill.
   */
  private EditBillView editBillView;

  /**
   * The view for editing a service.
   */
  private EditServiceView editServiceView;

  /**
   * The controller for managing customer-related operations.
   */
  private CustomerController customerController;

  /**
   * The currently logged-in customer.
   */
  private Customer loggedInCustomer;

  /**
   * Constructs a new instance of the Utility App.
   *
   * <p>This constructor initializes the main application window. It sets the title of the window to "Utility App",
   * sets the default close operation to exit on close, sets the size of the window to 650x750 pixels,
   * centers the window on the screen, makes the window non-resizable, and initializes the customer controller and
   * the logged-in customer to null. Finally, it makes the window visible and displays the selection pane.</p>
   *
   * @implNote This constructor serves as the entry point of the application.
   * @implSpec This constructor relies on the existence of the {@link CustomerController} class and the {@link JFrame} class
   *           to perform the operation.
   * @see CustomerController
   * @see JFrame
   */
  public App() {
    setTitle("Utility App");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(650, 750);
    setLocationRelativeTo(null);
    setResizable(false);
    selectionPane();

    customerController = new CustomerController();
    loggedInCustomer = null;

    setVisible(true);
  }

  /**
   * Changes the displayed panel in the container.
   *
   * <p>This method replaces the currently displayed panel in the container with the specified {@code panel}.
   * It first removes all components from the content pane using the {@code removeAll} method, then adds the {@code panel}
   * to the content pane using the {@code add} method. Finally, it calls the {@code revalidate} and {@code repaint} methods
   * to update the container and repaint it.</p>
   *
   * @param panel the panel to be displayed in the container
   * @implSpec This method relies on the existence of the {@code getContentPane}, {@code removeAll}, {@code add},
   *           {@code revalidate}, and {@code repaint} methods to perform the operation.
   * @see #getContentPane()
   */
  protected void paneChange(JPanel panel) {
    getContentPane().removeAll();
    getContentPane().add(panel);
    revalidate();
    repaint();
  }

  /**
   * Displays the selection pane for login.
   *
   * <p>This method creates a new instance of the {@link LoginSelectionView} class and assigns it to the {@code loginSelectionView}
   * field of the current instance. It then invokes the {@code paneChange} method, passing the {@code loginSelectionView} as a parameter,
   * to display the selection pane for login.</p>
   *
   * @implNote This method is responsible for displaying the selection pane for login.
   * @implSpec This method relies on the existence of the {@link LoginSelectionView} class and the {@code paneChange} method
   *           to perform the operation.
   * @see LoginSelectionView
   * @see #paneChange(View)
   */
  protected void selectionPane() {
    loginSelectionView = new LoginSelectionView(this);
    paneChange(loginSelectionView);
  }

  /**
   * Displays the customer login view.
   *
   * <p>This method creates a new instance of the {@link CustomerLoginView} class and assigns it to the {@code customerLoginView}
   * field of the current instance. It then invokes the {@code paneChange} method, passing the {@code customerLoginView} as a parameter,
   * to display the customer login view.</p>
   *
   * @implNote This method is responsible for displaying the customer login view.
   * @implSpec This method relies on the existence of the {@link CustomerLoginView} class and the {@code paneChange} method
   *           to perform the operation.
   * @see CustomerLoginView
   * @see #paneChange(View)
   */
  protected void customerLogin() {
    customerLoginView = new CustomerLoginView(this);
    paneChange(customerLoginView);
  }

  /**
   * Validates the login credentials of a customer.
   *
   * <p>This method validates the login credentials of a customer by invoking the {@link CustomerController#validateLogin(String, String)}
   * method and passing the provided username and password as parameters.</p>
   *
   * @implNote This method is responsible for validating the login credentials of a customer.
   * @implSpec This method relies on the existence of the {@link CustomerController} class and its {@code validateLogin} method
   *           to perform the operation.
   * @param username The username of the customer.
   * @param password The password of the customer.
   * @return {@code true} if the login credentials are valid, {@code false} otherwise.
   * @see CustomerController#validateLogin(String, String)
   */
  protected boolean validateCustomerLogin(String username, String password) {
    return customerController.validateLogin(username, password);
  }

  /**
   * Loads a customer based on the provided username and password.
   *
   * <p>This method loads a customer by invoking the {@link CustomerController#loadCustomer(String, String)} method and passing
   * the provided username and password as parameters. The loaded customer is then assigned to the {@code loggedInCustomer}
   * field of the current instance.</p>
   *
   * @implNote This method is responsible for loading a customer based on the provided credentials.
   * @implSpec This method relies on the existence of the {@link CustomerController} class and its {@code loadCustomer} method
   *           to perform the operation.
   * @param username The username of the customer to be loaded.
   * @param password The password of the customer to be loaded.
   * @return The customer that has been loaded based on the provided credentials.
   * @see CustomerController#loadCustomer(String, String)
   */
  protected Customer loadCustomer(String username, String password) {
    return (
      this.loggedInCustomer =
        customerController.loadCustomer(username, password)
    );
  }

  /**
   * Opens the customer dashboard view.
   *
   * <p>This method opens the customer dashboard view by creating a new instance of the {@link CustomerDashboardView} class
   * and passing the current instance and the logged-in customer as parameters. It then calls the {@link #paneChange(View)}
   * method to change the active pane to the customer dashboard view.</p>
   *
   * @implNote This method is responsible for opening the customer dashboard view.
   * @implSpec This method relies on the existence of the {@link CustomerDashboardView} class and its constructor, as well as
   *           the {@link #paneChange(View)} method, to perform the operation.
   * @param loggedInCustomer The logged-in customer object.
   * @see CustomerDashboardView
   * @see #paneChange(View)
   */
  protected void customerDashboard() {
    customerDashboardView = new CustomerDashboardView(this, loggedInCustomer);
    paneChange(customerDashboardView);
  }

  /**
   * Opens the new bill view for creating a new bill.
   *
   * <p>This method opens the new bill view by creating a new instance of the {@link NewBillView} class and passing the
   * current instance as a parameter. It then calls the {@link #paneChange(View)} method to change the active pane to
   * the new bill view.</p>
   *
   * @implNote This method is responsible for opening the new bill view.
   * @implSpec This method relies on the existence of the {@link NewBillView} class and its constructor, as well as
   *           the {@link #paneChange(View)} method, to perform the operation.
   * @see NewBillView
   * @see #paneChange(View)
   */
  protected void newBillView() {
    newBillView = new NewBillView(this);
    paneChange(newBillView);
  }

  /**
   * Adds a new bill for the logged-in customer.
   *
   * <p>This method allows the logged-in customer to add a new bill by specifying the utility type, meter measurement,
   * and date. It calls the {@link Customer#addBill(String, double, String)} method on the {@link loggedInCustomer}
   * object, passing the utilityType, meterMeasurement, and date as parameters.</p>
   *
   * @param utilityType The type of utility for the bill (e.g., electricity, water, gas).
   * @param meterMeasurement The meter measurement for the bill.
   * @param date The date of the bill in the format "yyyy-MM-dd".
   * @throws IllegalArgumentException if the utilityType or date is invalid.
   * @implNote This method is responsible for adding new bills for the logged-in customer.
   * @implSpec This method relies on the existence of the {@link loggedInCustomer} object and its
   *           {@link Customer#addBill(String, double, String)} method to perform the addition.
   * @see Customer#addBill(String, double, String)
   */
  protected void addNewBill(
    String utilityType,
    double meterMeasurement,
    String date
  ) {
    loggedInCustomer.addBill(utilityType, meterMeasurement, date);
  }

  /**
   * Opens the edit bill page for a specific utility bill.
   *
   * <p>This method opens the edit bill page for a specific utility bill by creating a new instance of the
   * {@link EditBillView} class and passing the current instance and the utility bill as parameters. It then calls
   * the {@link #paneChange(View)} method to change the active pane to the edit bill view.</p>
   *
   * @param utilityBill The utility bill to be edited.
   * @implNote This method is responsible for opening the edit bill page.
   * @implSpec This method relies on the existence of the {@link EditBillView} class and its constructor, as well
   *           as the {@link #paneChange(View)} method, to perform the operation.
   * @see EditBillView
   * @see #paneChange(View)
   */
  protected void editBillPage(UtilityBill utilityBill) {
    editBillView = new EditBillView(this, utilityBill);
    paneChange(editBillView);
  }

  /**
   * Edits a bill for the logged-in customer.
   *
   * <p>This method allows the logged-in customer to edit a bill by specifying the ID of the bill and the new meter
   * measurement. It calls the {@link Customer#editBill(int, double)} method on the {@link loggedInCustomer} object,
   * passing the ID and meterMeasurement as parameters.</p>
   *
   * @param id The ID of the bill to be edited.
   * @param meterMeasurement The new meter measurement for the bill.
   * @throws IllegalArgumentException if the ID is invalid or does not correspond to a bill for the logged-in customer.
   * @implNote This method is responsible for editing bills for the logged-in customer.
   * @implSpec This method relies on the existence of the {@link loggedInCustomer} object and its
   *           {@link Customer#editBill(int, double)} method to perform the editing.
   * @see Customer#editBill(int, double)
   */
  protected void editBill(int id, double meterMeasurement) {
    loggedInCustomer.editBill(id, meterMeasurement);
  }

  /**
   * Deletes a bill for the logged-in customer.
   *
   * <p>This method deletes the bill with the specified ID for the currently logged-in customer. It calls the
   * {@link Customer#deleteBill(int)} method on the {@link loggedInCustomer} object, passing the ID as the parameter.</p>
   *
   * @param id The ID of the bill to be deleted.
   * @throws IllegalArgumentException if the ID is invalid or does not correspond to a bill for the logged-in customer.
   * @implNote This method is responsible for deleting bills for the logged-in customer.
   * @implSpec This method relies on the existence of the {@link loggedInCustomer} object and its
   *           {@link Customer#deleteBill(int)} method to perform the deletion.
   * @see Customer#deleteBill(int)
   */
  protected void deleteBill(int id) {
    loggedInCustomer.deleteBill(id);
  }

  /**
   * Opens the registration view for new customers.
   *
   * <p>This method creates a new instance of the {@link CustomerRegistrationView} class and sets it as the active pane
   * by calling the {@link #paneChange} method. The registration view provides a user interface for new customers to
   * register and create an account in the system.</p>
   *
   * @implNote This method is specifically designed to open the registration view for new customers.
   * @implSpec This method relies on the existence of the {@link CustomerRegistrationView} class and the {@link #paneChange}
   *           method to function correctly.
   * @see CustomerRegistrationView
   * @see #paneChange(JPanel)
   */
  protected void openRegistrationView() {
    customerRegistrationView = new CustomerRegistrationView(this);
    paneChange(customerRegistrationView);
  }

  /**
   * Registers a new user in the system.
   *
   * <p>This method registers a new user with the specified username, email, and password. It delegates the registration
   * process to the {@link CustomerController} class by calling its {@link CustomerController#registerNewUser}
   * method. The method returns a boolean value indicating the success or failure of the registration process.</p>
   *
   * @param username The username of the new user.
   * @param email The email address of the new user.
   * @param password The password of the new user.
   * @return {@code true} if the user registration is successful, {@code false} otherwise.
   * @throws NullPointerException if any of the parameters (username, email, password) is null.
   * @implNote This method is responsible for registering new users in the system. It relies on the existence of the
   *           {@link CustomerController} class and its {@link CustomerController#registerNewUser} method to
   *           perform the registration process.
   * @implSpec The registration process may involve additional validation or checks, such as checking for duplicate
   *           usernames or email addresses.
   * @see CustomerController
   * @see CustomerController#registerNewUser(String, String, String)
   */
  protected boolean registerNewUser(
    String username,
    String email,
    String password
  ) {
    return customerController.registerNewUser(username, email, password);
  }

  /**
   * Displays the admin login view.
   *
   * <p>This method creates a new instance of the {@link AdminLoginView} class, passing the current instance
   * of the class as a parameter. It then calls the {@link #paneChange} method to change the active pane to the
   * newly created admin login view.</p>
   *
   * <p>The admin login view provides a user interface for administrators to authenticate themselves and gain access
   * to the administrative functionalities of the system.</p>
   *
   * @implNote This method is specifically designed for use in an admin module or section of the application.
   * @implSpec This method relies on the existence of the {@link AdminLoginView} class and the {@link #paneChange}
   *           method to function correctly.
   * @see AdminLoginView
   * @see #paneChange(JPanel)
   */
  protected void adminLogin() {
    adminLoginView = new AdminLoginView(this);
    paneChange(adminLoginView);
  }

  /**
   * Displays the admin dashboard view.
   *
   * <p>This method creates a new instance of the {@link AdminDashboardView} class, passing the current instance
   * of the class as a parameter. It then calls the {@link #paneChange} method to change the active pane to the
   * newly created admin dashboard view.</p>
   *
   * @implNote The admin dashboard view provides access to various administrative functionalities and information
   *           pertaining to the system. It is designed specifically for users with administrative privileges.
   * @implSpec This method relies on the existence of the {@link AdminDashboardView} class and the {@link #paneChange}
   *           method to function correctly.
   * @see AdminDashboardView
   * @see #paneChange(JPanel)
   */
  protected void adminDashboard() {
    adminDashboardView = new AdminDashboardView(this);
    paneChange(adminDashboardView);
  }

  /**
   * Opens the edit service page for a specific service type.
   *
   * <p>This method creates a new instance of the {@link EditServiceView} class, passing the current instance
   * of the class and the specified service type as parameters. It then calls the {@link #paneChange} method
   * to change the active pane to the newly created edit service view.</p>
   *
   * @param serviceType The service type for which the edit service page should be opened.
   * @throws NullPointerException If the serviceType parameter is null.
   * @implSpec This method relies on the existence of the {@link EditServiceView} class and the {@link #paneChange}
   *           method to function correctly.
   * @see EditServiceView
   * @see #paneChange(JPanel)
   */
  protected void editServicePage(ServiceType serviceType) {
    editServiceView = new EditServiceView(this, serviceType);
    paneChange(editServiceView);
  }

  /**
   * Updates the service charges and unit charges for a specific service type.
   *
   * <p>This method updates the service charges and unit charges for a given service type. It calls the
   * necessary methods from the {@link ServiceController} class to perform the updates.</p>
   *
   * @param serviceType   The service type to be edited.
   * @param serviceCharges The new service charges to be set for the service type.
   * @param unitCharges    The new unit charges to be set for the service type.
   * @throws NullPointerException     If the serviceType parameter is null.
   * @throws IllegalArgumentException If the serviceCharges or unitCharges parameters are negative.
   * @implSpec This method delegates the update operations to the {@link ServiceController} class.
   * @see ServiceController#updateServiceCharges(ServiceType, double)
   * @see ServiceController#updateUnitCharges(ServiceType, double)
   */
  protected void editService(
    ServiceType serviceType,
    double serviceCharges,
    double unitCharges
  ) {
    ServiceController.updateServiceCharges(serviceType, serviceCharges);
    ServiceController.updateUnitCharges(serviceType, unitCharges);
  }
}
