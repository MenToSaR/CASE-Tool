package de.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Marcel on 20.04.2015.
 */
public class DataKnot implements Serializable, Iterator<DataKnot>, Iterable<DataKnot> {
    private DataKnot itKnot = null;

    private ArrayList<DataKnot> listChildren = new ArrayList<DataKnot>();
    private DataKnot theParent = null;

    private HashMap<String, String> mapData = new HashMap<String, String>();
    private String theTag = "Tag";
    private String theValue;

    public DataKnot(String pTag) {
        theTag = pTag;
    }

    private DataKnot(DataKnot pParent, String pTag) {
        theParent = pParent;
        theTag = pTag;
    }

    public void addChild(DataKnot pKnot) {
        if (pKnot != null) {
            pKnot.setParent(this);
            listChildren.add(pKnot);
        }
    }

    public DataKnot addChild(String pTag) {
        DataKnot tempKnot = new DataKnot(this, pTag);
        listChildren.add(tempKnot);
        return tempKnot;
    }

    public void removeChild(DataKnot pChild) {
        listChildren.remove(pChild);
    }

    public DataKnot getParent() {
        return theParent;
    }

    private void setParent(DataKnot pKnot) {
        theParent = pKnot;
    }

    public boolean hasChildren() {
        return listChildren.size() > 0;
    }

    public ArrayList<DataKnot> getChildren() {
        return listChildren;
    }

    public ArrayList<DataKnot> getChildrenByTag(String pTag) {
        ArrayList<DataKnot> tempList = new ArrayList<DataKnot>();
        for (DataKnot eachKnot : listChildren) {
            if (eachKnot.getTag().equals(pTag)) {
                tempList.add(eachKnot);
            }
        }
        return tempList;
    }

    public DataKnot getFirstChildByTag(String pTag) {
        for (DataKnot eachKnot : listChildren) {
            if (eachKnot.getTag().equals(pTag)) {
                return eachKnot;
            }
        }
        return null;
    }

    public void clear() {
        listChildren.clear();
        mapData.clear();
        theValue = "";
    }

    public void addData(String pKey, String pValue) {
        mapData.put(pKey, pValue);
    }

    public void setData(String pKey, String pValue) {
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

    public String getDataByKey(String pKey) {
        return mapData.get(pKey);
    }

    public String getTag() {
        return theTag;
    }

    public void printKnot() {
        System.out.print("<" + getTag());
        for (String eachString : this.getData().keySet()) {
            System.out.print(" " + eachString + "=" + this.getData().get(eachString));
        }
        System.out.print(">" + ((getValue() == null) ? "" : getValue()));
        if (getChildren().size() > 0) {
            System.out.println();
        }
        printChildren(this.getChildren(), 1);
        if (getChildren().size() > 0) {
            System.out.println("</" + getTag() + ">");
        }
    }

    private void printChildren(ArrayList<DataKnot> pKnot, int pLevel) {
        for (DataKnot eachKnot : pKnot) {
            for (int i = 0; i < pLevel; i++) {
                System.out.print("\t");
            }
            System.out.print("<" + eachKnot.getTag());
            for (String eachString : eachKnot.getData().keySet()) {
                System.out.print(" " + eachString + "=" + eachKnot.getData().get(eachString));
            }
            System.out.print(">" + ((eachKnot.getValue() == null) ? "" : eachKnot.getValue()));

            if (eachKnot.getChildren().size() > 0) {
                System.out.println();
            }

            printChildren(eachKnot.getChildren(), pLevel + 1);

            if (eachKnot.getChildren().size() > 0) {
                for (int i = 0; i < pLevel; i++) {
                    System.out.print("\t");
                }
            }
            System.out.println("</" + eachKnot.getTag() + ">");
        }

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
