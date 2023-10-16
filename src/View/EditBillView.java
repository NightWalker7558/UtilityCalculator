package View;

import Model.UtilityBill;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The EditBillView class represents a graphical user interface for editing a bill's details.
 * It allows users to view and modify the name, price, reading, and date associated with a bill.
 * Users can navigate back, delete the bill, or save the changes made to the bill's information.
 * <p>
 * This class serves as part of the App's user interface and is used for managing bill details.
 */
public class EditBillView extends JPanel {

  /**
   * A reference to the main application instance, facilitating communication between the view and
   * the application logic.
   */
  protected App app;

  /**
   * A label displaying the bill's name.
   */
  private JLabel nameLabel;

  /**
   * A label displaying the bill's price.
   */
  private JLabel priceLabel;

  /**
   * A label displaying the bill's reading.
   */
  private JLabel readingLabel;

  /**
   * A label displaying the bill's date.
   */
  private JLabel dateLabel;

  /**
   * A button for navigating back to the previous screen.
   */
  private JButton backButton;

  /**
   * A button for deleting the bill.
   */
  private JButton deleteButton;

  /**
   * A button for saving changes made to the bill's details.
   */
  private JButton saveButton;

  /**
   * Constructs a new EditBillView.
   * Initializes the graphical user interface components, including labels and buttons, for
   * viewing and editing bill details. It also sets up event listeners for user interactions.
   *
   * @param app The main application instance to connect the view with the application logic.
   */
  public EditBillView(App app, UtilityBill utilityBill) {
    this.app = app;

    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    JPanel contentPanel = new JPanel();
    GroupLayout layout = new GroupLayout(contentPanel);
    contentPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    contentPanel.setBackground(Color.WHITE);
    contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

    // Subscription details
    JLabel nameFieldLabel = createFieldLabel("Utility Type:");
    nameLabel = createEditableLabel(utilityBill.getUtilityType());

    JLabel priceFieldLabel = createFieldLabel("Price ($):");
    priceLabel =
      createEditableLabel(String.format("%.2f", utilityBill.getPrice()));

    JLabel readingFieldLabel = createFieldLabel("Reading:");
    readingLabel =
      createEditableLabel(Double.toString(utilityBill.getMeterMeasurement()));
    JButton editReadingButton = createEditButton("Edit Reading", readingLabel);

    JLabel dateFieldLabel = createFieldLabel("Date (yyyy-MM-dd):");
    dateLabel = createEditableLabel(utilityBill.getDate());

    saveButton = new JButton("Save");
    saveButton.setFont(new Font("Arial", Font.PLAIN, 12));
    saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    saveButton.setEnabled(false);
    saveButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String utilityType = nameLabel.getText();
          String reading = readingLabel.getText();
          String date = dateLabel.getText();

          if (utilityType.isEmpty() || reading.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(
              EditBillView.this,
              "Please fill in all the fields.",
              "Incomplete Fields",
              JOptionPane.ERROR_MESSAGE
            );
          } else if (!isNumeric(reading)) {
            JOptionPane.showMessageDialog(
              EditBillView.this,
              "Data and Talk Time must be numeric values.",
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE
            );
          } else {
            app.editBill(utilityBill.getId(), Double.parseDouble(reading));
            // priceLabel.setText(String.format("%.2f", price));

            saveButton.setEnabled(false);
            backButton.setEnabled(true);

            app.editBillPage(utilityBill);
          }
        }
      }
    );

    backButton = new JButton("Back");
    backButton.setFont(new Font("Arial", Font.PLAIN, 12));
    backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    backButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          app.customerDashboard();
        }
      }
    );

    deleteButton = new JButton("Delete");
    deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
    deleteButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    deleteButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Handle delete button action here
          app.deleteBill(utilityBill.getId());
          app.adminDashboard();
        }
      }
    );

    layout.setHorizontalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameFieldLabel)
                .addComponent(priceFieldLabel)
                .addComponent(readingFieldLabel)
                .addComponent(dateFieldLabel)
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel)
                .addComponent(priceLabel)
                .addComponent(readingLabel)
                .addComponent(dateLabel)
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(editReadingButton)
            )
        )
        .addGroup(
          layout
            .createSequentialGroup()
            .addComponent(saveButton)
            .addComponent(backButton)
            .addComponent(deleteButton)
        )
    );

    layout.setVerticalGroup(
      layout
        .createSequentialGroup()
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(nameFieldLabel)
            .addComponent(nameLabel)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(priceFieldLabel)
            .addComponent(priceLabel)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(readingFieldLabel)
            .addComponent(readingLabel)
            .addComponent(editReadingButton)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(dateFieldLabel)
            .addComponent(dateLabel)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(saveButton)
            .addComponent(backButton)
            .addComponent(deleteButton)
        )
    );

    add(contentPanel, BorderLayout.CENTER);

    setPreferredSize(new Dimension(600, 400));
    setVisible(true);
  }

  /**
   * The createFieldLabel method generates a custom label with specified text, font, alignment, and border.
   * This label is typically used alongside input fields to provide descriptive text or labels for user input.
   *
   * @param text The text content of the label.
   * @return A new JLabel customized with the specified text, font, alignment, and border settings.
   *
   * @implSpec This method ensures that the created label adheres to a consistent visual style,
   * using the "Arial" font with a bold style and a font size of 14. The label's border is set to create
   * padding on the top, bottom, and right, ensuring an aesthetically pleasing and user-friendly layout.
   *
   * @param text The text content to be displayed on the label.
   * @return A new JLabel instance with the provided text and custom settings.
   * @see JLabel
   * @see Font
   * @see EmptyBorder
   */
  private JLabel createFieldLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setBorder(new EmptyBorder(5, 0, 5, 10));
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    return label;
  }

  /**
   * The createEditableLabel method generates an editable label with the specified text, font, alignment, and border.
   * This label is often used to display information that users can edit or modify.
   *
   * @param text The text content of the editable label.
   * @return A new JLabel customized with the specified text, font, alignment, and border settings.
   *
   * @implSpec This method ensures that the created editable label adheres to a consistent visual style.
   * It uses the "Arial" font with a plain style and a font size of 14. The label's border is set to create
   * padding on the top and bottom, providing a visually pleasing layout. The label is also aligned to the
   * left to maintain consistent placement within the user interface.
   *
   * @param text The text content to be displayed on the editable label.
   * @return A new JLabel instance with the provided text and custom settings.
   * @see JLabel
   * @see Font
   * @see EmptyBorder
   */
  private JLabel createEditableLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setBorder(new EmptyBorder(5, 0, 5, 0));
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    return label;
  }

  /**
   * The createEditButton method generates an edit button with the specified text, font, and functionality
   * for modifying the content of a target JLabel when clicked.
   *
   * @param text The text content of the edit button.
   * @param targetLabel The JLabel whose content will be modified when the edit button is clicked.
   * @return A new JButton customized with the specified text, font, and action listener for editing a JLabel.
   *
   * @implSpec This method ensures that the created edit button adheres to a consistent visual style. It uses
   * the "Arial" font with a plain style and a font size of 12. The button is aligned to the left to maintain
   * consistent placement within the user interface. When the edit button is clicked, it displays a dialog
   * prompting the user to enter a new value. If a non-empty value is provided, the target JLabel's content is
   * updated, and other buttons' states are adjusted accordingly.
   *
   * @param text The text content to be displayed on the edit button.
   * @param targetLabel The JLabel whose content will be modified when the edit button is clicked.
   * @return A new JButton instance with the provided text, font, and functionality.
   * @see JButton
   * @see JLabel
   * @see Font
   * @see ActionListener
   * @see ActionEvent
   * @see JOptionPane
   */
  private JButton createEditButton(String text, JLabel targetLabel) {
    JButton button = new JButton(text);
    button.setFont(new Font("Arial", Font.PLAIN, 12));
    button.setAlignmentX(Component.LEFT_ALIGNMENT);
    button.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String newValue = JOptionPane.showInputDialog(
            EditBillView.this,
            "Enter new value:"
          );
          if (newValue != null && !newValue.isEmpty()) {
            targetLabel.setText(newValue);
            saveButton.setEnabled(true);
            backButton.setEnabled(false);
          }
        }
      }
    );
    return button;
  }

  /**
   * The isNumeric method checks whether a given string is a numeric value, which may include integers
   * and floating-point numbers (with or without a sign).
   *
   * @param str The input string to be checked for numeric content.
   * @return {@code true} if the input string is a numeric value, and {@code false} otherwise.
   *
   * @implSpec This method determines whether the provided string is numeric by using a regular expression.
   * It checks if the string matches the pattern for numeric values, which may include an optional sign
   * (positive or negative) and an optional decimal part. The method returns {@code true} if the string
   * matches the numeric pattern and {@code false} if it does not.
   *
   * @param str The string to be evaluated for numeric content.
   * @return {@code true} if the input string is numeric, and {@code false} otherwise.
   * @see String#matches(String)
   */
  private boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }
}
