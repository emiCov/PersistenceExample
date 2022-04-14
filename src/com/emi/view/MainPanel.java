package com.emi.view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements ShowResults {
    private ChoicePanel choicePanel;
    private InsertEmployeePanel insertEmployeePanel;
    private EmptyPanel emptyPanel;
    private SelectIdPanel selectIdPanel;
    private ShowResultsPanel showResultsPanel;

    public MainPanel() {
        setBackground(Color.ORANGE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        choicePanel = new ChoicePanel();
        insertEmployeePanel = new InsertEmployeePanel();
        emptyPanel = new EmptyPanel();
        selectIdPanel = new SelectIdPanel();
        showResultsPanel = new ShowResultsPanel();


        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(choicePanel, gbc);

        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        add(emptyPanel, gbc);
        emptyPanel.setPreferredSize(new Dimension(450, 250));

        add(insertEmployeePanel, gbc);
        insertEmployeePanel.setVisible(false);
        insertEmployeePanel.setPreferredSize(new Dimension(450, 250));

        add(selectIdPanel, gbc);
        selectIdPanel.setVisible(false);
        selectIdPanel.setPreferredSize(new Dimension(450, 250));

        add(showResultsPanel, gbc);
        showResultsPanel.setVisible(false);
        showResultsPanel.setPreferredSize(new Dimension(450, 250));

    }

    public void choseInsert() {
        insertEmployeePanel.setVisible(true);
        emptyPanel.setVisible(false);
        selectIdPanel.setVisible(false);
        showResultsPanel.setVisible(false);
        choicePanel.choiceStatus(false);
    }

    public void choseSelectId() {
        selectIdPanel.setVisible(true);
        emptyPanel.setVisible(false);
        insertEmployeePanel.setVisible(false);
        showResultsPanel.setVisible(false);
        choicePanel.choiceStatus(false);
    }

    public void choseGetAll() {
        showResultsPanel.setVisible(true);
        emptyPanel.setVisible(false);
        selectIdPanel.setVisible(false);
        insertEmployeePanel.setVisible(false);
        choicePanel.choiceStatus(false);
    }

    public void cancelButtonPressed() {
        emptyPanel.setVisible(true);
        selectIdPanel.setVisible(false);
        insertEmployeePanel.setVisible(false);
        showResultsPanel.setVisible(false);
        choicePanel.choiceStatus(true);
    }

    public ChoicePanel getChoicePanel() {
        return choicePanel;
    }

    public InsertEmployeePanel getInsertEmployeePanel() {
        return insertEmployeePanel;
    }

    public SelectIdPanel getSelectIdPanel() {
        return selectIdPanel;
    }

    public ShowResultsPanel getShowResultsPanel() {
        return showResultsPanel;
    }

    @Override
    public void showResults() {
        showResultsPanel.setVisible(true);
        emptyPanel.setVisible(false);
        selectIdPanel.setVisible(false);
        insertEmployeePanel.setVisible(false);
        choicePanel.choiceStatus(false);
    }
}
