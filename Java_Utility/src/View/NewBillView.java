package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import View.includes.TextPrompt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NewBillView extends JPanel {
    private JComboBox typeField;
    private JTextField readingField;
    private JFormattedTextField dateTextField;
    private JButton addButton;
    private JButton cancelButton;

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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = (String) typeField.getSelectedItem();
                String reading = readingField.getText();
                String date = dateTextField.getText();

                // Perform input validation here
                if (type.isEmpty() || reading.isEmpty() || date.isEmpty() ) {
                    JOptionPane.showMessageDialog(NewBillView.this,
                            "Please fill in all the fields.", "Incomplete Fields", JOptionPane.ERROR_MESSAGE);
                } else if (!isNumeric(reading)) {
                    JOptionPane.showMessageDialog(NewBillView.this,
                            "Reading must be a numeric value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } else {
                    app.addNewBill(type, Double.parseDouble(reading), date);
                    
                    typeField.setSelectedIndex(0);
                    readingField.setText("");
                    dateTextField.setText("");

                    JOptionPane.showMessageDialog(NewBillView.this,
                            "New Utility Bill added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    app.customerDashboard();
                }
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.decode("#F44336"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.customerDashboard();
            }
        });

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(typeLabel)
                                .addComponent(readingLabel)
                                .addComponent(dateLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(typeField)
                                .addComponent(readingField)
                                .addComponent(dateTextField))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(typeLabel)
                                .addComponent(typeField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(readingLabel)
                                .addComponent(readingField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(dateLabel)
                                .addComponent(dateTextField))
        );

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(titleLabel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
