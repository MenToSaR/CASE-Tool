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
public class IDTitleTextPanel extends EditorPanelElement {
    private JTextField txtID;
    private JTextField txtField;
    private JTextArea txaArea;
    private JPanel thePanel;
    private JButton btnDelete;

    public IDTitleTextPanel(EditorPanelHolder pHolder, String pID , String ... pString) {
        super(pHolder, pID);
        init();

        txtField.setText(pString[0]);
        txaArea.setText(pString[1]);
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

        txtField.getDocument().addDocumentListener(new DocumentListener() {
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

        txaArea.setLineWrap(true);
        txaArea.setWrapStyleWord(true);
        txaArea.getDocument().addDocumentListener(new DocumentListener() {
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
        tempKnot.addData("ID", txtID.getText());
        tempKnot.addData("TITLE", txtField.getText());
        tempKnot.addData("TEXT", txaArea.getText());
        return tempKnot;
    }



    @Override
    public void setData(DataKnot pKnot) {
        txtID.setText(pKnot.getDataByKey("ID"));
        txtField.setText(pKnot.getDataByKey("TITLE"));
        txaArea.setText(pKnot.getDataByKey("TEXT"));
    }
}
