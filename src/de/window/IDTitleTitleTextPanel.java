package de.window;

import de.database.DataKnot;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class IDTitleTitleTextPanel extends EditorPanelElement {
    private JTextField txtID;
    private JTextField txtFieldUpper;
    private JTextArea textArea1;
    private JPanel thePanel;
    private JTextField txtFieldLower;
    private JButton btnDelete;

    public IDTitleTitleTextPanel(EditorPanelHolder pHolder, String pID, String ... pString) {
        super(pHolder, pID);
        init();

        txtFieldUpper.setText(pString[0]);
        txtFieldLower.setText(pString[1]);
        textArea1.setText(pString[2]);
    }

    public void init() {

        txtID.setText(getID());
        txtID.getDocument().addDocumentListener(new DocumentListener() {
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

        txtFieldUpper.getDocument().addDocumentListener(new DocumentListener() {
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

        txtFieldLower.getDocument().addDocumentListener(new DocumentListener() {
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
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });

    }

    @Override
    public JPanel getElement() {
        return thePanel;
    }

    @Override
    public DataKnot getData() {
        DataKnot tempKnot = new DataKnot("elementdata");
        tempKnot.addData("ID", getID());
        tempKnot.addData("TITLE", txtFieldUpper.getText());
        tempKnot.addData("TITLE2", txtFieldLower.getText());
        tempKnot.addData("TEXT", textArea1.getText());
        return tempKnot;
    }

    @Override
    public void setData(DataKnot pKnot) {
        txtID.setText(pKnot.getDataByKey("ID"));
        txtFieldUpper.setText(pKnot.getDataByKey("TITLE"));
        txtFieldLower.setText(pKnot.getDataByKey("TITLE2"));
        textArea1.setText(pKnot.getDataByKey("TEXT"));
    }
}
