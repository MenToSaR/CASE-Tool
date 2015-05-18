package de.window;

import de.database.DataKnot;
import de.project.InputUnitElement;
import de.project.ProjectManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pascal on 11.05.2015.
 */
public class TextPanel implements InputUnitElement {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton speichernButton;

    private ProjectManager theProjectManager;
    private String theIdentifier;

    public TextPanel(ProjectManager pPM, String pID) {
        theProjectManager = pPM;
        theIdentifier = pID;

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theProjectManager.saveData(textArea1.getText(), theIdentifier);
                speichernButton.setEnabled(false);
            }
        });

        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                somethingChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                somethingChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                somethingChanged();
            }
        });
    }

    private void somethingChanged() {
        speichernButton.setEnabled(true);
    }

    public JPanel getPanel(){
    return panel1;
}

    @Override
    public String getIdentifier() {
        return theIdentifier;
    }

    @Override
    public void loadData(DataKnot pKnot) {
        if (pKnot != null) {
            textArea1.setText(pKnot.getDataByKey("TEXT"));
            speichernButton.setEnabled(false);
        }
    }
}
