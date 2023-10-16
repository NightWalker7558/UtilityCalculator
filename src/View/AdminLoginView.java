package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * <h1><link>AdminLoginView</link></h1>
 *
 * <p><link>AdminLoginView</link> is a subclass of <link>LoginView</link> that provides functionality for admin login.</p>
 * <p>It inherits the login button action event from <link>LoginView</link> and adds additional validation for admin credentials.</p>
 *
 * @see <link>LoginView</link>
 */
public class AdminLoginView extends LoginView {

  /**
   * Constructs a new <link>AdminLoginView</link> object.
   *
   * @param app The main application instance.
   * @throws NullPointerException if the {@code app} parameter is {@code null}.
   */
  public AdminLoginView(App app) {
    super(app);
    loginButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String username = usernameField.getText();
          String password = String.valueOf(passwordField.getPassword());

          if (username.equals("admin") && password.equals("admin")) {
            app.adminDashboard();
          } else {
            JOptionPane.showMessageDialog(
              AdminLoginView.this,
              "Incorrect username or password",
              "Error",
              JOptionPane.ERROR_MESSAGE
            );
          }
        }
      }
    );
  }
}
