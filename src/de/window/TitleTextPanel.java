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
public class TitleTextPanel extends EditorPanelElement {
    private JTextField txtField;
    private JTextArea txaText;
    private JPanel thePanel;
    private JButton btnDelete;

    public TitleTextPanel(EditorPanelHolder pHolder, String pID, String ... pString) {
        super(pHolder, pID);
        init();

        txtField.setText(pString[0]);
        txaText.setText(pString[1]);
    }

    public void init() {

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

        txaText.setLineWrap(true);
        txaText.setWrapStyleWord(true);
        txaText.getDocument().addDocumentListener(new DocumentListener() {
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
        tempKnot.addData("TITLE", txtField.getText());
        tempKnot.addData("TEXT", txaText.getText());
        return tempKnot;
    }

    @Override
    public void setData(DataKnot pKnot) {
        txtField.setText(pKnot.getDataByKey("TITLE"));
        txaText.setText(pKnot.getDataByKey("TEXT"));
    }
}
