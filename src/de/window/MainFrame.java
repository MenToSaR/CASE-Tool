package de.window;

import de.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pascal on 27.04.2015.
 */
public class MainFrame extends JFrame{

    private Core theCore;

    private JTree tree1;
    private JButton button1;
    private JPanel thePanel;
    private JButton startCalc;

    public MainFrame(Core pCore) {
        theCore = pCore;

        setContentPane(this.thePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getSize().getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getSize().getHeight() / 2));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                System.out.println("Button gedrueckt!");
            }
        });
        startCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.calculate();
            }
        });
    }

    public void open() {
        this.setVisible(true);
    }
}
