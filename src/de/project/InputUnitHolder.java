package de.project;

import de.window.SomethingChangedListener;
import de.window.SomethingChangedObserver;

import javax.swing.*;

/**
 * Created by Pascal on 11.05.2015.
 */
public class InputUnitHolder {

    private String theName;
    private InputUnitElement theElement;

    public InputUnitHolder(String pName, InputUnitElement pElement){
        theName = pName;
        theElement = pElement;
    }

    public void save() {
        theElement.save();
    }

    public InputUnitElement getElement() {
        return theElement;
    }

    public JPanel getPanel(){
        return theElement.getPanel();
    }

    public String getName(){
        return theName;
    }
}
