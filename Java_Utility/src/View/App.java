package View;

import Controller.CustomerController;

import Model.Customer;

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
    LoginSelectionView loginSelectionView;
    AdminLoginView adminLoginView;
    CustomerLoginView customerLoginView;
    CustomerController customerController;
    CustomerRegistrationView customerRegistrationView;
    CustomerDashboardView customerDashboardView;
    // AdminDashboardView adminDashboardView;
    // SubscriptionPlanDetailView subscriptionPlanDetailView;
    // NewSubscriptionPlanView newSubscriptionPlanView;

    public App() {
        setTitle("Utility App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        selectionPane();

        customerController = new CustomerController();

        setVisible(true);
    }

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
        return customerController.loadCustomer(username, password);
    }

    // Customer Dashboard

    protected void customerDashboard(Customer customer) {
        customerDashboardView = new CustomerDashboardView(this, customer);
        paneChange(customerDashboardView);
    }

    // Customer Registration Page

    protected void openRegistrationView() {
        customerRegistrationView = new CustomerRegistrationView(this);
        paneChange(customerRegistrationView);
    }

    protected boolean registerNewUser(String username, String email, String password) {
        return customerController.registerNewUser(username, email, password);
    }

    // Admin Login Page

    protected void adminLogin() {
        adminLoginView = new AdminLoginView(this);
        paneChange(adminLoginView);
    }
}