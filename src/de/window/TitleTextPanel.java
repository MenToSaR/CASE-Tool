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
    private JTextField textField2;
    private JTextArea textArea1;
    private JPanel thePanel;
    private JButton xButton;

    public TitleTextPanel(EditorPanelHolder pHolder, String pID) {
        super(pHolder, pID);
        init();
    }

    public void init() {

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

    @Override
    public JPanel getElement() {
        return thePanel;
    }

    @Override
    public DataKnot getData() {
        DataKnot tempKnot = new DataKnot("elementdata");
        tempKnot.addData("TITLE", textField2.getText());
        tempKnot.addData("TEXT", textArea1.getText());
        return tempKnot;
    }

    @Override
    public void setData(DataKnot pKnot) {
        textField2.setText(pKnot.getDataByKey("TITLE"));
        textArea1.setText(pKnot.getDataByKey("TEXT"));
    }
}
