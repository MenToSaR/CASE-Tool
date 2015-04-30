package de.calculator;

import de.database.DataKnot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Marcel on 30.04.2015 in CASE.
 */
public class CalcFrame extends JFrame {
    private JList list1;
    private JPanel panel1;
    private JButton berechneButton;
    private JButton abbrechenButton;

    public CalcFrame() {
        setContentPane(this.panel1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getSize().getWidth() / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getSize().getHeight() / 2));
    }

    public void open(ArrayList<DataKnot> pData) {
        DefaultListModel<String> tempListModel = new DefaultListModel<>();
        for (DataKnot eachKnot : pData) {
            tempListModel.addElement(eachKnot.getValue());
        }
        list1.setModel(tempListModel);
        this.setVisible(true);
    }
}
