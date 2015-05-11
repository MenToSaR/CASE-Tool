package de.project;

import javax.swing.*;

/**
 * Created by Pascal on 11.05.2015.
 */
public class InputUnitHolder {

    private String theName;
    private JPanel pnlPanel;

    public InputUnitHolder(String pName ,JPanel pPanel){

        theName = pName;

        pnlPanel = pPanel;
    }

    public JPanel getPanel(){
        return pnlPanel;
    }

    public String getName(){
        return theName;
    }


}
