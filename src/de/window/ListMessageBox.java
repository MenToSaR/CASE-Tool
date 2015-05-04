package de.window;

import de.database.DataKnot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListMessageBox extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList lstList;
    private JLabel lblLabel;

    public static int RESULT_OK = 0;
    public static int RESULT_CANCEL = 1;
    public static int RESULT_NULL = -1;

    private int nResult = RESULT_NULL;

    public ListMessageBox(String pTitle, String pText, ArrayList<String> pList) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setTitle(pTitle);
        lblLabel.setText(pText);

        DefaultListModel<String> tempListModel = new DefaultListModel<>();
        for (String eachString : pList) {
            tempListModel.addElement(eachString);
        }
        lstList.setModel(tempListModel);

        setResizable(false);
        pack();
        setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getWidth() / 2, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    private void onOK() {
        nResult = RESULT_OK;
        dispose();
    }

    private void onCancel() {
        nResult = RESULT_CANCEL;
        dispose();
    }

    public int getResult() {
        return nResult;
    }

    public String getSelection() {
        if (nResult == RESULT_OK) {
            return "" + lstList.getSelectedValue();
        } else {
            return "";
        }
    }
}
