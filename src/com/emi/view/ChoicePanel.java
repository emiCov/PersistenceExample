package com.emi.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class ChoicePanel extends JPanel {
    private JLabel choiceLabel;
    private JComboBox<String> choices;
    private ChoiceMade choiceMade;
    private boolean updateChoiceSelected = false;
    private boolean deleteChoiceSelected = false;
    private boolean showEmployeeChoiceSelected = false;


    public ChoicePanel() {
        choiceLabel = new JLabel("Choose an option: ");

        choices = new JComboBox<>(new String[]{
                "Choose an option",
                "Insert a new employee",
                "Update an employee's data",
                "Delete an employee",
                "Show all employees",
                "Show an employee by his ID"
        });

        choices.addItemListener(e -> {
            if (e.getStateChange() != ItemEvent.SELECTED)
                return;

            String choice = (String) e.getItem();

            switch (choice) {
                case "Insert a new employee" -> {
                    if (choiceMade != null) {
                        choiceMade.choseInsert();
                        this.updateChoiceSelected = false;
                        this.showEmployeeChoiceSelected = false;
                        this.deleteChoiceSelected = false;
                    }

                }

                case "Update an employee's data" -> {
                    if (choiceMade != null) {
                        choiceMade.choseSomethingThatNeedsId();
                        this.updateChoiceSelected = true;
                        this.showEmployeeChoiceSelected = false;
                        this.deleteChoiceSelected = false;
                    }

                }

                case "Delete an employee" -> {
                    if (choiceMade != null) {
                        choiceMade.choseSomethingThatNeedsId();
                        this.updateChoiceSelected = false;
                        this.showEmployeeChoiceSelected = false;
                        this.deleteChoiceSelected = true;
                    }
                }

                case "Show an employee by his ID" -> {
                    if (choiceMade != null) {
                        choiceMade.choseSomethingThatNeedsId();
                        this.updateChoiceSelected = false;
                        this.showEmployeeChoiceSelected = true;
                        this.deleteChoiceSelected = false;

                    }
                }

                case "Show all employees" -> {
                    if (choiceMade != null) {
                        choiceMade.choseGetAll();
                        this.updateChoiceSelected = false;
                        this.showEmployeeChoiceSelected = true;
                        this.deleteChoiceSelected = false;
                    }

                }

            }

        });

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(choiceLabel);
        add(choices);

    }

    public void choiceSelected(ChoiceMade choiceMade) {
        this.choiceMade = choiceMade;
    }

    public void choiceStatus(boolean status) {
        choices.setEnabled(status);
        choiceLabel.setEnabled(status);
    }

    public boolean isUpdateChoiceSelected() {
        return updateChoiceSelected;
    }

    public boolean isDeleteChoiceSelected() {
        return deleteChoiceSelected;
    }

}
