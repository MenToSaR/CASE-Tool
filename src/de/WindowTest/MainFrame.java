package de.WindowTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pascal on 27.04.2015.
 */
public class MainFrame {
    private JTree tree1;
    private JButton button1;

    public MainFrame() {
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button gedrueckt!");
            }
        });
    }
}
