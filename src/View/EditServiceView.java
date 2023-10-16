package View;

import Model.ServiceType;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * A graphical user interface panel for editing service details.
 *
 * This class extends JPanel to create a user interface for editing service information, such as utility type, service
 * charges, and unit charges. Users can edit the values and save the changes.
 *
 * @param app The application instance that controls the view.
 * @param serviceType The type of service being edited.
 *
 * @implSpec This panel provides a graphical interface for editing service information, including utility type, service
 * charges, and unit charges. Users can input new values and save the changes. It utilizes various Swing components to
 * achieve this functionality.
 *
 * @throws NullPointerException If the {@code app} parameter is {@code null}.
 */
public class EditServiceView extends JPanel {

  /**
   * The main application instance that this UI is associated with.
   *
   * @see App
   */
  protected App app;

  /**
   * Label displaying the type of service.
   */
  private JLabel typeLabel;

  /**
   * Label displaying the price of the service.
   */
  private JLabel servicePriceLabel;

  /**
   * Label displaying the unit price of the service.
   */
  private JLabel unitPriceLabel;

  /**
   * Button to navigate back to the previous screen.
   */
  private JButton backButton;

  /**
   * Button to save the configured pricing information.
   *
   * @implSpec This button triggers the saving of service pricing data when clicked.
   */
  private JButton saveButton;

  /**
   * EditServiceView represents a user interface for modifying and saving service pricing details.
   * Users can edit and save the service charges and unit charges for a specific service type.
   *
   * @param app The main application instance associated with this view.
   * @param serviceType The service type for which pricing details will be edited.
   *
   * @implSpec This view is designed to allow users to edit and save service pricing information.
   *          It includes UI components for editing, validation, and saving service charges and unit charges.
   *          It also provides navigation back to the admin dashboard.
   * @throws NullPointerException if the 'app' parameter is null.
   */
  public EditServiceView(App app, ServiceType serviceType) {
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
    typeLabel = createEditableLabel(serviceType.name());

    JLabel servicePriceFieldLabel = createFieldLabel("Service Charges ($):");
    servicePriceLabel =
      createEditableLabel(
        String.format("%.2f", serviceType.getServiceCharges())
      );
    JButton editServiceButton = createEditButton(
      "Edit Service Charges",
      servicePriceLabel
    );

    JLabel unitPriceFieldLabel = createFieldLabel("Unit Charges ($):");
    unitPriceLabel =
      createEditableLabel(String.format("%.2f", serviceType.getUnitCharges()));
    JButton editUnitButton = createEditButton(
      "Edit Unit Charges",
      unitPriceLabel
    );

    saveButton = new JButton("Save");
    saveButton.setFont(new Font("Arial", Font.PLAIN, 12));
    saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    saveButton.setEnabled(false);
    saveButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String utilityType = typeLabel.getText();
          String unit = unitPriceLabel.getText();
          String service = servicePriceLabel.getText();

          if (utilityType.isEmpty() || unit.isEmpty() || service.isEmpty()) {
            JOptionPane.showMessageDialog(
              EditServiceView.this,
              "Please fill in all the fields.",
              "Incomplete Fields",
              JOptionPane.ERROR_MESSAGE
            );
          } else if (!isNumeric(unit) || !isNumeric(service)) {
            JOptionPane.showMessageDialog(
              EditServiceView.this,
              "Unit and Service Charges must be numeric values.",
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE
            );
          } else {
            app.editService(
              serviceType,
              Double.parseDouble(service),
              Double.parseDouble(unit)
            );

            saveButton.setEnabled(false);
            backButton.setEnabled(true);
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
                .addComponent(servicePriceFieldLabel)
                .addComponent(unitPriceFieldLabel)
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(typeLabel)
                .addComponent(servicePriceLabel)
                .addComponent(unitPriceLabel)
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(editServiceButton)
                .addComponent(editUnitButton)
            )
        )
        .addGroup(
          layout
            .createSequentialGroup()
            .addComponent(saveButton)
            .addComponent(backButton)
        )
    );

    layout.setVerticalGroup(
      layout
        .createSequentialGroup()
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(nameFieldLabel)
            .addComponent(typeLabel)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(servicePriceFieldLabel)
            .addComponent(servicePriceLabel)
            .addComponent(editServiceButton)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(unitPriceFieldLabel)
            .addComponent(unitPriceLabel)
            .addComponent(editUnitButton)
        )
        .addGroup(
          layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(saveButton)
            .addComponent(backButton)
        )
    );

    add(contentPanel, BorderLayout.CENTER);

    setPreferredSize(new Dimension(600, 400));
    setVisible(true);
  }

  /**
   * Creates and configures a field label for display in the user interface.
   *
   * @param text The text content of the label.
   *
   * @return A JLabel component representing the field label.
   *
   * @implSpec This method is responsible for creating a label with the specified text content.
   *          It sets the font, border, and alignment properties to ensure a consistent and clear label appearance.
   */
  private JLabel createFieldLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setBorder(new EmptyBorder(5, 0, 5, 10));
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    return label;
  }

  /**
   * Creates and configures an editable label for display in the user interface.
   *
   * @param text The text content of the label.
   *
   * @return A JLabel component representing the editable label.
   *
   * @implSpec This method is responsible for creating a label with the specified text content.
   *          It sets the font, border, and alignment properties to ensure an editable label appearance.
   */
  private JLabel createEditableLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setBorder(new EmptyBorder(5, 0, 5, 0));
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    return label;
  }

  /**
   * Creates and configures an edit button for interaction in the user interface.
   *
   * @param text The text content of the button.
   * @param targetLabel The JLabel that the button will edit when clicked.
   *
   * @return A JButton component representing the edit button.
   *
   * @implSpec This method is responsible for creating a button with the specified text content.
   *          It sets the font, alignment properties, and adds an action listener to enable editing.
   *          When the button is clicked, it opens a dialog for entering a new value, which is then
   *          applied to the target label. Additionally, it enables the "Save" button and disables the "Back" button.
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
            EditServiceView.this,
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
   * Checks if the given string represents a numeric value.
   *
   * @param str The string to be checked for numeric validity.
   * @return {@code true} if the string is a valid numeric representation; {@code false} otherwise.
   *
   * @implSpec This method examines the input string to determine if it represents a numeric value. It allows for both
   * positive and negative integers, as well as decimal numbers. The regular expression used for matching numeric values
   * is "-?\\d+(\\.\\d+)?".
   *
   * @throws NullPointerException If the input string is {@code null}.
   */
  private boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }
}
