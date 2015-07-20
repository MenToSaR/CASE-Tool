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
    private JButton btnAdd;
    private JButton btnSafe;
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

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theHolder.addElement();
                theHolder.print();
                update();
            }
        });

        btnSafe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        thePane.getVerticalScrollBar().setUnitIncrement(25);

        btnSafe.setEnabled(false);
        theHolder.print();
    }

    public void update() {
        theManager.somethingChanged();
        theManager.updateInterface();
        btnSafe.setEnabled(true);
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
    public void save() {
        theManager.saveData(theHolder, theIdentifier);
        btnSafe.setEnabled(false);
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
        btnSafe.setEnabled(false);
    }
}
