package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.ServiceType;
import Model.UtilityBill;

import Controller.StaffController;
import Controller.ServiceController;;

public class AdminDashboardView extends JPanel {
    private JPanel adminInfoPanel;
    private JPanel servicesPanel;
    private JPanel totalPanel;
    private JPanel searchBar;
    private JPanel searchResultsContainer;
    private JPanel searchResults;
    protected App app;

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
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

    private JPanel createContentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Color.WHITE);

        contentPane.add(searchResultsContainer);

        return contentPane;
    }

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
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.selectionPane();
            }
        });

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(nameLabel)
                                .addComponent(emailLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(logoutButton)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addComponent(emailLabel))
                        .addComponent(logoutButton));

        return panel;
    }

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
            utilityPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, 2 * padding, padding));

            JLabel utilityLabel = new JLabel(utilities[i]);
            utilityLabel.setHorizontalAlignment(JLabel.CENTER);
            utilityPanel.add(utilityLabel);

            JLabel serviceChargeLabel = new JLabel("Service Charge: $" + ServiceController.getServicePrice(serviceType));
            JLabel unitChargeLabel = new JLabel("Unit Charge: $" + ServiceController.getUnitPrice(serviceType));

            JPanel chargesPanel = new JPanel();
            chargesPanel.setLayout(new GridLayout(2, 1));
            chargesPanel.add(serviceChargeLabel);
            chargesPanel.add(unitChargeLabel);

            utilityPanel.add(chargesPanel);

            JButton editButton = new JButton("Edit");
            editButton.addActionListener((e) -> {
                app.editServicePage(serviceType);
            });

            utilityPanel.add(editButton);

            utilitiesPanel.add(utilityPanel);
        }

        panel.add(utilitiesPanel);

        return panel;
    }

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
        searchButton.setPreferredSize(new Dimension(150, searchButton.getPreferredSize().height));

        searchButton.addActionListener(e -> {
            performSearch(searchTextArea.getText());
        });

        searchTextArea.getDocument().addDocumentListener(new DocumentListener() {
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
        });

        gbc.gridx = 0;
        searchBarPanel.add(searchTextArea, gbc);

        gbc.gridx = 1;
        searchBarPanel.add(searchButton, gbc);

        return searchBarPanel;
    }

    private void performSearch(String searchText) {
        searchResults = createResultsPanel(StaffController.viewUserBills(searchText));
        refreshSearchResultsPanel();
    }

    private void refreshSearchResultsPanel() {
        if (searchResults != null) {
            searchResultsContainer.removeAll();
            searchResultsContainer.add(searchResults);
            searchResultsContainer.revalidate();
            searchResultsContainer.repaint();
        }
    }

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

        JLabel priceLabel = new JLabel("Price: $" + String.format("%.2f", bill.getPrice()));
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

    private JPanel createTotalPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel totalLabel = new JLabel("Total Bill: $" + String.format("%.2f", StaffController.calculateTotalPrice()));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(totalLabel);

        return panel;
    }

}
