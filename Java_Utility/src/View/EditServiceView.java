package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ServiceType;

public class EditServiceView extends JPanel {
    protected App app;
    private JLabel typeLabel;
    private JLabel servicePriceLabel;
    private JLabel unitPriceLabel;
    private JButton backButton;
    private JButton saveButton;

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
        servicePriceLabel = createEditableLabel(String.format("%.2f", serviceType.getServiceCharges()));
        JButton editServiceButton = createEditButton("Edit Service Charges", servicePriceLabel);

        JLabel unitPriceFieldLabel = createFieldLabel("Unit Charges ($):");
        unitPriceLabel = createEditableLabel(String.format("%.2f",serviceType.getUnitCharges()));
        JButton editUnitButton = createEditButton("Edit Unit Charges", unitPriceLabel);

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 12));
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        saveButton.setEnabled(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String utilityType = typeLabel.getText();
                String unit = unitPriceLabel.getText();
                String service = servicePriceLabel.getText();

                if (utilityType.isEmpty() || unit.isEmpty() || service.isEmpty()) {
                    JOptionPane.showMessageDialog(EditServiceView.this,
                            "Please fill in all the fields.", "Incomplete Fields", JOptionPane.ERROR_MESSAGE);
                } else if (!isNumeric(unit) || !isNumeric(service)) {
                    JOptionPane.showMessageDialog(EditServiceView.this,
                            "Unit and Service Charges must be numeric values.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } else {
                    app.editService(serviceType,  Double.parseDouble(service), Double.parseDouble(unit));

                    saveButton.setEnabled(false);
                    backButton.setEnabled(true);
                }
            }
        });

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.adminDashboard();
            }
        });

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameFieldLabel)
                                        .addComponent(servicePriceFieldLabel)
                                        .addComponent(unitPriceFieldLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(typeLabel)
                                        .addComponent(servicePriceLabel)
                                        .addComponent(unitPriceLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(editServiceButton)
                                        .addComponent(editUnitButton)))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addComponent(backButton)));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameFieldLabel)
                                .addComponent(typeLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(servicePriceFieldLabel)
                                .addComponent(servicePriceLabel)
                                .addComponent(editServiceButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(unitPriceFieldLabel)
                                .addComponent(unitPriceLabel)
                                .addComponent(editUnitButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(saveButton)
                                .addComponent(backButton)));

        add(contentPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(600, 400));
        setVisible(true);
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(new EmptyBorder(5, 0, 5, 10));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newValue = JOptionPane.showInputDialog(
                        EditServiceView.this, "Enter new value:");
                if (newValue != null && !newValue.isEmpty()) {
                    targetLabel.setText(newValue);
                    saveButton.setEnabled(true);
                    backButton.setEnabled(false);
                }
            }
        });
        return button;
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
