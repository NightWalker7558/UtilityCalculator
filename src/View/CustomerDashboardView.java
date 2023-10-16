package View;

import Model.Customer;
import Model.UtilityBill;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The CustomerDashboardView class represents the dashboard view for a customer in the application.
 *
 * <p>This class extends JPanel to provide a container for displaying the customer dashboard. It contains the necessary components and
 * functionality to display the customer's information panel, current bills panel, and interact with the parent application. It inherits
 * the layout and background properties from JPanel.</p>
 *
 * @see JPanel
 */
public class CustomerDashboardView extends JPanel {

  /**
   * The customer information panel.
   *
   * @see JPanel
   */
  private JPanel customerInfoPanel;

  /**
   * The current bills panel.
   *
   * @see JPanel
   */
  private JPanel currentBillsPanel;

  /**
   * The customer object.
   *
   * @see Customer
   */
  private Customer customer;

  /**
   * The parent application instance.
   *
   * @see App
   */
  protected App app;

  /**
   * CustomerDashboardView represents the dashboard view for a customer in the application.
   *
   * <p>This class extends a container with a BorderLayout and sets a white background. It displays the customer's information panel at the
   * top, a scrollable content pane in the center, and an "Add Bill" button at the bottom. The view is initialized with an instance of the
   * parent application and a customer object.</p>
   *
   * <p><strong>Note:</strong> The customer's bills are obtained from the customer object.</p>
   *
   * @param app The parent application.
   * @param customer The customer object for which the dashboard view is created.
   * @see App
   * @see Customer
   * @see BorderLayout
   * @see Color
   * @see JScrollPane
   * @see JButton
   * @see Dimension
   * @see createCustomerInfoPanel()
   * @see createCurrentBillsPanel(List)
   * @see createContentPane()
   * @see newBillView()
   */
  public CustomerDashboardView(App app, Customer customer) {
    this.app = app;
    this.customer = customer;

    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    customerInfoPanel = createCustomerInfoPanel();
    currentBillsPanel = createCurrentBillsPanel(this.customer.getBills());

    add(customerInfoPanel, BorderLayout.NORTH);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
    );
    scrollPane.setHorizontalScrollBarPolicy(
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    scrollPane.setViewportView(createContentPane());

    add(scrollPane, BorderLayout.CENTER);

    JButton addBillButton = new JButton("Add Bill");
    addBillButton.addActionListener(e -> {
      app.newBillView();
    });

    add(addBillButton, BorderLayout.SOUTH);

    setPreferredSize(new Dimension(600, 400));

    setVisible(true);
  }

  /**
   * Creates a content pane for the application.
   *
   * <p>This method creates a JPanel that serves as the content pane for the application. The content pane has a vertical BoxLayout and a
   * white background. It adds the "currentBillsPanel" to the content pane.</p>
   *
   * @return A JPanel serving as the content pane.
   * @see JPanel
   * @see BoxLayout
   * @see Color
   * @see currentBillsPanel
   */
  private JPanel createContentPane() {
    JPanel contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    contentPane.setBackground(Color.WHITE);

    contentPane.add(currentBillsPanel);

    return contentPane;
  }

  /**
   * Creates a panel displaying customer information.
   *
   * <p>This method creates a JPanel that displays customer information, including the username and email. It also includes a logout button
   * that triggers the "selectionPane" method in the parent application when clicked. The panel has a background color and uses a GroupLayout
   * layout manager for arranging its components.</p>
   *
   * <p><strong>Note:</strong> The customer information is obtained from the "customer" object, which is assumed to be an instance of a
   * customer class.</p>
   *
   * @return A JPanel displaying customer information.
   * @see JPanel
   * @see JLabel
   * @see JButton
   * @see Font
   * @see Color
   * @see GroupLayout
   * @see ActionListener
   * @see ActionEvent
   * @see Component
   * @see Dimension
   */
  private JPanel createCustomerInfoPanel() {
    JPanel panel = new JPanel();
    panel.setBackground(Color.decode("#98c1d9"));

    JLabel usernameLabel = new JLabel(customer.getUsername());
    usernameLabel.setFont(new Font("Arial", Font.BOLD, 28));
    JLabel emailLabel = new JLabel(customer.getEmail());
    emailLabel.setFont(new Font("Arial", Font.ITALIC, 14));

    JButton logoutButton = new JButton("Logout");
    logoutButton.setBackground(Color.decode("#293241"));
    logoutButton.setForeground(Color.WHITE);
    logoutButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          app.selectionPane();
        }
      }
    );

    GroupLayout layout = new GroupLayout(panel);
    panel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    Component gap = Box.createRigidArea(new Dimension(20, 50));

    layout.setHorizontalGroup(
      layout
        .createSequentialGroup()
        .addComponent(gap)
        .addGroup(
          layout
            .createParallelGroup()
            .addComponent(usernameLabel)
            .addComponent(emailLabel)
        )
        .addPreferredGap(
          LayoutStyle.ComponentPlacement.RELATED,
          GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addComponent(logoutButton)
        .addComponent(gap)
    );

    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(gap)
        .addGroup(
          layout
            .createSequentialGroup()
            .addComponent(usernameLabel)
            .addComponent(emailLabel)
        )
        .addComponent(logoutButton)
        .addComponent(gap)
    );

    return panel;
  }

  /**
   * Creates a panel to display a list of current utility bills.
   *
   * <p>This method creates a JPanel that displays a list of current utility bills. The panel includes a title, "Utility Bills",
   * and dynamically generates bill panels using the {@link #createBillPanel(UtilityBill)} method for each bill in the provided
   * {@code bills} list. If the list is empty or null, a message indicating "None" is displayed.</p>
   *
   * <p><strong>Note:</strong> The actual implementation of the bill panel creation is done by the {@link #createBillPanel(UtilityBill)}
   * method, which sets up the necessary UI components for each bill. This method only handles the layout and arrangement of the bill panels.
   * It also sets the title label and adds the bill panels to the content panel.</p>
   *
   * @param bills The list of utility bills to be displayed.
   * @return A JPanel displaying the list of current utility bills.
   * @see UtilityBill
   * @see ArrayList
   * @see JPanel
   * @see JLabel
   * @see Color
   * @see BorderLayout
   * @see BoxLayout
   * @see Component
   * @see #createBillPanel(UtilityBill)
   */
  private JPanel createCurrentBillsPanel(ArrayList<UtilityBill> bills) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add space above the panel

    JLabel titleLabel = new JLabel("Utility Bills");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    titleLabel.setHorizontalAlignment(JLabel.CENTER);

    JPanel contentPanel = new JPanel();
    contentPanel.setBackground(Color.WHITE);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    if (bills == null || bills.isEmpty()) {
      JLabel emptyLabel = new JLabel("None");
      emptyLabel.setFont(new Font("Arial", Font.PLAIN, 14));
      emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

      // Add spacing before and after the "None" label
      contentPanel.add(Box.createVerticalStrut(20));
      contentPanel.add(emptyLabel);
      contentPanel.add(Box.createVerticalStrut(20));
    } else {
      for (UtilityBill bill : bills) {
        JPanel planPanel = createBillPanel(bill);

        // Add spacing between each plan
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(planPanel);
      }
    }

    panel.add(contentPanel, BorderLayout.CENTER);
    panel.add(titleLabel, BorderLayout.NORTH);

    return panel;
  }

  /**
   * Creates a panel to display information about a utility bill.
   *
   * <p>This method creates a JPanel that displays information about a utility bill. The panel includes labels for the utility type,
   * meter measurement, price, and date of the bill. It also includes buttons for editing and deleting the bill.</p>
   *
   * <p><strong>Note:</strong> The actual implementation of the bill editing and deletion functionality is not shown in this method.
   * The method only sets up the necessary UI components and defines the button actions.</p>
   *
   * @param bill The utility bill for which the panel is created.
   * @return A JPanel displaying information about the utility bill.
   * @see UtilityBill
   * @see JPanel
   * @see JLabel
   * @see JButton
   * @see Color
   * @see BorderLayout
   * @see BorderFactory
   * @see Font
   * @see BoxLayout
   * @see FlowLayout
   */
  private JPanel createBillPanel(UtilityBill bill) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel typeLabel = new JLabel(bill.getUtilityType());
    typeLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    JLabel dataLabel = new JLabel("Usage: " + bill.getMeterMeasurement());
    dataLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    JLabel priceLabel = new JLabel(
      "Price: $" + String.format("%.2f", bill.getPrice())
    );
    priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    JLabel dateLabel = new JLabel("Date: " + bill.getDate());
    dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setBackground(Color.WHITE);
    infoPanel.add(typeLabel);
    infoPanel.add(dataLabel);
    infoPanel.add(priceLabel);
    infoPanel.add(dateLabel);

    panel.add(infoPanel, BorderLayout.CENTER);

    JButton actionButton = new JButton("Edit");
    actionButton.addActionListener(e -> {
      app.editBillPage(bill);
    });

    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(e -> {
      app.deleteBill(bill.getId());
      app.customerDashboard();
    });

    // Create a panel with FlowLayout to hold the buttons side by side
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(actionButton);
    buttonPanel.add(deleteButton);

    panel.add(buttonPanel, BorderLayout.SOUTH);

    return panel;
  }
}
