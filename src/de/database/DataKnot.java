package de.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Marcel on 20.04.2015.
 */
public class DataKnot implements Serializable, Iterator<DataKnot>, Iterable<DataKnot> {
    private DataKnot itKnot = null;

    private ArrayList<DataKnot> listChildren = new ArrayList<>();
    private DataKnot theParent = null;

    private HashMap<String, String> mapData = new HashMap<>();
    private String theTag = "Tag";
    private String theValue;

    public DataKnot(String pTag) {
        theTag = pTag;
    }

    public DataKnot(String pTag, String pValue) {
        theTag = pTag;
        theValue = pValue;
    }

    public DataKnot(DataKnot pParent, String pTag) {
        theParent = pParent;
        theTag = pTag;
    }

    public void addChild(DataKnot pKnot) {
        theParent = this;
        listChildren.add(pKnot);
    }

    public DataKnot addChild(String pValue) {
        DataKnot tempKnot = new DataKnot(this, pValue);
        listChildren.add(tempKnot);
        return tempKnot;
    }

    public DataKnot getParent() {
        return theParent;
    }

    public ArrayList<DataKnot> getChildren() {
        return listChildren;
    }

    public ArrayList<DataKnot> getChildrenByTag(String pTag) {
        ArrayList<DataKnot> tempList = new ArrayList<>();
        for (DataKnot eachKnot : listChildren) {
            if (eachKnot.getTag().equals(pTag)) {
                tempList.add(eachKnot);
            }
        }
        return tempList;
    }

    public void addData(String pKey, String pValue) {
        mapData.put(pKey, pValue);
    }

    public void setValue(String pValue) {
        theValue = pValue;
    }

    public HashMap<String, String> getData() {
        return mapData;
    }

    public String getValue() {
        return theValue;
    }

    public String getValueByKey(String pKey) {
        return mapData.get(pKey);
    }

    public String getTag() {
        return theTag;
    }

    @Override
    public boolean hasNext() {
        if (itKnot.getChildren().size() > 0) {
            return true;
        } else {
            DataKnot tempParent = itKnot;
            DataKnot tempChild;
            while (tempParent.getParent() != null) {
                tempChild = tempParent;
                tempParent = tempParent.getParent();
                ArrayList<DataKnot> listKnot = tempParent.getChildren();
                for (int i = 0; i < listKnot.size(); i++) {
                    if (listKnot.get(i).equals(tempChild)) {
                        if (i + 1 < listKnot.size()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    @Override
    public DataKnot next() {
        if (itKnot.getChildren().size() > 0) {
            itKnot = itKnot.getChildren().get(0);
            return itKnot;
        } else {
            DataKnot tempParent = itKnot;
            DataKnot tempChild;
            while (tempParent.getParent() != null) {
                tempChild = tempParent;
                tempParent = tempParent.getParent();
                ArrayList<DataKnot> listKnot = tempParent.getChildren();
                for (int i = 0; i < listKnot.size(); i++) {
                    if (listKnot.get(i).equals(tempChild)) {
                        if (i + 1 < listKnot.size()) {
                            itKnot = listKnot.get(i + 1);
                            return itKnot;
                        }
                    }
                }
            }
            return null;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<DataKnot> iterator() {
        itKnot = new DataKnot("NULL");
        itKnot.addChild(this);
        return this;
    }
}
