package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The CustomerRegistrationView class represents a graphical user interface for customer registration.
 * It allows users to input their registration information, including username, email, password, and
 * confirmation password. Users can register or cancel the registration process. Additionally, a
 * "Show Password" checkbox is provided to toggle the visibility of the password fields.
 * <p>
 * This class is part of the App's user interface and is used in the customer registration process.
 */
public class CustomerRegistrationView extends JPanel {

  /**
   * The text field for entering the desired username.
   */
  private JTextField usernameField;

  /**
   * The text field for entering the user's email address.
   */
  private JTextField emailField;

  /**
   * The password field for entering the user's desired password.
   */
  private JPasswordField passwordField;

  /**
   * The password field for confirming the user's password.
   */
  private JPasswordField confirmPasswordField;

  /**
   * The button for initiating the registration process.
   */
  private JButton registerButton;

  /**
   * The button for canceling the registration process.
   */
  private JButton cancelButton;

  /**
   * A checkbox that, when selected, makes the password fields visible.
   */
  private JCheckBox showPasswordCheckBox;

  /**
   * Reference to the App class, used for interactions and communication between the view and the
   * application logic.
   */
  App app;

  /**
   * Constructs the customer registration view.
   * Initializes the layout, background color, and border of the view.
   *
   * @param app The reference to the parent application.
   */
  public CustomerRegistrationView(App app) {
    this.app = app;

    setLayout(new GridBagLayout());
    setBackground(Color.WHITE);
    setBorder(new EmptyBorder(20, 20, 20, 20));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    JLabel usernameLabel = new JLabel("Username:");
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(usernameLabel, gbc);

    usernameField = new JTextField(20);
    gbc.gridx = 1;
    gbc.gridy = 0;
    add(usernameField, gbc);

    JLabel emailLabel = new JLabel("Email:");
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(emailLabel, gbc);

    emailField = new JTextField(20);
    gbc.gridx = 1;
    gbc.gridy = 1;
    add(emailField, gbc);

    JLabel passwordLabel = new JLabel("Password:");
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(passwordLabel, gbc);

    passwordField = new JPasswordField(20);
    gbc.gridx = 1;
    gbc.gridy = 2;
    add(passwordField, gbc);

    JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(confirmPasswordLabel, gbc);

    confirmPasswordField = new JPasswordField(20);
    gbc.gridx = 1;
    gbc.gridy = 3;
    add(confirmPasswordField, gbc);

    showPasswordCheckBox = new JCheckBox("Show Password");
    showPasswordCheckBox.setBackground(Color.WHITE);
    gbc.gridx = 1;
    gbc.gridy = 4;
    showPasswordCheckBox.addItemListener(
      new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
          int state = e.getStateChange();
          if (state == ItemEvent.SELECTED) {
            passwordField.setEchoChar((char) 0); // Show password
            confirmPasswordField.setEchoChar((char) 0);
          } else {
            passwordField.setEchoChar('\u2022'); // Hide password with bullet character
            confirmPasswordField.setEchoChar('\u2022');
          }
        }
      }
    );
    add(showPasswordCheckBox, gbc);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBackground(Color.WHITE);

    registerButton = new JButton("Register");
    registerButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String username = usernameField.getText();
          String email = emailField.getText();
          String password = String.valueOf(passwordField.getPassword());
          String confirmPassword = String.valueOf(
            confirmPasswordField.getPassword()
          );

          boolean isValid = validateRegistration(
            username,
            email,
            password,
            confirmPassword
          );

          if (isValid) {
            app.selectionPane();
          }
        }
      }
    );
    buttonPanel.add(registerButton);

    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          app.selectionPane();
        }
      }
    );
    buttonPanel.add(cancelButton);

    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = GridBagConstraints.REMAINDER; // Set gridwidth to REMAINDER
    add(buttonPanel, gbc);
  }

  /**
   * Validates the registration details entered by the user.
   *
   * <p>This method performs validation checks on the provided registration details, including the username, email, password, and confirm password.
   * It displays error messages using JOptionPane if any validation errors are encountered. If all validation checks pass, it calls the
   * registerNewUser method of the parent application to register the new user.</p>
   *
   * @param username The username entered by the user.
   * @param email The email entered by the user.
   * @param password The password entered by the user.
   * @param confirmPassword The confirmed password entered by the user.
   * @return true if the registration details are valid and the user is successfully registered, false otherwise.
   * @throws NullPointerException if any of the parameters are null.
   * @see App#registerNewUser(String, String, String)
   */
  private boolean validateRegistration(
    String username,
    String email,
    String password,
    String confirmPassword
  ) {
    // Perform validation checks here
    if (
      username.isEmpty() ||
      email.isEmpty() ||
      password.isEmpty() ||
      confirmPassword.isEmpty()
    ) {
      JOptionPane.showMessageDialog(
        CustomerRegistrationView.this,
        "All fields are required",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return false;
    } else if (!password.equals(confirmPassword)) {
      JOptionPane.showMessageDialog(
        CustomerRegistrationView.this,
        "Passwords do not match",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );

      return false;
    } else {
      return app.registerNewUser(username, password, email);
    }
  }
}
