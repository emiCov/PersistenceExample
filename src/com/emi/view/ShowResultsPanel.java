package com.emi.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class ShowResultsPanel extends JPanel {
    private JLabel titleLabel;
    private JTextArea showEmployeesTextArea;
    private JButton closeButton;
    private CancelButtonPressed closeButtonPressed;

    public ShowResultsPanel() {
        titleLabel = new JLabel();
        showEmployeesTextArea = new JTextArea();
        showEmployeesTextArea.setPreferredSize(new Dimension(300, 300));
        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(150, 40));

        closeButton.addActionListener(e -> {
            if (closeButtonPressed != null)
                closeButtonPressed.pressCancelButton();
            clearText();
        });

        setLayout(new BorderLayout());

        add(titleLabel, BorderLayout.NORTH);
        add(showEmployeesTextArea, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

        Border outBorder = BorderFactory.createEmptyBorder();
        Border inBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setBorder(new CompoundBorder(outBorder, inBorder));

    }

    public void pressCancelButton(CancelButtonPressed closeButtonPressed) {
        this.closeButtonPressed = closeButtonPressed;
    }

    public void setText(String text) {
        showEmployeesTextArea.setText(text);
    }

    public void appendText(String text) {
        showEmployeesTextArea.append(text);
    }

    public void clearText() {
        showEmployeesTextArea.setText("");
    }

}
