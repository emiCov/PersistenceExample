package com.emi.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class InsertEmployeePanel extends JPanel {
    private JLabel nameLabel;
    private JTextField nameText;
    private JLabel ageLabel;
    private JTextField ageText;
    private JLabel addressLabel;
    private JTextField addressText;
    private JLabel salaryLabel;
    private JTextField salaryText;
    private JButton confirmButton;
    private JButton cancelButton;
    private CancelButtonPressed cancelButtonPressed;
    private InsertConfirmButtonPressed insertConfirmButtonPressed;

    public InsertEmployeePanel() {
        nameLabel = new JLabel("Insert the name of the employee");
        nameText = new JTextField();
        ageLabel = new JLabel("Insert the age of the employee");
        ageText = new JTextField();
        addressLabel = new JLabel("Insert the address of the employee");
        addressText = new JTextField();
        salaryLabel = new JLabel("Insert the salary of the employee");
        salaryText = new JTextField();
        confirmButton = new JButton("Confirm Data");
        confirmButton.setPreferredSize(new Dimension(150, 40));
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(150, 40));

        cancelButton.addActionListener(e -> {
            if (cancelButtonPressed != null)
                cancelButtonPressed.pressCancelButton();
        });

        confirmButton.addActionListener(e -> {

            String name = nameText.getText();
            String address = addressText.getText();
            int age;
            double salary;

            try {
                age = Integer.parseInt(ageText.getText());
                salary = Double.parseDouble(salaryText.getText());

                if (age > 0 && salary >= 0) {
                    if (name.equals("") || address.equals(""))
                        JOptionPane.showMessageDialog(null,
                                "Please enter values in the name and/or address fields",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    else if (insertConfirmButtonPressed != null)
                        insertConfirmButtonPressed.getEmployeeData(name, age, address, salary);
                } else
                    JOptionPane.showMessageDialog(null,
                            "Please enter proper values in the age and/or salary fields",
                            "ERROR", JOptionPane.ERROR_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Please enter digits in the age and/or salary fields",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weighty = 1;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(nameLabel, gbc);

        gbc.gridy = 1;
        add(ageLabel, gbc);

        gbc.gridy = 2;
        add(addressLabel, gbc);

        gbc.gridy = 3;
        add(salaryLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameText, gbc);

        gbc.gridy = 1;
        add(ageText, gbc);

        gbc.gridy = 2;
        add(addressText, gbc);

        gbc.gridy = 3;
        add(salaryText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(confirmButton, gbc);

        gbc.gridx = 1;
        add(cancelButton, gbc);

        Border outBorder = BorderFactory.createEmptyBorder();
        Border inBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setBorder(new CompoundBorder(outBorder, inBorder));
    }

    public void pressCancelButton(CancelButtonPressed cancelButtonPressed) {
        this.cancelButtonPressed = cancelButtonPressed;
    }

    public void pressConfirmInsertButton(InsertConfirmButtonPressed insertConfirmButtonPressed) {
        this.insertConfirmButtonPressed = insertConfirmButtonPressed;
    }

    public void clearText() {
        nameText.setText("");
        ageText.setText("");
        addressText.setText("");
        salaryText.setText("");
    }

}
