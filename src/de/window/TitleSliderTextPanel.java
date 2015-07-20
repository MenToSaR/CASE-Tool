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
    private JTextField txtID;
    private JTextArea txaText;
    private JPanel thePanel;
    private JSlider sldSlider;
    private JButton btnDelete;

    public TitleSliderTextPanel(EditorPanelHolder pHolder, String pID, String ... pString) {
        super(pHolder, pID);
        init();

        txtID.setText(pString[0]);
        sldSlider.setToolTipText(pString[1]);
        txaText.setText(pString[2]);
    }

    public void init() {
        sldSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                somethingChanged();
            }
        });

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
        tempKnot.addData("TITLE", txtID.getText());
        tempKnot.addData("VALUE", "" + sldSlider.getValue());
        tempKnot.addData("TEXT", txaText.getText());
        return tempKnot;
    }

    @Override
    public void setData(DataKnot pKnot) {
        txtID.setText(pKnot.getDataByKey("TITLE"));
        sldSlider.setValue(Integer.valueOf(pKnot.getDataByKey("VALUE")));
        txaText.setText(pKnot.getDataByKey("TEXT"));
    }
}
