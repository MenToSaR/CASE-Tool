package de.window;

import de.database.DataKnot;
import de.project.InputUnitElement;
import de.project.ProjectManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class EditorPanel implements InputUnitElement {
    private JButton button1;
    private JButton sButton;
    private JPanel thePanel;
    private JScrollPane thePane;
    private JPanel theElementHolderPanel;

    private ProjectManager theManager;
    private EditorPanelHolder theHolder;
    private String theIdentifier;

    public EditorPanel(ProjectManager pManager, EditorPanelHolder pHolder, String pID) {
        theManager = pManager;
        theHolder = pHolder;
        theIdentifier = pID;

        theHolder.setEditor(this);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theHolder.addElement();
                theHolder.print();
                update();
            }
        });

        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theManager.saveData(theHolder, theIdentifier);
                sButton.setEnabled(false);
            }
        });

        thePane.getVerticalScrollBar().setUnitIncrement(25);

        sButton.setEnabled(false);
        theHolder.print();
    }

    public void update() {
        theManager.updateInterface();
        sButton.setEnabled(true);
    }

    public JPanel getPanel() {
        return thePanel;
    }

    public JPanel getContentPanel() {
        return theElementHolderPanel;
    }

    public EditorPanelHolder getHolder() {
        return theHolder;
    }


    @Override
    public String getIdentifier() {
        return theIdentifier;
    }

    @Override
    public void loadData(DataKnot pKnot) {
        if (pKnot != null) {
            theHolder.reset();
            if (pKnot.getDataByKey("ID") != null) {
                theHolder.setID(Integer.valueOf(pKnot.getDataByKey("ID")));
            }
            for (DataKnot eachKnot : pKnot.getChildren()){
                theHolder.addElement(eachKnot);
            }
        }

        theHolder.print();
        sButton.setEnabled(false);
    }
}
