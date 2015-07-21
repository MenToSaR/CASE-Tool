package de.window;

import java.util.ArrayList;

/**
 * Created by Marcel on 04.05.2015 in CASE.
 */
public class MessageBoxFactory {

    /**
     * Factor Klasse fuer Message Boxen (in und output)
     * @param pTitle
     * @param pText
     * @param pList
     * @return
     */

    public static String createListMessageBox(String pTitle, String pText, ArrayList<String> pList) {
        ListMessageBox theListMessageBox = new ListMessageBox(pTitle, pText, pList);
        return theListMessageBox.getSelection();
    }

    public static String createTextMessageBox(String pTitle, String pText) {
        TextMessageBox theTextMessageBox = new TextMessageBox(pTitle, pText);
        return theTextMessageBox.getText();
    }

    public static int createMessageBox(String pTitle, String pText) {
        MessageBox theMessageBox = new MessageBox(pTitle, pText);
        return theMessageBox.getResult();
    }
}
