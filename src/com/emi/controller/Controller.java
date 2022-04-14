package com.emi.controller;

import com.emi.model.Database;
import com.emi.model.EmployeeDao;
import com.emi.model.EmployeeDaoImpl;
import com.emi.view.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Controller implements ChoiceMade, CancelButtonPressed, InsertConfirmButtonPressed, IdSelected {
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private EmployeeDao employeeDao;
    private Database db;
    private int id;

    public Controller() {
        mainFrame = new MainFrame();
        mainPanel = new MainPanel();

        mainFrame.setContentPane(mainPanel);

        employeeDao = new EmployeeDaoImpl();

        db = Database.instance();

        try {
            db.connect();
            System.out.println("Connected");

            mainPanel.getChoicePanel().choiceSelected(this);
            mainPanel.getSelectIdPanel().pressCancelButton(this);
            mainPanel.getInsertEmployeePanel().pressCancelButton(this);
            mainPanel.getShowResultsPanel().pressCancelButton(this);
            mainPanel.getInsertEmployeePanel().pressConfirmInsertButton(this);
            mainPanel.getSelectIdPanel().selectId(this);

        } catch (SQLException e) {
            System.out.println("Cannot connect to the database.");
            e.printStackTrace();
        }

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    db.closeConnection();
                    System.out.println("Connection closed.");
                } catch (SQLException exc) {
                    System.out.println("Cannot close the connection.");
                    exc.printStackTrace();
                }
            }
        });
    }

    @Override
    public void choseInsert() {
        mainPanel.choseInsert();
    }

    @Override
    public void choseSomethingThatNeedsId() {
        mainPanel.choseSelectId();
    }

    @Override
    public void choseGetAll() {
        var list = employeeDao.getAll();
        for (var em : list)
            mainPanel.getShowResultsPanel().appendText(em.toString() + "\n");
        mainPanel.choseGetAll();
    }

    @Override
    public void pressCancelButton() {
        mainPanel.cancelButtonPressed();
    }

    @Override
    public void getEmployeeData(String name, int age, String address, double salary) {
        if (mainPanel.getChoicePanel().isUpdateChoiceSelected()) {
            employeeDao.updateById(this.id, employeeDao.createEmployee(name, age, address, salary));
            mainPanel.getShowResultsPanel().setText("Updated employee with id " + this.id);
        } else {
            employeeDao.save(employeeDao.createEmployee(name, age, address, salary));
            mainPanel.getShowResultsPanel().setText("Inserted a new employee");
        }

        mainPanel.showResults();
        mainPanel.getInsertEmployeePanel().clearText();
    }

    @Override
    public void getId(int id) {
        mainPanel.getShowResultsPanel().clearText();

        if (mainPanel.getChoicePanel().isDeleteChoiceSelected()) {
            String result = "No employee with id " + id + " in the database";

            if (employeeDao.deleteById(id)) {
                result = "Deleted employee with id " + id;
            }

            mainPanel.getShowResultsPanel().setText(result);
            mainPanel.showResults();

        } else if (mainPanel.getChoicePanel().isUpdateChoiceSelected()) {
            if (employeeDao.getById(id) != null) {
                this.id = id;
                mainPanel.choseInsert();
            } else {
                mainPanel.getShowResultsPanel().setText("No employee with id " + id + " in the database to update");
                mainPanel.showResults();
            }

        } else {
            String result = "No employee with id " + id + " in the database";

            try {
                result = employeeDao.getById(id).toString();
                mainPanel.getShowResultsPanel().setText(result);
            } catch (NullPointerException ex) {
                mainPanel.getShowResultsPanel().setText(result);
            }

            mainPanel.showResults();
        }

        mainPanel.getSelectIdPanel().clearText();
    }
}
