package de.project;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Pascal on 11.05.2015.
 */
public class InputUnitManager {

    private ArrayList<InputUnitHolder> listHolder = new ArrayList<>();

    public void addHolder(InputUnitHolder pHolder){
        listHolder.add(pHolder);
    }

    public void removeHolder(InputUnitHolder pHolder){
        listHolder.remove(pHolder);
    }

    public JPanel getPanelByName(String pName){
        JPanel tempPanel = null;
        for (InputUnitHolder eachHolder : listHolder) {
            if (eachHolder.getName().equals(pName)){
                tempPanel = eachHolder.getPanel();
            }
        }
        return tempPanel;
    }

    public ArrayList<String> getNames(){
        ArrayList<String> tempList = new ArrayList<String>();
        for (InputUnitHolder eachHolder : listHolder) {
            tempList.add(eachHolder.getPanel().toString());
        }
        return tempList;
    }
}
