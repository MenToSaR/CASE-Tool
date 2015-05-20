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
    private JButton button1;
    private JScrollPane thePanelHolder;

    public MainFrame(Core pCore) {
        theCore = pCore;

        tree.setModel(null);

        setIconImage(new ImageIcon("src/res/graph.png").getImage());
        setTitle("CASE_TOOL");
        setContentPane(this.thePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();

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
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.saveCompleteProject();
            }
        });
        startImprovement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.optimize();
            }
        });
    }

    public void open() {
        this.setVisible(true);
        setSize(this.getInsets().left + getInsets().right + getSize().width, getInsets().top + getInsets().bottom + getSize().height);
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getSize().getWidth() / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getSize().getHeight() / 2));
        setMinimumSize(new Dimension(getSize().width + 64, getSize().height));
        setPreferredSize(getSize());
    }

    public void showPanel(JPanel pPanel) {
        thePanels.removeAll();
        thePanels.setLayout(new BorderLayout());
        if (pPanel != null) {
            thePanels.add(pPanel, BorderLayout.CENTER);
        }
        revalidate();
        repaint();
    }

    public void enableSaveButton(boolean pB) {
        button1.setEnabled(pB);
    }

    public void projectLoaded() {
        dButton.setEnabled(true);
        startCalc.setEnabled(true);
        startImprovement.setEnabled(true);
    }

    public void reset() {
        showPanel(null);
        showTree("", null);
        dButton.setEnabled(false);
        button1.setEnabled(false);
        startCalc.setEnabled(false);
        startImprovement.setEnabled(false);
    }

    public void showTree(String pProjectName, ArrayList<String> pName) {
        tree.setModel(null);

        if (pName != null) {
            MutableTreeNode theNode = new DefaultMutableTreeNode(pProjectName);
            DefaultTreeModel theModel = new DefaultTreeModel(theNode);
            for (String eachKnot : pName) {
                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(eachKnot);
                theNode.insert(tempNode, theNode.getChildCount());
            }
            theModel.reload(theNode);
            tree.setModel(theModel);
        }
    }
}
