package de.window;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class FunctionPanel extends JPanel{
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JButton saveButton;
    private JPanel thePanel;

    public FunctionPanel(String pID, String pTitle, String pText) {
        textField1.setText(pID);
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
        textField2.setText(pTitle);
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
        textArea1.setText(pText);
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
        saveButton.setEnabled(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
            }
        });
    }

    private void somethingChanged() {
        saveButton.setEnabled(true);
    }

    public JPanel getPanel() {
        return thePanel;
    }
}
