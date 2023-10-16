package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The `LoginSelectionView` class represents a graphical user interface component
 * for selecting the type of login, either as an admin or a user.
 *
 * This view provides two buttons for choosing between admin and user login options.
 *
 * @see App
 *
 * @param app The main application instance that manages the login process.
 *
 * @implSpec This class relies on the `App` class to handle login procedures. When a user selects
 *          either admin or user login, it triggers actions associated with opening the respective login
 *          windows through the `App` instance.
 */
public class LoginSelectionView extends JPanel {

  /**
   * The button for logging in as an admin user.
   *
   * This button allows administrators to log in to the application with administrative privileges.
   */
  private JButton adminLoginButton;

  /**
   * The button for logging in as a regular user.
   *
   * This button allows regular users to log in to the application with standard user privileges.
   */
  private JButton userLoginButton;

  /**
   * The application instance that controls the view.
   *
   * This field holds a reference to the main application instance that manages and controls the user interface.
   *
   * @see App
   */
  protected App app;

  /**
   * Creates a view for user selection (Admin or User login).
   *
   * @param app The application instance that controls the view.
   *            This field holds a reference to the main application instance that manages and controls the user interface.
   *            It is used to trigger the appropriate login window based on the user's selection.
   * @implSpec This constructor initializes the user selection buttons for Admin and User login.
   *           It sets their appearance and registers action listeners to open the respective login windows.
   *           The buttons are styled with specific backgrounds, text colors, and fonts.
   *           This view is organized with a grid layout containing two buttons.
   */
  public LoginSelectionView(App app) {
    this.app = app;

    setLayout(new BorderLayout());

    adminLoginButton = new JButton("Admin Login");
    adminLoginButton.setBackground(Color.decode("#98c1d9"));
    adminLoginButton.setForeground(Color.BLACK);
    adminLoginButton.setFont(adminLoginButton.getFont().deriveFont(30f));

    userLoginButton = new JButton("User Login");
    userLoginButton.setBackground(Color.decode("#ee6c4d"));
    userLoginButton.setForeground(Color.WHITE);
    userLoginButton.setFont(userLoginButton.getFont().deriveFont(30f));

    adminLoginButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          openAdminLoginWindow();
        }
      }
    );

    userLoginButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          openUserLoginWindow();
        }
      }
    );

    JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 0, 0));
    buttonPanel.setOpaque(false);

    buttonPanel.add(adminLoginButton);
    buttonPanel.add(userLoginButton);

    add(buttonPanel, BorderLayout.CENTER);
  }

  /**
   * Opens the Admin Login window.
   *
   * This method is called when the user clicks on the "Admin Login" button.
   * It triggers the display of the Admin Login window, allowing administrators to log in.
   * The behavior of this method is implemented by invoking the `adminLogin` method on the application instance.
   *
   * @implSpec This method delegates the responsibility of opening the Admin Login window to the application instance
   *           by calling the `adminLogin` method.
   * @see App#adminLogin()
   */
  private void openAdminLoginWindow() {
    app.adminLogin();
  }

  /**
   * Opens the User Login window.
   *
   * This method is called when the user clicks on the "User Login" button.
   * It triggers the display of the User Login window, allowing customers to log in.
   * The behavior of this method is implemented by invoking the `customerLogin` method on the application instance.
   *
   * @implSpec This method delegates the responsibility of opening the User Login window to the application instance
   *           by calling the `customerLogin` method.
   * @see App#customerLogin()
   */
  private void openUserLoginWindow() {
    app.customerLogin();
  }
}
