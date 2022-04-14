package com.emi.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class EmptyPanel extends JPanel {

    // just for the alignment of the choice Panel
    public EmptyPanel() {
        setBackground(Color.ORANGE);
        setLayout(new FlowLayout());

        Border outBorder = BorderFactory.createEmptyBorder();
        Border inBorder = BorderFactory.createEmptyBorder(100, 100, 100, 100);

        setBorder(new CompoundBorder(outBorder, inBorder));


    }

}
