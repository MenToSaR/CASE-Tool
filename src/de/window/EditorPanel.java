package de.window;

import de.project.ProjectManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class EditorPanel {
    private JButton button1;
    private JButton sButton;
    private JPanel thePanel;
    private JScrollPane thePane;
    private JPanel theElementHolderPanel;

    private ProjectManager theManager;
    private EditorPanelHolder theHolder;

    public EditorPanel(ProjectManager pManager, EditorPanelHolder pHolder) {
        theManager = pManager;
        theHolder = pHolder;

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theHolder.addElement();
                theHolder.printToPanel(theElementHolderPanel);
                theManager.updateInterface();
            }
        });

        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theManager.saveData(theHolder);
            }
        });

        theHolder.printToPanel(theElementHolderPanel);
    }

    public JPanel getPanel() {
        return thePanel;
    }

    public EditorPanelHolder getHolder() {
        return theHolder;
    }
}
