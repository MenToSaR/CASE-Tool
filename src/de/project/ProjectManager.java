package de.project;

import de.Core;
import de.database.DataKnot;
import de.database.Database;
import de.window.*;
import jdk.internal.util.xml.impl.Input;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class ProjectManager {

    private static String theFunctionDataFileName = "FUNCTION_DATA.dat";

    private DataKnot theProjectData = new DataKnot("root");

    private MainFrame theFrame;
    private Core theCore;

    private InputUnitManager theInputUnitManager = new InputUnitManager();

    public ProjectManager(Core pCore, MainFrame pFrame) {
        theCore = pCore;
        theFrame = pFrame;

        initInputUnitManager();
    }

    public ArrayList<String> getTreeList(){
        return theInputUnitManager.getNames();
    }

    private void initInputUnitManager(){
        theInputUnitManager.addHolder(new InputUnitHolder("Zielbestimmungen", new TextPanel(this, "projecttarget")));
        theInputUnitManager.addHolder(new InputUnitHolder("Produkteinsatz", new TextPanel(this, "productusage")));
        theInputUnitManager.addHolder(new InputUnitHolder("Umgebung", new TextPanel(this, "environment")));
        theInputUnitManager.addHolder(new InputUnitHolder("Produktfunktionen", new EditorPanel(this, new EditorPanelHolder(IDTitleTitleTextPanel.class), "productfunction")));
        theInputUnitManager.addHolder(new InputUnitHolder("Produktdaten", new EditorPanel(this, new EditorPanelHolder(IDTitleTextPanel.class), "productdata")));
        theInputUnitManager.addHolder(new InputUnitHolder("Qualitätsanforderungen", new EditorPanel(this, new EditorPanelHolder(TitleSliderTextPanel.class), "quality")));
        theInputUnitManager.addHolder(new InputUnitHolder("Ergänzungen", new TextPanel(this, "miscellaneous")));
        theInputUnitManager.addHolder(new InputUnitHolder("Glossar", new EditorPanel(this, new EditorPanelHolder(TitleTextPanel.class), "glossary")));
    }

    public void loadProjectData(Database pDatabase) {
        theProjectData.clear();
        theProjectData.addChild(pDatabase.readData(Database.PROJECT_CONFIG_FILE));

        DataKnot tempKnot = pDatabase.readData(theFunctionDataFileName);
        if (tempKnot == null) {
            tempKnot = new DataKnot("data");
        }

        theProjectData.addChild(tempKnot);

        for (InputUnitHolder eachHolder : theInputUnitManager.getElements()) {
            eachHolder.getElement().loadData(theProjectData.getFirstChildByTag("data").getFirstChildByTag(eachHolder.getElement().getIdentifier()));
        }

        theProjectData.printKnot();
    }

    public void updateInterface() {
        theFrame.pack();
    }

    public void saveData(EditorPanelHolder pHolder, String pID) {
        DataKnot tempKnot = theProjectData.getFirstChildByTag("data").getFirstChildByTag(pID);
        theProjectData.getFirstChildByTag("data").removeChild(tempKnot);

        tempKnot = new DataKnot(pID);
        for (EditorPanelElement eachElement : pHolder.getElements()) {
            DataKnot tempChild = tempKnot.addChild("element");

            HashMap<String, String> tempMap = eachElement.getData().getData();
            for (String eachKey : tempMap.keySet()) {
                tempChild.addData(eachKey, tempMap.get(eachKey));
            }
        }

        theProjectData.getFirstChildByTag("data").addChild(tempKnot);

        theCore.saveData(theProjectData.getFirstChildByTag("data"), theFunctionDataFileName);
    }

    public void saveData(String pString, String pID) {
        DataKnot tempKnot = theProjectData.getFirstChildByTag("data").getFirstChildByTag(pID);
        theProjectData.getFirstChildByTag("data").removeChild(tempKnot);

        DataKnot tempChild = new DataKnot(pID);
        tempChild.addData("TEXT", pString);

        theProjectData.getFirstChildByTag("data").addChild(tempChild);

        theCore.saveData(theProjectData.getFirstChildByTag("data"), theFunctionDataFileName);
    }

    public DataKnot loadData(String pID) {
        return theProjectData.getFirstChildByTag(pID);
    }

    public DataKnot getDataKnot(){
        return theProjectData;
    }


    public void showPage(String pName, MainFrame pFrame) {
    JPanel tempPanel = theInputUnitManager.getPanelByName(pName);
        System.out.println(pName);
    pFrame.showPanel(tempPanel);
    }
}


