package com.emi;

import com.emi.controller.Controller;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Controller::new);
    }
}
