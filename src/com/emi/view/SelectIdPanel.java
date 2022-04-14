package com.emi.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class SelectIdPanel extends JPanel {
    private JLabel idLabel;
    private JTextField idText;
    private JButton confirmButton;
    private JButton cancelButton;
    private CancelButtonPressed cancelButtonPressed;
    private IdSelected idSelected;

    public SelectIdPanel() {
        idLabel = new JLabel("Please enter the id of the employee");
        idText = new JTextField();
        confirmButton = new JButton("Confirm Data");
        confirmButton.setPreferredSize(new Dimension(150, 40));
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(150, 40));

        cancelButton.addActionListener(e -> {
            if (cancelButtonPressed != null)
                cancelButtonPressed.pressCancelButton();
        });

        confirmButton.addActionListener(e -> {
            int id = -1;

            try {
                id = Integer.parseInt(idText.getText());

                if (id > 0) {
                    if (idSelected != null)
                        idSelected.getId(id);
                } else
                    JOptionPane.showMessageDialog(null,
                            "Please enter values greater than 0 in the id field",
                            "ERROR", JOptionPane.ERROR_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Please enter digits in the id field",
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
        add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(idText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
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

    public void selectId(IdSelected idSelected) {
        this.idSelected = idSelected;
    }

    public void clearText() {
        idText.setText("");
    }

}
