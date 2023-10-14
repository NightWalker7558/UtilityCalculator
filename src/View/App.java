package View;

import Controller.CustomerController;
import Controller.ServiceController;
import Model.Customer;
import Model.ServiceType;
import Model.UtilityBill;
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.ArrayList;
// import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

// import Model.SubscriptionManager;
// import Model.SubscriptionPlan;
// import Controller.CustomerController;

public class App extends JFrame {

  // View Panels
  LoginSelectionView loginSelectionView;
  AdminLoginView adminLoginView;
  CustomerLoginView customerLoginView;
  CustomerRegistrationView customerRegistrationView;
  CustomerDashboardView customerDashboardView;
  AdminDashboardView adminDashboardView;
  NewBillView newBillView;
  EditBillView editBillView;
  EditServiceView editServiceView;

  // Controllers
  CustomerController customerController;

  // Model
  Customer loggedInCustomer;

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
   * @param panel
   */
  protected void paneChange(JPanel panel) {
    getContentPane().removeAll();
    getContentPane().add(panel);
    revalidate();
    repaint();
  }

  // Select Between User and Admin

  protected void selectionPane() {
    loginSelectionView = new LoginSelectionView(this);
    paneChange(loginSelectionView);
  }

  // Customer Login Page

  protected void customerLogin() {
    customerLoginView = new CustomerLoginView(this);
    paneChange(customerLoginView);
  }

  // Customer Validation and Other Operations

  protected boolean validateCustomerLogin(String username, String password) {
    return customerController.validateLogin(username, password);
  }

  protected Customer loadCustomer(String username, String password) {
    return (
      this.loggedInCustomer =
        customerController.loadCustomer(username, password)
    );
  }

  // Customer Dashboard

  protected void customerDashboard() {
    customerDashboardView = new CustomerDashboardView(this, loggedInCustomer);
    paneChange(customerDashboardView);
  }

  // New Bill Page

  protected void newBillView() {
    newBillView = new NewBillView(this);
    paneChange(newBillView);
  }

  // Add New Bill

  protected void addNewBill(
    String utilityType,
    double meterMeasurement,
    String date
  ) {
    loggedInCustomer.addBill(utilityType, meterMeasurement, date);
  }

  // Edit Bill Page

  protected void editBillPage(UtilityBill utilityBill) {
    editBillView = new EditBillView(this, utilityBill);
    paneChange(editBillView);
  }

  // Edit Bill

  protected void editBill(int id, double meterMeasurement) {
    loggedInCustomer.editBill(id, meterMeasurement);
  }

  // Delete Bill

  protected void deleteBill(int id) {
    loggedInCustomer.deleteBill(id);
  }

  // Customer Registration Page

  protected void openRegistrationView() {
    customerRegistrationView = new CustomerRegistrationView(this);
    paneChange(customerRegistrationView);
  }

  protected boolean registerNewUser(
    String username,
    String email,
    String password
  ) {
    return customerController.registerNewUser(username, email, password);
  }

  // Admin Login Page

  protected void adminLogin() {
    adminLoginView = new AdminLoginView(this);
    paneChange(adminLoginView);
  }

  // Admin Dashboard

  protected void adminDashboard() {
    adminDashboardView = new AdminDashboardView(this);
    paneChange(adminDashboardView);
  }

  // Edit Service Page

  protected void editServicePage(ServiceType serviceType) {
    editServiceView = new EditServiceView(this, serviceType);
    paneChange(editServiceView);
  }

  // Edit Service Function

  protected void editService(
    ServiceType serviceType,
    double serviceCharges,
    double unitCharges
  ) {
    ServiceController.updateServiceCharges(serviceType, serviceCharges);
    ServiceController.updateUnitCharges(serviceType, unitCharges);
  }
}
