package View;

import Model.Customer;
import Model.UtilityBill;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CustomerDashboardView extends JPanel {

  private JPanel customerInfoPanel;
  private JPanel currentBillsPanel;
  private Customer customer;
  protected App app;

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
   * @return JPanel
   */
  private JPanel createContentPane() {
    JPanel contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    contentPane.setBackground(Color.WHITE);

    contentPane.add(currentBillsPanel);

    return contentPane;
  }

  /**
   * @return JPanel
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
