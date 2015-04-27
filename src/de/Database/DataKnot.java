package de.database;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Marcel on 20.04.2015.
 */
public class DataKnot implements Serializable {

    private ArrayList<DataKnot> listChildren = new ArrayList<>();
    private DataKnot theParent = null;

    private String theName;
    private String theValue;

    public DataKnot(String pName, String pValue) {
        theName = pName;
        theValue = pValue;
    }

    public DataKnot(DataKnot pParent, String pName, String pValue) {
        theParent = pParent;
        theName = pName;
        theValue = pValue;
    }

    public DataKnot addChild(String pName, String pValue) {
        DataKnot tempKnot = new DataKnot(this, pName, pValue);
        listChildren.add(tempKnot);
        return tempKnot;
    }

    public DataKnot getParent() {
        return theParent;
    }

    public ArrayList<DataKnot> getChildren() {
        return listChildren;
    }

    public ArrayList<DataKnot> getChildrenByName(String pName) {
        ArrayList<DataKnot> tempList = new ArrayList<>();
        for (DataKnot eachKnot : listChildren) {
            if (eachKnot.getName().equals(pName)) {
                tempList.add(eachKnot);
            }
        }
        return tempList;
    }

    public String getName() {
        return theName;
    }

    public String getValue() {
        return theValue;
    }
}
