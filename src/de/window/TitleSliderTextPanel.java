package de.window;

import de.database.DataKnot;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class TitleSliderTextPanel extends EditorPanelElement {
    private JTextField textField2;
    private JTextArea textArea1;
    private JButton saveButton;
    private JPanel thePanel;
    private JSlider slider1;
    private JButton xButton;

    public TitleSliderTextPanel(EditorPanelHolder pHolder) {
        super(pHolder);
        init();
    }

    public void init() {
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
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
        saveButton.setEnabled(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
                update();
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
        saveButton.setEnabled(true);
    }

    @Override
    public JPanel getElement() {
        return thePanel;
    }

    @Override
    public DataKnot getData() {
        DataKnot tempKnot = new DataKnot("elementdata");
        tempKnot.addData("TITLE", textField2.getText());
        tempKnot.addData("VALUE", "" + slider1.getValue());
        tempKnot.addData("TEXT", textArea1.getText());
        return tempKnot;
    }

    @Override
    public void setData(DataKnot pKnot) {
        textField2.setText(pKnot.getDataByKey("TITLE"));
        slider1.setValue(Integer.valueOf(pKnot.getDataByKey("VALUE")));
        textArea1.setText(pKnot.getDataByKey("TEXT"));
        saveButton.setEnabled(false);
    }
}