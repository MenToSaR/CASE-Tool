package de.window;

import de.Core;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Pascal on 27.04.2015.
 */
public class MainFrame extends JFrame{

    private Core theCore;

    private JTree tree;
    private JPanel thePanel;
    private JButton startCalc;
    private JButton startImprovement;
    private JButton nButton;
    private JButton oButton;
    private JButton rButton;
    private JButton dButton;
    private JPanel thePanels;
    private JScrollPane thePanelHolder;

    public MainFrame(Core pCore) {
        theCore = pCore;

        tree.setModel(null);

        setIconImage(new ImageIcon("res/graph.png").getImage());
        setTitle("CASE_TOOL");
        setContentPane(this.thePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getSize().getWidth() / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getSize().getHeight() / 2));

        startCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.calculate();
            }
        });
        nButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.createProject();
            }
        });
        oButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.openProject();
            }
        });
        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.reopenProject();
            }
        });
        dButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.deleteProject();
            }
        });


        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                theCore.showPage("" + e.getPath().getLastPathComponent());
            }
        });
    }

    public void open() {
        this.setVisible(true);
    }

    public void showPanel(JPanel pPanel) {
        thePanels.removeAll();
        thePanels.setLayout(new BorderLayout());
        thePanels.add(pPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

    }



    public void showTree(String pProjectName, ArrayList<String> pName) {
        tree.setModel(null);

        MutableTreeNode theNode = new DefaultMutableTreeNode(pProjectName);
        DefaultTreeModel theModel = new DefaultTreeModel(theNode);
        for (String eachKnot : pName) {
            DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(eachKnot);
            theNode.insert(tempNode, theNode.getChildCount());
        }
        theModel.reload(theNode);
        tree.setModel(theModel);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
