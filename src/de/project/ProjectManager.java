package de.project;

import de.Core;
import de.database.DataKnot;
import de.database.Database;
import de.window.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
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
        theInputUnitManager.addHolder(new InputUnitHolder("Zielbestimmungen", new TextPanel().getPanel()));
        theInputUnitManager.addHolder(new InputUnitHolder("Produkteinsatz", new TextPanel().getPanel()));
        theInputUnitManager.addHolder(new InputUnitHolder("Umgebung", new TextPanel().getPanel()));
        theInputUnitManager.addHolder(new InputUnitHolder("Produktfunktionen", new TextPanel().getPanel()));
        theInputUnitManager.addHolder(new InputUnitHolder("Produktdaten", new TextPanel().getPanel()));
        theInputUnitManager.addHolder(new InputUnitHolder("Qualitätsanforderungen", new TextPanel().getPanel()));
        theInputUnitManager.addHolder(new InputUnitHolder("Ergänzungen", new TextPanel().getPanel()));
        theInputUnitManager.addHolder(new InputUnitHolder("Glossar", new TextPanel().getPanel()));

    }

    public void loadProjectData(Database pDatabase) {
        theProjectData.addChild(pDatabase.readData(Database.PROJECT_CONFIG_FILE));
        theProjectData.addChild(pDatabase.readData(theFunctionDataFileName));
        theProjectData.printKnot();
    }

    public void showProjectData(MainFrame pFrame) {
        EditorPanelHolder tempEditorPanelHolder = new EditorPanelHolder(IDTitleTitleTextPanel.class);

        if (theProjectData.getFirstChildByTag("productdata") != null) {
            for (DataKnot eachKnot : theProjectData.getFirstChildByTag("productdata").getChildrenByTag("element")) {
                IDTitleTitleTextPanel tempFunctionPanel = new IDTitleTitleTextPanel(eachKnot.getDataByKey("ID"), eachKnot.getDataByKey("TITLE"), eachKnot.getDataByKey("TEXT"));
                tempEditorPanelHolder.addElement(tempFunctionPanel);
            }
        }
        EditorPanel theEditorPanel = new EditorPanel(this, tempEditorPanelHolder);

        pFrame.showPanel(theEditorPanel.getPanel());
    }

    public void updateInterface() {
        theFrame.pack();
    }

    public void saveData(EditorPanelHolder pHolder) {
        DataKnot tempKnot = new DataKnot("productdata");
        for (EditorPanelElement eachElement : pHolder.getElements()) {
            DataKnot tempChild = tempKnot.addChild("element");

            HashMap<String, String> tempMap = eachElement.getData().getData();
            for (String eachKey : tempMap.keySet()) {
                tempChild.addData(eachKey, tempMap.get(eachKey));
            }
        }
        theCore.saveData(tempKnot, theFunctionDataFileName);
    }

    public void showPage(String pName, MainFrame pFrame){
    JPanel tempPanel = theInputUnitManager.getPanelByName(pName);
        System.out.println(pName);
    pFrame.showPanel(tempPanel);
    }
};


