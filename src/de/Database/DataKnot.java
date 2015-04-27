package de.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marcel on 20.04.2015.
 */
public class DataKnot implements Serializable {

    private ArrayList<DataKnot> listChildren = new ArrayList<>();
    private DataKnot theParent = null;

    private HashMap<String, String> mapData = new HashMap<>();

    public DataKnot(String pValue) {
        mapData.put("Tag", pValue);
    }

    public DataKnot(DataKnot pParent, String pValue) {
        theParent = pParent;
        mapData.put("Tag", pValue);
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
            if (eachKnot.getValueByKey("Tag").equals(pTag)) {
                tempList.add(eachKnot);
            }
        }
        return tempList;
    }

    public void addData(String pKey, String pValue) {
        mapData.put(pKey, pValue);
    }

    public HashMap<String, String> getData() {
        return mapData;
    }

    public String getValueByKey(String pKey) {
        return mapData.get(pKey);
    }

    public String getTag() {
        return mapData.get("Tag");
    }
}
