package View;

import View.includes.TextPrompt;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * A JPanel that provides a user interface for creating a new subscription plan for a utility bill.
 * Users can input the utility type, reading, and date, and then add the new bill or cancel the operation.
 * This view is typically used within a larger application to manage utility bills.
 *
 * <p>Example usage:
 * <pre>
 * App myApp = new App();
 * NewBillView newBillView = new NewBillView(myApp);
 * JFrame frame = new JFrame("New Utility Bill");
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * frame.getContentPane().add(newBillView);
 * frame.pack();
 * frame.setVisible(true);
 * </pre>
 * </p>
 *
 * @see App
 * @see JComboBox
 * @see JTextField
 * @see JFormattedTextField
 * @see JButton
 * @see JPanel
 */
public class NewBillView extends JPanel {

  /**
   * JComboBox for selecting the type of utility, such as electricity, gas, or water.
   */
  private JComboBox<String> typeField;

  /**
   * JTextField for entering the reading value associated with the utility bill.
   */
  private JTextField readingField;

  /**
   * JFormattedTextField for entering the date of the utility bill in the "yyyy-MM-dd" format.
   */
  private JFormattedTextField dateTextField;

  /**
   * JButton for adding a new utility bill entry based on the provided data.
   */
  private JButton addButton;

  /**
   * JButton for canceling the process of adding a new utility bill and returning to the previous view.
   */
  private JButton cancelButton;

  /**
   * <h1>NewBillView</h1>
   * The `NewBillView` class provides a graphical user interface for creating a new utility bill entry. It allows customers to input data for a new utility bill, including the utility type, reading, and date.
   *
   * <p>This view consists of components such as labels, input fields, and buttons for user interaction. Users can select the utility type from a dropdown menu, enter the reading, and specify the date. The "Add" button allows users to submit the new bill entry, and the "Cancel" button enables them to return to the customer dashboard.</p>
   *
   * @param app The main application instance that controls the view and handles user interactions.
   */
  public NewBillView(App app) {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    setBorder(new EmptyBorder(20, 20, 20, 20));

    JLabel titleLabel = new JLabel("Create New Subscription Plan");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    titleLabel.setHorizontalAlignment(JLabel.CENTER);

    JPanel contentPanel = new JPanel();
    contentPanel.setBackground(Color.WHITE);
    GroupLayout layout = new GroupLayout(contentPanel);
    contentPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    JLabel typeLabel = new JLabel("Utility Type:");
    JLabel readingLabel = new JLabel("Reading:");
    JLabel dateLabel = new JLabel("Date:");

    String s1[] = { "ELECTRICITY", "GAS", "WATER" };
    typeField = new JComboBox<String>(s1);
    readingField = new JTextField(20);
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    dateTextField = new JFormattedTextField(format);
    new TextPrompt("2020-01-31", dateTextField);

    addButton = new JButton("Add");
    addButton.setBackground(Color.decode("#4CAF50"));
    addButton.setForeground(Color.WHITE);
    addButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String type = (String) typeField.getSelectedItem();
          String reading = readingField.getText();
          String date = dateTextField.getText();

          // Perform input validation here
          if (type.isEmpty() || reading.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(
              NewBillView.this,
              "Please fill in all the fields.",
              "Incomplete Fields",
              JOptionPane.ERROR_MESSAGE
            );
          } else if (!isNumeric(reading)) {
            JOptionPane.showMessageDialog(
              NewBillView.this,
              "Reading must be a numeric value.",
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE
            );
          } else {
            app.addNewBill(type, Double.parseDouble(reading), date);

            typeField.setSelectedIndex(0);
            readingField.setText("");
            dateTextField.setText("");

            JOptionPane.showMessageDialog(
              NewBillView.this,
              "New Utility Bill added successfully!",
              "Success",
              JOptionPane.INFORMATION_MESSAGE
            );

            app.customerDashboard();
          }
        }
      }
    );

    cancelButton = new JButton("Cancel");
    cancelButton.setBackground(Color.decode("#F44336"));
    cancelButton.setForeground(Color.WHITE);
    cancelButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          app.customerDashboard();
        }
      }
    );

    layout.setHorizontalGroup(
      layout
        .createSequentialGroup()
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(typeLabel)
            .addComponent(readingLabel)
            .addComponent(dateLabel)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(typeField)
            .addComponent(readingField)
            .addComponent(dateTextField)
        )
    );

    layout.setVerticalGroup(
      layout
        .createSequentialGroup()
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(typeLabel)
            .addComponent(typeField)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(readingLabel)
            .addComponent(readingField)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(dateLabel)
            .addComponent(dateTextField)
        )
    );

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.add(addButton);
    buttonPanel.add(cancelButton);

    add(titleLabel, BorderLayout.NORTH);
    add(contentPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Checks if a string is numeric.
   *
   * @param str The string to check.
   * @return `true` if the string is numeric, `false` otherwise.
   */
  private boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }
}
