package View;

import Model.UtilityBill;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EditBillView extends JPanel {

  protected App app;
  private JLabel nameLabel;
  private JLabel priceLabel;
  private JLabel readingLabel;
  private JLabel dateLabel;
  private JButton backButton;
  private JButton deleteButton;
  private JButton saveButton;

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
   * @param text
   * @return JLabel
   */
  private JLabel createFieldLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setBorder(new EmptyBorder(5, 0, 5, 10));
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    return label;
  }

  /**
   * @param text
   * @return JLabel
   */
  private JLabel createEditableLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setBorder(new EmptyBorder(5, 0, 5, 0));
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    return label;
  }

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

  private boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }
}
