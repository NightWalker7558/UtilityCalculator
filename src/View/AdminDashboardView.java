package View;

import Controller.ServiceController;
import Controller.StaffController;
import Model.ServiceType;
import Model.UtilityBill;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The AdminDashboardView class represents a graphical user interface panel for the admin dashboard.
 *
 * <p>The admin dashboard view provides administrators with a user interface for managing utility bills and services. It includes
 * multiple sections for displaying admin information, service-related controls, total bills, and a search interface for accessing specific
 * utility bills.</p>
 *
 * @see JPanel
 * @see App
 */
public class AdminDashboardView extends JPanel {

  /**
   * A panel displaying admin information, including the admin's name and email.
   *
   * @see JPanel
   */
  private JPanel adminInfoPanel;

  /**
   * A panel displaying utility services and associated information.
   *
   * @see JPanel
   */
  private JPanel servicesPanel;

  /**
   * A panel displaying the total utility bill cost.
   *
   * @see JPanel
   */
  private JPanel totalPanel;

  /**
   * A panel displaying the search bar with search input and button.
   *
   * @see JPanel
   */
  private JPanel searchBar;

  /**
   * A panel displaying search results.
   *
   * @see JPanel
   */
  private JPanel searchResultsContainer;

  /**
   * A panel displaying search results.
   *
   * @see JPanel
   */
  private JPanel searchResults;

  /**
   * The parent application.
   *
   * @see App
   */
  protected App app;

  /**
   * Constructs an instance of <code>AdminDashboardView</code>.
   *
   * @param app The parent application.
   *
   * <p>This constructor initializes the admin dashboard view with various panels and components, such as the admin information panel and service-related panels.</p>
   */
  public AdminDashboardView(App app) {
    this.app = app;

    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    adminInfoPanel = createAdminInfoPanel();
    servicesPanel = createServicesPanel();
    totalPanel = createTotalPanel();
    searchBar = createSearchBar();
    searchResultsContainer = new JPanel();
    searchResults = createResultsPanel(StaffController.viewAllBills());
    searchResultsContainer.add(searchResults);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
    );
    scrollPane.setHorizontalScrollBarPolicy(
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    scrollPane.setViewportView(createContentPane());

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    mainPanel.add(adminInfoPanel);
    mainPanel.add(servicesPanel);
    mainPanel.add(totalPanel);
    mainPanel.add(searchBar);

    add(mainPanel, BorderLayout.NORTH);

    add(scrollPane, BorderLayout.CENTER);

    setPreferredSize(new Dimension(650, 750));
    setVisible(true);
  }

  /**
   * Creates and returns the content pane for displaying search results.
   *
   * <p>This method generates a graphical panel, referred to as the content pane, designed to display search results
   * in a vertical layout. The panel's background color is set to white to provide a clean and visually appealing backdrop
   * for the search results.</p>
   *
   * <p>The content pane primarily contains the {@code searchResultsContainer} component, which is populated with search results.
   * It arranges search results in a vertical stack, allowing users to scroll through a list of utility bills and related information.</p>
   *
   * @return The content pane for displaying search results.
   *
   * @see #searchResultsContainer
   *
   * @implSpec The method's implementation follows the application's user interface design standards for creating a content pane
   * dedicated to presenting search results. It employs a vertical BoxLayout for straightforward stacking of results within the pane.
   *
   */
  private JPanel createContentPane() {
    JPanel contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    contentPane.setBackground(Color.WHITE);

    contentPane.add(searchResultsContainer);

    return contentPane;
  }

  /**
   * Creates and returns the admin information panel with account details and a logout button.
   *
   * <p>This method generates a graphical panel in the user interface to display the admin's account information,
   * including the admin's name, email, and a logout button. The admin's name is presented as a large, bold title,
   * and the email is shown in italics. A "Logout" button is included, which allows the admin to log out of the system.</p>
   *
   * <p>The panel's background color is set to a specific shade of blue (hex color code: #98c1d9) to provide a
   * visually distinct appearance for the admin information section. The layout of the panel is defined using a
   * GroupLayout, which allows precise control over the placement and alignment of components.</p>
   *
   * <p>The admin's account information and the "Logout" button are aligned horizontally in the center of the panel.
   * The button's appearance is customized by setting its background color to a dark shade (hex color code: #293241)
   * and the text color to white, providing a clear visual cue for user interaction.</p>
   *
   * @return The admin information panel displaying the admin's account details and a "Logout" button.
   *
   * @see App#selectionPane()
   *
   * @implSpec This method adheres to the application's user interface design guidelines for displaying admin account information
   * and providing an option for the admin to log out. It uses GroupLayout for precise component placement and alignment.
   */
  private JPanel createAdminInfoPanel() {
    JPanel panel = new JPanel();
    panel.setBackground(Color.decode("#98c1d9"));

    JLabel nameLabel = new JLabel("Admin Account");
    nameLabel.setFont(new Font("Arial", Font.BOLD, 28));
    JLabel emailLabel = new JLabel("admin@utility.com");
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

    layout.setHorizontalGroup(
      layout
        .createSequentialGroup()
        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(
          layout
            .createParallelGroup()
            .addComponent(nameLabel)
            .addComponent(emailLabel)
        )
        .addPreferredGap(
          LayoutStyle.ComponentPlacement.RELATED,
          GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addComponent(logoutButton)
        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addGroup(
          layout
            .createSequentialGroup()
            .addComponent(nameLabel)
            .addComponent(emailLabel)
        )
        .addComponent(logoutButton)
    );

    return panel;
  }

  /**
   * Creates and returns the services panel displaying utility services and associated information.
   *
   * <p>This method generates a panel in the user interface that lists various utility services,
   * including their names, service charges, and unit charges. It also includes an "Edit" button
   * for each service, allowing administrators to modify service details.</p>
   *
   * <p>The services panel is organized using GridLayout, with a single row and multiple columns.
   * Each service is displayed in a separate sub-panel, with labels indicating the service name,
   * service charge, and unit charge. The "Edit" button is available for each service, allowing
   * administrators to access the service editing page using the {@link App#editServicePage(ServiceType)} method.</p>
   *
   * <p>The services' data, such as service charges and unit charges, are fetched from the
   * {@link ServiceController} class. The layout includes appropriate spacing and padding for
   * a visually appealing presentation.</p>
   *
   * @return The services panel displaying utility services, their charges, and "Edit" buttons.
   *
   * @see App#editServicePage(ServiceType)
   * @see ServiceController
   */
  private JPanel createServicesPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 1)); // 2 rows, 1 column

    String[] utilities = { "ELECTRICITY", "WATER", "GAS" };

    // Create a panel to hold the utilities and edit buttons
    JPanel utilitiesPanel = new JPanel();
    utilitiesPanel.setLayout(new GridLayout(1, utilities.length));

    int padding = 10;

    for (int i = 0; i < utilities.length; i++) {
      ServiceType serviceType = ServiceType.valueOf(utilities[i]);
      JPanel utilityPanel = new JPanel();
      utilityPanel.setLayout(new GridLayout(3, 1));
      utilityPanel.setBorder(
        BorderFactory.createEmptyBorder(padding, padding, 2 * padding, padding)
      );

      JLabel utilityLabel = new JLabel(utilities[i]);
      utilityLabel.setHorizontalAlignment(JLabel.CENTER);
      utilityPanel.add(utilityLabel);

      JLabel serviceChargeLabel = new JLabel(
        "Service Charge: $" + ServiceController.getServicePrice(serviceType)
      );
      JLabel unitChargeLabel = new JLabel(
        "Unit Charge: $" + ServiceController.getUnitPrice(serviceType)
      );

      JPanel chargesPanel = new JPanel();
      chargesPanel.setLayout(new GridLayout(2, 1));
      chargesPanel.add(serviceChargeLabel);
      chargesPanel.add(unitChargeLabel);

      utilityPanel.add(chargesPanel);

      JButton editButton = new JButton("Edit");
      editButton.addActionListener(e -> {
        app.editServicePage(serviceType);
      });

      utilityPanel.add(editButton);

      utilitiesPanel.add(utilityPanel);
    }

    panel.add(utilitiesPanel);

    return panel;
  }

  /**
   * Creates and returns the search bar panel with search input and button.
   *
   * <p>This method creates a panel that serves as a search bar in the user interface.
   * The search bar includes a text area where users can input search text, a "Search" button
   * to initiate the search operation, and associated event listeners for user interaction.</p>
   *
   * <p>The search bar layout is based on GridBagLayout for precise placement of components.
   * It includes a text area for entering search text, and a button to trigger the search operation.
   * The button's action listener is set to invoke the {@link #performSearch(String)} method
   * when clicked. The text area also has a document listener to dynamically respond to changes
   * in the search text input.</p>
   *
   * <p>The method sets up GridBagConstraints for fine-tuning the layout, such as adding padding around
   * components.</p>
   *
   * @return The search bar panel that includes a search text input area and a "Search" button.
   *
   * @see #performSearch(String)
   */
  private JPanel createSearchBar() {
    JPanel searchBarPanel = new JPanel();
    searchBarPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(2, 2, 2, 2); // Add padding

    JTextArea searchTextArea = new JTextArea();
    searchTextArea.setColumns(30);
    searchTextArea.setRows(2);

    searchTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    JButton searchButton = new JButton("Search");
    searchButton.setPreferredSize(
      new Dimension(150, searchButton.getPreferredSize().height)
    );

    searchButton.addActionListener(e -> {
      performSearch(searchTextArea.getText());
    });

    searchTextArea
      .getDocument()
      .addDocumentListener(
        new DocumentListener() {
          @Override
          public void insertUpdate(DocumentEvent e) {
            performSearch(searchTextArea.getText());
          }

          @Override
          public void removeUpdate(DocumentEvent e) {
            performSearch(searchTextArea.getText());
          }

          @Override
          public void changedUpdate(DocumentEvent e) {
            // Mandatory to add.
          }
        }
      );

    gbc.gridx = 0;
    searchBarPanel.add(searchTextArea, gbc);

    gbc.gridx = 1;
    searchBarPanel.add(searchButton, gbc);

    return searchBarPanel;
  }

  /**
   * Performs a search operation based on the provided search text.
   *
   * @param searchText The text to be used for searching utility bills.
   *
   * <p>This method performs a search operation based on the provided search text.
   * It retrieves utility bills that match the search criteria and displays the search results
   * within the search results panel. If no matching utility bills are found, the search results panel
   * displays a message indicating that no results were found. This method is typically triggered
   * when the user initiates a search action within the application.</p>
   *
   * @param searchText The text used for searching utility bills.
   *
   * @see StaffController
   */
  private void performSearch(String searchText) {
    searchResults =
      createResultsPanel(StaffController.viewUserBills(searchText));
    refreshSearchResultsPanel();
  }

  /**
   * Refreshes the search results panel with updated content.
   *
   * <p>This method updates the search results panel with new content. It removes the current content
   * from the search results container, adds the updated search results, and then triggers a revalidation
   * and repaint of the container to reflect the changes. This method is called to refresh the displayed
   * search results when updates or changes occur.</p>
   *
   * <p>If the search results container is null or empty, no action is performed.</p>
   */
  private void refreshSearchResultsPanel() {
    if (searchResults != null) {
      searchResultsContainer.removeAll();
      searchResultsContainer.add(searchResults);
      searchResultsContainer.revalidate();
      searchResultsContainer.repaint();
    }
  }

  /**
   * Creates a panel to display utility bill information.
   *
   * @param bills An {@code ArrayList} of {@code UtilityBill} objects to display.
   * @return A panel displaying utility bill information or a "None" message if the list is empty.
   *
   * <p>This method generates a panel to display detailed information about utility bills, including customer name, utility type, usage data, price, and date.
   * If the provided {@code bills} list is empty or {@code null}, it displays a message indicating no utility bills are available.
   * Each bill's information is enclosed in a separate sub-panel with proper spacing.
   * The total utility bill cost is also calculated and displayed at the end of the panel.</p>
   *
   * @see #createBillPanel(UtilityBill)
   */
  private JPanel createResultsPanel(ArrayList<UtilityBill> bills) {
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
   * Creates a panel to display detailed information about a utility bill.
   *
   * @param bill A {@code UtilityBill} object containing bill information.
   * @return A panel displaying the utility bill details.
   *
   * <p>This method generates a panel to display detailed information about a utility bill.
   * The information includes the customer's name, utility type, usage data, price, and date of the bill.
   * Each piece of information is presented in a properly formatted label within the panel.
   * This method is typically used in the context of displaying individual utility bills within a larger view.</p>
   *
   * @param bill The {@code UtilityBill} object representing the utility bill to be displayed.
   *
   * @return A panel containing the formatted details of the provided utility bill.
   *
   * @see UtilityBill
   */
  private JPanel createBillPanel(UtilityBill bill) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel nameLabel = new JLabel("Customer: " + bill.getUserName());
    nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

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
    infoPanel.add(nameLabel);
    infoPanel.add(typeLabel);
    infoPanel.add(dataLabel);
    infoPanel.add(priceLabel);
    infoPanel.add(dateLabel);

    panel.add(infoPanel, BorderLayout.CENTER);

    return panel;
  }

  /**
   * Creates a panel to display the total utility bill cost.
   *
   * @return A panel displaying the total utility bill cost.
   *
   * <p>This method generates a panel to display the total utility bill cost for all utility bills.
   * It calculates the total bill cost using the {@code StaffController.calculateTotalPrice()} method
   * and presents it in the panel with proper formatting and styling.</p>
   *
   * @see StaffController#calculateTotalPrice()
   */
  private JPanel createTotalPanel() {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new FlowLayout(FlowLayout.CENTER));

    JLabel totalLabel = new JLabel(
      "Total Bill: $" +
      String.format("%.2f", StaffController.calculateTotalPrice())
    );
    totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

    panel.add(totalLabel);

    return panel;
  }
}
