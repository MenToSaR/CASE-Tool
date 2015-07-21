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

    private JTree treeProjectTree;
    private JPanel thePanel;
    private JButton btnStartCalculation;
    private JButton btnStartOptimizing;
    private JButton btnNew;
    private JButton btnOpen;
    private JButton btnReopen;
    private JButton btnDelete;
    private JPanel thePanels;
    private JButton btnSafe;
    private JScrollPane thePanelHolder;

    /**
     * Hauptoberfläche
     *
     * View in MVC
     *
     * @param pCore
     */

    /**
     * Initialisiert Oberfläche und erzeugt Komponenten (Buttons, Felder, ...)
     * @param pCore
     */

    public MainFrame(Core pCore) {
        theCore = pCore;

        treeProjectTree.setModel(null);

        setIconImage(new ImageIcon("src/res/graph.png").getImage());
        setTitle("CASE_TOOL");
        setContentPane(thePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();

        btnStartCalculation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.calculate();
            }
        });
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.createProject();
            }
        });
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.openProject();
            }
        });
        btnReopen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.reopenProject();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.deleteProject();
            }
        });

        treeProjectTree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                theCore.showPage("" + e.getPath().getLastPathComponent());
            }
        });
        btnSafe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.saveCompleteProject();
            }
        });
        btnStartOptimizing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theCore.optimize();
            }
        });
    }

    /**
     * Öffnet Oberfläche und zeigt diese An
     */

    public void open() {
        this.setVisible(true);
        setSize(this.getInsets().left + getInsets().right + getSize().width, getInsets().top + getInsets().bottom + getSize().height);
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getSize().getWidth() / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getSize().getHeight() / 2));
        setMinimumSize(new Dimension(getSize().width + 64, getSize().height));
        setPreferredSize(getSize());
    }

    /**
     * Zeigt übergebenes Panel im Hauptpanel an
     * @param pPanel Anzuzeigendes Panel
     */

    public void showPanel(JPanel pPanel) {
        thePanels.removeAll();
        thePanels.setLayout(new BorderLayout());
        if (pPanel != null) {
            thePanels.add(pPanel, BorderLayout.CENTER);
        }
        revalidate();
        repaint();
    }

    /**
     * heißt so wie seine Funktion
     * @param pB
     */

    public void enableSaveButton(boolean pB) {
        btnSafe.setEnabled(pB);
    }

    /**
     * Enabled Buttons sobald Projekt geladen wurde
     */

    public void projectLoaded() {
        btnDelete.setEnabled(true);
        btnStartCalculation.setEnabled(true);
        btnStartOptimizing.setEnabled(true);
    }

    /**
     * Disabled Buttons wenn Projekt entladen
     */

    public void reset() {
        showPanel(null);
        showTree("", null);
        btnDelete.setEnabled(false);
        btnSafe.setEnabled(false);
        btnStartCalculation.setEnabled(false);
        btnStartOptimizing.setEnabled(false);
    }

    /**
     * Zeigt hirarchie der Komponenten an um Auswahl zu ermöglichen
     * @param pProjectName
     * @param pName Liste der Komponenten
     */

    public void showTree(String pProjectName, ArrayList<String> pName) {
        treeProjectTree.setModel(null);

        if (pName != null) {
            MutableTreeNode theNode = new DefaultMutableTreeNode(pProjectName);
            DefaultTreeModel theModel = new DefaultTreeModel(theNode);
            for (String eachKnot : pName) {
                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(eachKnot);
                theNode.insert(tempNode, theNode.getChildCount());
            }
            theModel.reload(theNode);
            treeProjectTree.setModel(theModel);
        }
    }
}
