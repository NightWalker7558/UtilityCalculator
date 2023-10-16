package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The CustomerLoginView class represents the login view for customers in the application.
 *
 * <p>This class extends the LoginView class and provides the necessary components and functionality for customers to log in.
 * It inherits the layout and behavior of the LoginView class and adds specific actions for customer login and registration.</p>
 *
 * @see LoginView
 */
public class CustomerLoginView extends LoginView {

  /**
   * Constructs a CustomerLoginView with the specified parent application.
   *
   * @param app The parent application instance.
   * @see App
   */
  public CustomerLoginView(App app) {
    super(app);
    loginButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String username = usernameField.getText();
          String password = String.valueOf(passwordField.getPassword());

          if (app.validateCustomerLogin(username, password)) {
            app.loadCustomer(username, password);
            app.customerDashboard();
          } else {
            JOptionPane.showMessageDialog(
              CustomerLoginView.this,
              "Incorrect username or password",
              "Error",
              JOptionPane.ERROR_MESSAGE
            );
          }
        }
      }
    );

    JButton registerButton = new JButton("Register");
    registerButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          app.openRegistrationView();
        }
      }
    );

    JPanel buttonPanel = (JPanel) loginButton.getParent();
    buttonPanel.add(registerButton);
  }
}
