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
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JPanel thePanel;
    private JTextField textField3;
    private JButton xButton;

    public IDTitleTitleTextPanel(EditorPanelHolder pHolder, String pID, String ... pString) {
        super(pHolder, pID);
        init();

        textField2.setText(pString[0]);
        textField3.setText(pString[1]);
        textArea1.setText(pString[2]);
    }

    public void init() {

        textField1.setText(getID());
        textField1.getDocument().addDocumentListener(new DocumentListener() {
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

        textField2.getDocument().addDocumentListener(new DocumentListener() {
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

        textField3.getDocument().addDocumentListener(new DocumentListener() {
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
        xButton.addActionListener(new ActionListener() {
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
        tempKnot.addData("TITLE", textField2.getText());
        tempKnot.addData("TITLE2", textField3.getText());
        tempKnot.addData("TEXT", textArea1.getText());
        return tempKnot;
    }

    @Override
    public void setData(DataKnot pKnot) {
        textField1.setText(pKnot.getDataByKey("ID"));
        textField2.setText(pKnot.getDataByKey("TITLE"));
        textField3.setText(pKnot.getDataByKey("TITLE2"));
        textArea1.setText(pKnot.getDataByKey("TEXT"));
    }
}
