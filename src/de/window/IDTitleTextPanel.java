package de.window;

import de.database.DataKnot;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class IDTitleTextPanel extends EditorPanelElement {
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JPanel thePanel;
    private JButton xButton;

    public IDTitleTextPanel(EditorPanelHolder pHolder) {
        super(pHolder);
        init();
    }

    public void init() {

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

    private void somethingChanged() {
        // TODO hier Quelltext einfügen
    }

    @Override
    public JPanel getElement() {
        return thePanel;
    }

    @Override
    public DataKnot getData() {
        DataKnot tempKnot = new DataKnot("elementdata");
        tempKnot.addData("ID", textField1.getText());
        tempKnot.addData("TITLE", textField2.getText());
        tempKnot.addData("TEXT", textArea1.getText());
        return tempKnot;
    }



    @Override
    public void setData(DataKnot pKnot) {
        textField1.setText(pKnot.getDataByKey("ID"));
        textField2.setText(pKnot.getDataByKey("TITLE"));
        textArea1.setText(pKnot.getDataByKey("TEXT"));
    }
}
